package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getAllUsersByIsStudentIsTrue();
    List<User> getAllUsersByIsStudentIsFalse();

}
