package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.ActorDTO;
import bg.fmi.popcornpals.dto.MediaRequestDTO;
import bg.fmi.popcornpals.dto.StudioDTO;
import bg.fmi.popcornpals.exception.notfound.MediaNotFoundException;
import bg.fmi.popcornpals.exception.nocontent.NoAssignedStudioException;
import bg.fmi.popcornpals.mapper.StudioMapper;
import bg.fmi.popcornpals.model.Media;
import bg.fmi.popcornpals.dto.MediaDTO;
import bg.fmi.popcornpals.model.Studio;
import bg.fmi.popcornpals.exception.notfound.StudioNotFoundException;
import bg.fmi.popcornpals.repository.MediaRepository;
import bg.fmi.popcornpals.mapper.ActorMapper;
import bg.fmi.popcornpals.mapper.MediaMapper;
import bg.fmi.popcornpals.repository.StudioRepository;
import bg.fmi.popcornpals.util.MediaType;
import bg.fmi.popcornpals.util.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MediaService {
    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private StudioMapper studioMapper;
    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private ActorMapper actorMapper;

    public MediaDTO createMedia(MediaRequestDTO media) {
        Media newMedia = mediaMapper.toEntity(media);
        return mediaMapper.toDTO(mediaRepository.save(newMedia));
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
        return mediaMapper.toDTO(mediaRepository.save(existingMedia));
    }
    public MediaDTO assignStudio(Long mediaId, Long studioId) {
        Media media = mediaRepository.findById(mediaId)
                .orElseThrow(MediaNotFoundException::new);
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(StudioNotFoundException::new);
        media.setStudio(studio);
        return mediaMapper.toDTO(mediaRepository.save(media));
    }

    public void deleteMediaById(Long id) {
        Media toDelete = mediaRepository.findById(id)
                .orElseThrow(MediaNotFoundException::new);
        mediaRepository.delete(toDelete);
    }
}
