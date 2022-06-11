package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
    User getUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    User getUserByEmail(String email);
    List<User> getAllUsersByIsStudentIsTrue();
    List<User> getAllUsersByIsStudentIsFalse();

}
