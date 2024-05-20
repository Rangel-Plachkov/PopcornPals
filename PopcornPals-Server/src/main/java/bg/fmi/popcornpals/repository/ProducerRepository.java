package bg.fmi.popcornpals.repository;

import bg.fmi.popcornpals.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long>{
}
