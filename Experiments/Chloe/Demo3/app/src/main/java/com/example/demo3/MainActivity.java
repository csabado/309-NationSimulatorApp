package com.example.demo3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import com.android.volley.toolbox.StringRequest;

import com.example.demo3.app.AppController;
import com.example.demo3.net_utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

        private Button btnStringReq, btnReply;
        private EditText userID;
        private TextView msgResponse;
        private ProgressDialog pDialog;

        private RequestQueue queue;
        private String TAG = MainActivity.class.getSimpleName();
        private Button enterBTN;


        // This tag will be used to cancel the request
        private String tag_string_req = "string_req";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            btnStringReq = (Button) findViewById(R.id.btn_sendMessage);
            btnReply = (Button) findViewById(R.id.btn_reply);
            userID = (EditText) findViewById(R.id.et_userID);
            msgResponse = (TextView) findViewById(R.id.et_message);


            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);

            btnStringReq.setOnClickListener(new View.OnClickListener() {
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
            Network network = new BasicNetwork(new HurlStack());
            Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
            queue = new RequestQueue(cache,network);
            queue.start();

            StringRequest strReq = new StringRequest(Request.Method.GET, Const.URL_STRING_REQ, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                        Log.d(TAG, response.toString());
                        //JSONObject message = new JSONObject(response.toString());
                        //String msg = message.getString("recipientId");
                        msgResponse.setText(response.toString());
                        hideProgressDialog();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    msgResponse.setText(error.toString());
                    hideProgressDialog();
                }
            });

            // Adding request to request queue
            queue.add(strReq);

        }



        public boolean trySending(String userID, String msg, MessageHandler msgHandler) {
            try{
                if(msgHandler.getResponse(userID,msg).getBoolean("messageSuccess")) {
                    return true;
                }
                return false;
            }catch (JSONException e){

            }

            return false;
        }

    }


