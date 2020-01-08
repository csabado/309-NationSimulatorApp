package org.cs309.backend.Gov;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *Implements GovService.java
 *@author Joshua Kalyanapu
 */

@Service
public class GovServiceImpl implements GovService {
    @Autowired
    private GovRepository govRepository;

    public List<Gov> getAll() {
	ArrayList<Gov> al = (ArrayList<Gov>)govRepository.findAll();
	return al;
    }

    public Gov getById(Long id) {
	Optional o = govRepository.findById(id);
	if (o.isPresent()) {
	    return (Gov)o.get();
	}
	else {
	    return null;
	}
    }
}
