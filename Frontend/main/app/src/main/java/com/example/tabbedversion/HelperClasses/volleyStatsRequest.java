package com.example.tabbedversion.HelperClasses;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * class that implements volleyInterface for use in Activities
 */
public class volleyStatsRequest implements volleyInterface {

    /**
     *
     * @param url
     * @param money
     * @param militaryIndex
     * @param civilIndex
     * @param name
     * @param errorPage
     * @param id
     * @return Request that gets Index values
     */
    @Override
    public StringRequest statRequest(String url, final TextView money, final TextView militaryIndex, final TextView civilIndex, final TextView name, final TextView errorPage, final String id) {

        StringRequest req = new StringRequest(Request.Method.GET, url,new Listener<String>() {
            @Override
            public void onResponse(String response){
                try {

                    JSONParser parser = new JSONParser();
                    JSONObject stat = (JSONObject)parser.parse(response);
                    JSONObject nationState = (JSONObject)stat.get(id);
                    name.setText("1");
                    militaryIndex.setText(nationState.get("militaryIndex")+"");
                    money.setText(nationState.get("economicIndex")+"");
                    civilIndex.setText(nationState.get("civilUnrest")+"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorPage.setText(error.getMessage());
            }
        });
        return req;
    }

    /**
     *
     * @param url
     * @param population
     * @param errorPage
     * @param id
     * @return Request that gets Population of Nation
     */
    @Override
    public StringRequest popRequest(String url, final TextView population, final TextView errorPage, final String id) {
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONParser parser = new JSONParser();
                    JSONObject stat = (JSONObject)parser.parse(response);
                    JSONObject nationState = (JSONObject)stat.get(id);
                    population.setText("lowerClass:"+nationState.get("lowerclass")+" "+"middleClass:"+nationState.get("middleclass")+" "+"upperClass:"+nationState.get("upperclass"));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorPage.setText(error.getMessage());
            }

        });
        return req;
    }

    /**
     *
     * @param url
     * @param taxRate
     * @param errorPage
     * @param id
     * @return Request that gets Tax Rates
     */
    @Override
    public StringRequest taxRequest(String url, final TextView taxRate, final TextView errorPage, final String id) {
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONParser parser = new JSONParser();
                    JSONObject stat = (JSONObject)parser.parse(response);
                    JSONObject nationState = (JSONObject)stat.get(id);
                    taxRate.setText("lowerClass:"+nationState.get("lowerclass")+" "+"middleClass:"+nationState.get("middleclass")+" "+"upperClass:"+nationState.get("upperclass"));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorPage.setText(error.getMessage());
            }

        });
        return req;

    }

    /**
     *
     * @param url
     * @param govType
     * @param errorPage
     * @param id
     * @return Request that gets Government Type
     */
    @Override
    public StringRequest govRequest(String url, final TextView govType, final TextView errorPage, final String id) {
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONParser parser = new JSONParser();
                    JSONObject stat = (JSONObject)parser.parse(response);
                    JSONObject nationState = (JSONObject)stat.get(id);
                    govType.setText(nationState.get("type")+"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorPage.setText(error.getMessage());
            }

        });
        return req;
    }

    @Override
    public StringRequest allyRequest(String url, final TextView allianceAdd, final TextView errorPage, final String id) {
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONParser parser = new JSONParser();
                    JSONObject stat = (JSONObject)parser.parse(response);
                    JSONObject nationState = (JSONObject)stat.get(id);
                    allianceAdd.setText(nationState.get("Result")+"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorPage.setText(error.getMessage());
            }

        });
        return req;
    }

    @Override
    public StringRequest createRequest(String url, final TextView createAlliance, final TextView errorPage, final String id) {
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONParser parser = new JSONParser();
                    JSONObject stat = (JSONObject)parser.parse(response);
                    JSONObject nationState = (JSONObject)stat.get(id);
                    createAlliance.setText(nationState.get("create")+"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorPage.setText(error.getMessage());
            }

        });
        return req;
    }
}
