package bg.fmi.popcornpals.mapper;

import bg.fmi.popcornpals.dto.MediaDTO;
import bg.fmi.popcornpals.dto.MediaRequestDTO;
import bg.fmi.popcornpals.model.Media;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.AfterMapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaMapper {

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "genre", target = "genre")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "length", target = "length")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "parent.ID", target = "parent_id")
    MediaDTO toDTO(Media media);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "genre", target = "genre")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "length", target = "length")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "parent_id", target = "parent.ID")
    Media toEntity(MediaDTO mediaDTO);


    @Mapping(source = "type", target = "type")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "genre", target = "genre")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "length", target = "length")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "parent_id", target = "parent.ID")
    Media toEntity(MediaRequestDTO mediaRequestDTO);

    @AfterMapping
    default void setParentId(MediaDTO source, @MappingTarget Media destination) {
        if (source.getParent_id() == null) {
            destination.setParent(null);
        }
    }

    List<MediaDTO> toDTOList(List<Media> mediaList);
    List<Media> toEntityList(List<MediaDTO> mediaDTOList);

    List<MediaRequestDTO> toRequestDTOList(List<Media> mediaList);

}
