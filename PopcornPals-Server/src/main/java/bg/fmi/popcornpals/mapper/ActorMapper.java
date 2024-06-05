package bg.fmi.popcornpals.mapper;

import bg.fmi.popcornpals.dto.ActorDTO;
import bg.fmi.popcornpals.model.Actor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthdate", target = "birthdate")
    ActorDTO toDTO(Actor actor);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthdate", target = "birthdate")
    Actor toEntity(ActorDTO actorDTO);

    List<ActorDTO> toDTOList(List<Actor> actorsList);
    List<Actor> toEntityList(List<ActorDTO> actorsDTOList);
}
