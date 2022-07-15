package nl.novi.eindopdracht.repositories;
import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> getProfilesByIsStudentIsFalse();
    List<Profile> getProfilesByIsStudentIsTrue();
}
