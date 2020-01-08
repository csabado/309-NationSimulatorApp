package org.cs309.backend.Message;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

/**
 *Implements MessageService.java
 *@author Joshua Kalyanapu
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    
    @Override
    public List<Message> getAll() {
	ArrayList<Message> messages = (ArrayList)messageRepository.findAll();
	return messages;
    }

    @Override
    public List<Message> getMessagesByRecipientid(Long id) {
	ArrayList<Message> messages = (ArrayList)messageRepository.findByRecipientid(id);
	return messages;
    }

    @Override    
    public List<Message> getMessagesBySenderid(Long id) {
	ArrayList<Message> messages = (ArrayList)messageRepository.findBySenderid(id);
	return messages;
    }

    @Override    
    public boolean sendMessage(Long senderId, Long recipientId, String body) {
	Message m = new Message();
	m.setSenderid(senderId);
	m.setRecipientid(recipientId);
	m.setBody(body);
	messageRepository.save(m);
	return true;
    }
}
