package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.ActorDTO;
import bg.fmi.popcornpals.dto.ActorRequestDTO;
import bg.fmi.popcornpals.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/actors/")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ActorDTO> getActor(@PathVariable("id") Long actorId) {
        ActorDTO actorDTO = actorService.getActorById(actorId);
        return actorDTO == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(actorDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ActorDTO>> getActors(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "name", required = false) String actorName) {
        return new ResponseEntity<>(actorService.getActors(pageNo, pageSize, actorName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActorDTO> createActor(@RequestBody @Valid ActorRequestDTO actorRequestDTO) {
        return new ResponseEntity<>(actorService.createActor(actorRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ActorDTO> updateActor(@PathVariable("id") Long actorId,
                                                @RequestBody @Valid ActorRequestDTO actorRequestDTO) {
        ActorDTO updatedActorDTO = actorService.updateActor(actorId, actorRequestDTO);
        return updatedActorDTO == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(updatedActorDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable("id") Long actorId) {
        ActorDTO actorDTO = actorService.getActorById(actorId);
        if(actorDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        actorService.deleteActor(actorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
