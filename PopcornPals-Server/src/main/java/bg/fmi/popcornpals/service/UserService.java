package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.PlaylistDTO;
import bg.fmi.popcornpals.dto.UserDTO;
import bg.fmi.popcornpals.model.User;
import bg.fmi.popcornpals.repository.PlaylistRepository;
import bg.fmi.popcornpals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PlaylistRepository playlistRepository;

    @Autowired
    public UserService(UserRepository userRepository, PlaylistRepository playlistRepository) {
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
    }

    public List<UserDTO> getUsers(Integer pageNo, Integer pageSize, String name, String username) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> users = null;
        if(name != null) {
            users = userRepository.findByNameIgnoreCaseContaining(name, pageable);
        }
        else if(username != null) {
            users = userRepository.findByUsernameIgnoreCaseContaining(username, pageable);
        }
        else {
            users = userRepository.findAll(pageable);
        }
        return users.getContent().stream().map(u -> mapToDTO(u)).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return mapToDTO(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User newUser = userRepository.save(mapToEntity(userDTO));
        return mapToDTO(newUser);
    }

    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow();

        if(userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if(userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }
        if(userDTO.getDescription() != null) {
            user.setDescription(userDTO.getDescription());
        }
        if(userDTO.getBirthday() != null) {
            user.setBirthday(userDTO.getBirthday());
        }
        return mapToDTO(userRepository.save(user));
    }

    public void deleteUser(Long userId) {
        User toDelete = userRepository.findById(userId).orElseThrow();
        userRepository.delete(toDelete);
    }

    public List<PlaylistDTO> findPlaylistsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return playlistRepository.findAllByUser(user.getID()).stream().map(p -> PlaylistDTO.mapToDTO(p)).collect(Collectors.toList());
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
