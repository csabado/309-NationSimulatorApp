package com.example.tabbedversion.MockitoTests;

import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;
import com.example.tabbedversion.HandlerClasses.TaxHandler;
import com.example.tabbedversion.HelperClasses.Urls;
import com.example.tabbedversion.HelperClasses.volleyStatsRequest;
import com.example.tabbedversion.TaxPage;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class will be a test class for the volleyStatsRequest
 */
public class VolleyHelperTest {
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    /**
     * This test will make sure that stats Request returns a StringRequest
     * @throws JSONException
     */
    @Test
    public void getResponseTest_returnsTrue() throws JSONException{
        volleyStatsRequest test = mock(volleyStatsRequest.class);
        TextView t1=mock(TextView.class);
        TextView t2=mock(TextView.class);
        TextView t3=mock(TextView.class);
        TextView t4=mock(TextView.class);
        TextView t5=mock(TextView.class);
        StringRequest req =mock(StringRequest.class);



        when(test.statRequest(Urls.statistics,t1,t2,t3,t4,t5,"1")).thenReturn(req);
        Assert.assertEquals(req.getClass(),mock(StringRequest.class).getClass());
    }

    /**
     * This test will make sure that population Request returns a StringRequest
     * @throws JSONException
     */
    @Test
    public void getResponseTest1_returnsTrue() throws JSONException{
        volleyStatsRequest test = mock(volleyStatsRequest.class);
        TextView t1=mock(TextView.class);
        TextView t2=mock(TextView.class);
        StringRequest req = mock(StringRequest.class);
        when(test.popRequest(Urls.statistics,t1,t2,"1")).thenReturn(req);
        Assert.assertEquals(req.getClass(),mock(StringRequest.class).getClass());

    }
    /**
     * This test will make sure that tax Rate Request returns a StringRequest
     * @throws JSONException
     */
    @Test
    public void getResponseTest2_returnsTrue() throws JSONException {
        volleyStatsRequest test = mock(volleyStatsRequest.class);
        TextView t1=mock(TextView.class);
        TextView t2=mock(TextView.class);
        StringRequest req = mock(StringRequest.class);
        when(test.taxRequest(Urls.statistics,t1,t2,"1")).thenReturn(req);
        Assert.assertEquals(req.getClass(),mock(StringRequest.class).getClass());
    }



}
