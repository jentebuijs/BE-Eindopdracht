package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.models.Authority;
import nl.novi.eindopdracht.models.EAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority getByName(EAuthority name);
}
