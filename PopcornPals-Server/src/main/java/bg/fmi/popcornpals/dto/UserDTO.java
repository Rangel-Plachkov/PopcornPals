package bg.fmi.popcornpals.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long ID;
    private String username;
    private String password;
    private String name;
    private String email;
    private String description;
    private LocalDate birthday;
}
