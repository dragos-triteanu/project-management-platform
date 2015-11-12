package ro.management.platform.controller.chat;

import ro.management.platform.model.entities.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ro.management.platform.model.entities.Message;
import ro.management.platform.repository.MessageRepository;
import ro.management.platform.repository.OrderRepository;
import ro.management.platform.services.MessageService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class ChatController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat")
    public void sendMessage(ChatMessage message) {
        message.setTimestamp(new Timestamp(new Date().getTime()));
        messageService.addMessage(message);
        template.convertAndSend("/topic/message/" + message.getOrderId(),message);
    }
}