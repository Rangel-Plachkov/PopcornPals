package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.ProducerDTO;
import bg.fmi.popcornpals.service.ProducerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/producers/")
public class ProducerController {
    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<ProducerDTO> createProducer(@RequestBody @Valid ProducerDTO producerDTO) {
        return new ResponseEntity<>(producerService.createProducer(producerDTO), HttpStatus.CREATED);
    }
}
