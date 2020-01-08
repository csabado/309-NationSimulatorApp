package org.cs309.backend.Alliance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.cs309.backend.Account.*;
import java.util.ArrayList;
import java.util.List;
/**
 *JSON REST Controller for Alliance
 *@author Kevin Liu
 */

@Controller
@RequestMapping(path="/alliance")
public class AllianceController {
    private AllianceService allianceService;
    private AccountRepository accountRepository;
    
    @Autowired
    public void setAllianceService(AllianceService allianceService) {
	this.allianceService = allianceService;
    }

    @GetMapping (path = "/all")
    public @ResponseBody String getAll() {
	ArrayList<Alliance> al = (ArrayList)allianceService.getAll();
	
	if (al.size() == 0) {
	    return "{}";
	}
	String toReturn = new String();
	toReturn += "{";
	for (Alliance i : al) {
	    toReturn += (i + ", ");
	}
	toReturn = toReturn.substring(0, toReturn.length()-2);
	toReturn += "}";
	return toReturn;
    }
    
    @GetMapping(path="/{id}")
    public @ResponseBody String getById(@PathVariable("id") String id) {
	Alliance i = allianceService.getById(Long.parseLong(id));
	if (i != null) {
	    return "{" + i + "}";
	}
	else {
	    return "{\"Result\": \"DNE\"}";
	}
    }

    @GetMapping(path="/creator/{creatorId}")
    public @ResponseBody String getByCreatorId(@PathVariable("creatorId")String creatorId)
    {		
    	Alliance i = allianceService.getByCreatorId(Long.parseLong(creatorId));
	if (i != null) {
	    return "{" + i + "}";
	}
	else {
	    return "{\"Result\": \"DNE\"}";
	}
    }

    @PostMapping(path="/create/{creatorId}")
    public @ResponseBody String createAlliance(@PathVariable("creatorId") String creatorId)
    {
	Alliance i = allianceService.getByCreatorId(Long.parseLong(creatorId));
	if (i == null)
	    {
		return "{\"Result\": \"Alliance already exists!\"}";
	
	    }
	Alliance a = new Alliance();
	a.setCreatorId(Long.valueOf(creatorId));
	return "{\"Result\": \"Alliance Created successfully\"}";
    }

    @PostMapping(path="/add") 
    public @ResponseBody String addTo(@RequestParam("id") Long id, @RequestParam("toAdd") String toAdd)
    {
	List<Account> i = accountRepository.findByUsername(toAdd);

	Alliance alliance = allianceService.getById(id);

	if (i.size() == 0) {
	    return "{\"Result\"; \"User does not exist!\"}";
	}

	Account a = i.get(0);
	if (alliance.addMember(Long.toString(a.getId())))
	    {
		return "{\"Result\": \"Member added successfully!\"}";
	    }
	return "{\"Result\": \"Error adding Member\"}";
    }


    @PostMapping(path="/remove") 
    public @ResponseBody String remove(@RequestParam("id") Long id, @RequestParam("toRemove") String toRemove)
    {
	List<Account> i = accountRepository.findByUsername(toRemove);

	Alliance alliance = allianceService.getById(id);
	if (i.size() == 0) {
	    return "{\"Result\"; \"User does not exist!\"}";
	}

	Account a = i.get(0);
	if (alliance.removeMember(Long.toString(a.getId())))
	    {
		return "{\"Result\": \"Member removed successfully!\"}";
	    }
	return "{\"Result\": \"Error removoing Member\"}";
    }

    

	
    
    
}
  
