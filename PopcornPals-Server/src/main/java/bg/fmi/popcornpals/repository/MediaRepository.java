package bg.fmi.popcornpals.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bg.fmi.popcornpals.model.Media;
import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long>{

    default List<Media> findByTitle(String title){
        return findAll().stream().filter(media -> media.getTitle().equals(title)).toList();
    }
}
