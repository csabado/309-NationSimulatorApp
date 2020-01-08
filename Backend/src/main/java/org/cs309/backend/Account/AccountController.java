package org.cs309.backend.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 *Controller for the Account. Accessible from /accounts/*
 *@author Joshua Kalyanapu
 */

@Controller
@RequestMapping(path="/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    /**
     *Gets all accounts. This is used for debugging purposes and will be deleted for the release.
     *Accessible via /accounts/all
     *@return A String representation of a JSON object consisting of Accounts converted to JSON objects
     * with all ids in the database as keys and usernames as values. 
     */
    @GetMapping(path="/all")
    public @ResponseBody String getAll() {
	ArrayList<Account> al = (ArrayList)getAllAccounts();
	Map<String, String> accounts = new HashMap<String, String>();
	for (Account a : al) {
	    accounts.put(String.valueOf(a.getId()), a.getUsername());
	}
	JSONObject jo = new JSONObject(accounts);
	return jo.toString();
    }

    /**
     *Access the Accounts repository in AccountRepository.java and obtains a List of all the accounts in the 
     *database
     *@return A List of all accounts in the database
     */
    public List<Account> getAllAccounts() {
	List<Account> accounts = new ArrayList<>();
	accountRepository.findAll().forEach(accounts::add);
	return accounts;
    }

    /**
     *Gets an account via a particular ID, converts the account to a JSONObject, and returns that JSONObject
     *Accessible via /accounts/*id*
     *@return A String representation of a JSONObject with id as the key and username as the value. If the object
     *If the id doesn't exist in the database, returns a JSONObject with "Error" as the key and "DNE" as the 
     *value
     */
    @GetMapping(path="/{id}")
    public @ResponseBody String getId(@PathVariable("id") Long id) {
	Account a = getById(id);
	if (a == null) {
	    return "{\"Error\":\"DNE\"}";
	}
	Map<String, String> hm = new HashMap<String, String>();
	hm.put(String.valueOf(a.getId()), a.getUsername());
	JSONObject jo = new JSONObject(hm);
	return jo.toString();
    }

    /**
     *Access the Accounts repository in AccountRepository.java and obtains an Account using the ID.
     *@return The account as an Optional, or null if the id does not exist. 
     */
    public Account getById(long id) {
	Optional<Account> o = accountRepository.findById(id);
	if (!o.isPresent()) {
	    return null;
	}
	return o.get();
    }

    /**
     *Gets a particular account by username. Accessible via /byname/*nationname*
     *@return A String representation of a JSONObject with the key as the Account id and the value as the Account
     *username
     */
    @GetMapping(path="/byname/{name}")
    public @ResponseBody String getByName(@PathVariable("name") String name) {
	Account a = getAccountByName(name);
	if (a == null) {
	    return "{\"DNE\":\"DNE\"}";
	}
	Map<String, String> hm = new HashMap<String, String>();
	hm.put(String.valueOf(a.getId()), a.getUsername());
	JSONObject jo = new JSONObject(hm);
	return jo.toString();
	
    }

    /**
     *Gets a particular account by name by accessing the Accounts repository in AccountRepository.java
     *@return The Account object for a given name if found, otherwise null
     */
    public Account getAccountByName(String name) {
	ArrayList<Account> al = (ArrayList)accountRepository.findByUsername(name);
	if (al.isEmpty()) {
	    return null;
	}
	else {
	    return al.get(0);
	}
    }
}
