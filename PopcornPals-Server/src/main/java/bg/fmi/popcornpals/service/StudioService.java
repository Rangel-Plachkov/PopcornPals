package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.model.Studio;
import bg.fmi.popcornpals.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudioService {

    @Autowired
    private StudioRepository studioRepository;


    public Studio createStudio(Studio studio) {
        return studioRepository.save(studio);
    }
    public List<Studio> getStudios(Long id, String name) {
        if(id != null){
            Studio studio = studioRepository.findById(id).orElse(null);
            return studio == null ? null : List.of(studio);
        }else if(name != null && !name.isEmpty()){
            return studioRepository.findByName(name);
        }else{
            return studioRepository.findAll();
        }
    }
    public Studio updateStudio(Studio studio) {
        return studioRepository.save(studio);
    }
    public void deleteStudioById(Long id) {
        studioRepository.deleteById(id);
    }
    public void deleteAllStudios() {
        studioRepository.deleteAll();
    }
}
