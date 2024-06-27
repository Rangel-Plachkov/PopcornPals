package bg.fmi.popcornpals.repository;

import bg.fmi.popcornpals.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Page<User> findByNameIgnoreCaseContaining(String name, Pageable pageable);
    Page<User> findByUsernameIgnoreCaseContaining(String username, Pageable pageable);
}
