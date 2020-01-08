package org.cs309.backend.Tax;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 *Crud Repository for the Taxes table on the database
 *@author Joshua Kalyanapu
 */
public interface TaxRepository extends CrudRepository<Tax, Long> {
    
}
