package org.cs309.backend.Issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 *Implements IssueService.java
 *@author Joshua Kalyanapu
 */

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;
    
    public List<Issue> getAll() {
	ArrayList<Issue> al = (ArrayList<Issue>)issueRepository.findAll();
	return al;
    }

    public Issue getById(Long id) {
	Optional o = issueRepository.findById(id);
	if (o.isPresent()) {
	    return (Issue)o.get();
	}
	else {
	    return null;
	}
    }

    public Issue getByTitle(String title) {
	ArrayList<Issue> al = (ArrayList<Issue>)issueRepository.findByTitle(title);
	if (al.get(0) == null) {
	    return null;
	}
	else {
	    return al.get(0);
	}
    }
}
