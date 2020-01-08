package org.cs309.backend.Tax;

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

@Controller
@RequestMapping(path="/taxes")
public class TaxController {

    private TaxService taxService;
    
    @Autowired
    public void setTaxService(TaxService taxService) {
	this.taxService = taxService;
    }
    
    @GetMapping(path="/all")
    public @ResponseBody String getAll() {
	ArrayList<Tax> al = (ArrayList)taxService.getAll();
	String toReturn = new String();
	toReturn += "{";
	for (Tax t : al) {
	    toReturn += (t + ", ");
	}
	toReturn = toReturn.substring(0, toReturn.length()-2);
	toReturn += "}";
	return toReturn;
    }

    @GetMapping(path="/{id}")
    public @ResponseBody String getTaxes(@PathVariable("id") String id) 
    {
	Tax t = taxService.getById(Long.parseLong(id));
	if (t == null) {
	    return "{\"Error\":\"DNE\"}";
	}
	return "{" + t + "}";
    }

    @PostMapping(path="/change")
    public @ResponseBody String change(@RequestParam("id") String id,
				       @RequestParam("lowerclass") String lowerclass,
				       @RequestParam("middleclass") String middleclass,
				       @RequestParam("upperclass") String upperclass)
    {
	Tax t = taxService.getById(Long.parseLong(id));
	if (t == null) {
	    return "{\"Result\": \"DNE\"}";
	}
	else {
	    taxService.change(Long.parseLong(id),
			      Double.parseDouble(lowerclass),
			      Double.parseDouble(middleclass),
			      Double.parseDouble(upperclass));
	    return "{\"Result\": \"Success\"}";
	}
    }
}
