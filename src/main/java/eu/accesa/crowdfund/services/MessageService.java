package eu.accesa.crowdfund.services;

import eu.accesa.crowdfund.model.Message;
import eu.accesa.crowdfund.repository.MessageRepository;
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
