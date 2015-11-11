package ro.management.platform.controller.chat;

import ro.management.platform.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/chat")
    public void sendMessage(ChatMessage message) {
        message.setDate(new Date());
        template.convertAndSend("/topic/message/" + message.getOrderId(),message);
    }
}