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
@RequestMapping(path = "api/actors/")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ActorDTO> getActor(@PathVariable("id") Long actorId) {
        return new ResponseEntity<>(actorService.getActorById(actorId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ActorDTO>> getActors(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "name", required = false) String actorName) {
        return new ResponseEntity<>(actorService.getActors(pageNo, pageSize, actorName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActorDTO> createActor(@RequestBody @Valid ActorDTO actorDTO) {
        return new ResponseEntity<>(actorService.createActor(actorDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ActorDTO> updateActor(@PathVariable("id") Long actorId,
                                                @RequestBody @Valid ActorDTO actorDTO) {
        return new ResponseEntity<>(actorService.updateActor(actorId, actorDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable("id") Long actorId) {
        actorService.deleteActor(actorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
