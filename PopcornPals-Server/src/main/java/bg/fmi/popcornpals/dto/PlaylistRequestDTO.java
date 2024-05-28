package bg.fmi.popcornpals.dto;

import bg.fmi.popcornpals.model.Playlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistRequestDTO {
    private Long ID;
    private String name;
    private Long creator;
}
