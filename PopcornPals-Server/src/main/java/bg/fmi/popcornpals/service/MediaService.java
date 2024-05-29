package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.model.Media;
import bg.fmi.popcornpals.repository.MediaRepository;
import bg.fmi.popcornpals.util.MediaType;
import bg.fmi.popcornpals.util.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {
    @Autowired
    private MediaRepository mediaRepository;

    public Media createMedia(Media media) {
        return mediaRepository.save(media);
    }

    public List<Media> getMedia(Long ID, String title, MediaType type, Genre genre) {
        if(ID != null) {
            Media media = mediaRepository.findById(ID).orElse(null);
            return media == null ? null : List.of(media);
        } else if (title != null && !title.isEmpty()) {
            return mediaRepository.findByTitleContainingIgnoreCase(title);
        } else if (type != null) {
            return mediaRepository.findByType(type);
        } else if (genre != null) {
            return mediaRepository.findByGenre(genre);
        } else {
            return mediaRepository.findAll();
        }
    }
    public Media updateMedia(Media media) {
        return mediaRepository.save(media);
    }
    public void deleteMediaById(Long id) {
        mediaRepository.deleteById(id);
    }
    public void deleteAllMedia() {
        mediaRepository.deleteAll();
    }

}
