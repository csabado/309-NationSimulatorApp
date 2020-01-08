package com.example.tabbedversion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import com.example.tabbedversion.HelperClasses.MessageHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * This Message class will allow the user to compose a message to another nation
 * @author Chloe
 */
public class MessageCompose extends Activity implements MessageHelper {
    RequestQueue queue;
    Button send;
    private String url ="http://coms-309-sr-7.misc.iastate.edu:8080/messages/submit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_compose);

        final EditText recipient = findViewById(R.id.et_name);
        final EditText message = findViewById(R.id.et_message);
        send = findViewById(R.id.btn_send);


        Network network = new BasicNetwork(new HurlStack());
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        queue = new RequestQueue(cache,network);
        queue.start();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(recipient, message);
            }
        });
    }

    /**
     * This method will send a POST request to the server with a message to another user
     *
     * @param to  EditText that will contain the recipent
     * @param msg EditText that contains the message to be sent
     */
    @Override
    public void sendMessage(final EditText to, final EditText msg) {
        StringRequest sendmsg = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                msg.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                msg.setText(error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("senderId", "1");
                params.put("recipientId", "2");
                params.put("body", msg.getText().toString());

                return params;
            }
        };
        queue.add(sendmsg);
        Log.d(msg.getText().toString(), "sendMessage: ");
    }
    /**
     * This method will send a GET request to the server
     * This will display messages the current user has
     *
     * @param t TextView box that will display messages
     */
    @Override
    public void getMessages(TextView t) {

    }


}
