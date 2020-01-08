package org.cs309.backend.MyIssues;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MyIssuesServiceImpl implements MyIssuesService {
    @Autowired
    private MyIssuesRepository myIssuesRepository;

    @Override
    public List<MyIssues> getAll() {
	ArrayList<MyIssues> myissues = (ArrayList)myIssuesRepository.findAll();
	return myissues;
    }

    @Override
    public MyIssues getById(Long id) {
	Optional o = myIssuesRepository.findById(id);
	if (o.isPresent()) {
	    return (MyIssues)o.get();
	}
	else {
	    return null;
	}
    }

    @Override
    public void change(Long id, String issueList) {
	MyIssues m = new MyIssues(id, issueList);
	myIssuesRepository.save(m);
    }

    @Override
    public void add(Long id, Long issueId) {
	MyIssues m = getById(id);
	if (m == null) {
	    return;
	}
	else {
	    m.addIssue(issueId);
	}
    }

    @Override
    public void remove(Long id, Long issueId) {
	MyIssues m = getById(id);
	if (m == null) {
	    return;
	}
	else {
	    m.removeIssue(issueId);
	}
    }
}
