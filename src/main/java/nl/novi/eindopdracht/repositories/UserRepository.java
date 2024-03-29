package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findUsersByAuthoritiesContaining(String userrole);
}
