package com.example.tabbedversion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.example.tabbedversion.HelperClasses.Urls;
import com.example.tabbedversion.HelperClasses.WebsocketHelper;
import com.example.tabbedversion.HelperClasses.volleyStatsRequest;

import org.java_websocket.client.WebSocketClient;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Stat Tab that displays current Nation's Statistics
 */
public class tab1 extends Fragment {
    private static final String TAG =MainActivity.class.getName();
    private Button bigButton;
    private Button button2;
    private Button button3;
    private Button inbox;
    private Button compose;
    RequestQueue queue;
    private WebSocketClient mWebSocketClient;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.stat_page,container,false);
    }
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState)  {
        super.onViewCreated(view, savedInstanceState);

        Network network = new BasicNetwork(new HurlStack());
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 1024 * 1024);
        queue = new RequestQueue(cache,network);
        queue.start();
        final TextView name = view.findViewById(R.id.nameID);
        final TextView population = view.findViewById(R.id.population);
        final TextView money = view.findViewById(R.id.money);
        final TextView taxRate = view.findViewById(R.id.taxRate);
        final TextView civilIndex = view.findViewById(R.id.unrest);
        final TextView militaryIndex = view.findViewById(R.id.mIndex);
        final TextView errorPage = view.findViewById(R.id.error);
        final TextView govType = view.findViewById(R.id.govType);

        compose= view.findViewById(R.id.compose);
        compose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),MessageCompose.class);
                startActivity(intent);
            }
        });
        inbox = view.findViewById(R.id.inbox);
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),MessageInbox.class);
                startActivity(intent);
            }
        });
        button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Uses volley helper class to make request for stat values
                 */
                volleyStatsRequest req1 = new volleyStatsRequest();
                queue.add(req1.statRequest(Urls.statistics, militaryIndex,money,civilIndex,name,errorPage,"1"));
                queue.add(req1.popRequest(Urls.population,population,errorPage,"1"));
                queue.add(req1.taxRequest(Urls.taxes,taxRate,errorPage,"1"));
                queue.add(req1.govRequest(Urls.government,govType,errorPage,"1"));


            }
        });

        URI uri;
        try
        {
            uri = new URI(Urls.websocket);

        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
            return;
        }
        WebsocketHelper ws = new WebsocketHelper();
        ws.websocketCall(uri,getActivity().getApplicationContext(),getActivity());

            }


    }


