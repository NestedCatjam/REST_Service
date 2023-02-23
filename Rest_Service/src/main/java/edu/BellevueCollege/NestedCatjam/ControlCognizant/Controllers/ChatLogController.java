package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ChatLog;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/chatlog")
public class ChatLogController {
    @Autowired
    private ChatLogRepository repository;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ChatLog> getChatLogById(@PathVariable Long id) {
        ChatLog chatLog = repository.findById(id).orElse(null);
        if (chatLog == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(chatLog);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ChatLog> postChatLog(@Valid @RequestBody ChatLog log) {
        ChatLog savedChatLog = repository.save(log);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedChatLog);
    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<String> deleteChatLog(@PathVariable Long id) {
//        if (repository.existsById(id)) {
//            repository.deleteById(id);
//            return ResponseEntity.ok("ChatLog with id: " + id + " deleted.");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<ChatLog> updateChatLog(@PathVariable Long id, @Valid @RequestBody ChatLog log) {
//        ChatLog existingChatLog = repository.findById(id).orElse(null);
//        if (existingChatLog == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            log.setId(id);
//
//            ChatLog updatedChatLog = repository.save(log);
//            return ResponseEntity.ok(updatedChatLog);
//        }
//    }
}
