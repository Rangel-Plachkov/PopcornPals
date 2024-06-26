package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.MediaDTO;
import bg.fmi.popcornpals.dto.ProducerDTO;
import bg.fmi.popcornpals.dto.ProducerRequestDTO;
import bg.fmi.popcornpals.service.ProducerService;
import bg.fmi.popcornpals.util.PaginationProperties;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "api/producers/")
public class ProducerController {
    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    public ResponseEntity<Page<ProducerDTO>> getProducers(
            @RequestParam(value = "pageNo", defaultValue = PaginationProperties.DEFAULT_PAGE_NO, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationProperties.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "name", required = false) String producerName) {
        return new ResponseEntity<>(producerService.getProducers(pageNo, pageSize, producerName), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerDTO> getProducer(@PathVariable("id") Long producerId) {
        return new ResponseEntity<>(producerService.getProducerById(producerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProducerDTO> createProducer(@RequestBody @Valid ProducerRequestDTO producerRequestDTO) {
        return new ResponseEntity<>(producerService.createProducer(producerRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProducerDTO> updateProducer(@PathVariable("id") Long producerId,
                                                      @RequestBody @Valid ProducerRequestDTO producerRequestDTO) {
        return new ResponseEntity<>(producerService.updateProducer(producerId, producerRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProducer(@PathVariable("id") Long producerId) {
        producerService.deleteProducer(producerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}/media/")
    public ResponseEntity<Page<MediaDTO>> getProducedMedia(
            @PathVariable("id") Long producerId,
            @RequestParam(value = "pageNo", defaultValue = PaginationProperties.DEFAULT_PAGE_NO, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationProperties.DEFAULT_PAGE_SIZE, required = false) Integer pageSize) {
        Page<MediaDTO> media = producerService.getProducedMedia(producerId, pageNo, pageSize);
        if(media.isEmpty()) {
            return new ResponseEntity<>(media, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(media, HttpStatus.OK);
    }
}
