package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.RequestDto;
import nl.novi.eindopdracht.models.Request;
import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    //MAPPINGS
    @PostMapping("/new")
    public ResponseEntity<String> makeRequest(@RequestBody RequestDto requestDto) {
        Request newRequest = requestService.addRequest(requestDto);
        URI location = URI.create(newRequest.getId().toString());
        return ResponseEntity.created(location).body("Het verzoek is verstuurd");
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Request>> getRequests(@PathVariable String username) {
        return ResponseEntity.ok().body(requestService.getByUsername(username));
    }

//    @GetMapping("/outgoing")
//    public ResponseEntity<List<Request>> getOutgoingRequests(@RequestParam String username) {
//        return ResponseEntity.ok().body(requestService.getBySenderUsername(username));
//    }
//
//    @GetMapping("/incoming")
//    public ResponseEntity<List<Request>> getIncomingRequests(@RequestParam String username) {
//        return ResponseEntity.ok().body(requestService.getByReceiverUsername(username));
//    }

}
