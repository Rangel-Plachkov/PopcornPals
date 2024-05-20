package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.StudioDTO;
import bg.fmi.popcornpals.mapper.StudioMapper;
import bg.fmi.popcornpals.model.Studio;
import bg.fmi.popcornpals.service.StudioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studios")
public class StudioController {

    @Autowired
    private StudioService studioService;
    @Autowired
    private StudioMapper studioMapper;

    @PostMapping("/create")
    public ResponseEntity<StudioDTO> createStudio(@Valid @RequestBody StudioDTO studio) {
        Studio createdStudio = studioService.createStudio(studioMapper.toEntity(studio));
        return new ResponseEntity<>(studioMapper.toDTO(createdStudio), HttpStatus.CREATED);
    }

}
