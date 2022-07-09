package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

}
