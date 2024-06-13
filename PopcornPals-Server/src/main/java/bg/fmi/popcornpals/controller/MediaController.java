package bg.fmi.popcornpals.controller;


import bg.fmi.popcornpals.dto.MediaDTO;
import bg.fmi.popcornpals.dto.MediaRequestDTO;
import bg.fmi.popcornpals.service.MediaService;
import bg.fmi.popcornpals.util.Genre;
import bg.fmi.popcornpals.util.MediaType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<MediaDTO>> getMedia(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                                                   @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                   @RequestParam (name = "title", required = false) String title,
                                                   @RequestParam (name = "type", required = false)  MediaType type,
                                                   @RequestParam (name = "genre", required = false) Genre genre) {
        return new ResponseEntity<>(mediaService.getMedia(pageNo, pageSize, title, type, genre), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<MediaDTO> updateMedia(@PathVariable Long id,
                                                @Valid @RequestBody MediaRequestDTO media) {
        return new ResponseEntity<>(mediaService.updateMedia(id, media), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteMediaById(@PathVariable Long id) {
        mediaService.deleteMediaById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
