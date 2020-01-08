package com.example.tabbedversion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.tabbedversion.HandlerClasses.IssueHandler;
import com.example.tabbedversion.HelperClasses.IssuesHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.tabbedversion.HandlerClasses.IssueHandler;

import static android.content.ContentValues.TAG;

/**
 * This issues page will display an issue to the user and the user will have to decide and submit an option
 * as to solve the problem
 * @author Chloe
 */
public class issues_logic extends Fragment implements IssuesHelper {
    RequestQueue queue;
    String optionSelected = "";
    String urlIssue = "http://coms-309-sr-7.misc.iastate.edu:8080/myissues/1";
    String urlAnswer = "http://coms-309-sr-7.misc.iastate.edu:8080/myissues/ans";
    //String url = "http://10.26.1.164:8080/myissues/ans";
    //String idsend = "1";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.issues,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView ans = view.findViewById(R.id.tv_optionError);
        final TextView issue = view.findViewById(R.id.tv_IssueDisplay2);
        final EditText issueid = view.findViewById(R.id.et_id);
        final CheckBox checkA = view.findViewById(R.id.cb_A);
        final CheckBox checkB = view.findViewById(R.id.cb_B);
        final CheckBox checkC = view.findViewById(R.id.cb_c);

        ImageButton refresh = view.findViewById(R.id.btn_refresh);
        Button submit = view.findViewById(R.id.btn_submit);
        try {
            URL url = new URL("google.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Network network = new BasicNetwork(new HurlStack());
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 1024 * 1024);
        queue = new RequestQueue(cache,network);
        queue.start();
        refresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getIssue(issue);
            }
        });

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ sendOptionToServer(checkA, checkB, checkC, issueid, ans, issue);
            }
        });
    }

    /**
     * This method should check which check box is selected and send the users selected option to the server
     * using a POST request
     * @param A Checkbox for the selected option A
     * @param B Checkbox for the selected option B
     * @param C CheckBox for the selected option none
     * @param issueid issue being resolved
     * @param Ans TextView box that displays what happens once the option is selected
     * @param issue TextView box that displays any errors
     */
    @Override
    public void sendOptionToServer(CheckBox A, CheckBox B, CheckBox C, final EditText issueid, final TextView Ans, final TextView issue) {
        if(A.isChecked()){
            optionSelected = "yes";
        }else if (B.isChecked()){
            optionSelected = "no";
        }else if (C.isChecked()){
            optionSelected = "nothing";
        }
        StringRequest sendOptionSelection = new StringRequest(Request.Method.POST, urlAnswer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    //JSONObject issueAnswer = new JSONObject((response));
                Log.d(response, "ans ");
                    if(response.contains("Success")){
                        Ans.setText("Success!");
                    }
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                issue.setText(error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("id", "1");
                params.put("issueId",issueid.getText().toString());
                params.put("response", optionSelected);
                return params;
            }
        };
        Log.d(TAG, "sendOptionToServer: ");
        Log.d(optionSelected, "response");
        Log.d(issueid.getText().toString(), "issue");

        queue.add(sendOptionSelection);
    }

    /**
     * This method should send a GET request to the server, requesting the user's issue
     * @param t The TextView that is used to display the issue
     */
    @Override
    public void getIssue(final TextView t) {
        JsonObjectRequest reqIssue = new JsonObjectRequest(Request.Method.GET, urlIssue, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject issue = (JSONObject)response.get("1");
                    String title = issue.getString("title");
                    String body = issue.getString("body");
                    String display1 = "1: " + title + "- " + body + "\n";
                    JSONObject issue2 = (JSONObject)response.get("2");
                    String title2 = issue2.getString("title");
                    String body2 = issue2.getString("body");
                    String display2 = "2: " + title2 + "- " + body2 + "\n";
                    t.setText(display1 + display2);
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


    public boolean tryIssues(String issue, String a, String b,  IssueHandler IssueHandler) throws JSONException {

        //Does not work because .getResponse has not been implemented
        if (IssueHandler.getResponse(issue, a, b).getBoolean("IssueSuccess")) {
            return true;
        }

        return false;
    }
}
