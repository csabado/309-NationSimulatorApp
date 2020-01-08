package com.example.demo3;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;





public class MainActivityTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void getResponseTest_returnsTrue() throws JSONException{
        //This creates a Mock Object of the class that we have not fully implemented
        MessageHandler test = mock(MessageHandler.class);
        MainActivity testMessageSent = new MainActivity();
//These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String userIdCorrect = "1";
        String messageCorrect = "Hello";
// The response from the server, that represents if it matches what is expected
        JSONObject response = new JSONObject();
/*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        response.put("messageSuccess", new Boolean(true));
// This specifies behavior of getResponse from MessageHandler, its like overriding the behavior, forcing the specific value
        //we force this unimplemented method to return our predefine variable response
        when(test.getResponse(userIdCorrect,messageCorrect)).thenReturn(response);
        Assert.assertEquals(testMessageSent.trySending(userIdCorrect,messageCorrect,test),response.getBoolean("messageSuccess"));

    }
    @Test
    public void getResponseTest_returnsFalse() throws JSONException{
        MessageHandler test = mock(MessageHandler.class);
        MainActivity testMessageSent = new MainActivity();

        String userIDfail = "Joe";
        String messagefail = "";

        JSONObject response = new JSONObject();

        response.put("messageSuccess", new Boolean(false));
        when(test.getResponse(userIDfail,messagefail)).thenReturn(response);
        Assert.assertEquals(testMessageSent.trySending(userIDfail,messagefail,test),response.getBoolean("messageSuccess"));
    }
}
