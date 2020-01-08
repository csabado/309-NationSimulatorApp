package com.example.demodecision;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    private String nationName, taxRate, immPolicy;
    Button enterBTN;
    EditText NN, TR, IP;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NN = (EditText) findViewById(R.id.NationName);
        TR = (EditText) findViewById(R.id.TaxRate);
        IP = (EditText) findViewById(R.id.ImmigrationPolicy);
        enterBTN = (Button) findViewById(R.id.EnterDecision);

        enterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nationName = NN.getText().toString();
                taxRate = TR.getText().toString();
                immPolicy = IP.getText().toString();
                final String TAG = MainActivity.class.getSimpleName();
                Log.d(TAG, "tag ");
                Log.d(TAG, "nation name: " + nationName);
                Log.d(TAG, "tax rate: " + taxRate);
                Log.d(TAG, "policy: " + immPolicy);

                String data = "{"+
                        "\"nationName\"" + "\"" + nationName + "\","+
                        "\"taxRate\"" + "\"" + taxRate + "\""+
                        "\"immigrationPolicy\"" + "\"" + immPolicy + "\""+
                        "}";
                Submit(data);

            }
        });
    }
    private void Submit(String data)
    {
        final String savedata= data;
        String URL="http://coms-309-sr-7.misc.iastate.edu:8080";

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objres=new JSONObject(response);
                    Toast.makeText(getApplicationContext(),objres.toString(),Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_LONG).show();

                }
                //Log.i("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                //Log.v("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return savedata == null ? null : savedata.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    //Log.v("Unsupported Encoding while trying to get the bytes", data);
                    return null;
                }
            }

        };
        requestQueue.add(stringRequest);
    }


}
