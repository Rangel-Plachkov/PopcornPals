package bg.fmi.popcornpals.repository;

import bg.fmi.popcornpals.util.MediaType;
import bg.fmi.popcornpals.util.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import bg.fmi.popcornpals.model.Media;
import bg.fmi.popcornpals.model.Studio;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long>{

    Page<Media> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Media> findByType(MediaType type, Pageable pageable);
    Page<Media> findByGenre(Genre genre, Pageable pageable);

    Page<Media> findByStudio(Studio studio, Pageable pageable);


}
