package bg.fmi.popcornpals.mapper;

import bg.fmi.popcornpals.dto.ProducerDTO;
import bg.fmi.popcornpals.dto.ProducerRequestDTO;
import bg.fmi.popcornpals.model.Media;
import bg.fmi.popcornpals.model.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProducerMapper {
    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthdate", target = "birthdate")
    @Mapping(source = "producedMedia", target = "producedMedia")
    ProducerDTO toDTO(Producer producer);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthdate", target = "birthdate")
    @Mapping(source = "producedMedia", target = "producedMedia")
    ProducerRequestDTO toRequestDTO(Producer producer);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthdate", target = "birthdate")
    @Mapping(source = "producedMedia", target = "producedMedia")
    Producer toEntity(ProducerDTO producerDTO);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthdate", target = "birthdate")
    @Mapping(source = "producedMedia", target = "producedMedia")
    Producer toEntity(ProducerRequestDTO producerRequestDTO);

    List<ProducerDTO> toDTOList(List<Producer> producerList);
    List<ProducerRequestDTO> toRequestDTOList(List<Producer> producerList);
    List<Producer> fromProducerDTOToEntityList(List<ProducerDTO> producerDTOList);
    List<Producer> toEntityList(List<ProducerRequestDTO> producerRequestDTOList);

    default List<Long> mapMediaToIds(List<Media> mediaList) {
        if(mediaList == null) {
            return null;
        }
        List<Long> producedMedia = new ArrayList<>();
        for(Media media : mediaList) {
            producedMedia.add(media.getID());
        }
        return producedMedia;
    }

    default List<Media> mapIdsToMedia(List<Long> mediaIdList) {
        if(mediaIdList == null) {
            return null;
        }
        List<Media> producedMedia = new ArrayList<>();
        for(Long media : mediaIdList) {
            Media newMedia = new Media();
            newMedia.setID(media);
            producedMedia.add(newMedia);
        }
        return producedMedia;
    }
}
