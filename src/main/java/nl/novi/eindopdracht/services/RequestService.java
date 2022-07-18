package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.models.Request;
import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request addRequest(User sender, User recipient, String message) {
        Request newRequest = new Request(sender, recipient, message);
        return requestRepository.save(newRequest);
    }

    public List<Request> getBySender(Long id) {
        return requestRepository.getAllBySenderId(id);
    }

    public List<Request> getByRecipient(Long id) {
        return requestRepository.getAllByRecipientId(id);
    }

    public void deleteRequestsByUser(Long id) {
        requestRepository.deleteAllByRecipientId(id);
        requestRepository.deleteAllBySenderId(id);
    }
}
