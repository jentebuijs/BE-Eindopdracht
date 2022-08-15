package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.exceptions.NotAllowedException;
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
        return messageRepository.getAllByApprovedIsTrue();
    }

    public List<Message> getUnapprovedMessages() { return messageRepository.getAllByApprovedIsFalse(); }

    public Message getMessage(Long id) {
        Optional<Message> possibleMessage = messageRepository.findById(id);
        if (possibleMessage.isEmpty()) {
            throw new RecordNotFoundException("Dit bericht is niet bekend");
        } else {
            Message message = possibleMessage.get();
            if (!message.isApproved()) {
                throw new NotAllowedException("U bent niet bevoegd om dit bericht te lezen");
            } return message;
        }
    }

    public List<Message> getBuddyMessages() {
        return messageRepository.getAllByApprovedIsTrueAndForBuddyIsTrue();
    }

    public List<Message> getStudentMessages() {
        return messageRepository.getAllByApprovedIsTrueAndForStudentIsTrue();
    }

    public List<Message> getMessagesForBothRoles() {
        return messageRepository.getAllByApprovedIsTrueAndForStudentIsTrueAndForBuddyIsTrue();
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        boolean messageExists = messageRepository.existsById(id);
        if (!messageExists) {
            throw new RecordNotFoundException("Dit bericht is niet bekend");
        } messageRepository.deleteById(id);
        }

}
