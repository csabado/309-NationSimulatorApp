package org.cs309.backend.Tax;

import java.util.List;

public interface TaxService {
    public List<Tax> getAll();
    public Tax getById(Long id);
    public boolean change(Long id, Double lowerclass, Double middleclass, Double upperclass);
}
