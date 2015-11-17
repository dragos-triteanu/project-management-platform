package ro.management.platform.services;

import ro.management.platform.model.dto.ChatMessage;
import ro.management.platform.model.entities.Message;
import ro.management.platform.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dragos on 10/7/2015.
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessages(int orderId) {

        List<Message> messages = messageRepository.getMessages(orderId);

        return messages;
    }

    public List<ChatMessage> getChatMessages(int orderId) {

        List<Message> messages = messageRepository.getMessages(orderId);
        List<ChatMessage> chatMessages = new ArrayList<>();
        for(Message message : messages){
            ChatMessage chatMessage = toChatMessage(message);
            chatMessages.add(chatMessage);
        }

        return chatMessages;
    }

    private ChatMessage toChatMessage(Message message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(message.getContent());
        chatMessage.setFrom(message.getSender());
        chatMessage.setOrderId(message.getOrder().getOrderId());
        chatMessage.setTimestamp(new Date(message.getTimestamp().getTime()));
        chatMessage.setLocation(message.getLocation());
        chatMessage.setFileName(message.getFileName());
        return chatMessage;
    }


    public void addMessage(final ChatMessage message){
        messageRepository.convertAndAddChatMessageAsMessage(message);
    }
}
