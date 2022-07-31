package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getAllByApprovedIsTrue();
    List<Message> getAllByApprovedIsFalse();
    List<Message> getAllByApprovedIsTrueAndForBuddyIsTrue();
    List<Message> getAllByApprovedIsTrueAndForStudentIsTrue();
    List<Message> getAllByApprovedIsTrueAndForStudentIsTrueAndForBuddyIsTrue();
}
