package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.DAO.ChatLogBoDao;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ChatLog;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/api/chatlog")
public class ChatLogController {
    @Autowired
    private ChatLogBoDao chatLogBoDao;

    @GetMapping("/get-chatlog-by-id")
    public ChatLog getChatLogById(@RequestParam String id) {
        return chatLogBoDao.getChatLog(Long.parseLong(id));
    }

    @PostMapping("/post-chatlog")
    public String postChatLog(@RequestParam ChatLog log) {
        chatLogBoDao.postChatLog(log);
        return "Chatlog with id: "+log.getId() + " saved.";
    }

    @DeleteMapping("/delete-chatlog")
    public String deleteChatLog(@RequestParam String id) {
        chatLogBoDao.deleteChatLog(chatLogBoDao.getChatLog(Long.parseLong(id)));
        return "ChatLog with id: " + id + " deleted.";
    }

    @PutMapping("/update-chatlog")
    public String updateChatLog(@RequestParam ChatLog log) {
        chatLogBoDao.updateChatLog(log);
        return "ChatLog with id: "+log.getId() +" updated.";
    }
}
