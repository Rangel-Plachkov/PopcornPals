package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.StudioDTO;
import bg.fmi.popcornpals.mapper.StudioMapper;
import bg.fmi.popcornpals.model.Studio;
import bg.fmi.popcornpals.service.StudioService;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping("/api/studios/")
public class StudioController {

    @Autowired
    private StudioService studioService;

    @Autowired
    private StudioMapper studioMapper;

    @PostMapping
    public ResponseEntity<StudioDTO> createStudio(@Valid @RequestBody StudioDTO studio) {
        studio.setID(null);
        Studio createdStudio = studioService.createStudio(studioMapper.toEntity(studio));
        return new ResponseEntity<>(studioMapper.toDTO(createdStudio), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudioDTO>> getStudios(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name) {

        List<Studio> studioList = studioService.getStudios(id, name);

        if (studioList == null || studioList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<StudioDTO> studioDTOList = studioMapper.toDTOList(studioList);

        return new ResponseEntity<>(studioDTOList, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudioDTO> updateStudio(@PathVariable Long id, @Valid @RequestBody StudioDTO studio) {
        Studio existingStudio = studioService.getStudios(id, null).get(0);
        if (existingStudio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studio.setID(id);
        Studio studioToUpdate = studioMapper.toEntity(studio);

        Studio updatedStudio = studioService.updateStudio(studioToUpdate);

        StudioDTO updatedStudioDTO = studioMapper.toDTO(updatedStudio);

        return new ResponseEntity<>(updatedStudioDTO, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudioById(@PathVariable Long id) {
        Studio existingStudio = studioService.getStudios(id, null).get(0);
        if (existingStudio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studioService.deleteStudioById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllStudios() {
        studioService.deleteAllStudios();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
