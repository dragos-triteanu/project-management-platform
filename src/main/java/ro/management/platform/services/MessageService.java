package ro.management.platform.services;

import ro.management.platform.model.Message;
import ro.management.platform.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
