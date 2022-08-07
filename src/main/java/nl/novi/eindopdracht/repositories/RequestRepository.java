package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> getAllBySenderUsername(String username);
    List<Request> getAllByRecipientUsername(String username);
}
