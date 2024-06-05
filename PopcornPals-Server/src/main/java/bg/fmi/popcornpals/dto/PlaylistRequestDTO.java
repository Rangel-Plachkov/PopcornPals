package bg.fmi.popcornpals.dto;

import bg.fmi.popcornpals.model.Playlist;
import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.StringSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistRequestDTO {
    private Long ID;

    @NotNull(message = "Playlist: Name cannot be null")
    @NotBlank(message = "Playlist: Name cannot be blank")
    @Size(max = StringSize.PLAYLIST_NAME_MAX, message = "Playlist: Name must be no more than " + StringSize.PLAYLIST_NAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.PLAYLIST_NAME, message = "Playlist: Name can contain only letters, digits and spaces")
    private String name;

    private Long creator;
    private List<Long> content;
}
