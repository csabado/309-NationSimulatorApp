package com.example.tabbedversion.HelperClasses;

import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;

/**
 * Interface to be used for StringRequests in stat page
 */
public interface volleyInterface {
    /**
     *
     * @param url
     * @param money
     * @param militaryIndex
     * @param civilIndex
     * @param name
     * @param errorPage
     * @param id
     * @return StringRequest to be added to Request Queue
     */
    StringRequest statRequest(String url, TextView money, TextView militaryIndex, TextView civilIndex, TextView name, TextView errorPage, String id);

    /**
     *
     * @param url
     * @param population
     * @param errorPage
     * @param id
     * @return StringRequest to be added to Request Queue
     */
    StringRequest popRequest(String url, TextView population, TextView errorPage, String id);

    /**
     *
     * @param url
     * @param taxRate
     * @param errorPage
     * @param id
     * @return StringRequest to be added to Request Queue
     */
    StringRequest taxRequest(String url, TextView taxRate, TextView errorPage, String id);

    /**
     *
     * @param url
     * @param govType
     * @param errorPage
     * @param id
     * @return StringRequest to be added to Request Queue
     */
    StringRequest govRequest(String url,TextView govType,TextView errorPage, String id);
    StringRequest allyRequest(String url, TextView allianceAdd, TextView errorPage, String id);
    StringRequest createRequest(String url, TextView createAlliance, TextView errorPage, String id);



}
