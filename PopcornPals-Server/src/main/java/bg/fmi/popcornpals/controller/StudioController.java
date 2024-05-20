package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.StudioDTO;
import bg.fmi.popcornpals.service.StudioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studio")
public class StudioController {

    @Autowired
    private StudioService studioService;

    @PostMapping("/create")
    public void createStudio(@Valid @RequestBody StudioDTO studio) {
        //studioService.createStudio();
    }

}
