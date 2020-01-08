package org.cs309.backend.Gov;

import java.util.List;

/**
 *Interface for the Gov service
 *@author Joshua Kalyanapu
 */
public interface GovService {
    /**
     *Obtains all rows of gov from the database and returns them as a List
     *@return a List of all govs
     */
    public List<Gov> getAll();

    /**
     *Obtains a row of gov from the database by id
     *@param id The id for which to search
     *@return a Gov object representing the relevant gov in the database
     */
    public Gov getById(Long id);
}
