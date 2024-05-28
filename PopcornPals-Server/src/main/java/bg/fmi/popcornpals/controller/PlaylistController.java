package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.PlaylistDTO;
import bg.fmi.popcornpals.dto.PlaylistRequestDTO;
import bg.fmi.popcornpals.service.PlaylistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/playlists/")
public class PlaylistController {
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<List<PlaylistDTO>> getPlaylists(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
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
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
