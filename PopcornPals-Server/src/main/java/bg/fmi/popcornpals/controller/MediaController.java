package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.*;
import bg.fmi.popcornpals.service.MediaService;
import bg.fmi.popcornpals.util.Genre;
import bg.fmi.popcornpals.util.PaginationProperties;
import bg.fmi.popcornpals.util.MediaType;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/media/")
public class MediaController {
    @Autowired
    private MediaService mediaService;
    @PostMapping
    public ResponseEntity<MediaDTO> createMedia(@Valid @RequestBody MediaRequestDTO media) {
        return new ResponseEntity<>(mediaService.createMedia(media), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<MediaDTO> getMedia(@PathVariable Long id) {
        return new ResponseEntity<>(mediaService.getMediaById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<MediaDTO>> getMedia(@RequestParam(value = "pageNo", defaultValue = PaginationProperties.DEFAULT_PAGE_NO , required = false) Integer pageNo,
                                                   @RequestParam(value = "pageSize", defaultValue = PaginationProperties.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
                                                   @RequestParam (name = "title", required = false) String title,
                                                   @RequestParam (name = "type", required = false)  MediaType type,
                                                   @RequestParam (name = "genre", required = false) Genre genre) {
        return new ResponseEntity<>(mediaService.getMedia(pageNo, pageSize, title, type, genre), HttpStatus.OK);
    }
    @GetMapping("{id}/actors")
    public ResponseEntity<List<ActorDTO>> getActorsInMedia(@PathVariable Long id) {
        return new ResponseEntity<>(mediaService.getActorsInMedia(id), HttpStatus.OK);
    }
    @GetMapping("{id}/producers")
    public ResponseEntity<List<ProducerDTO>> getProducersInMedia(@PathVariable Long id) {
        return new ResponseEntity<>(mediaService.getProducerOfMedia(id), HttpStatus.OK);
    }
    @GetMapping("{id}/studio")
    public ResponseEntity<StudioDTO> getStudioOfMedia(@PathVariable Long id) {
        return new ResponseEntity<>(mediaService.getStudioOfMedia(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<MediaDTO> updateMedia(@PathVariable Long id,
                                                @Valid @RequestBody MediaRequestDTO media) {
        return new ResponseEntity<>(mediaService.updateMedia(id, media), HttpStatus.OK);
    }
    @PatchMapping("{id}/studio/{studioId}")
    public ResponseEntity<MediaDTO> assignStudio(@PathVariable Long id,
                                                @PathVariable Long studioId) {
        return new ResponseEntity<>(mediaService.assignStudio(id, studioId), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteMediaById(@PathVariable Long id) {
        mediaService.deleteMediaById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
