package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.models.Request;
import nl.novi.eindopdracht.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> getAllBySenderUsernameAndStatusIs(String username, Status status);
    List<Request> getAllByReceiverUsernameAndStatusIs(String username, Status status);
}
