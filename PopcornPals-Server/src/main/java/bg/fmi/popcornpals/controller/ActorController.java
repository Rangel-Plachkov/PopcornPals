package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.ActorDTO;
import bg.fmi.popcornpals.dto.ActorRequestDTO;
import bg.fmi.popcornpals.dto.MediaDTO;
import bg.fmi.popcornpals.service.ActorService;
import bg.fmi.popcornpals.util.PaginationProperties;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Slf4j
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

    public ResponseEntity<Page<ActorDTO>> getActors(
            @RequestParam(value = "pageNo", defaultValue = PaginationProperties.DEFAULT_PAGE_NO, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationProperties.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
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
        return new ResponseEntity<>(actorService.updateActor(actorId, actorRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable("id") Long actorId) {
        actorService.deleteActor(actorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}/media/")
    public ResponseEntity<Page<MediaDTO>> getMedia(
            @PathVariable("id") Long actorId,
            @RequestParam(value = "pageNo", defaultValue = PaginationProperties.DEFAULT_PAGE_NO, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationProperties.DEFAULT_PAGE_SIZE, required = false) Integer pageSize) {
        Page<MediaDTO> media = actorService.getMedia(actorId, pageNo, pageSize);
        if(media.isEmpty()) {
            return new ResponseEntity<>(media, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(media, HttpStatus.OK);
    }
}
