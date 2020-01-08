package org.cs309.backend.Issue;

import java.util.List;

/**
 *Interface for the Issue service
 *@author Joshua Kalyanapu
 */
public interface IssueService {

    /**
     *Obtains all rows of Issue from the database and returns them as a List
     *@return a List of all issuess
     */
    public List<Issue> getAll();

    /**
     *Obtains a row of issue from the database by id
     *@param id The id for which to search
     *@return An Issue object representing the relevant issue in the database
     */
    public Issue getById(Long id);

    /**
     *Obtains a row of issue from the database by title
     *@param title The title for which to search
     *@return An Issue object representing the relevant issue in the database
     */
    public Issue getByTitle(String title);
}
