
package backend;


import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicesRepository extends CrudRepository<Indices, Integer> {
    Collection<Indices> findAll();

    Collection<Indices> findById(@Param("id") int id);

    Indices save (Indices Indices);
}
