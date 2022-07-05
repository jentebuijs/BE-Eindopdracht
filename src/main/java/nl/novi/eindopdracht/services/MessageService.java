package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.models.Message;

import java.util.List;

public interface MessageService {

    List<Message> getMessages();
    Message getMessage(Long id);
    void addMessage(Message message);
    void deleteMessage(Long id);
}
