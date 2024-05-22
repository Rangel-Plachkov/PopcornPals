package bg.fmi.popcornpals.repository;

import bg.fmi.popcornpals.model.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {
    default List<Studio> findByName(String name){
        return findAll().stream().filter(studio -> studio.getName().equals(name)).toList();
    }
}
