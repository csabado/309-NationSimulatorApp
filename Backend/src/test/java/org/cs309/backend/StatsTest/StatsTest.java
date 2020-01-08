package org.cs309.backend.StatsTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import org.cs309.backend.Stats.*;

public class StatsTest {
    @InjectMocks
    StatsController StatsController;

    @Mock
    StatsService statsService;

    @Before
    public void init(){
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById(){
	when (statsService.getById(new Long(15))).thenReturn(new Stats(new Long(15)));
	Stats a = statsService.getById(new Long(15));
	System.out.println(a);
	assertTrue(a.getId().equals(new Long(15)));
    }
}
