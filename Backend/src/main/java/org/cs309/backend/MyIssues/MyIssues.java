package org.cs309.backend.MyIssues;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.List;
import java.util.Iterator;
import java.util.Arrays;

@Entity
@Table(name="myissues")
public class MyIssues {
    @Id
    private Long id;

    private String issueList;

    public MyIssues(Long id, String issueList) {
	this.id = id;
	this.issueList = issueList;
    }

    public MyIssues(Long id) {
	this.id = id;
    }

    public MyIssues() {
	this.issueList = new String();
    }

    public Long getId() {
	return this.id;
    }

    public String getIssueList() {
	return this.issueList;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setIssueList(String issueList) {
	this.issueList = issueList;
    }

    public void addIssue(Long issueId) {
	if (this.issueList.length() == 0) {
	    this.issueList += String.valueOf(issueId);
	}
	else {
	    this.issueList = this.issueList + ("," + issueId);
	}
    }

    public void removeIssue(Long issueId) {
	List<String> result = Arrays.asList(this.issueList.split("\\s*,\\s*"));
	Iterator<String> iter = result.iterator();
	while (iter.hasNext()) {
	    if (iter.next().equals(String.valueOf(issueId))) {
		iter.remove();
		break;
	    }
	}
	this.issueList = String.join(",", result); 
    }
    
}
