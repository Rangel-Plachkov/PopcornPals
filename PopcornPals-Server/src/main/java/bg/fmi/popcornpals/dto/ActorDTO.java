package bg.fmi.popcornpals.dto;

import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.StringSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorDTO {
    private Long Id;

    @NotNull(message = "Actor: Name cannot be null")
    @NotBlank(message = "Actor: Name cannot be blank")
    @Size(max = StringSize.PERSON_NAME_MAX, message = "Actor: Name must be no more than " + StringSize.PERSON_NAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.PERSON_NAME, message = "Actor: Name can contain only letters, spaces, hyphens and apostrophes")
    private String name;

    @Size(max = StringSize.DESCRIPTION_MAX, message = "Actor: Description must be no more than " + StringSize.DESCRIPTION_MAX + " characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION, message = "Actor: Invalid symbols in description")
    private String description;
    private LocalDate birthdate;
}
