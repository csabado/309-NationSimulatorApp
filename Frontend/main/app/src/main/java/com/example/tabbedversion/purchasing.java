package com.example.tabbedversion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

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
import com.example.tabbedversion.HandlerClasses.MilitaryHandler;
import com.example.tabbedversion.HelperClasses.MilitaryPurchasingHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;


/**
 * This class will handle all Military purchases and will allow the user to see their current military status
 * They have the ability to purchase infantry, air force, and navy
 * @author Chloe Sabado
 */
public class purchasing extends Fragment implements MilitaryPurchasingHelper {
    RequestQueue queue;
    private String urlCurrentMilitary = "http://coms-309-sr-7.misc.iastate.edu:8080/military/1";
    private String urlSendMilitary = "http://coms-309-sr-7.misc.iastate.edu:8080/military/change";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.purchasing_xml,container,false);
    }


    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final EditText infantry = view.findViewById(R.id.et_infantry);
        final EditText airforce = view.findViewById(R.id.et_airforce);
        final EditText navy = view.findViewById(R.id.et_navy);
        final TextView errormsg = view.findViewById(R.id.tv_error);
        final TextView currentMilitary = view.findViewById(R.id.tv_currentMilitary);

        Button submit = view.findViewById(R.id.btn_submit);
        ImageButton refresh = view.findViewById(R.id.btn_refresh);


        Network network = new BasicNetwork(new HurlStack());
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 1024 * 1024);
        queue = new RequestQueue(cache,network);
        queue.start();

        refresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getCurrentMilitary(currentMilitary,errormsg);
            }
        });
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sendPurchasesToServer(infantry,airforce,navy,errormsg);
            }
        });
    }



    /**
     * This method will send a GET request to the server and display the user's current military status
     *
     * @param t     TextView box that is to display the user's current Military status
     * @param errormsg TextView box that will display errors
     */
    @Override
    public void getCurrentMilitary(final TextView t, final TextView errormsg) {
        JsonObjectRequest reqMilitary = new JsonObjectRequest(Request.Method.GET, urlCurrentMilitary, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject militaryres = (JSONObject)response.get("1");
                    String infantryres = militaryres.getString("infantry");
                    String airforceres = militaryres.getString("airforce");
                    String navyres = militaryres.getString("navy");
                    t.setText("Infantry: " + infantryres + "\n" + "Air Force: " + airforceres + "\n" + "Navy: " + navyres);
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

    }

    /**
     * This method will send a POST request with the user's Infantry, Airforce, and Navy to the server
     *
     * @param infantry TextView that will contain the user's request to purchase infantry
     * @param airforce TextView that will contain the user's request to purchase air force
     * @param navy     TextView that will contain the user's request to purchase navy
     * @param errormsg    TextView that will display errors
     */
    @Override
    public void sendPurchasesToServer(final TextView infantry, final TextView airforce, final TextView navy, final TextView errormsg) {
        StringRequest sendMilitaryPurchase = new StringRequest(Request.Method.POST, urlSendMilitary, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("Success")){
                    errormsg.setText("Success!");
                }
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
                params.put("infantry", infantry.getText().toString());
                params.put("airforce", airforce.getText().toString());
                params.put("navy", navy.getText().toString());
                return params;
            }
        };
        queue.add(sendMilitaryPurchase);
    }

    /**
     * Testing method
     * @param i Infantry purchase
     * @param a Air force purchase
     * @param n Navy purchase
     * @param MilitaryHandler MilitaryHandler helper
     * @return true is successful false otherwise
     * @throws JSONException
     */
    public boolean tryPurchase(String i, String a, String n,  MilitaryHandler MilitaryHandler) throws JSONException {

        //Does not work because .getResponse has not been implemented
        if (MilitaryHandler.getResponse(i, a, n).getBoolean("purchaseSuccess")) {
            return true;
        }

        return false;
    }
}
