package org.cs309.backend.Alliance;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AllianceRepository extends CrudRepository<Alliance, Long> {

    List<Alliance> findByCreatorId(Long creatorId);
}
