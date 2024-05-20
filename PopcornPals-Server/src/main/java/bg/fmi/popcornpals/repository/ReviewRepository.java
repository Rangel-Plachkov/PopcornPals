package bg.fmi.popcornpals.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bg.fmi.popcornpals.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
}
