package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.models.Authority;
import nl.novi.eindopdracht.models.EAuthority;
import nl.novi.eindopdracht.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority getByName(EAuthority name);
}
