package com.example.tabbedversion;

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
import com.example.tabbedversion.HelperClasses.volleyStatsRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Tab for users to create and remove alliances
 */
public class tab2 extends Fragment {
    RequestQueue queue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alliance_page,container,false);


    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button createBut = view.findViewById(R.id.Create);
        final Button addBut = view.findViewById(R.id.Add);
        final Button viewBut = view.findViewById(R.id.View);
        final Button removeBut = view.findViewById(R.id.Remove);
        final TextView errorPage = view.findViewById(R.id.errorPage);
        Network network = new BasicNetwork(new HurlStack());
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 1024 * 1024);
        queue = new RequestQueue(cache,network);
        queue.start();
        final volleyStatsRequest req = new volleyStatsRequest();
        /**
         * Creates alliance with user id
         */
        createBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final TextView createText1 = view.findViewById(R.id.IDCreate);
            queue.add(req.createRequest(Urls.allianceCreate,createText1,errorPage,"1"));

            }});
        /**
         * views Alliance by sending git Request
         */
        viewBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView viewID = view.findViewById(R.id.IDView);
                queue.add(req.allyRequest(Urls.alliances,viewID,errorPage,"1"));
            }
        });

        /**
         * Add players to alliance based on ID input
         */
        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView idAdd = view.findViewById(R.id.IDAdd);
                final TextView toAdd = view.findViewById(R.id.toAdd);
                StringRequest createAlliance = new StringRequest(Request.Method.POST, Urls.alliancesAdd, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorPage.setText(error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("toAdd",toAdd.getText().toString()+"");
                        params.put("id", idAdd.getText().toString() + "");

                        return params;
                    }
                };
                queue.add(createAlliance);
            }
        });

        /**
         * Removes player from alliance based on ID input
         */
        removeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView toRemove = view.findViewById(R.id.toRemove);
                final TextView viewText1 = view.findViewById(R.id.IDRemove);
                StringRequest viewFriends = new StringRequest(Request.Method.POST, Urls.alliancesRemove, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                            }

                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                errorPage.setText(error.toString());
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("id", viewText1.getText().toString()+"");
                        params.put("toRemove",toRemove.getText().toString()+"");

                        return params;
                    }
                };
                queue.add(viewFriends);
            }
        });
    }
}
