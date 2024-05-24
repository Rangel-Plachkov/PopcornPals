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

    public ProducerDTO getProducerById(Long producerId) {
        Producer producer = producerRepository.findById(producerId).orElseThrow();
        return mapToDTO(producer);
    }

    public ProducerDTO createProducer(ProducerDTO producerDTO) {
        Producer newProducer = producerRepository.save(mapToEntity(producerDTO));
        return mapToDTO(newProducer);
    }

    public ProducerDTO updateProducer(Long producerId, ProducerDTO producerDTO) {
        Producer producer = producerRepository.findById(producerId).orElseThrow();

        if(producerDTO.getName() != null) {
            producer.setName(producerDTO.getName());
        }
        if(producerDTO.getDescription() != null) {
            producer.setDescription(producerDTO.getDescription());
        }
        if(producerDTO.getBirthdate() != null) {
            producer.setBirthdate(producerDTO.getBirthdate());
        }
        Producer newProducer = producerRepository.save(producer);
        return mapToDTO(newProducer);
    }

    public void deleteProducer(Long producerId) {
        Producer toDelete = producerRepository.findById(producerId).orElseThrow();
        producerRepository.delete(toDelete);
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
