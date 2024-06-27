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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

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
        return studioMapper.toDTO(createdStudio);
    }
    public StudioDTO getStudioById(Long id) {
        Studio studio = studioRepository.findById(id)
                .orElseThrow(StudioNotFoundException::new);
        return studioMapper.toDTO(studio);
    }
    public List<StudioDTO> getStudios(Integer pageNo, Integer pageSize, String studioName) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Studio> studios = null;
        if(studioName != null) {
            studios = studioRepository.findByNameIgnoreCaseContaining(studioName, pageable);
        }
        else {
            studios = studioRepository.findAll(pageable);
        }
        return studioMapper.toDTOList(studios.getContent());
    }
    public List<MediaDTO> getStudioMedia(Integer pageNo, Integer pageSize,Long studioId) {
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(StudioNotFoundException::new);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Media> media = null;
        media = mediaRepository.findByStudio(studio, pageable);
        if(media.isEmpty()) {
            throw new NoAssignedMediaException();
        }
        return mediaMapper.toDTOList(media.getContent());
    }


    public StudioDTO updateStudio(Long studioId, StudioRequestDTO studioDTO) {
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(StudioNotFoundException::new);
        studio = studioMapper.toEntity(studioDTO);
        Studio updatedStudio = studioRepository.save(studio);
        return studioMapper.toDTO(updatedStudio);
    }
    public void deleteStudio(Long id) {
        Studio toDelete = studioRepository.findById(id)
                .orElseThrow(StudioNotFoundException::new);
        studioRepository.delete(toDelete);
    }
}
