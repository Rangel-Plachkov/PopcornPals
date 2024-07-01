package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.*;
import bg.fmi.popcornpals.exception.notfound.MediaNotFoundException;
import bg.fmi.popcornpals.exception.nocontent.NoAssignedStudioException;
import bg.fmi.popcornpals.mapper.*;
import bg.fmi.popcornpals.model.Media;
import bg.fmi.popcornpals.model.Studio;
import bg.fmi.popcornpals.exception.notfound.StudioNotFoundException;
import bg.fmi.popcornpals.repository.MediaRepository;
import bg.fmi.popcornpals.repository.StudioRepository;
import bg.fmi.popcornpals.util.MediaType;
import bg.fmi.popcornpals.util.Genre;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    @Autowired
    private ReviewMapper reviewMapper;

    public MediaDTO createMedia(MediaRequestDTO media) {
        Media newMedia = mediaMapper.toEntity(media);
        Media savedMedia = mediaRepository.save(newMedia);
        log.info("Media with id {} was created", savedMedia.getID());
        return mediaMapper.toDTO(savedMedia);
    }

    public MediaDTO getMediaById(Long id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(MediaNotFoundException::new);
        log.info("Found media with id: {}", id);
        return mediaMapper.toDTO(media);
    }

    public Page<MediaDTO> getMedia(Integer pageNo, Integer pageSize, String title, MediaType type, Genre genre) {
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
        log.info("Found {} media", media.getTotalElements());
        return media.map(mediaMapper::toDTO);
    }
    public Page<ActorDTO> getActorsInMedia(Long id, Integer pageNo, Integer pageSize) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(MediaNotFoundException::new);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<ActorDTO> actorsList = actorMapper.toDTOList(media.getActors());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), actorsList.size());
        List<ActorDTO> pageContent = actorsList.subList(start, end);
        log.info("Found {} actors in media with id {}", actorsList.size(), id);
        return new PageImpl<>(pageContent, pageable, actorsList.size());
    }
    public Page<ProducerDTO> getProducerOfMedia(Long id, Integer pageNo, Integer pageSize) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(MediaNotFoundException::new);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<ProducerDTO> producersList = producerMapper.toDTOList(media.getProducers());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), producersList.size());
        List<ProducerDTO> pageContent = producersList.subList(start, end);
        log.info("Found {} producers in media with id {}", producersList.size(), id);
        return new PageImpl<>(pageContent, pageable, producersList.size());
    }
    public StudioDTO getStudioOfMedia(Long id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(MediaNotFoundException::new);
        Studio studio = media.getStudio();
        if(studio == null) {
            throw new NoAssignedStudioException();
        }
        log.info("Found studio with id {} for media with id {}", studio.getID(), id);
        return studioMapper.toDTO(media.getStudio());
    }

    public Page<ReviewDTO> getReviewsOfMedia(Long id, Integer pageNo, Integer pageSize) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(MediaNotFoundException::new);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<ReviewDTO> reviewsList =  reviewMapper.toDTOList(media.getReviews());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), reviewsList.size());
        List<ReviewDTO> pageContent = reviewsList.subList(start, end);
        log.info("Found {} reviews for media with id {}", reviewsList.size(), id);
        return new PageImpl<>(pageContent, pageable, reviewsList.size());
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
