package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.models.Message;
import nl.novi.eindopdracht.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessage(Long id) {
        return messageRepository.getById(id);
    }

    @Override
    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Long id) {
        Optional<Message> possibleMessage = messageRepository.findById(id);
        if (possibleMessage.isPresent()) {
            messageRepository.deleteById(id);
        } throw new RecordNotFoundException("Dit bericht is niet bekend");
    }
}
