package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.model.Studio;
import bg.fmi.popcornpals.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudioService {

    private final StudioRepository studioRepository;

    @Autowired
    public StudioService(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    public Studio createStudio(Studio studio) {
        return studioRepository.save(studio);
    }
    public Studio getStudioById(Long id) {
        return studioRepository.findById(id).orElse(null);
    }
    public Studio updateStudio(Studio studio) {
        return studioRepository.save(studio);
    }
    public void deleteStudioById(Long id) {
        studioRepository.deleteById(id);
    }
}
