package com.example.tabbedversion.HelperClasses;


import android.widget.TextView;

/**
 * The Interface TaxHelper will assist the Tax Rate Changes class
 * The methods are to be implemented in the Tax Rate Changes Class
 * @author  Chloe Sabado
 */
public interface TaxHelper {


    /**
     * This method will send a GET request to the server and display the user's current Tax Rate status
     * @param t TextView box that is to display the user's current Tax Rate status
     * @param errormsg TextView box that will display errors
     */
    void getCurrentTax(TextView t, TextView errormsg);


    /**
     * This method will send a POST request with the user's different tax rates per class to server
     * @param lower TextView that will contain the user's request to change lower class tax rate
     * @param middle TextView that will contain the user's request to change middle class tax rate
     * @param upper TextView that will contain the user's request to change upper class tax rate
     * @param errormsg TextView that will display errors
     */
    void sendNewTax(TextView lower, TextView middle, TextView upper, TextView errormsg);
}
