package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ChatLog;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/api/chatlog")
public class ChatLogController {
    @Autowired
    ChatLogRepository chatLogRepository;

    @GetMapping("/get-chatlog-by-id")
    public ChatLog getChatLogById(@RequestParam String id) {
        return chatLogRepository.findChatLogById(Long.parseLong(id));
    }

    @PostMapping("/post-chatlog")
    public String postChatLog(@RequestParam ChatLog log) {
        chatLogRepository.save(log);
        return "Chatlog with id: "+log.getId() + " saved.";
    }

    @DeleteMapping("/delete-chatlog")
    public String deleteChatLog(@RequestParam String id) {
        chatLogRepository.deleteById(Long.parseLong(id));
        return "ChatLog with id: " + id + " deleted.";
    }

    @PutMapping("/update-chatlog")
    public String updateChatLog(@RequestParam ChatLog log) {
        for (ChatLog chatLog : chatLogRepository.findAll()) {
            if (chatLog.getId() == log.getId()) {
                chatLog.setId(log.getId());
                chatLog.setEvidence(log.getEvidence());
                chatLog.setId(log.getId());
            }
        }
        return "ChatLog with id: "+log.getId() +" updated.";
    }
}
