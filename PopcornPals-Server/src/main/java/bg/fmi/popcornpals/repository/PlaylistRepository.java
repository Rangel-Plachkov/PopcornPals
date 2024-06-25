package bg.fmi.popcornpals.repository;

import bg.fmi.popcornpals.model.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
    @Query(value = "SELECT * FROM playlist WHERE user_id = ?1",
            countQuery = "SELECT count(*) FROM playlist WHERE user_id = ?1",
            nativeQuery = true)
    List<Playlist> findAllByUser(Long userId);
    Page<Playlist> findByNameIgnoreCaseContaining(String name, Pageable pageable);
}
