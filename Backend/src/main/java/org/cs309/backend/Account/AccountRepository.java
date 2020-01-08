package org.cs309.backend.Account;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 *Crud Repository for the Accounts table on the database
 *@author Joshua Kalyanapu
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    /**
     *Queries the database for a row in Accounts that has a username equal to the provided username
     *@param username The username to query the Accounts table for
     *@return A list of all matched Accounts, should be of either size 0 or 1
     */
    List<Account> findByUsername(String username);
}
