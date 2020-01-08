package org.cs309.backend.Issue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
/**
 *JSON REST Controller for Issue
 *@author Joshua Kalyanapu
 */
@Controller
@RequestMapping(path="/issue")
public class IssueController {
    private IssueService issueService;

    /**
     *Used by Spring's dependency injection to inject the relevant IssueService into the class.
     *Only called internally by Spring
     *@param issueService An IssueService to use for various requests to the database
     */
    @Autowired
    public void setIssueService(IssueService issueService) {
	this.issueService = issueService;
    }

    /**
     *Returns a JSON representation of all Issues in the database.
     *Accessible from "/issue/all"
     *@return A JSON representaiton of all Issues in the database
     */
    @GetMapping(path="/all")
    public @ResponseBody String getAll() {
	ArrayList<Issue> al = (ArrayList)issueService.getAll();
	if (al.size() == 0) {
	    return "{}";
	}
	String toReturn = new String();
	toReturn += "{";
	for (Issue i : al) {
	    toReturn += (i + ", ");
	}
	toReturn = toReturn.substring(0, toReturn.length()-2);
	toReturn += "}";
	return toReturn;
    }
    
    /**
     *Returns a JSON representation of a specific Issue, by id. 
     *Accessible via "/issue/{id}"
     *@param id The ID for which to obtain the Issue
     *@return A JSON String representation of the requested Issue
     */
    @GetMapping(path="/{id}")
    public @ResponseBody String getById(@PathVariable("id") String id) {
	Issue i = issueService.getById(Long.parseLong(id));
	if (i != null) {
	    return "{" + i + "}";
	}
	else {
	    return "{\"Result\": \"DNE\"}";
	}
    }

    /**
     *Returns a JSON representation of a specific issue searched by title
     *Accessible via "/issue/title/{title}
     *@param title The title for which to search
     *@return The JSON String representation of the requested Issue, or an error in JSON if it does not exist
     */
    @GetMapping(path="/title/{title}")
    public @ResponseBody String getByTitle(@PathVariable("title") String title) {
	Issue i = issueService.getByTitle(title);
	if (i != null) {
	    return "{" + i + "}";
	}
	else {
	    return "{\"Result\": \"DNE\"}";
	}	       
    }
}
