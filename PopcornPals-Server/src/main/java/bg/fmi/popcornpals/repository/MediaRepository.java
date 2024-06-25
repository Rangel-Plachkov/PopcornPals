package bg.fmi.popcornpals.repository;

import bg.fmi.popcornpals.util.MediaType;
import bg.fmi.popcornpals.util.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bg.fmi.popcornpals.model.Media;
import org.springframework.data.domain.Page;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long>{

    Page<Media> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Media> findByType(MediaType type, Pageable pageable);
    Page<Media> findByGenre(Genre genre, Pageable pageable);


}
