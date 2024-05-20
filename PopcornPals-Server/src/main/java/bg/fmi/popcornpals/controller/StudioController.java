package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studio")
public class StudioController {

    @Autowired
    private StudioService studioService;


}
