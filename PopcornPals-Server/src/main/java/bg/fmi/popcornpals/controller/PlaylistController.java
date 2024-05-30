package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.MediaDTO;
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
        PlaylistDTO playlistDTO = playlistService.getPlaylistById(playlistId);
        return playlistDTO == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(playlistDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody @Valid PlaylistRequestDTO playlistRequestDTO) {
        PlaylistDTO playlistDTO = playlistService.createPlaylist(playlistRequestDTO);
        return playlistDTO == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(playlistDTO, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<PlaylistDTO> updatePlaylist(@PathVariable("id") Long playlistId,
                                                      @RequestBody @Valid PlaylistRequestDTO playlistRequestDTO) {
        PlaylistDTO updatedPlaylistDTO = playlistService.updatePlaylist(playlistId, playlistRequestDTO);
        return updatedPlaylistDTO == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(updatedPlaylistDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable("id") Long playlistId) {
        PlaylistDTO playlistDTO = playlistService.getPlaylistById(playlistId);
        if(playlistDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playlistService.deletePlaylist(playlistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}/media/")
    public ResponseEntity<List<MediaDTO>> getContent(@PathVariable("id") Long playlistId) {
        List<MediaDTO> content = playlistService.getContent(playlistId);
        return content == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(content, HttpStatus.OK);
    }
}
