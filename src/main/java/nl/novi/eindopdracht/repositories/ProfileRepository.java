package nl.novi.eindopdracht.repositories;
import nl.novi.eindopdracht.models.EAuthority;
import nl.novi.eindopdracht.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String> {
    List<Profile> getProfilesByRoleIsNot(EAuthority role);
}
