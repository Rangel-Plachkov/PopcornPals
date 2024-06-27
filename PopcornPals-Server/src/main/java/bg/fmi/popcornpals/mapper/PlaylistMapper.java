package bg.fmi.popcornpals.mapper;

import bg.fmi.popcornpals.dto.PlaylistDTO;
import bg.fmi.popcornpals.dto.PlaylistRequestDTO;
import bg.fmi.popcornpals.model.Media;
import bg.fmi.popcornpals.model.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "creator", target = "creator")
    @Mapping(source = "content", target = "content")
    PlaylistDTO toDTO(Playlist playlist);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "creator.ID", target = "creator")
    @Mapping(source = "content", target = "content")
    PlaylistRequestDTO toRequestDTO(Playlist playlist);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "creator", target = "creator")
    @Mapping(source = "content", target = "content")
    Playlist toEntity(PlaylistDTO playlistDTO);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "creator", target = "creator.ID")
    @Mapping(source = "content", target = "content")
    Playlist toEntity(PlaylistRequestDTO playlistRequestDTO);

    List<Playlist> fromPlaylistDTOtoEntityList(List<PlaylistDTO> playlistDTOList);
    List<Playlist> toEntityList(List<PlaylistRequestDTO> playlistRequestDTOList);
    List<PlaylistDTO> toDTOList(List<Playlist> playlistList);
    List<PlaylistRequestDTO> toRequestDTOList(List<Playlist> playlistList);

    default List<Media> mapIdsToMedia(List<Long> mediaIdList) {
        if(mediaIdList == null) {
            return null;
        }
        List<Media> content = new ArrayList<Media>();
        for(Long media : mediaIdList) {
             Media newMedia = new Media();
             newMedia.setID(media);
             content.add(newMedia);
        }
        return content;
    }

    default List<Long> mapMediaToIds(List<Media> mediaList) {
        if(mediaList == null) {
            return null;
        }
        List<Long> content = new ArrayList<Long>();
        for(Media media : mediaList) {
            content.add(media.getID());
        }
        return content;
    }
}
