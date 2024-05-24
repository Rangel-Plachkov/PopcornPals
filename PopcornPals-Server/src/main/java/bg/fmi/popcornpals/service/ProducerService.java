package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.ProducerDTO;
import bg.fmi.popcornpals.model.Producer;
import bg.fmi.popcornpals.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private final ProducerRepository producerRepository;

    @Autowired
    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public ProducerDTO createProducer(ProducerDTO producerDTO) {
        Producer newProducer = producerRepository.save(mapToEntity(producerDTO));
        return mapToDTO(newProducer);
    }

    private ProducerDTO mapToDTO(Producer producer) {
        ProducerDTO producerDTO = new ProducerDTO();
        producerDTO.setID(producer.getID());
        producerDTO.setName(producer.getName());
        producerDTO.setDescription(producer.getDescription());
        producerDTO.setBirthdate(producer.getBirthdate());
        return producerDTO;
    }

    private Producer mapToEntity(ProducerDTO producerDTO) {
        Producer producer = new Producer();
        producer.setName(producerDTO.getName());
        producer.setDescription(producerDTO.getDescription());
        producer.setBirthdate(producerDTO.getBirthdate());
        return producer;
    }
}
