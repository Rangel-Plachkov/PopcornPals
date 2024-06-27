package bg.fmi.popcornpals.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import bg.fmi.popcornpals.model.Media;
import bg.fmi.popcornpals.model.User;
import bg.fmi.popcornpals.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    Page<Review> findByMedia(Media media, Pageable pageable);
    Page<Review> findByCreator(User creator, Pageable pageable);
    Page<Review> findByMediaAndCreator(Media media, User creator, Pageable pageable);

}
