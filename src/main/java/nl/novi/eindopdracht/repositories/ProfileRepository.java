package nl.novi.eindopdracht.repositories;
import nl.novi.eindopdracht.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
