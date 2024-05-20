package bg.fmi.popcornpals.mapper;

import bg.fmi.popcornpals.dto.StudioDTO;
import bg.fmi.popcornpals.model.Studio;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudioMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "foundingDate", target = "foundingDate")
    StudioDTO toDTO(Studio studio);


    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "foundingDate", target = "foundingDate")
    Studio toEntity(StudioDTO studioDTO);


}
