package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.models.Message;
import nl.novi.eindopdracht.services.MessageService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    //MAPPINGS
    @GetMapping
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.ok().body(messageService.getMessages());
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Message>> getUnapprovedMessages() {
        return ResponseEntity.ok().body(messageService.getUnapprovedMessages());
    }

    @GetMapping("/buddies")
    public ResponseEntity<List<Message>> getMessagesForBuddies() {
        return ResponseEntity.ok().body(messageService.getBuddyMessages());
    }

    @GetMapping("/students")
    public ResponseEntity<List<Message>> getMessagesForStudents() {
        return ResponseEntity.ok().body(messageService.getStudentMessages());
    }

    @GetMapping("/both-roles")
    public ResponseEntity<List<Message>> getMessagesForBothRoles() {
        return ResponseEntity.ok().body(messageService.getMessagesForBothRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Long id) {
        return ResponseEntity.ok().body(messageService.getMessage(id));
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> addMessage(@RequestBody Message message) {
        messageService.addMessage(message);
        URI location = URI.create(message.getId().toString());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok().body("Bericht succesvol verwijderd");
    }

}
