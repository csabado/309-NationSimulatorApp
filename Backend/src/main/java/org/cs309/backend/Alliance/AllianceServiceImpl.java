package org.cs309.backend.Alliance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 *Implements Alliance Service.java
 *@author Kevin Liu
 */

@Service
public class AllianceServiceImpl implements AllianceService {
    @Autowired
    private AllianceRepository allianceRepository;

    public List<Alliance> getAll(){
	ArrayList<Alliance> al = (ArrayList<Alliance>)allianceRepository.findAll();
	return al;
    }

    public Alliance getById(Long id) {
	Optional o = allianceRepository.findById(id);
	if (o.isPresent()) {
	    return (Alliance)o.get();
	}
	else {
	    return null;
	}
    }

	public Alliance getByCreatorId(Long CreatorId){
	    ArrayList<Alliance> al = (ArrayList<Alliance>)allianceRepository.findByCreatorId(CreatorId);
	    if (al.get(0) == null){
		return null;
	    }
	    else {
		return al.get(0);
	    }
	}
}
    

    
