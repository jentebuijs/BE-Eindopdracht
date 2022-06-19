package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
