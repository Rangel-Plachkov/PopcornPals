package bg.fmi.popcornpals.controller;


import bg.fmi.popcornpals.dto.MediaDTO;
import bg.fmi.popcornpals.mapper.MediaMapper;
import bg.fmi.popcornpals.model.Media;
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
    @Autowired
    private MediaMapper mediaMapper;

    @PostMapping
    public ResponseEntity<MediaDTO> createMedia(@Valid @RequestBody MediaDTO media) {
        media.setID(null);
        Media createdMedia = mediaService.createMedia(mediaMapper.toEntity(media));
        return new ResponseEntity<>(mediaMapper.toDTO(createdMedia), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<MediaDTO>> getMedia(@RequestParam (name = "id", required = false) Long ID,
                                                   @RequestParam (name = "title", required = false) String title,
                                                   @RequestParam (name = "type", required = false)  MediaType type,
                                                    @RequestParam (name = "genre", required = false) Genre genre) {
        List<Media> mediaList = mediaService.getMedia(ID, title, type, genre);
        if(mediaList == null || mediaList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mediaMapper.toDTOList(mediaList), HttpStatus.OK);

    }
    @PutMapping("{id}")
    public ResponseEntity<MediaDTO> updateMedia(@PathVariable Long id, @Valid @RequestBody MediaDTO media) {
        Media existingMedia = mediaService.getMedia(id,null,null,null).get(0);
        if(existingMedia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        media.setID(id);
        Media mediaToUpdate = mediaMapper.toEntity(media);
        Media updatedMedia = mediaService.updateMedia(mediaToUpdate);
        MediaDTO updatedMediaDTO = mediaMapper.toDTO(updatedMedia);
        return new ResponseEntity<>(updatedMediaDTO, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteMediaById(@PathVariable Long id) {
        Media existingMedia = mediaService.getMedia(id,null,null,null).get(0);
        if(existingMedia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        mediaService.deleteMediaById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("deleteAll")
    public ResponseEntity<HttpStatus> deleteAllMedia() {
        mediaService.deleteAllMedia();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
