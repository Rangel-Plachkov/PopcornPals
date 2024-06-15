package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.MediaDTO;
import bg.fmi.popcornpals.dto.ProducerDTO;
import bg.fmi.popcornpals.exception.notfound.ProducerNotFoundException;
import bg.fmi.popcornpals.dto.ProducerRequestDTO;
import bg.fmi.popcornpals.mapper.MediaMapper;
import bg.fmi.popcornpals.mapper.ProducerMapper;
import bg.fmi.popcornpals.model.Media;
import bg.fmi.popcornpals.model.Producer;
import bg.fmi.popcornpals.repository.MediaRepository;
import bg.fmi.popcornpals.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProducerService {
    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;
    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    @Autowired
    public ProducerService(ProducerRepository producerRepository, ProducerMapper producerMapper,
                           MediaRepository mediaRepository, MediaMapper mediaMapper) {
        this.producerRepository = producerRepository;
        this.producerMapper = producerMapper;
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
    }

    public ProducerDTO getProducerById(Long producerId) {
        Producer producer = producerRepository.findById(producerId)
                .orElseThrow(ProducerNotFoundException::new);
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

    public ProducerDTO createProducer(ProducerRequestDTO producerRequestDTO) {
        List<Media> producedMedia = producerRequestDTO.getProducedMedia() != null
                ? mediaRepository.findAllById(producerRequestDTO.getProducedMedia())
                : new ArrayList<Media>();

        Producer producer = new Producer();
        producer.setName(producerRequestDTO.getName());
        producer.setDescription(producerRequestDTO.getDescription());
        producer.setBirthdate(producerRequestDTO.getBirthdate());
        producer.setProducedMedia(producedMedia);

        Producer newProducer = producerRepository.save(producer);
        return producerMapper.toDTO(newProducer);
    }

    public ProducerDTO updateProducer(Long producerId, ProducerRequestDTO producerRequestDTO) {
        Producer producer = producerRepository.findById(producerId)
                .orElseThrow(ProducerNotFoundException::new);

        producer.setName(producerRequestDTO.getName());
        producer.setDescription(producerRequestDTO.getDescription());
        producer.setBirthdate(producerRequestDTO.getBirthdate());
        List<Media> producedMedia = producerRequestDTO.getProducedMedia() != null
                ? mediaRepository.findAllById(producerRequestDTO.getProducedMedia())
                : new ArrayList<Media>();
        producer.setProducedMedia(producedMedia);

        Producer newProducer = producerRepository.save(producer);
        return producerMapper.toDTO(newProducer);
    }

    public void deleteProducer(Long producerId) {
        Producer toDelete = producerRepository.findById(producerId)
                .orElseThrow(ProducerNotFoundException::new);
        producerRepository.delete(toDelete);
    }

    public List<MediaDTO> getProducedMedia(Long producerId) {
        Producer producer = producerRepository.findById(producerId)
                .orElseThrow(ProducerNotFoundException::new);
        return mediaMapper.toDTOList(producer.getProducedMedia());
    }
}
