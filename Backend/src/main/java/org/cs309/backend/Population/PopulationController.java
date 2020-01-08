package org.cs309.backend.Population;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping(path="/population")
public class PopulationController {

    private PopulationService populationService;

    @Autowired
    public void setPopulationService(PopulationService populationService) {
	this.populationService = populationService;
    }
    
    @GetMapping(path="/all")
    public @ResponseBody String all() {
	ArrayList<Population> al = (ArrayList)populationService.getAll();
	String toReturn = new String();
	toReturn += "{";
	for (Population p : al) {
	    toReturn += (p.toString() + ", "); 
	}
	toReturn = toReturn.substring(0, toReturn.length()-2); //get rid of the last comma and the space after
	toReturn += "}";
	return toReturn;
    }

    @GetMapping(path="/{id}")
    public @ResponseBody String byId(@PathVariable("id") String id) {
	Population p = populationService.getById(Long.parseLong(id));
	if (p == null) {
	    return "{\"Result\": \"DNE\"}";
	}
	else {
	    return "{" + p.toString() + "}";
	}
    }

    @PostMapping(path="change")
    public @ResponseBody String change(@RequestParam("id") String id,
				       @RequestParam("lowerclass") String lowerclass,
				       @RequestParam("middleclass") String middleclass,
				       @RequestParam("upperclass") String upperclass)
    {
	Population p = populationService.getById(Long.parseLong(id));
	if (p == null) {
	    return "{\"Result\": \"DNE\"}";
	}
	else {
	    populationService.change(Long.parseLong(id),
				   Long.parseLong(lowerclass),
				   Long.parseLong(middleclass),
				   Long.parseLong(upperclass));
	    return "{\"Result\": \"Success\"}";
	}
    }
}
