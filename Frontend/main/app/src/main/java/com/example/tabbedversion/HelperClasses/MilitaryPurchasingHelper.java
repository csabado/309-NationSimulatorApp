package com.example.tabbedversion.HelperClasses;

import android.widget.TextView;

/**
 * The Interface MilitaryPurchasingHelper will assist the Military Purchasing class
 * The methods are to be implemented in the Military Purchasing Class
 * @author  Chloe Sabado
 */
public interface MilitaryPurchasingHelper {

    /**
     * This method will send a GET request to the server and display the user's current military status
     * @param t TextView box that is to display the user's current Military status
     * @param errormsg TextView box that will display errors
     */
    void getCurrentMilitary(TextView t, TextView errormsg);


    /**
     * This method will send a POST request with the user's Infantry, Airforce, and Navy to the server
     * @param infantry TextView that will contain the user's request to purchase infantry
     * @param airforce TextView that will contain the user's request to purchase air force
     * @param navy TextView that will contain the user's request to purchase navy
     * @param errormsg TextView that will display errors
     */
    void sendPurchasesToServer(TextView infantry, TextView airforce, TextView navy, TextView errormsg);



}