package bg.fmi.popcornpals.dto;

import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.StringSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerRequestDTO {
    private Long ID;

    @NotNull(message = "Producer: Name cannot be null")
    @NotBlank(message = "Producer: Name cannot be blank")
    @Size(max = StringSize.PERSON_NAME_MAX, message = "Producer: Name must be no more than " + StringSize.PERSON_NAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.PERSON_NAME, message = "Producer: Name can contain only letters, spaces, hyphens and apostrophes")
    private String name;

    @Size(max = StringSize.DESCRIPTION_MAX, message = "Producer: Description must be no more than " + StringSize.DESCRIPTION_MAX + " characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION, message = "Producer: Invalid symbols in description")
    private String description;
    private LocalDate birthdate;
    private List<Long> producedMedia;
}
