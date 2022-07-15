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

    //MAPPINGS: getAll, getById, post, put, delete

    @GetMapping
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.ok().body(messageService.getMessages());
    }

    @GetMapping("/buddies")
    public ResponseEntity<List<Message>> getMessagesForBuddies() {
        return ResponseEntity.ok().body(messageService.getBuddyMessages());
    }

    @GetMapping("/students")
    public ResponseEntity<List<Message>> getMessagesForStudents() {
        return ResponseEntity.ok().body(messageService.getStudentMessages());
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
    public ResponseEntity<HttpStatus> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateMessage(@PathVariable Long id, @RequestBody Message message) {
        messageService.updateMessage(id, message);
        return ResponseEntity.ok().build();
    }
}
