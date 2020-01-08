package com.example.demo3;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.demo3.app.AppController;
import com.example.demo3.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class JsonRequestActivity extends Activity implements View.OnClickListener {

    private String TAG = JsonRequestActivity.class.getSimpleName();
    private Button btnsend;
    private EditText messagetosend, userID;
    private TextView tvresponse;
    private ProgressDialog pDialog;
    private RequestQueue queue;
    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_request);

        btnsend = (Button) findViewById(R.id.btn_sendMessage);
        userID = (EditText) findViewById(R.id.et_userID);
        messagetosend = (EditText) findViewById(R.id.et_message);
        tvresponse = (TextView) findViewById(R.id.tv_message);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjReq();
            }
        });
       // btnsend.setOnClickListener(makeJsonObjReq());
        //btnJsonArray.setOnClickListener(this);
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

    /**
     * Making json object request
     */
    private void makeJsonObjReq() {
        showProgressDialog();
        Network network = new BasicNetwork(new HurlStack());
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        queue = new RequestQueue(cache,network);
        queue.start();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.URL_JSON_OBJECT,null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        messagetosend.setText(response.toString());
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("senderId", "1");
                params.put("recipient_nation", "SLEEPY");
                params.put("body", messagetosend.toString());

                return params;
            }

        };

        // Adding request to request queue
        queue.add(jsonObjReq);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendMessage:
                makeJsonObjReq();
                break;
            //case R.id.btnJsonArray:
            //  makeJsonArryReq();
            // break;
        }

    }

}
