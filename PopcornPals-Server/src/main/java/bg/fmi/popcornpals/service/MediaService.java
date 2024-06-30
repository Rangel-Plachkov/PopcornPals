package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.ActorDTO;
import bg.fmi.popcornpals.dto.MediaDTO;
import bg.fmi.popcornpals.dto.MediaRequestDTO;
import bg.fmi.popcornpals.dto.ProducerDTO;
import bg.fmi.popcornpals.dto.StudioDTO;
import bg.fmi.popcornpals.exception.notfound.MediaNotFoundException;
import bg.fmi.popcornpals.exception.nocontent.NoAssignedStudioException;
import bg.fmi.popcornpals.mapper.ProducerMapper;
import bg.fmi.popcornpals.mapper.StudioMapper;
import bg.fmi.popcornpals.model.Media;
import bg.fmi.popcornpals.model.Studio;
import bg.fmi.popcornpals.exception.notfound.StudioNotFoundException;
import bg.fmi.popcornpals.repository.MediaRepository;
import bg.fmi.popcornpals.mapper.ActorMapper;
import bg.fmi.popcornpals.mapper.MediaMapper;
import bg.fmi.popcornpals.repository.StudioRepository;
import bg.fmi.popcornpals.util.MediaType;
import bg.fmi.popcornpals.util.Genre;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class MediaService {
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private StudioRepository studioRepository;
    @Autowired
    private MediaMapper mediaMapper;
    @Autowired
    private StudioMapper studioMapper;
    @Autowired
    private ActorMapper actorMapper;
    @Autowired
    private ProducerMapper producerMapper;

    public MediaDTO createMedia(MediaRequestDTO media) {
        Media newMedia = mediaMapper.toEntity(media);
        Media savedMedia = mediaRepository.save(newMedia);
        log.info("Media with id {} was created", savedMedia.getID());
        return mediaMapper.toDTO(savedMedia);
    }

    public MediaDTO getMediaById(Long id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(MediaNotFoundException::new);
        return mediaMapper.toDTO(media);
    }

    public List<MediaDTO> getMedia(Integer pageNo, Integer pageSize, String title, MediaType type, Genre genre) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Media> media = null;
        if(title != null) {
            media = mediaRepository.findByTitleContainingIgnoreCase(title, pageable);
        } else if(type != null) {
            media = mediaRepository.findByType(type, pageable);
        } else if(genre != null) {
            media = mediaRepository.findByGenre(genre, pageable);
        } else {
            media = mediaRepository.findAll(pageable);
        }
        return mediaMapper.toDTOList(media.getContent());
    }
    public List<ActorDTO> getActorsInMedia(Long id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(MediaNotFoundException::new);
        return actorMapper.toDTOList(media.getActors());
    }
    public List<ProducerDTO> getProducerOfMedia(Long id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(MediaNotFoundException::new);
        return producerMapper.toDTOList(media.getProducers());
    }
    public StudioDTO getStudioOfMedia(Long id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(MediaNotFoundException::new);
        Studio studio = media.getStudio();
        if(studio == null) {
            throw new NoAssignedStudioException();
        }
        return studioMapper.toDTO(media.getStudio());
    }


    public MediaDTO updateMedia(Long mediaId ,MediaRequestDTO media) {
        Media existingMedia = mediaRepository.findById(mediaId)
                .orElseThrow(MediaNotFoundException::new);
        existingMedia = mediaMapper.toEntity(media);
        existingMedia.setID(mediaId);
        Media updatedMedia = mediaRepository.save(existingMedia);
        log.info("Media with id {} was updated", updatedMedia.getID());
        return mediaMapper.toDTO(updatedMedia);
    }
    public MediaDTO assignStudio(Long mediaId, Long studioId) {
        Media media = mediaRepository.findById(mediaId)
                .orElseThrow(MediaNotFoundException::new);
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(StudioNotFoundException::new);
        media.setStudio(studio);
        log.info("Assigned studio with id {} to media with id {}", studioId, mediaId);
        return mediaMapper.toDTO(mediaRepository.save(media));
    }

    public void deleteMediaById(Long id) {
        Media toDelete = mediaRepository.findById(id)
                .orElseThrow(MediaNotFoundException::new);
        log.info("Media with id {} was deleted", id);
        mediaRepository.delete(toDelete);
    }
}
