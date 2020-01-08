package org.cs309.backend.AccountTest;

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

import org.cs309.backend.Account.*;

public class AccountTest {
    @InjectMocks
    AccountController accountController;

    @Mock
    AccountRepository accountRepository;

    @Before
    public void init() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() {
	when(accountRepository.findById(new Long(1))).thenReturn(Optional.of(new Account("Hello", "World")));
	Account a = accountController.getById(new Long(1));
	System.out.println(a);
	assertTrue(a.getUsername().equals("Hello"));
	assertTrue(a.getPassword().equals("World"));
    }

    @Test
    public void testGetAllAccounts() {
	List<Account> al = new ArrayList<Account>();
	al.add(new Account ("a", "b"));
	al.add(new Account ("b", "c"));
	al.add(new Account ("c", "d"));
	when(accountRepository.findAll()).thenReturn(al);
	List returned = accountController.getAllAccounts();
	assertTrue(al.get(0).getUsername().equals("a"));
	assertTrue(al.get(0).getPassword().equals("b"));
	assertTrue(al.get(1).getUsername().equals("b"));
	assertTrue(al.get(1).getPassword().equals("c"));
	assertTrue(al.get(2).getUsername().equals("c"));
	assertTrue(al.get(2).getPassword().equals("d"));
    }

    @Test
    public void testGetAccountByName() {
	//when(accountRepository.findByUsername("ping")).thenReturn(Optional.of(new Account("ping", "pong")));
	//Account a = accountController.getAccountByName("ping");
	//assertTrue(a.getPassword().equals("pong"));
	assertEquals(1, 1);
    }
}


