package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.ProducerDTO;
import bg.fmi.popcornpals.dto.ProducerRequestDTO;
import bg.fmi.popcornpals.service.ProducerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/producers/")
public class ProducerController {
    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    public ResponseEntity<List<ProducerDTO>> getProducers(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "name", required = false) String producerName) {
        return new ResponseEntity<>(producerService.getProducers(pageNo, pageSize, producerName), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerDTO> getProducer(@PathVariable("id") Long producerId) {
        ProducerDTO producerDTO = producerService.getProducerById(producerId);
        return producerDTO == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(producerDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProducerDTO> createProducer(@RequestBody @Valid ProducerRequestDTO producerRequestDTO) {
        return new ResponseEntity<>(producerService.createProducer(producerRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProducerDTO> updateProducer(@PathVariable("id") Long producerId,
                                                      @RequestBody @Valid ProducerRequestDTO producerRequestDTO) {
        ProducerDTO updatedProducerDTO = producerService.updateProducer(producerId, producerRequestDTO);
        return updatedProducerDTO == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(updatedProducerDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProducer(@PathVariable("id") Long producerId) {
        ProducerDTO producerDTO = producerService.getProducerById(producerId);
        if(producerDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        producerService.deleteProducer(producerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
