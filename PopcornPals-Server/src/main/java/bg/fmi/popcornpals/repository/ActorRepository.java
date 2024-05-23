package bg.fmi.popcornpals.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bg.fmi.popcornpals.model.Actor;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long>{
    List<Actor> findByNameIgnoreCaseContaining(String name);
}
