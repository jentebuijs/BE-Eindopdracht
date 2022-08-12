package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.RequestDto;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.models.Request;
import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.repositories.RequestRepository;
import nl.novi.eindopdracht.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    public RequestService(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public Request addRequest(RequestDto requestDto) {
        Request newRequest = new Request();
        Optional<User> optionalSender = userRepository.findById(requestDto.getSender());
        if(optionalSender.isEmpty()) {
            throw new RecordNotFoundException("De afzender van dit verzoek is niet bekend");
        } newRequest.setSender(optionalSender.get());
        Optional<User> optionalRecipient = userRepository.findById(requestDto.getReceiver());
        if(optionalRecipient.isEmpty()) {
            throw new RecordNotFoundException("De ontvanger van dit verzoek is niet bekend");
        } newRequest.setReceiver(optionalRecipient.get());
        newRequest.setMessage(requestDto.getMessage());
        return requestRepository.save(newRequest);
    }

    public List<Request> getByUsername(String username) {
        List<Request> outgoingRequests = requestRepository.getAllBySenderUsername(username);
        List<Request> incomingRequests = requestRepository.getAllByReceiverUsername(username);
        List<Request> totalRequests = new ArrayList<>();
        totalRequests.addAll(outgoingRequests);
        totalRequests.addAll(incomingRequests);
        return totalRequests;
    }
}
