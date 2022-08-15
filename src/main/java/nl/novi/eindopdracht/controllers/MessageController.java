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
    public ResponseEntity<?> getMessages(@RequestParam(value = "type", required = false) Optional<String> type) {
        if (type.isPresent()) {
            switch (type.get()) {
                case "admin" -> {
                    return ResponseEntity.ok().body(messageService.getUnapprovedMessages());
                }
                case "buddies" -> {
                    return ResponseEntity.ok().body(messageService.getBuddyMessages());
                }
                case "students" -> {
                    return ResponseEntity.ok().body(messageService.getStudentMessages());
                }
                case "both" -> {
                    return ResponseEntity.ok().body(messageService.getMessagesForBothRoles());
                }
            }
        }
        return ResponseEntity.ok().body(messageService.getMessages());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Long id) {
        return ResponseEntity.ok().body(messageService.getMessage(id));
    }

    @PostMapping("/new")
    public ResponseEntity<String> addMessage(@RequestBody Message message) {
        messageService.addMessage(message);
        URI location = URI.create(message.getId().toString());
        return ResponseEntity.created(location).body("Het bericht met id " + message.getId() + " is toegevoegd");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok().body("Bericht succesvol verwijderd");
    }

}
