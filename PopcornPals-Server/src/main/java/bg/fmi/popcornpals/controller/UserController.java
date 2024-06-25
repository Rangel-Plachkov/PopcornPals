package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.PlaylistDTO;
import bg.fmi.popcornpals.dto.UserDTO;
import bg.fmi.popcornpals.dto.UserRequestDTO;
import bg.fmi.popcornpals.service.UserService;
import bg.fmi.popcornpals.util.PaginationProperties;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping(path = "api/users/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(
            @RequestParam(value = "pageNo", defaultValue = PaginationProperties.DEFAULT_PAGE_NO, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationProperties.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "username", required = false) String username) {
        return new ResponseEntity<>(userService.getUsers(pageNo, pageSize, name, username), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.createUser(userRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long userId,
                                              @RequestBody @Valid UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.updateUser(userId, userRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}/playlists")
    public ResponseEntity<List<PlaylistDTO>> getPlaylists(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(userService.findPlaylistsByUser(userId), HttpStatus.OK);
    }
}
