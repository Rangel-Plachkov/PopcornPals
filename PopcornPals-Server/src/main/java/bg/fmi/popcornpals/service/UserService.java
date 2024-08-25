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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PlaylistMapper playlistMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper, PlaylistMapper playlistMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.playlistMapper = playlistMapper;
    }

    public Page<UserDTO> getUsers(Integer pageNo, Integer pageSize, String name, String username) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> users = null;
        if(name != null && !name.isBlank()) {
            users = userRepository.findByNameIgnoreCaseContaining(name, pageable);
        }
        else if(username != null && !username.isBlank()) {
            users = userRepository.findByUsernameIgnoreCaseContaining(username, pageable);
        }
        else {
            users = userRepository.findAll(pageable);
        }
        log.info("Found " + users.getTotalElements() + " users");
        return users.map(user -> userMapper.toDTO(user));
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        log.info("Found user with id: " + userId);
        return userMapper.toDTO(user);
    }

    public UserDTO createUser(UserRequestDTO userRequestDTO) {
        User newUser = userRepository.save(userMapper.toEntity(userRequestDTO));
        log.info("User with id {} was created", newUser.getID());
        return userMapper.toDTO(newUser);
    }

    public UserDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.setName(userRequestDTO.getName());
        user.setUsername(userRequestDTO.getUsername());
        user.setDescription(userRequestDTO.getDescription());
        user.setBirthday(userRequestDTO.getBirthday());
        log.info("User with id {} was updated", user.getID());
        return userMapper.toDTO(userRepository.save(user));
    }

    public void deleteUser(Long userId) {
        User toDelete = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        log.info("User with id {} was deleted", toDelete.getID());
        userRepository.delete(toDelete);
    }

    public Page<PlaylistDTO> findPlaylistsByUser(Long userId, Integer pageNo, Integer pageSize) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<PlaylistDTO> playlistList = playlistMapper.toDTOList(user.getPlaylists());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), playlistList.size());
        List<PlaylistDTO> pageContent = playlistList.subList(start, end);
        log.info("Found {} playlists for user with id {}", playlistList.size(), userId);
        return new PageImpl<>(pageContent, pageable, playlistList.size());
    }
}
