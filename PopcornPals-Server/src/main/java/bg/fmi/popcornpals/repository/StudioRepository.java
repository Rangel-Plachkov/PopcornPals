package bg.fmi.popcornpals.repository;

import bg.fmi.popcornpals.model.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    List<Studio> findByNameIgnoreCaseContaining(String name);
}
