package org.cs309.backend.Stats;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private StatsRepository statsRepository;
    
    @Override
    public List<Stats> getAll() {
	ArrayList<Stats> al = (ArrayList)statsRepository.findAll();
	return al;
    }

    @Override
    public Stats getById(Long id) {
	Optional o = statsRepository.findById(id);
	if (o.isPresent()) {
	    return (Stats)o.get();
	}
	else {
	    return null;
	}
    }

    @Override
    public void save(Stats s) {
	statsRepository.save(s);
    }
}
