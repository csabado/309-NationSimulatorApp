package com.example.tabbedversion.HelperClasses;

import android.widget.TextView;

/**
 * This interface is to be implemented in the decision activity
 */
public interface DecisionHelper {

    /**
     * This Method will send the user's decisons to the server
     * @param name TextView box that gets the new Nation Name
     * @param rate TextView box that gets the new Tax Rate
     * @param policy TextView box that gets the policy (open, strict, none)
     */
    void sendDecisionToServer(TextView name, TextView rate, TextView policy);
}
