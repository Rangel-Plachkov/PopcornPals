package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.ActorDTO;
import bg.fmi.popcornpals.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/actors/")
public class ActorController {
    private final ActorService actorService;
    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("{id}")
    public ActorDTO getActor(@PathVariable("id") Long actorId) {
        return actorService.getActorById(actorId);
    }
    @GetMapping("getByName/{name}")
    public List<ActorDTO> getActorsByName(@PathVariable("name") String actorName) {
        return actorService.getActorsByName(actorName);
    }
    @GetMapping
    public List<ActorDTO> getActors() {
        return actorService.getActors();
    }
    @PostMapping
    public ActorDTO createActor(@RequestBody ActorDTO actorDTO) {
        return actorService.createActor(actorDTO);
    }
    @PutMapping("{id}")
    public ActorDTO updateActor(@PathVariable("id") Long actorId,
                                                @RequestBody @Valid ActorDTO actorDTO) {
        return actorService.updateActor(actorId, actorDTO);
    }
    @DeleteMapping("{id}")
    public void deleteActor(@PathVariable("id") Long actorId) {
        actorService.deleteActor(actorId);
    }
}
