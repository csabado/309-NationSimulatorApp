package org.cs309.backend.MyIssues;

import java.util.List;

public interface MyIssuesService {
    public List<MyIssues> getAll();
    public MyIssues getById(Long id);
    public void change(Long id, String issueList);
    public void add(Long id, Long issueId);
    public void remove(Long id, Long issueId);
}

