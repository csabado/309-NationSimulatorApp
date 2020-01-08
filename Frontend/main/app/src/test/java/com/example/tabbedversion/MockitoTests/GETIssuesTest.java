package com.example.tabbedversion.MockitoTests;

import org.json.JSONException;
import org.json.JSONObject;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import com.example.tabbedversion.HandlerClasses.IssueHandler;
import com.example.tabbedversion.issues_logic;

/**
 * This class will be a test class for the Issue page to ensure the Issues being received are correct
 * @author Chloe
 */
public class GETIssuesTest {
   @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

   /**
    * This Test ensure that the responses are not null
    * @throws JSONException
    */
   @Test
   public void getResponseTest_returnsTrue() throws JSONException{
      IssueHandler test = mock(IssueHandler.class);
      issues_logic testissue = new issues_logic();

      String issue = "Issue";
      String optiona = "option a";
      String optionb = "option b";

      JSONObject response = new JSONObject();

      response.put("issueSuccess", new Boolean(true));

      when(test.getResponse(issue,optiona,optionb)).thenReturn(response);
      Assert.assertEquals(testissue.tryIssues(issue, optiona, optionb, test), response.getBoolean("issueSuccess"));
   }


   /**
    * This Test will get to see if the responses will catch if nothing is returned
    * @throws JSONException
    */
   @Test
   public void getResponseTest_returnsFalse() throws JSONException{
      IssueHandler test = mock(IssueHandler.class);
      issues_logic testissue = new issues_logic();

      String issue = null;
      String optiona = null;
      String optionb = null;

      JSONObject response = new JSONObject();
      response.put("issueSuccess", new Boolean(true));
      when(test.getResponse(issue,optiona, optionb)).thenReturn(response);
      Assert.assertEquals(testissue.tryIssues(issue, optiona, optionb,test),response.getBoolean("issueSuccess"));
   }

}

