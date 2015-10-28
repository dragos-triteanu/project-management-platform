package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/chat")
public class ChatController {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @MessageMapping("/conversation")
  @SendTo("/topic/message")
  public ChatMessage sendMessage(ChatMessage message) {
    logger.info("Message sent");
    return message;
  }
}