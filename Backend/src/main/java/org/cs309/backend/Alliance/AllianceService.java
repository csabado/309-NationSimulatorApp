package org.cs309.backend.Alliance;

import java.util.List;

/**
 *Interface for the Alliance Service
 *@author Kevin Liu
 */

public interface AllianceService{
    /**
     *Obtains all rows of Alliance from the data base and returns them as a List
     *@return a List of all govs
     */
    public List<Alliance> getAll();

    /**
     *Obtains an Alliance based on Id
     *@param the Alliance id
     */
    public Alliance getById(Long id);

    /**
     *Obtains an Alliance based on creator id
     *@param the creator id
     */
    public Alliance getByCreatorId(Long Id);
}
