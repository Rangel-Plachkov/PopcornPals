package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.MediaDTO;
import bg.fmi.popcornpals.dto.StudioDTO;
import bg.fmi.popcornpals.dto.StudioRequestDTO;
import bg.fmi.popcornpals.service.StudioService;
import bg.fmi.popcornpals.util.PaginationProperties;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/studios/")
public class StudioController {

    @Autowired
    private StudioService studioService;


    @PostMapping
    public ResponseEntity<StudioDTO> createStudio(@Valid @RequestBody StudioRequestDTO studio) {
        return new ResponseEntity<>(studioService.createStudio(studio), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudioDTO> getStudio(@PathVariable("id") Long studioId) {
        return new ResponseEntity<>(studioService.getStudioById(studioId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudioDTO>> getStudios(
            @RequestParam(value = "pageNo", defaultValue = PaginationProperties.DEFAULT_PAGE_NO, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationProperties.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "name", required = false) String studioName) {
        return new ResponseEntity<>(studioService.getStudios(pageNo, pageSize, studioName), HttpStatus.OK);
    }

    @GetMapping("{id}/media")
    public ResponseEntity<List<MediaDTO>> getStudioMedia(
            @PathVariable("id") Long studioId,
            @RequestParam(value = "pageNo", defaultValue = PaginationProperties.DEFAULT_PAGE_NO, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationProperties.DEFAULT_PAGE_SIZE, required = false) Integer pageSize) {
        return new ResponseEntity<>(studioService.getStudioMedia(pageNo,pageSize,studioId), HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<StudioDTO> updateStudio(@PathVariable("id") Long studioId,
                                                  @RequestBody @Valid StudioRequestDTO studioDTO) {
        return new ResponseEntity<>(studioService.updateStudio(studioId, studioDTO), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudioById(@PathVariable Long id) {
        studioService.deleteStudio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
