package bg.fmi.popcornpals.mapper;

import bg.fmi.popcornpals.dto.PlaylistDTO;
import bg.fmi.popcornpals.dto.PlaylistRequestDTO;
import bg.fmi.popcornpals.model.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "creator", target = "creator")
    PlaylistDTO toDTO(Playlist playlist);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "creator.ID", target = "creator")
    PlaylistRequestDTO toRequestDTO(Playlist playlist);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "creator", target = "creator")
    Playlist toEntity(PlaylistDTO playlistDTO);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "creator", target = "creator.ID")
    Playlist toEntity(PlaylistRequestDTO playlistRequestDTO);

    List<Playlist> fromPlaylistDTOtoEntityList(List<PlaylistDTO> playlistDTOList);
    List<Playlist> toEntityList(List<PlaylistRequestDTO> playlistRequestDTOList);
    List<PlaylistDTO> toDTOList(List<Playlist> playlistList);
    List<PlaylistRequestDTO> toRequestDTOList(List<Playlist> playlistList);

}
