package edu.BellevueCollege.NestedCatjam.ControlCognizant.BO;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Chatlog;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/api/chatlog")
public class ChatLogOperations {
    @Autowired
    protected ChatLogRepository chatLogRepository;

    @PostMapping("/postChatLog")
    public String postChatLog(Chatlog chatlog) {
        chatLogRepository.save(chatlog);
        return ("saved chatlog with id: " + chatlog.getId());
    }

    @GetMapping("/getChatLogById")
    public String getChatLogById(String id) {
        return chatLogRepository.findById(Long.valueOf(id)).toString();
    }

    @DeleteMapping("/deleteChatLogById")
    public String deleteChatLogById(String id) {
        chatLogRepository.deleteById(Long.valueOf(id));
        return ("Deleted chatlog with id: " + id);
    }

    @PutMapping("/updateChatLogById")
    public String updateChatLogById(String id, Chatlog chatlog) {
        for(Chatlog c : chatLogRepository.findAll()) {
            if (c.getId() == Long.parseLong(id)) {
                c.setPosts(chatlog.getPosts());
                c.setEvidence(chatlog.getEvidence());
                c.setId(chatlog.getId());
                chatLogRepository.save(c);
                return ("Updated chatlog with id: " + id);
            }
        }
        return ("Chatlog with id: " + id + " not found");
    }
}
