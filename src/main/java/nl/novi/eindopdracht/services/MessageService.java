package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.models.Message;
import nl.novi.eindopdracht.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Message getMessage(Long id) {
        return messageRepository.getById(id);
    }

    public List<Message> getBuddyMessages() {
        return messageRepository.getMessagesByForBuddyIsTrue();
    }

    public List<Message> getStudentMessages() {
        return messageRepository.getMessagesByForStudentIsTrue();
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        Optional<Message> possibleMessage = messageRepository.findById(id);
        if (possibleMessage.isPresent()) {
            messageRepository.deleteById(id);
        } throw new RecordNotFoundException("Dit bericht is niet bekend");
    }

}
