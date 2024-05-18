package bg.fmi.popcornpals.model;

import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.StringSize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;

    @NotNull(message = "Media: Title cannot be null")
    @NotBlank(message = "Media: Title cannot be blank")
    @Size(max = StringSize.TITLE_MAX, message = "Media: Title must be no more than " +  StringSize.TITLE_MAX + " characters")
    @Pattern(regexp = RegexPattern.MEDIA_TITLE, message = "Media: Title can contain only letters, digits and spaces")
    private String title;


    private LocalDate releaseDate;

    private LocalDate endDate;

    @Min(value = 0, message = "Media: Length must be at least 0")
    private Integer length;


    @NotBlank(message = "Media: Description cannot be blank")
    @Size(max = StringSize.DESCRIPTION_MAX, message = "Media: Description must be no more than " +  StringSize.DESCRIPTION_MAX + " characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION, message = "Media: Invalid symbols in description")
    private String description;
}
