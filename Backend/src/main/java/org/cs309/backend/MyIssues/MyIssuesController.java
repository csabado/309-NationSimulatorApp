package org.cs309.backend.MyIssues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Arrays; 

import org.cs309.backend.Issue.*;
import org.cs309.backend.Stats.*;

@Controller
@RequestMapping(path="/myissues")
public class MyIssuesController {
    private MyIssuesService myIssuesService;
    private IssueService issueService;
    private StatsService statsService;

    @Autowired
    public void setMyIssuesService(MyIssuesService myIssuesService) {
	this.myIssuesService = myIssuesService;
    }

    @Autowired
    public void setIssueService(IssueService issueService) {
	this.issueService = issueService;
    }

    @Autowired
    public void setStatsService(StatsService statsService){
	this.statsService = statsService;
    }

    @GetMapping(path="/{id}")
    public @ResponseBody String getById(@PathVariable("id") String id) {
	MyIssues m = myIssuesService.getById(Long.parseLong(id));
	if (m != null) {
	    String toReturn = new String();
	    toReturn += "{";
	    List<String> result = Arrays.asList(m.getIssueList().split("\\s*,\\s*"));
	    for (String s : result) {
		toReturn += issueService.getById(Long.parseLong(s)) + ", ";
	    }
	    toReturn = toReturn.substring(0, toReturn.length()-2);
	    toReturn += "}";
	    return toReturn;
	}
	else {
	    return "{\"Result\": \"DNE\"}";
	}
    }

    @PostMapping(path="/ans")
    public @ResponseBody String ans(@RequestParam("id") String id, @RequestParam("issueId") String issueId, @RequestParam("response") String response)
    {
	Issue i = issueService.getById(Long.parseLong(issueId));
	Stats s = statsService.getById(Long.parseLong(id));

	if (response == "yes")
	    {
		s.setEconomicIndex(s.getEconomicIndex() + i.getEconomicIndexEffectYes());
		s.setMilitaryIndex(s.getMilitaryIndex() + i.getMilitaryIndexEffectYes());
		s.setCivilUnrest(s.getCivilUnrest() + i.getCivilUnrestEffectYes());
		statsService.save(s);
		return "{\"Result\": \"Success!\"}";
	    }
	if (response == "no")
	    {
		s.setEconomicIndex(s.getEconomicIndex() + i.getEconomicIndexEffectNo());
		s.setMilitaryIndex(s.getMilitaryIndex() + i.getMilitaryIndexEffectNo());
		s.setCivilUnrest(s.getCivilUnrest() + i.getCivilUnrestEffectNo());
		statsService.save(s);
		return "{\"Result\": \"Success!\"}";
	    }
	if (response == "nothing")
	    {
		s.setEconomicIndex(s.getEconomicIndex() + i.getEconomicIndexEffectNothing());
		s.setMilitaryIndex(s.getMilitaryIndex() + i.getMilitaryIndexEffectNothing());
		s.setCivilUnrest(s.getCivilUnrest() + i.getCivilUnrestEffectNothing());
		statsService.save(s);
		return "{\"Result\": \"Success!\"}";
	    }
	System.out.println(response);
        return "{\"Result\": \"Success\"}";
    }
}
