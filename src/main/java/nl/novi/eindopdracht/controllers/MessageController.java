package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.models.Message;
import nl.novi.eindopdracht.services.MessageService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    //MAPPINGS
    @GetMapping
    public ResponseEntity<?> getMessages(@RequestParam(value = "type", required = false) Optional<String> type,
                                         @RequestParam(value = "id", required = false) Optional<Long> id) {
        if (id.isPresent()) {
            return ResponseEntity.ok().body(messageService.getMessage(id.get()));
        } else if (type.isPresent() && type.get().equals("admin")) {
            return ResponseEntity.ok().body(messageService.getUnapprovedMessages());
        }
        return ResponseEntity.ok().body(messageService.getApprovedMessages());
    }

    @PostMapping("/new")
    public ResponseEntity<String> addMessage(@RequestBody Message message) {
        messageService.addMessage(message);
        URI location = URI.create(message.getId().toString());
        return ResponseEntity.created(location).body("Het bericht met id " + message.getId() + " is toegevoegd");
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateMessage(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok().body(messageService.updateMessageStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok().body("Bericht succesvol verwijderd");
    }

}
