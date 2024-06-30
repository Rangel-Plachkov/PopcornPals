package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.MediaDTO;
import bg.fmi.popcornpals.dto.PlaylistDTO;
import bg.fmi.popcornpals.dto.PlaylistRequestDTO;
import bg.fmi.popcornpals.service.PlaylistService;
import bg.fmi.popcornpals.util.PaginationProperties;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

@Slf4j
@RestController
@RequestMapping(path = "api/playlists/")
public class PlaylistController {
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<Page<PlaylistDTO>> getPlaylists(
            @RequestParam(value = "pageNo", defaultValue = PaginationProperties.DEFAULT_PAGE_NO, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationProperties.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "name", required = false) String name) {
        return new ResponseEntity<>(playlistService.getPlaylists(pageNo, pageSize, name), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PlaylistDTO> getPlaylist(@PathVariable("id") Long playlistId) {
        return new ResponseEntity<>(playlistService.getPlaylistById(playlistId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody @Valid PlaylistRequestDTO playlistRequestDTO) {
        return new ResponseEntity<>(playlistService.createPlaylist(playlistRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<PlaylistDTO> updatePlaylist(@PathVariable("id") Long playlistId,
                                                      @RequestBody @Valid PlaylistRequestDTO playlistRequestDTO) {
        return new ResponseEntity<>(playlistService.updatePlaylist(playlistId, playlistRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable("id") Long playlistId) {
        playlistService.deletePlaylist(playlistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}/media/")
    public ResponseEntity<Page<MediaDTO>> getContent(
            @PathVariable("id") Long playlistId,
            @RequestParam(value = "pageNo", defaultValue = PaginationProperties.DEFAULT_PAGE_NO, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationProperties.DEFAULT_PAGE_SIZE, required = false) Integer pageSize) {
        Page<MediaDTO> content = playlistService.getContent(playlistId, pageNo, pageSize);
        if(content.isEmpty()) {
            return new ResponseEntity<>(content, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(content, HttpStatus.OK);
    }
}
