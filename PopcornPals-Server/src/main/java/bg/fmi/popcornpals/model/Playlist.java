package bg.fmi.popcornpals.model;

import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.StringSize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @NotNull(message = "Playlist: Name cannot be null")
    @NotBlank(message = "Playlist: Name cannot be blank")
    @Size(max = StringSize.PLAYLIST_NAME_MAX, message = "Playlist: Name must be no more than " + StringSize.PLAYLIST_NAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.PLAYLIST_NAME, message = "Playlist: Name can contain only letters, digits and spaces")
    private String name;

    @ManyToMany
    @JoinTable(name = "playlist_media",
                joinColumns = @JoinColumn(name = "playlist_id"),
                inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    public Playlist(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof Playlist playlist)) {
            return false;
        }
        return name.equals(playlist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
