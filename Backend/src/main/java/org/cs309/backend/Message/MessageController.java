package org.cs309.backend.Message;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;
import java.util.Iterator;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 *The controller for messages operations. Accessible via /messages/*
 *@author Joshua Kalyanapu
 */

@Controller
@RequestMapping(path="/messages")
public class MessageController {
    private MessageService messageService;

    /**
     *Used by Spring's dependency injection to inject the relevant MessageService into the class.
     *Only called internally by Spring
     *@param govService A MessageService to use for various requests to the database
     */
    @Autowired
    public void setMessageService(MessageService messageService) {
	this.messageService = messageService;
    }
    
    /**
     *Gets the inbox for a particular user. Accessible via /messages/inbox/*id*
     *@param recipientId The id of the recipient for which to obtain the mailbox
     *@return a JSON representation of the user's inbox. The key is the message id and the value is a JSON object consisting of the Message's sender id, recipient id, and time. 
     */
    @GetMapping(path="/inbox/{recipientId}")
    public @ResponseBody String getRecipientMessages(@PathVariable("recipientId") Long recipientId) {
	ArrayList<Message> al = (ArrayList)messageService.getMessagesByRecipientid(recipientId);
	String toReturn = new String();
	toReturn += "{";
	for (Message m : al) {
	    toReturn += (m + ", ");
	}
	toReturn = toReturn.substring(0, toReturn.length()-2); //eliminate the last comma and space after
	toReturn += "}";
	return toReturn;
    }

    /**
     *Gets all messages in the database. Used for debugging purposes, will be deleted in release. Accessiblt via /messages/all
     *@return a JSON representation of all messages in the database. 
     */

    @GetMapping(path="/all")
    public @ResponseBody String getAll() {
	ArrayList<Message> al = (ArrayList)messageService.getAll();
	String toReturn = new String();
	toReturn += "{";
	for (Message m : al) {
	    toReturn += (m + ", ");
	}
	toReturn = toReturn.substring(0, toReturn.length()-2); //eliminate the last comma and space after
	toReturn += "}";
	return toReturn;
    }

    /**
     *Gets a user's sent messages. Accessible via /messages/sent/*id* 
     *@param id The user for whom to get sent messages
     *@return A JSON representation of the user's sent messages
     */
    @GetMapping(path="/sent/{senderid}")
    public @ResponseBody String getBySenderid(@PathVariable("senderid") Long id) {
	ArrayList<Message> al = (ArrayList)messageService.getMessagesBySenderid(id);
	String toReturn = new String();
	toReturn += "{";
	for (Message m : al) {
	    toReturn += (m + ", ");
	}
	toReturn = toReturn.substring(0, toReturn.length()-2); //eliminate the last comma and space after
	toReturn += "}";
	return toReturn;
    }

    /**
     *Submits a new message to a user. Must be a POST request sent to /messages/submit
     *@param senderId The ID for the sender of the message
     *@param recipientName The name of the recipient of the message
     *@param body The body of the message
     */
    @PostMapping(path="/submit")
    public @ResponseBody String submitMessage(@RequestParam("senderId") String senderId,
					      @RequestParam("recipientName") String recipientName,
					      @RequestParam("body") String body)
    {
	//TODO implement this function (can't do it yet because Account hasn't been reimplemented
	return "{\"Error\": \"Not Implemented\"}";
    }
}
