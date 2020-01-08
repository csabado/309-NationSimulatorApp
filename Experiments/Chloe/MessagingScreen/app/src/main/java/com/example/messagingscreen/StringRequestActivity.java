package com.example.messagingscreen;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.messagingscreen.app.AppController;
import com.example.messagingscreen.net_utils.Const;

import org.w3c.dom.Text;


public class StringRequestActivity extends Activity {



    private Button btnStringReq, btnReply;
    private EditText msgResponse, userID;
    private ProgressDialog pDialog;


    private String TAG = StringRequestActivity.class.getSimpleName();
    private Button enterBTN;
    //private EditText NN, TR, IP;


    // This tag will be used to cancel the request
    private String tag_string_req = "string_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.string_request);

        btnStringReq = (Button) findViewById(R.id.btn_sendMessage);
        btnReply = (Button) findViewById(R.id.btn_reply);
        userID = (EditText) findViewById(R.id.et_userID);
        msgResponse = (EditText) findViewById(R.id.et_message);


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        enterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeStringReq();
            }
        });

        btnReply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
    }

    private void openActivity(){
        Intent intent = new Intent( this, JsonRequestActivity.class);
        startActivity(intent);
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
     * */
    private void makeStringReq() {
        showProgressDialog();

        StringRequest strReq = new StringRequest(Request.Method.GET, Const.URL_STRING_REQ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                msgResponse.setText(response.toString());


                hideProgressDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
}
