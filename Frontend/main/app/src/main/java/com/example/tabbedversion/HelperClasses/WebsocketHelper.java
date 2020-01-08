package com.example.tabbedversion.HelperClasses;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.tabbedversion.MainActivity;
import com.example.tabbedversion.R;
import com.example.tabbedversion.tab1;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * method that connects to websocket
 */
public class WebsocketHelper  {
    WebSocketClient mWebSocketClient;
    Context context;
    Activity activity;
    public void websocketCall(URI uri, Context c,Activity a)
    {
        context=c;
        activity=a;
         mWebSocketClient = new WebSocketClient(uri,new Draft_17()) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                mWebSocketClient.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
            }

            @Override
            public void onMessage(final String message) {
                final String s = message;
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
                        NotificationChannel channel = new NotificationChannel("1", "n1", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager notificationManager = activity.getApplication().getSystemService(NotificationManager.class);
                        notificationManager.createNotificationChannel(channel);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "1")
                                .setSmallIcon(R.drawable.ic_help_outline_black_24dp)
                                .setContentTitle("notification")
                                .setContentText(message)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                        notificationManager.notify(1, builder.build());
                    }
                });

                Log.i("Message",s);
                /*new Handler().postDelayed(new Runnable() {
                    public void run() {
                        return;
                    }
                }, 5 * 1000);*/

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.i("Websocket", "Closed " + reason+code);
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
                Log.i("Websocket", "Error " + ex.getMessage());
            }
        };
        mWebSocketClient.connect();
    }
}
