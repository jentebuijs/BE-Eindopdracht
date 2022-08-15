package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.RequestInputDto;
import nl.novi.eindopdracht.dtos.RequestOutputDto;
import nl.novi.eindopdracht.models.Request;
import nl.novi.eindopdracht.services.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/requests")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    //MAPPINGS
    @PostMapping("/new")
    public ResponseEntity<String> makeRequest(@RequestBody RequestInputDto requestDto) {
        Request newRequest = requestService.addRequest(requestDto);
        URI location = URI.create(newRequest.getId().toString());
        return ResponseEntity.created(location).body("Het verzoek is verstuurd");
    }

    @GetMapping("/{username}")
    public ResponseEntity<RequestOutputDto> getRequests(@PathVariable String username) {
        return ResponseEntity.ok().body(requestService.getByUsername(username));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRequest(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok().body(requestService.updateRequestStatus(id, status));
    }

}
