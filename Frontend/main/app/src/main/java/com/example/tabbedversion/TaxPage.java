package com.example.tabbedversion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.example.tabbedversion.HandlerClasses.MilitaryHandler;
import com.example.tabbedversion.HandlerClasses.TaxHandler;
import com.example.tabbedversion.HelperClasses.TaxHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * This class will allow the user to change tax rates for each specific class
 * @author Chloe
 */
public class TaxPage extends Fragment implements TaxHelper {
    RequestQueue queue;
    String urlTax = "http://coms-309-sr-7.misc.iastate.edu:8080/taxes/1";
    String urlChange = "http://coms-309-sr-7.misc.iastate.edu:8080/taxes/change";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_tax_page,container,false);
    }
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final EditText lc = view.findViewById(R.id.et_lc);
        final EditText mc = view.findViewById(R.id.et_lc);
        final EditText uc = view.findViewById(R.id.et_lc);
        final TextView cur = view.findViewById(R.id.tv_currentTax);
        final TextView errormsg = view.findViewById(R.id.tv_error);
        Button submit = view.findViewById(R.id.btn_submit);
        ImageButton refresh = view.findViewById(R.id.btn_refresh);

        Network network = new BasicNetwork(new HurlStack());
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 1024 * 1024);
        queue = new RequestQueue(cache,network);
        queue.start();

        refresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getCurrentTax(cur,errormsg);
            }
        });
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sendNewTax(lc,mc,uc,errormsg);
            }
        });

    }

    /**
     * This method will send a GET request to the server and display the user's current Tax Rate status
     *
     * @param t        TextView box that is to display the user's current Tax Rate status
     * @param errormsg TextView box that will display errors
     */
    @Override
    public void getCurrentTax(final TextView t, final TextView errormsg) {
        JsonObjectRequest reqIssue = new JsonObjectRequest(Request.Method.GET, urlTax, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject tax = (JSONObject)response.get("1");
                    String lower = tax.getString("lowerclass");
                    String middle = tax.getString("middleclass");
                    String upper = tax.getString("upperclass");
                    String display = "Lower : " + lower + "\nMiddle: " + middle + "\nUpper: " + upper;
                    t.setText(display);
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
        queue.add(reqIssue);
    }


    /**
     * This method will send a POST request with the user's different tax rates per class to server
     *
     * @param lower    TextView that will contain the user's request to change lower class tax rate
     * @param middle   TextView that will contain the user's request to change middle class tax rate
     * @param upper    TextView that will contain the user's request to change upper class tax rate
     * @param errormsg TextView that will display errors
     */
    @Override
    public void sendNewTax(final TextView lower, final TextView middle, final TextView upper, final TextView errormsg) {
        StringRequest sendTax = new StringRequest(Request.Method.POST, urlChange, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("Success")){
                    errormsg.setText("Success!");
                }
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
                params.put("lowerclass", lower.getText().toString());
                params.put("middleclass", middle.getText().toString());
                params.put("upperclass", upper.getText().toString());
                return params;
            }
        };
        queue.add(sendTax);
    }

    /**
     * Method for testing purposes
     * @param lower Lower class new tax rate
     * @param middle Middle class new tax rate
     * @param upper Upper class new tax rate
     * @param TaxHandler TaxHandler class
     * @return true if successfull false otherwise
     * @throws JSONException
     */
    public boolean tryTax(String lower, String middle, String upper,  TaxHandler TaxHandler) throws JSONException {

        //Does not work because .getResponse has not been implemented
        if (TaxHandler.getResponse(lower,middle,upper, TaxHandler).getBoolean("taxSuccess")) {
            return true;
        }

        return false;
    }
}
