package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.UserDTO;
import bg.fmi.popcornpals.model.User;
import bg.fmi.popcornpals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return mapToDTO(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User newUser = userRepository.save(mapToEntity(userDTO));
        return mapToDTO(newUser);
    }

    private UserDTO mapToDTO(User user) {
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

    private User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setDescription(userDTO.getDescription());
        user.setBirthday(userDTO.getBirthday());
        return user;
    }
}
