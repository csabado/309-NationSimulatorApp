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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tabbedversion.HelperClasses.DecisionHelper;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

import java.nio.charset.StandardCharsets;


public class decisions extends Fragment implements DecisionHelper {

   String url = " ";
    RequestQueue queue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.decisions_xml,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final EditText nationname = view.findViewById(R.id.nationName);
        final EditText taxrate = view.findViewById(R.id.taxRate);
        final EditText policy = view.findViewById(R.id.et_policy);
        Button btnEnter = view.findViewById(R.id.gobtn);


        btnEnter.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendDecisionToServer(nationname, taxrate, policy);
                    }
        }));
    }

    /**
     * This Method will send the user's decisons to the server
     *
     * @param name   TextView box that gets the new Nation Name
     * @param rate   TextView box that gets the new Tax Rate
     * @param policy TextView box that gets the policy (open, strict, none)
     */
    @Override
    public void sendDecisionToServer(final TextView name, final TextView rate, final TextView policy) {
        StringRequest sendDecision = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject issueAnswer = new JSONObject((response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("NationName", name.toString());
                params.put("TaxRate", rate.toString());
                params.put("ImmigrationPolicy", policy.toString());
                return params;
            }
        };
        queue.add(sendDecision);
    }
}
