package org.cs309.backend.Population;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 *Crud Repository for the Populations table on the database
 *@author Joshua Kalyanapu
 */
public interface PopulationRepository extends CrudRepository<Population, Long> {
    
}
