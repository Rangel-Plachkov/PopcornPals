package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.PlaylistDTO;
import bg.fmi.popcornpals.dto.PlaylistRequestDTO;
import bg.fmi.popcornpals.dto.UserDTO;
import bg.fmi.popcornpals.exception.PlaylistNotFoundException;
import bg.fmi.popcornpals.exception.UserNotFoundException;
import bg.fmi.popcornpals.mapper.PlaylistMapper;
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
    private final PlaylistMapper playlistMapper;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository,
                           UserRepository userRepository,
                           PlaylistMapper playlistMapper) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.playlistMapper = playlistMapper;
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
        return playlistMapper.toDTOList(playlists.getContent());
    }

    public PlaylistDTO getPlaylistById(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(PlaylistNotFoundException::new);
        return playlistMapper.toDTO(playlist);
    }

    public PlaylistDTO createPlaylist(PlaylistRequestDTO playlistDTO) {
        User user = userRepository.findById(playlistDTO.getCreator())
                .orElseThrow(UserNotFoundException::new);

        Playlist playlist = new Playlist();
        playlist.setName(playlistDTO.getName());
        playlist.setCreator(user);
        Playlist newPlaylist = playlistRepository.save(playlist);
        return playlistMapper.toDTO(newPlaylist);
    }

    public PlaylistDTO updatePlaylist(Long playlistId, PlaylistRequestDTO playlistDTO) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(PlaylistNotFoundException::new);

        if(playlistDTO.getName() != null) {
            playlist.setName(playlistDTO.getName());
        }

        return playlistMapper.toDTO(playlistRepository.save(playlist));
    }

    public void deletePlaylist(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(PlaylistNotFoundException::new);
        playlistRepository.delete(playlist);
    }
}
