package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.StudioDTO;
import bg.fmi.popcornpals.mapper.StudioMapper;
import bg.fmi.popcornpals.model.Studio;
import bg.fmi.popcornpals.service.StudioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

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
    @GetMapping("/readById/{id}")
    public ResponseEntity<StudioDTO> getStudioById(@PathVariable Long id) {
        Studio studio = studioService.getStudioById(id);
        if (studio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studioMapper.toDTO(studio), HttpStatus.OK);
    }
    @GetMapping("/readByName/")
    public ResponseEntity<List<StudioDTO>> getStudioByName(@RequestParam (name = "name") String name) {
        List<Studio> studioList = studioService.getStudioByName(name);

        if (studioList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<StudioDTO> studioDTOList = new ArrayList<>();

        for (Studio studio : studioList) {
            studioDTOList.add(studioMapper.toDTO(studio));
        }
        return new ResponseEntity<>(studioDTOList, HttpStatus.OK);
    }
    @GetMapping("/readAll")
    public ResponseEntity<List<StudioDTO>> getAllStudios() {
        List<Studio> studioList = studioService.getAllStudios();

        if (studioList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<StudioDTO> studioDTOList = new ArrayList<>();

        for (Studio studio : studioList) {
            studioDTOList.add(studioMapper.toDTO(studio));
        }
        return new ResponseEntity<>(studioDTOList, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<StudioDTO> updateStudio(@PathVariable Long id, @Valid @RequestBody StudioDTO studio) {
        Studio existingStudio = studioService.getStudioById(id);
        if (existingStudio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studio.setID(id);
        Studio studioToUpdate = studioMapper.toEntity(studio);

        Studio updatedStudio = studioService.updateStudio(studioToUpdate);

        StudioDTO updatedStudioDTO = studioMapper.toDTO(updatedStudio);

        return new ResponseEntity<>(updatedStudioDTO, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudioById(@PathVariable Long id) {
        studioService.deleteStudioById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllStudios() {
        studioService.deleteAllStudios();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
