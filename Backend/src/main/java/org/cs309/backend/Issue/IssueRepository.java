package org.cs309.backend.Issue;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 *CRUD repository for the Issue table on the database
 *@author Joshua Kalyanapu
 */
public interface IssueRepository extends CrudRepository<Issue, Long> {
    /**
     *Searches the Issue table for an issue matching the given title
     *@param title The title for which to search
     *@return A List of all items with the relevant title
     */
    List<Issue> findByTitle(String title);
}
