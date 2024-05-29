package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.ProducerDTO;
import bg.fmi.popcornpals.mapper.ProducerMapper;
import bg.fmi.popcornpals.model.Producer;
import bg.fmi.popcornpals.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerService {
    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;

    @Autowired
    public ProducerService(ProducerRepository producerRepository, ProducerMapper producerMapper) {
        this.producerRepository = producerRepository;
        this.producerMapper = producerMapper;
    }

    public ProducerDTO getProducerById(Long producerId) {
        Producer producer = producerRepository.findById(producerId).orElse(null);
        return producerMapper.toDTO(producer);
    }

    public List<ProducerDTO> getProducers(Integer pageNo, Integer pageSize, String producerName) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Producer> producers = null;
        if(producerName != null) {
            producers = producerRepository.findByNameIgnoreCaseContaining(producerName, pageable);
        }
        else {
            producers = producerRepository.findAll(pageable);
        }
        return producerMapper.toDTOList(producers.getContent());
    }

    public ProducerDTO createProducer(ProducerDTO producerDTO) {
        Producer newProducer = producerRepository.save(producerMapper.toEntity(producerDTO));
        return producerMapper.toDTO(newProducer);
    }

    public ProducerDTO updateProducer(Long producerId, ProducerDTO producerDTO) {
        Producer producer = producerRepository.findById(producerId).orElse(null);
        if(producer == null) {
            return null;
        }

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
        return producerMapper.toDTO(newProducer);
    }

    public void deleteProducer(Long producerId) {
        Producer toDelete = producerRepository.findById(producerId).orElseThrow();
        producerRepository.delete(toDelete);
    }
}
