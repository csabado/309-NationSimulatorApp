package org.cs309.backend.Military;

import org.cs309.backend.Military.Military;
import java.util.List;

public interface MilitaryService {
    public List<Military> getAll();
    public Military getById(Long id);
    public boolean change(Long id, Long infantry, Long airforce, Long navy);
}

