package bg.fmi.popcornpals.mapper;

import bg.fmi.popcornpals.dto.ProducerDTO;
import bg.fmi.popcornpals.model.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProducerMapper {
    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthdate", target = "birthdate")
    ProducerDTO toDTO(Producer producer);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthdate", target = "birthdate")
    Producer toEntity(ProducerDTO producerDTO);

    List<ProducerDTO> toDTOList(List<Producer> producerList);
    List<Producer> toEntityList(List<ProducerDTO> producerDTOList);
}
