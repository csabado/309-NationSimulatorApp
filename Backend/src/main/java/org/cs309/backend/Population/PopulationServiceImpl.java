package org.cs309.backend.Population;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PopulationServiceImpl implements PopulationService {

    @Autowired
    private PopulationRepository populationRepository;

    @Override
    public List<Population> getAll() {
	ArrayList<Population> toReturn = (ArrayList)populationRepository.findAll();
	return toReturn;
    }

    @Override
    public Population getById(Long id) {
	Optional o = populationRepository.findById(id);
	if (o.isPresent()) {
	    return (Population)o.get();
	}
	else {
	    return null;
	}
    }

    @Override
    public boolean change(Long id, Long lowerclass, Long middleclass, Long upperclass) {
	Population p = new Population(id, lowerclass, middleclass, upperclass);
	populationRepository.save(p);
	return true;
    }
}
