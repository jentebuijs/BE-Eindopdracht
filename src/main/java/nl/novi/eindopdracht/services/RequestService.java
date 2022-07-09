package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.repositories.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }
}
