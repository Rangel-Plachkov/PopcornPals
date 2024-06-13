package bg.fmi.popcornpals.dto;

import bg.fmi.popcornpals.util.MediaType;
import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.StringSize;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaRequestDTO {
    private MediaType type;

    @NotNull(message = "Media: Title cannot be null")
    @NotBlank(message = "Media: Title cannot be blank")
    @Size(max = StringSize.TITLE_MAX, message = "Media: Title must be no more than " +  StringSize.TITLE_MAX + " characters")
    @Pattern(regexp = RegexPattern.MEDIA_TITLE, message = "Media: Title can contain only letters, digits and spaces")
    private String title;

    private String genre;
    private LocalDate releaseDate;
    private LocalDate endDate;

    @Min(value = 0, message = "Media: Length must be at least 0")
    private Integer length;

    @NotBlank(message = "Media: Description cannot be blank")
    @Size(max = StringSize.DESCRIPTION_MAX, message = "Media: Description must be no more than " +  StringSize.DESCRIPTION_MAX + " characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION, message = "Media: Invalid symbols in description")
    private String description;

    private Long parent_id;

    public MediaRequestDTO(String title, String genre, LocalDate releaseDate, LocalDate endDate, Integer length, String description, Long parent_id) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
        this.length = length;
        this.description = description;
        this.parent_id = parent_id;
    }
    public MediaRequestDTO(String title) {
        this.title = title;
    }

}
