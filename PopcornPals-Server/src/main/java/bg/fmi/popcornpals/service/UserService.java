package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.PlaylistDTO;
import bg.fmi.popcornpals.dto.UserDTO;
import bg.fmi.popcornpals.dto.UserRequestDTO;
import bg.fmi.popcornpals.exception.notfound.UserNotFoundException;
import bg.fmi.popcornpals.mapper.PlaylistMapper;
import bg.fmi.popcornpals.mapper.UserMapper;
import bg.fmi.popcornpals.model.User;
import bg.fmi.popcornpals.repository.PlaylistRepository;
import bg.fmi.popcornpals.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PlaylistRepository playlistRepository;
    private final UserMapper userMapper;
    private final PlaylistMapper playlistMapper;

    @Autowired
    public UserService(UserRepository userRepository, PlaylistRepository playlistRepository,
                       UserMapper userMapper, PlaylistMapper playlistMapper) {
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
        this.userMapper = userMapper;
        this.playlistMapper = playlistMapper;
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
        return userMapper.toDTOList(users.getContent());
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return userMapper.toDTO(user);
    }

    public UserDTO createUser(UserRequestDTO userRequestDTO) {
        User newUser = userRepository.save(userMapper.toEntity(userRequestDTO));
        return userMapper.toDTO(newUser);
    }

    public UserDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.setName(userRequestDTO.getName());
        user.setUsername(userRequestDTO.getUsername());
        user.setDescription(userRequestDTO.getDescription());
        user.setBirthday(userRequestDTO.getBirthday());

        return userMapper.toDTO(userRepository.save(user));
    }

    public void deleteUser(Long userId) {
        User toDelete = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(toDelete);
    }

    public List<PlaylistDTO> findPlaylistsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return playlistMapper.toDTOList(playlistRepository.findAllByUser(user.getID()));
    }
}
