package bg.fmi.popcornpals.dto;

import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.StringSize;
import jakarta.validation.constraints.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private Long ID;

    @NotNull(message = "User: Username cannot be null")
    @NotBlank(message = "User: Username cannot be blank")
    @Size(min = StringSize.USERNAME_MIN, max = StringSize.USERNAME_MAX, message = "User: Username must be between " + StringSize.USERNAME_MIN + " and " + StringSize.USERNAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.USERNAME, message = "User: Username must begin with a letter")
    private String username;

    @NotNull(message = "User: Password cannot be null")
    @NotBlank(message = "User: Password cannot be blank")
    @Size(min = StringSize.PASSWORD_MIN, max = StringSize.PASSWORD_MAX, message = "User: Password must be between " + StringSize.PASSWORD_MIN + " and " + StringSize.PASSWORD_MAX  + " characters")
    @Pattern(regexp = RegexPattern.PASSWORD, message = "User: Password must contain at least one lowercase and uppercase letter, one digit and one special symbol")
    private String password;

    @NotNull(message = "User: Name cannot be null")
    @NotBlank(message = "User: Name cannot be blank")
    @Size(max = StringSize.PERSON_NAME_MAX, message = "User: Name must be no more than " + StringSize.PERSON_NAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.PERSON_NAME, message = "User: Name can contain only letters, spaces, hyphens and apostrophes")
    private String name;

    @NotBlank(message = "User: Email cannot be blank")
    @Email(message = "User: Email must be of valid email Id")
    private String email;

    @Size(max = StringSize.DESCRIPTION_MAX, message = "User: Description must be no more than " + StringSize.DESCRIPTION_MAX + " characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION, message = "User: Invalid symbols in description")
    private String description;

    private LocalDate birthday;
}
