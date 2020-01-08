package org.cs309.backend.Tax;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TaxServiceImpl implements TaxService {

    @Autowired
    private TaxRepository taxRepository;

    @Override
    public List<Tax> getAll() {
	ArrayList<Tax> al = (ArrayList)taxRepository.findAll();
	return al;
    }

    @Override
    public Tax getById(Long id) {
	Optional o = taxRepository.findById(id);
	if (!o.isPresent()) {
	    return null;
	}
	else {
	    return (Tax)o.get();
	}
    }

    @Override
    public boolean change(Long id, Double lowerclass, Double middleclass, Double upperclass) {
	Tax t = new Tax(id, lowerclass, middleclass, upperclass);
	taxRepository.save(t);
	return true;
    }
}
