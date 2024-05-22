package bg.fmi.popcornpals.controller;


import bg.fmi.popcornpals.dto.MediaDTO;
import bg.fmi.popcornpals.mapper.MediaMapper;
import bg.fmi.popcornpals.model.Media;
import bg.fmi.popcornpals.service.MediaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {
    @Autowired
    private MediaService mediaService;
    @Autowired
    private MediaMapper mediaMapper;

    @PostMapping("/create")
    public ResponseEntity<MediaDTO> createMedia(@Valid @RequestBody MediaDTO media) {

        Media temp = mediaMapper.toEntity(media);
        //
        Media createdMedia = mediaService.createMedia(temp);
        return new ResponseEntity<>(mediaMapper.toDTO(createdMedia), HttpStatus.CREATED);
    }
    @GetMapping("/readById/{id}")
    public ResponseEntity<MediaDTO> getMediaById(@PathVariable Long id) {
        Media media = mediaService.getMediaById(id);
        if (media == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mediaMapper.toDTO(media), HttpStatus.OK);
    }
    @GetMapping("/readByTitle")
    public ResponseEntity<List<MediaDTO>> getMediaByTitle(@RequestParam (name = "title") String title) {
        List<Media> mediaList = mediaService.getMediaByTitle(title);
        if(mediaList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mediaMapper.toDTOList(mediaList), HttpStatus.OK);
    }
    @GetMapping("/readAll")
    public ResponseEntity<List<MediaDTO>> getAllMedia() {
        List<Media> mediaList = mediaService.getAllMedia();
        if(mediaList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mediaMapper.toDTOList(mediaList), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MediaDTO> updateMedia(@PathVariable Long id, @Valid @RequestBody MediaDTO media) {
        Media existingMedia = mediaService.getMediaById(id);
        if(existingMedia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        media.setID(id);
        Media mediaToUpdate = mediaMapper.toEntity(media);
        Media updatedMedia = mediaService.updateMedia(mediaToUpdate);
        MediaDTO updatedMediaDTO = mediaMapper.toDTO(updatedMedia);
        return new ResponseEntity<>(updatedMediaDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteMediaById(@PathVariable Long id) {
        Media existingMedia = mediaService.getMediaById(id);
        if(existingMedia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        mediaService.deleteMediaById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllMedia() {
        mediaService.deleteAllMedia();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
