package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.ActorDTO;
import bg.fmi.popcornpals.mapper.ActorMapper;
import bg.fmi.popcornpals.model.Actor;
import bg.fmi.popcornpals.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    @Autowired
    public ActorService(ActorRepository actorRepository, ActorMapper actorMapper) {
        this.actorRepository = actorRepository;
        this.actorMapper = actorMapper;
    }

    public List<ActorDTO> getActors(Integer pageNo, Integer pageSize, String actorName) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Actor> actors = null;
        if(actorName != null) {
            actors = actorRepository.findByNameIgnoreCaseContaining(actorName, pageable);
        }
        else {
            actors = actorRepository.findAll(pageable);
        }
        return actors.getContent().stream().map(a -> actorMapper.toDTO(a)).collect(Collectors.toList());
    }

    // To Do: create custom exception to throw
    public ActorDTO getActorById(Long actorId) {
        Actor actor = actorRepository.findById(actorId).orElse(null);
        return actorMapper.toDTO(actor);
    }

    public ActorDTO createActor(ActorDTO actorDTO) {
        Actor actor = actorMapper.toEntity(actorDTO);
        Actor newActor = actorRepository.save(actor);
        return actorMapper.toDTO(newActor);
    }

    public ActorDTO updateActor(Long actorId, ActorDTO actorDTO) {
        Actor actor = actorRepository.findById(actorId).orElse(null);
        if(actor == null) {
            return null;
        }

        if(actorDTO.getName() != null) {
            actor.setName(actorDTO.getName());
        }
        if(actorDTO.getDescription() != null) {
            actor.setDescription(actorDTO.getDescription());
        }
        if(actorDTO.getBirthdate() != null) {
            actor.setBirthdate(actorDTO.getBirthdate());
        }

        Actor updatedActor = actorRepository.save(actor);
        return actorMapper.toDTO(updatedActor);
    }

    public void deleteActor(Long actorId) {
        Actor toDelete = actorRepository.findById(actorId).orElseThrow();
        actorRepository.delete(toDelete);
    }
}
