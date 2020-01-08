package org.cs309.backend.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.cs309.backend.WebsocketTest.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.cs309.backend.Population.*;

import java.util.ArrayList;

@Component
public class Services {
    @Autowired
    private PopulationRepository populationRepository;
    @Scheduled(cron = "0 0 0 ? * *") //run every night at midnight
    public void updatePopulation() {
	try {
	    WebsocketTestController.broadcastMessageToUsers("Updating");
	}
	catch (Exception e) {

	}

	PopulationController pc = new PopulationController();


	//update populations
	/*ArrayList<Population> al = (ArrayList<Population>)pc.findAllPopulation();
	for (Population p : al) {
	    p.setLowerclassPopulation(p.getLowerclassPopulation() + 1000);
    	    p.setMiddleclassPopulation(p.getMiddleclassPopulation() + 100);
    	    p.setUpperclassPopulation(p.getUpperclassPopulation() + 10);
	    populationRepository.save(p);
	    }*/
    }
}
