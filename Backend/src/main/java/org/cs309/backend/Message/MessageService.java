package org.cs309.backend.Message;
import java.util.List;

/**
 *Interface for the Message service
 *@author Joshua Kalyanapu
 */

public interface MessageService {

    /**
     *Obtains all rows of Message from the database and returns them as a List
     *@return a List of all Message
     */
    public List<Message> getAll();

    /**
     *Obtains a row of Message from the database by id
     *@param id The id for which to search
     *@return a Message object representing the relevant Message in the database
     */    
    public List<Message> getMessagesByRecipientid(Long id);

    /**
     *Obtains a row of Message from the database by senderId
     *@param senderId The senderId for which to search
     *@return a Message object representing the relevant Message in the database
     */    
    public List<Message> getMessagesBySenderid(Long id);

    /**
     *Obtains a row of Message from the database by recipientId
     *@param recipientId The recipientId for which to search
     *@return a Message object representing the relevant Message in the database
     */        
    public boolean sendMessage(Long senderId, Long recipientId, String body);
}
