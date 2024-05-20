package bg.fmi.popcornpals.repository;

import bg.fmi.popcornpals.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
}
