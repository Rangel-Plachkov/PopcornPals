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

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long ID;

    @NotNull(message = "User: Username cannot be null")
    @NotBlank(message = "User: Username cannot be blank")
    @Size(min = StringSize.USERNAME_MIN, max = StringSize.USERNAME_MAX, message = "User: Username must be between " + StringSize.USERNAME_MIN + " and " + StringSize.USERNAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.USERNAME, message = "User: Username must begin with a letter")
    private String username;

    @NotNull(message = "User: Name cannot be null")
    @NotBlank(message = "User: Name cannot be blank")
    @Size(max = StringSize.PERSON_NAME_MAX, message = "User: Name must be no more than " + StringSize.PERSON_NAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.PERSON_NAME, message = "User: Name can contain only letters, spaces, hyphens and apostrophes")
    private String name;

    @Size(max = StringSize.DESCRIPTION_MAX, message = "User: Description must be no more than " + StringSize.DESCRIPTION_MAX + " characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION, message = "User: Invalid symbols in description")
    private String description;

    private LocalDate birthday;
}
