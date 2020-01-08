package com.example.decisionpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private String nationName, policy;
    private RequestQueue requestQueue;
    private double taxRate;
    EditText NN,TR;
    Button btnEnter;
    Spinner mySpinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NN = (EditText) findViewById(R.id.nationName);
        TR = (EditText) findViewById(R.id.taxRate);
        mySpinner = (Spinner) findViewById(R.id.spin);
        btnEnter = (Button) findViewById(R.id.gobtn);


        btnEnter.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nationName = NN.getText().toString();
                taxRate = Float.valueOf(TR.getText().toString());
                policy = mySpinner.getSelectedItem().toString();

                //JSON Data
                String data = "{"+
                        "\"NationName\"" + "\"" + nationName + "\","+
                        "\"TaxRate\"" + "\"" + taxRate + "\""+
                        "\"ImmigrationPolicy\"" + "\"" + policy + "\""+
                        "}";
                Submit(data);
            }
            private void Submit(String data)
            {
                final String savedata= data;
                String URL="https://YOUR_API_URL";

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
        }));
    }


}
