//package edu.BellevueCollege.NestedCatjam.ControlCognizant.DAO;
//
//import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ChatLog;
//import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.ChatLogRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ChatLogBoDao {
//    @Autowired
//    private ChatLogRepository chatLogBoRepository;
//
//    public ChatLog getChatLog(long id) {
//        return chatLogBoRepository.findChatLogById(id);
//    }
//
//    public String postChatLog(ChatLog chatLog) {
//        chatLogBoRepository.save(chatLog);
//        return "Chatlog with id: "+chatLog.getId() + " saved.";
//    }
//
//    public String deleteChatLog(ChatLog chatLog) {
//        chatLogBoRepository.deleteById(chatLog.getId());
//        return "ChatLog with id: " + chatLog.getId() + " deleted.";
//    }
//
//    public String updateChatLog(ChatLog chatLog) {
//        for (ChatLog chatLog1 : chatLogBoRepository.findAll()) {
//            if (chatLog1.getId() == chatLog.getId()) {
//                chatLog1.setId(chatLog.getId());
//                chatLog1.setEvidence(chatLog.getEvidence());
//                chatLog1.setId(chatLog.getId());
//            }
//        }
//        return "ChatLog with id: "+chatLog.getId() +" updated.";
//    }
//}
