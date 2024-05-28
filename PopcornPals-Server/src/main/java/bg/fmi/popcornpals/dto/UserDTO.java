package bg.fmi.popcornpals.dto;

import bg.fmi.popcornpals.model.User;
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

    public static UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setID(user.getID());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setDescription(user.getDescription());
        userDTO.setBirthday(user.getBirthday());
        return userDTO;
    }
}
