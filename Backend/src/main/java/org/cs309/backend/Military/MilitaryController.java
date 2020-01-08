package org.cs309.backend.Military;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(path="/military")
public class MilitaryController {
    private MilitaryService militaryService;

    @Autowired
    public void setMilitaryService(MilitaryService militaryService) {
	this.militaryService = militaryService;
    }
    
    @GetMapping(path="/all")
    public @ResponseBody String getAll() {
	ArrayList<Military> al = (ArrayList)militaryService.getAll();
	String toReturn = new String("{");
	for (Military m : al) {
	    toReturn += (m.toString() + ", ");
	}
	toReturn = toReturn.substring(0, toReturn.length()-2); //eliminate the last comma and the space after the comma
	toReturn += "}";
	return toReturn;
    }

    @GetMapping(path="/{id}")
    public @ResponseBody String getById(@PathVariable("id") String id) {
	Military m = militaryService.getById(Long.parseLong(id));
	if (m != null) {
	    return "{" + m.toString() + "}";
	}
	else {
	    return "{\"Result\": \"DNE\"}";
	}
    }

    @PostMapping(path="/change")
    public @ResponseBody String change(@RequestParam("id") String id,
				       @RequestParam("infantry") String infantry,
				       @RequestParam("airforce") String airforce,
				       @RequestParam("navy") String navy)
    {
	//check for existence first
	Military m = militaryService.getById(Long.parseLong(id));
	if (m == null) {
	    return "{\"Result\": \"DNE\"}";	    
	}
	else {
	    if (militaryService.change(Long.parseLong(id),
				       Long.parseLong(infantry),
				       Long.parseLong(airforce),
				       Long.parseLong(navy)))
	    {
		
		return "{\"Result\": \"Success\"}";
	    }
	    else {
		return "{\"Result\": \"Failure\"}";
	    }
	}
    }
}
