package bg.fmi.popcornpals.mapper;

import bg.fmi.popcornpals.dto.ActorDTO;
import bg.fmi.popcornpals.dto.ActorRequestDTO;
import bg.fmi.popcornpals.model.Actor;
import bg.fmi.popcornpals.model.Media;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthdate", target = "birthdate")
    @Mapping(source = "starsIn", target = "starsIn")
    ActorDTO toDTO(Actor actor);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthdate", target = "birthdate")
    @Mapping(source = "starsIn", target = "starsIn")
    ActorRequestDTO toRequestDTO(Actor actor);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthdate", target = "birthdate")
    @Mapping(source = "starsIn", target = "starsIn")
    Actor toEntity(ActorDTO actorDTO);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthdate", target = "birthdate")
    @Mapping(source = "starsIn", target = "starsIn")
    Actor toEntity(ActorRequestDTO actorRequestDTO);

    List<ActorDTO> toDTOList(List<Actor> actorList);
    List<ActorRequestDTO> toRequestDTOList(List<Actor> actorList);
    List<Actor> fromActorDTOToEntityList(List<ActorDTO> actorDTOList);
    List<Actor> toEntityList(List<ActorRequestDTO> actorRequestDTOList);

    default List<Long> mapMediaToIds(List<Media> mediaList) {
        if(mediaList == null) {
            return null;
        }
        List<Long> starsIn = new ArrayList<Long>();
        for(Media media : mediaList) {
            starsIn.add(media.getID());
        }
        return starsIn;
    }

    default List<Media> mapIdsToMedia(List<Long> mediaIdList) {
        if(mediaIdList == null) {
            return null;
        }
        List<Media> starsIn = new ArrayList<Media>();
        for(Long media : mediaIdList) {
            Media newMedia = new Media();
            newMedia.setID(media);
            starsIn.add(newMedia);
        }
        return starsIn;
    }
}
