package org.cs309.backend.Military;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MilitaryServiceImpl implements MilitaryService {
    @Autowired
    private MilitaryRepository militaryRepository;
    
    @Override
    public List<Military> getAll() {
	ArrayList<Military> militaries = (ArrayList)militaryRepository.findAll();
	return militaries;
    }

    @Override
    public Military getById(Long id) {
	Optional o = militaryRepository.findById(id);
	if (o.isPresent()) {
	    return (Military)o.get();
	}
	else {
	    return null;
	}
    }

    @Override
    public boolean change(Long id, Long infantry, Long airforce, Long navy) {
	Military m = new Military(id, infantry, airforce, navy);
	militaryRepository.save(m);
	return true;
    }
}
					  
