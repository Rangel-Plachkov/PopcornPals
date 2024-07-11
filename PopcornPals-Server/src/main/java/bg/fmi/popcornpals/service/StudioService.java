package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.MediaDTO;
import bg.fmi.popcornpals.dto.StudioDTO;
import bg.fmi.popcornpals.dto.StudioRequestDTO;
import bg.fmi.popcornpals.exception.nocontent.NoAssignedMediaException;
import bg.fmi.popcornpals.exception.notfound.StudioNotFoundException;
import bg.fmi.popcornpals.mapper.StudioMapper;
import bg.fmi.popcornpals.mapper.MediaMapper;
import bg.fmi.popcornpals.model.Studio;
import bg.fmi.popcornpals.model.Media;
import bg.fmi.popcornpals.repository.StudioRepository;
import bg.fmi.popcornpals.repository.MediaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Slf4j
@Service
public class StudioService {

    @Autowired
    private StudioRepository studioRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private StudioMapper studioMapper;
    @Autowired
    private MediaMapper mediaMapper;


    public StudioDTO createStudio(StudioRequestDTO studioDTO) {
        Studio studio = studioMapper.toEntity(studioDTO);
        studio.setID(null);
        Studio createdStudio = studioRepository.save(studio);
        log.info("Studio with id {} was created", createdStudio.getID());
        return studioMapper.toDTO(createdStudio);
    }
    public StudioDTO getStudioById(Long id) {
        Studio studio = studioRepository.findById(id)
                .orElseThrow(StudioNotFoundException::new);
        log.info("Found studio with id: {}", id);
        return studioMapper.toDTO(studio);
    }
    public Page<StudioDTO> getStudios(Integer pageNo, Integer pageSize, String studioName) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Studio> studios = null;
        if(studioName != null) {
            studios = studioRepository.findByNameIgnoreCaseContaining(studioName, pageable);
        }
        else {
            studios = studioRepository.findAll(pageable);
        }
        log.info("Found " + studios.getTotalElements() + " studios");
        return studios.map(studio -> studioMapper.toDTO(studio));
    }
    public Page<MediaDTO> getStudioMedia(Integer pageNo, Integer pageSize,Long studioId) {
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(StudioNotFoundException::new);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Media> media = null;
        media = mediaRepository.findByStudio(studio, pageable);
        if(media.isEmpty()) {
            throw new NoAssignedMediaException();
        }
        log.info("Found " + media.getTotalElements() + " media assigned to studio with id: " + studioId);
        return media.map(m -> mediaMapper.toDTO(m));
    }


    public StudioDTO updateStudio(Long studioId, StudioRequestDTO studioDTO) {
        Studio studio;
        studio = studioMapper.toEntity(studioDTO);
        studio.setID(studioId);
        Studio updatedStudio = studioRepository.save(studio);
        log.info("Studio with id {} was updated", updatedStudio.getID());
        return studioMapper.toDTO(updatedStudio);
    }
    public void deleteStudio(Long id) {
        Studio toDelete = studioRepository.findById(id)
                .orElseThrow(StudioNotFoundException::new);
        log.info("Studio with id {} was deleted", toDelete.getID());
        studioRepository.delete(toDelete);
    }
}
