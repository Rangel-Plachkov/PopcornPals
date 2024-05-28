package bg.fmi.popcornpals.repository;

import bg.fmi.popcornpals.model.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long>{
    Page<Producer> findByNameIgnoreCaseContaining(String name, Pageable pageable);
}
