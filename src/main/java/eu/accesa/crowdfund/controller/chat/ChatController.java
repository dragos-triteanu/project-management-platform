package eu.accesa.crowdfund.controller.chat;

import eu.accesa.crowdfund.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class ChatController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(value = "/flap" , method = RequestMethod.GET)
    public String viewApplication() {
        return "index";
    }

    @MessageMapping("/chat")
    public void sendMessage(ChatMessage message) {
        message.setDate(new Date());
        logger.info("Message sent to " + message.getTo());
        template.convertAndSend("/topic/message/" + message.getTo(),message);
    }
}