package com.example.tabbedversion.MockitoTests;

import com.example.tabbedversion.HandlerClasses.TaxHandler;
import com.example.tabbedversion.TaxPage;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class will be a test class for the Tax changing page to ensure the responses being sent are correct
 * @author Chloe
 */
public class TaxTest {
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    /**
     * This test will make sure that the strings being sent are strings that contain nothing but numbers
     * @throws JSONException
     */
    @Test
    public void getResponseTest_returnsTrue() throws JSONException{
        TaxHandler test = mock(TaxHandler.class);
        TaxPage testpurchase = new TaxPage();

        String lower = "10";
        String middle = "3";
        String upper = "5";

        JSONObject response = new JSONObject();

        response.put("purchaseSuccess", new Boolean(true));

        when(test.getResponse(lower,middle,upper, test)).thenReturn(response);
        Assert.assertEquals(testpurchase.tryTax(lower, middle, upper, test), response.getBoolean("taxSuccess"));
    }

    /**
     * This test will check that the responses being give will catch if the strings contain things other than numbers
     * @throws JSONException
     */
    @Test
    public void getResponseTest_returnsFalse() throws JSONException{
        TaxHandler test = mock(TaxHandler.class);
        TaxPage testpurchase = new TaxPage();

        String lower = "seven";
        String middle = "ise";
        String upper = null;

        JSONObject response = new JSONObject();

        response.put("purchaseSuccess", new Boolean(true));
        when(test.getResponse(lower, middle, upper,test)).thenReturn(response);
        Assert.assertEquals(testpurchase.tryTax(lower,middle,upper,test),response.getBoolean("taxSuccess"));
    }
}
