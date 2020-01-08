package com.example.tabbedversion.HelperClasses;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


/**
 * The Interface IssuesHelper will assist both the Issues pages for both normal users and Alliance head
 * This Interface is to be implemented in the Issues pages
 * @author  Chloe Sabado
 */
public interface IssuesHelper {
    /**
     * This method should check which check box is selected and send the users selected option to the server
     * using a POST request
     * @param A Checkbox for the selected option yes
     * @param B Checkbox for the selected option no
     * @param C CheckBox for the selected option none
     * @param issueid issue being resolved
     * @param Ans TextView box that displays what happens once the option is selected
     * @param issue TextView box that displays any errors
     */
    void sendOptionToServer(CheckBox A, CheckBox B, CheckBox C, EditText issueid, TextView Ans, TextView issue);

    /**
     * This method should send a GET request to the server, requesting the user's issue
     * @param t The TextView that is used to display the issue
     */
    void getIssue(TextView t);

}
