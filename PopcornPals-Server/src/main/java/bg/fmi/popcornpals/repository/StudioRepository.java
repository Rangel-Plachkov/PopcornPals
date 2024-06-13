package bg.fmi.popcornpals.repository;

import bg.fmi.popcornpals.model.Studio;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    Page<Studio> findByNameIgnoreCaseContaining(String name, Pageable page);
}
