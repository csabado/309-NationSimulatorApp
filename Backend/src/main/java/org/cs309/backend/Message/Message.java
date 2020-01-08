package org.cs309.backend.Message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 *The Message object
 *@author Joshua Kalyanapu
 */

@Entity
@Table(name="messages")
public class Message {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="senderid")
    private long senderid;

    @Column(name="recipientid")
    private long recipientid;

    @CreationTimestamp
    @Column(name="time")
    private Timestamp time;

    @Column(name="body")
    private String body;

    /**
     *Constructor for the message. Used when creating an object for a new row in the database. 
     *@param id The id Message entry
     *@param senderId The id of the sender of the message
     *@param recipientId The id of the recipient of the message
     *@param time The timestamp for the message
     *@param body The body of the message
     */
    public Message(long id, long senderId, long recipientId, Timestamp time, String body) {
	this.id = id;
	this.senderid = senderId;
	this.recipientid = recipientId;
	this.body = body;
    }

    /**
     *Constructor for the message. Used when creating an object for a preexisting row in the database as id and
     *time are automatically set
     *@param senderId The id of the sender
     *@param recipientId The id of the recipient
     *@param body The body of the message
     */
    public Message(long senderId, long recipientId, String body) {
	this.senderid = senderId;
	this.recipientid = recipientId;
	this.body = body;
    }

    /**
     *Empty constructor for Message. Used so that the program would compile during development, will eventually 
     *be removed
     */
    public Message() {}

    /**
     *Gets the ID for this Message object
     *@return The id for this Message object
     */
    long getId() {
	return id;
    }

    /**
     *Gets the ID for the sender for this message object
     *@return The id for the sender for this Message object
     */
    long getSenderid() {
	return senderid;
    }

    /**
     *Gets the ID for the recipient for this message object
     *@return The id for the recipient for this Message object
     */
    long getRecipientid() {
	return recipientid;
    }

    /**
     *Gets the timestamp for this Message object
     *@return The timestamp for this Message object
     */
    Timestamp getTime() {
	return time;
    }

    /**
     *Gets the body for this Message object
     *@return The body for this Message object
     */
    String getBody() {
	return body;
    }

    /**
     *Sets the ID for this Message object
     *@param id The id for this Message object
     */
    void setId(long id) {
	this.id = id;
    }

    /**
     *Sets the ID for the sender for this Message object
     *@param senderId The ID for the sender for this Message object
     */
    void setSenderid(long senderId) {
	this.senderid = senderId;
    }

    /**
     *Sets the ID for the recipient for this Message object
     *@param recipientId The ID for the recipient for this Message object
     */
    void setRecipientid(long recipientId) {
	this.recipientid = recipientId;
    }

    /**
     *Sets the time for this Message object
     *@param time The time for this Message object
     */
    void setTime(Timestamp time) {
	this.time = time;
    }

    /**
     *Sets the body for this Message object
     *@param body The body for this Message object
     */
    void setBody(String body) {
	this.body = body;
    }

    /**
     *Returns a JSON representation without the outer braces (to make usage of this function in the controllers easier).
     *To convert this to a proper JSON String, add a "{" and "}" to the beginning and end of the String
     *e.g With a Message variable called g, a proper JSON String would be '{' + g.toString() + '}'
     *@return A partial JSON representation of the Message object, without the outer braces. 
     */
    @Override
    public String toString() {
	String toReturn = new String();
	toReturn += "\"" + this.id + "\": ";
	toReturn += "{";
	toReturn += "\"senderid\": \"" + this.senderid + "\", " ;
	toReturn += "\"recipientid\": \"" + this.recipientid + "\", ";
	toReturn += "\"time\": \"" + this.time + "\", ";
	toReturn += "\"body\": \"" + this.body + "\"";
	toReturn += "}";
	return toReturn;	
    }
}
