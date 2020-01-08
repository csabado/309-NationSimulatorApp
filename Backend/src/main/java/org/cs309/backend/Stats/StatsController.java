 package org.cs309.backend.Stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.lang.String;
import java.util.ArrayList;

/**
 *@author Joshua Kalyanapu
 *@author Kevin Liu
 *The statistics class, has information that will be shown on the home page. Includes various nation indices, 
 *population, and national funds. 
 */

@Controller
@RequestMapping(path="/stats")
public class StatsController {
    private StatsService statsService;

    @Autowired
    public void setStatsService(StatsService statsService) {
	this.statsService = statsService;
    }

    @GetMapping(path="/all")
    public @ResponseBody String getAll() {
	ArrayList<Stats> al = (ArrayList<Stats>)statsService.getAll();
	String toReturn = new String();
	toReturn += "{";
	for (Stats s : al) {
	    toReturn += s + ", ";
	}
	toReturn = toReturn.substring(0, toReturn.length()-2);
	toReturn += "}";
	return toReturn;
    }
    
    @GetMapping(path="/{id}")
    public @ResponseBody String getById(@PathVariable("id") Long id) {
	Stats s = statsService.getById(id);
	if (s == null) {
	    return "{\"Error\", \"DNE\"}";
	}
	else {
	    return "{" + s + "}";
	}
    }
}
