package bg.fmi.popcornpals.dto;

import bg.fmi.popcornpals.model.Playlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDTO {
    private Long ID;
    private String name;
    private UserDTO creator;

    public static PlaylistDTO mapToDTO(Playlist playlist) {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setID(playlist.getID());
        playlistDTO.setName(playlist.getName());
        playlistDTO.setCreator(UserDTO.mapToDTO(playlist.getCreator()));
        return playlistDTO;
    }
}
