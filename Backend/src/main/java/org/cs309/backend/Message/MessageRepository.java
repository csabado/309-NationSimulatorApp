package org.cs309.backend.Message;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *CRUD repository for the Messages table in the database
 *@author Joshua Kalyanapu
 */

public interface MessageRepository extends CrudRepository<Message, Long> {
    /**
     *Finds all rows in the Messages table with a matching senderId
     *@param senderId The senderId for which to search messages
     *@return A List of all rows in the Messages table with a matching senderId
     */
    List<Message> findBySenderid(Long senderId);

    /**
     *Finds all rows in the Messages table with a matching recipientId
     *@param recipientId The recipientId for which to search messages
     *@return A List of all rows in the Messages table with a matching recipientId
     */    
    List<Message> findByRecipientid(Long recipientId);
}
