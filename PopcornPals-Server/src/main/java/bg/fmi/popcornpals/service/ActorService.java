package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.ActorDTO;
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

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
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
        return actors.getContent().stream().map(a -> mapToDTO(a)).collect(Collectors.toList());
    }

    // To Do: create custom exception to throw
    public ActorDTO getActorById(Long actorId) {
        Actor actor = actorRepository.findById(actorId).orElseThrow();
        return mapToDTO(actor);
    }

    public ActorDTO createActor(ActorDTO actorDTO) {
        Actor actor = mapToEntity(actorDTO);
        Actor newActor = actorRepository.save(actor);
        return mapToDTO(newActor);
    }

    public ActorDTO updateActor(Long actorId, ActorDTO actorDTO) {
        Actor actor = actorRepository.findById(actorId).orElseThrow();

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
        return mapToDTO(updatedActor);
    }

    public void deleteActor(Long actorId) {
        Actor toDelete = actorRepository.findById(actorId).orElseThrow();
        actorRepository.delete(toDelete);
    }

    private ActorDTO mapToDTO(Actor actor) {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setId(actor.getID());
        actorDTO.setName(actor.getName());
        actorDTO.setDescription(actor.getDescription());
        actorDTO.setBirthdate(actor.getBirthdate());
        return actorDTO;
    }

    private Actor mapToEntity(ActorDTO actorDTO) {
        Actor actor = new Actor();
        actor.setID(actorDTO.getId());
        actor.setName(actorDTO.getName());
        actor.setDescription(actorDTO.getDescription());
        actor.setBirthdate(actorDTO.getBirthdate());
        return actor;
    }
}
