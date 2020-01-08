package com.example.issuespage;

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
import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Change URLS
 */

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    String optionSelected = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton refresh = findViewById(R.id.btn_refresh);
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
        final TextView issue = (TextView) findViewById(R.id.tv_IssueDisplay2);
        final TextView optionA = (TextView) findViewById(R.id.tv_A);
        final TextView optionB = (TextView) findViewById(R.id.tv_B);

        TextView error = (TextView) findViewById(R.id.tv_optionError);

        /**
        * Sends a GET request to request the issue and options from the server and displays them.
        */
        refresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String tag_json_obj = "json_obj_req";
                JSONObject decision = new JSONObject();
                //issue - /issues
                //optionA - /issues/A
                //optionB - /issues/B

                //Request Issue
                String urlIssue = "http://coms-309-sr-7.misc.iastate.edu:8080/issues";
                String url = "http://10.26.0.185:8080/issues/";
                JsonObjectRequest reqIssue = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String str = response.toString();
                            String rString = str.substring(19, str.length() - 1);
                            JSONObject issueDecision = new JSONObject((response.toString()));
                            String i = issueDecision.getString("Issue");
                            issue.setText(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        issue.setText(error.getMessage());
                    }
                });
                queue.add(reqIssue);

                //Request Option A
                String urlA = "http://coms-309-sr-7.misc.iastate.edu:8080/issues/A";
                String urlTestA = "http://10.26.0.185:8080/issues/A";
                JsonObjectRequest reqA = new JsonObjectRequest(Request.Method.GET, urlTestA, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String str = response.toString();
                            String rString = str.substring(19, str.length() - 1);
                            JSONObject issueDecision = new JSONObject((response.toString()));
                            String a = issueDecision.getString("A");
                            optionA.setText(a);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        issue.setText(error.getMessage());
                    }
                });
                queue.add(reqA);

                //Request Option B
                String urlB = "http://coms-309-sr-7.misc.iastate.edu:8080/issues/B";
                String urlTestB = "http://10.26.0.185:8080/issues/B";
                JsonObjectRequest reqB = new JsonObjectRequest(Request.Method.GET, urlTestB, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String str = response.toString();
                            String rString = str.substring(19, str.length() - 1);
                            JSONObject issueDecision = new JSONObject((response.toString()));
                            optionB.setText(issueDecision.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        issue.setText(error.getMessage());
                    }
                });
                queue.add(reqB);
            }
        });

        /**
         * Sends a POST request of the option selected to the Server
         */
        //Checks that both option A and B are not selected
//        if(checkA.isChecked() && checkB.isChecked()){
//            submit.setEnabled(false);
//            error.setText("Select only one option");
//        }else{
            submit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    final CheckBox checkA = (CheckBox) findViewById(R.id.cb_A);
                    final CheckBox checkB = (CheckBox) findViewById(R.id.cb_B);
                    //final EditText option = (EditText) findViewById(R.id.optionsel);
                    if(checkA.isChecked()){
                        optionSelected = "A";
                    }else{
                        optionSelected = "B";
                    }
                    String urlAnswer = "http://coms-309-sr-7.misc.iastate.edu:8080/issues/answer";
                    String urlOptionTest = "http://10.26.0.185:8080/issues/answer";
                    final TextView ans = (TextView) findViewById(R.id.tv_optionError);
                    StringRequest sendOptionSelection = new StringRequest(Request.Method.POST, urlOptionTest, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            ans.setText(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            issue.setText(error.toString());
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("Option", optionSelected);
                            return params;
                        }
                    };
                    queue.add(sendOptionSelection);
                }
            });
    }
}
