package com.example.tabbedversion.MockitoTests;

import com.example.tabbedversion.HandlerClasses.MilitaryHandler;
import com.example.tabbedversion.purchasing;

import org.json.JSONException;
import org.json.JSONObject;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.junit.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * This class will be a test class for the Military purchasing page to ensure the responses being sent are correct
 * @author Chloe
 */
public class GETMilitaryTest {
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    /**
     * This test will make sure that the strings being sent are strings that contain nothing but numbers
     * @throws JSONException
     */
    @Test
    public void getResponseTest_returnsTrue() throws JSONException{
        MilitaryHandler test = mock(MilitaryHandler.class);
        purchasing testpurchase = new purchasing();

        String infantry = "10";
        String airforce = "3";
        String navy = "5";

        JSONObject response = new JSONObject();

        response.put("purchaseSuccess", new Boolean(true));

        when(test.getResponse(infantry,airforce,navy)).thenReturn(response);
        Assert.assertEquals(testpurchase.tryPurchase(infantry, airforce, navy, test), response.getBoolean("purchaseSuccess"));
    }

    /**
     * This test will check that the responses being give will catch if the strings contain things other than numbers
     * @throws JSONException
     */
    @Test
    public void getResponseTest_returnsFalse() throws JSONException{
        MilitaryHandler test = mock(MilitaryHandler.class);
        purchasing testpurchase = new purchasing();

        String infantry = "seven";
        String airforce = "ise";
        String navy = null;

        JSONObject response = new JSONObject();

        response.put("purchaseSuccess", new Boolean(true));
        when(test.getResponse(infantry,airforce, navy)).thenReturn(response);
        Assert.assertEquals(testpurchase.tryPurchase(infantry, airforce, navy,test),response.getBoolean("purchaseSuccess"));
    }
}
