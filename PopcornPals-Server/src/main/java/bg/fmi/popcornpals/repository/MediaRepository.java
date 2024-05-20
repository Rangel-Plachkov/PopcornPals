package bg.fmi.popcornpals.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bg.fmi.popcornpals.model.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long>{
}
