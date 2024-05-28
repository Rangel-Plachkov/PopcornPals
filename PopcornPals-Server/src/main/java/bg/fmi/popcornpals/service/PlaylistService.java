package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.PlaylistDTO;
import bg.fmi.popcornpals.dto.PlaylistRequestDTO;
import bg.fmi.popcornpals.dto.UserDTO;
import bg.fmi.popcornpals.model.Playlist;
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
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository,
                           UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }

    public List<PlaylistDTO> getPlaylists(Integer pageNo, Integer pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Playlist> playlists = null;
        if(name != null) {
            playlists = playlistRepository.findByNameIgnoreCaseContaining(name, pageable);
        }
        else {
            playlists = playlistRepository.findAll(pageable);
        }
        return playlists.getContent().stream().map(playlist -> PlaylistDTO.mapToDTO(playlist)).collect(Collectors.toList());
    }

    public PlaylistDTO getPlaylistById(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow();
        return PlaylistDTO.mapToDTO(playlist);
    }

    public PlaylistDTO createPlaylist(PlaylistRequestDTO playlistDTO) {
        User user = userRepository.findById(playlistDTO.getCreator()).orElseThrow();
        Playlist playlist = new Playlist();
        playlist.setName(playlistDTO.getName());
        playlist.setCreator(user);
        Playlist newPlaylist = playlistRepository.save(playlist);
        return mapToDTO(newPlaylist);
    }

    public PlaylistDTO updatePlaylist(Long playlistId, PlaylistRequestDTO playlistDTO) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow();

        if(playlistDTO.getName() != null) {
            playlist.setName(playlistDTO.getName());
        }
        // update the content list?

        return PlaylistDTO.mapToDTO(playlistRepository.save(playlist));
    }

    public void deletePlaylist(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow();
        playlistRepository.delete(playlist);
    }

    private PlaylistDTO mapToDTO(Playlist playlist) {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setID(playlist.getID());
        playlistDTO.setName(playlist.getName());
        playlistDTO.setCreator(UserDTO.mapToDTO(playlist.getCreator()));
        return playlistDTO;
    }
}
