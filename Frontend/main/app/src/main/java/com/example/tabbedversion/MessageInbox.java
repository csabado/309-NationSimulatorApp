package com.example.tabbedversion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
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
import com.example.tabbedversion.HelperClasses.MessageHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * This Message class will allow the user to see all their messages that have been sent from other users
 * @author Chloe
 */
public class MessageInbox extends Activity implements MessageHelper {
    RequestQueue queue;
    String url ="http://coms-309-sr-7.misc.iastate.edu:8080/messages/inbox/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_inbox);
        final TextView messages = findViewById(R.id.tv_messages);
        Button refresh = findViewById(R.id.btn_refresh);
        Button reply = findViewById(R.id.btn_reply);

        try {
            URL url = new URL("google.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Network network = new BasicNetwork(new HurlStack());
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        queue = new RequestQueue(cache,network);
        queue.start();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMessages(messages);
            }
        });

        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MessageCompose.class));
            }
        });
    }
    /**
     * This method will send a GET request to the server
     * This will display messages the current user has
     *
     * @param t TextView box that will display messages
     */
    @Override
    public void getMessages(final TextView t) {
        JsonObjectRequest getInbox = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject message1 = (JSONObject) response.get("2");
                    String from1 = message1.getString("senderid");
                    String body1 = message1.getString("body");
                    String mail1 = "Monster Nation: " + body1 + "\n";

                    JSONObject message2 = (JSONObject) response.get("4");
                    String from2 = message2.getString("senderid");
                    String body2 = message2.getString("body");
                    String mail2 = "Monster Nation: " + body2 + "\n";

                    t.setText(mail1 + mail2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                t.setText(error.getMessage());
            }
        });
        queue.add(getInbox);
    }

    /**
     * This method will send a POST request to the server with a message to another user
     *
     * @param to  EditText that will contain the recipent
     * @param msg EditText that contains the message to be sent
     */
    @Override
    public void sendMessage(EditText to, EditText msg) {
    }
}
