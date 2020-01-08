package org.cs309.backend.Gov;

import org.cs309.backend.Stats.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.Optional;


/**
 *JSON REST Controller for Gov
 *@author Kevin Liu
 *@author Joshua Kalyanapu
 */

@Controller
@RequestMapping(path="/gov")
public class GovController{
    private GovService govService;

    /**
     *Used by Spring's dependency injection to inject the relevant GovService into the class.
     *Only called internally by Spring
     *@param govService A GovService to use for various requests to the database
     */
    @Autowired
    public void setGovService(GovService govService) {
	this.govService = govService;
    }

    /**
     *Returns a JSON representation of all Govs in the database.
     *Accessible from "/gov/all"
     *@return A JSON representaiton of all Govs in the database
     */
    @GetMapping(path="/all")
    public @ResponseBody String getAll() {
	ArrayList<Gov> al = (ArrayList<Gov>)govService.getAll();
	if (al.size() == 0) {
	    return "{}";
	}
	else {
	    String toReturn = new String();
	    toReturn += "{";
	    for (Gov g : al) {
		toReturn += (g + ", ");
	    }
	    toReturn = toReturn.substring(0, toReturn.length()-2);
	    toReturn += "}";
	    return toReturn;
	}
    }

    /**
     *Returns a JSON representation of a specific Gov, by id. 
     *Accessible via "/gov/{id}"
     *@param id The ID for which to obtain the government
     *@return A JSON String representation of the requested government
     */
    @GetMapping(path="/{id}")
    public @ResponseBody String getById(@PathVariable("id") String id) {
	Gov g = govService.getById(Long.parseLong(id));
	if (g == null) {
	    return "{\"Error\": \"DNE\"}";
	}
	else {
	    return "{" + g + "}";
	}
    }

    /**
     *Updates the values in the government. 
     *Not implemented yet as we are waiting on login and roles
     *@param id The id for the government to update
     *@return A JSON String saying the function isn't implemented. 
     */
    @PostMapping(path="/change")
    public @ResponseBody String change(@RequestParam Long id)
    {
	return "\"Result\": \"NI\"";
    }
}
