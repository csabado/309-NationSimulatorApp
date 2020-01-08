package com.example.militarypurchasing;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Change URLS
 */

public class purchasing extends AppCompatActivity {
    RequestQueue queue;
    String optionSelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button submit = findViewById(R.id.btn_submit);
        try {
            URL url = new URL("google.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Network network = new BasicNetwork(new HurlStack());
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        queue = new RequestQueue(cache,network);
        queue.start();

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               EditText infantry = (EditText) findViewById(R.id.et_infantry);
               EditText airforce = (EditText) findViewById(R.id.et_airforce);
               EditText navy = (EditText) findViewById(R.id.et_navy);
               final TextView errormsg = (TextView) findViewById(R.id.tv_error);
               final TextView currentMilitary = (TextView) findViewById(R.id.tv_currentMilitary);

                final String i = infantry.getText().toString();
                final String a = airforce.getText().toString();
                final String n = navy.getText().toString();

                String urlMilitary = "http://coms-309-sr-7.misc.iastate.edu:8080/military/id/1";
                JsonObjectRequest reqMilitary = new JsonObjectRequest(Request.Method.GET, urlMilitary, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String str = response.toString();
                            String rString = str.substring(19, str.length() - 1);
                            JSONObject militaryres = new JSONObject((response.toString()));
                            String infantryres = militaryres.getString("infantry");
                            String airforceres = militaryres.getString("airforce");
                            String navyres = militaryres.getString("navy");
                            currentMilitary.setText("Infantry: " + infantryres + "\n" + "Air Force: " + airforceres + "\n" + "Navy: " + navyres);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errormsg.setText(error.getMessage());
                    }
                });
                queue.add(reqMilitary);


                String url = "http://coms-309-sr-7.misc.iastate.edu:8080/military/purchase";
                StringRequest sendMilitaryPurchase = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //errormsg.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            errormsg.setText(error.getMessage());
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("id", "1");
                        params.put("infantry", i);
                        params.put("airforce", a);
                        params.put("navy", n);
                        return params;
                    }
                };
                queue.add(sendMilitaryPurchase);
            }
        });
    }
}
