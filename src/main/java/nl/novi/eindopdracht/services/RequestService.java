package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.RequestInputDto;
import nl.novi.eindopdracht.dtos.RequestOutputDto;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.models.Request;
import nl.novi.eindopdracht.models.Status;
import nl.novi.eindopdracht.repositories.ProfileRepository;
import nl.novi.eindopdracht.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final ProfileRepository profileRepository;

    public RequestService(RequestRepository requestRepository, ProfileRepository profileRepository) {
        this.requestRepository = requestRepository;
        this.profileRepository = profileRepository;
    }

    public Request addRequest(RequestInputDto requestDto) {
        Request newRequest = new Request();
        Optional<Profile> optionalSender = profileRepository.findById(requestDto.getSender());
        if (optionalSender.isEmpty()) {
            throw new RecordNotFoundException("De afzender van dit verzoek is niet bekend");
        }
        newRequest.setSender(optionalSender.get());
        Optional<Profile> optionalReceiver = profileRepository.findById(requestDto.getReceiver());
        if (optionalReceiver.isEmpty()) {
            throw new RecordNotFoundException("De ontvanger van dit verzoek is niet bekend");
        }
        newRequest.setReceiver(optionalReceiver.get());
        newRequest.setMessage(requestDto.getMessage());
        return requestRepository.save(newRequest);
    }

    public RequestOutputDto getByUsername(String username) {
        RequestOutputDto requestOutputDto = new RequestOutputDto();
        List<Request> pendingRequests = requestRepository.getAllBySenderUsernameAndStatusIs(username, Status.PENDING);
        pendingRequests.addAll(requestRepository.getAllByReceiverUsernameAndStatusIs(username, Status.PENDING));
        requestOutputDto.setPending(pendingRequests);
        List<Request> acceptedRequests = requestRepository.getAllBySenderUsernameAndStatusIs(username, Status.ACCEPTED);
        acceptedRequests.addAll(requestRepository.getAllByReceiverUsernameAndStatusIs(username, Status.ACCEPTED));
        requestOutputDto.setAccepted(acceptedRequests);
        requestOutputDto.setDeclined(requestRepository.getAllByReceiverUsernameAndStatusIs(username, Status.DECLINED));
        requestOutputDto.setCancelled(requestRepository.getAllBySenderUsernameAndStatusIs(username, Status.CANCELLED));
        return requestOutputDto;
    }


    public String updateRequestStatus(Long id, String status) {
        if (!requestRepository.existsById(id)) {
            throw new RecordNotFoundException("Dit verzoek is niet bekend");
        } else {
            Request request = requestRepository.findById(id).get();
            switch (status) {
                case ("accepted") -> request.setStatus(Status.ACCEPTED);
                case ("declined") -> request.setStatus(Status.DECLINED);
                case ("cancelled") -> request.setStatus(Status.CANCELLED);
            }
            requestRepository.save(request);
            return status;
        }
    }
}
