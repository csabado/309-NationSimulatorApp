package com.example.tabbedversion.HelperClasses;

import android.widget.EditText;
import android.widget.TextView;

/**
 * This MessageHelper interface is to be used in both of the messages activites
 */
public interface MessageHelper {

    /**
     * This method will send a POST request to the server with a message to another user
     * @param to EditText that will contain the recipent
     * @param msg EditText that contains the message to be sent
     */
    void sendMessage(EditText to, EditText msg);

    /**
     * This method will send a GET request to the server
     * This will display messages the current user has
     * @param t TextView box that will display messages
     */
    void getMessages(TextView t);
}
