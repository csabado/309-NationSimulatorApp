package org.cs309.backend.Stats;

import java.util.List;

public interface StatsService {
    public List<Stats> getAll();
    public Stats getById(Long id);
    public void save(Stats s);
}
