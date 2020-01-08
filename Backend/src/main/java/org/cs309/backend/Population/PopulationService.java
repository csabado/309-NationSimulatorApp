package org.cs309.backend.Population;

import java.util.List;

public interface PopulationService {
    List<Population> getAll();
    Population getById(Long id);
    boolean change(Long id, Long lowerclass, Long middleclass, Long upperclass);
}
