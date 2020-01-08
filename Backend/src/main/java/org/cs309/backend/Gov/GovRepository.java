package org.cs309.backend.Gov;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GovRepository extends CrudRepository<Gov, Long> {
}
