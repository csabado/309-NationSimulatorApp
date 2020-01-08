package org.cs309.backend.Account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 *This is the account entity. It stores usernames, passwords, and ids and can be used to map usernames to ids
 *@author Joshua Kalyanapu
 */
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    /**
     *The Account constructor. Initializes the private username and password variables
     *@param username The username of the account
     *@param password The password of the account
     *
     */
    public Account(String username, String password) {
	this.username = username;
	this.password = password;
    }
    
    /**
     *The Account constructor. Leaves the username and password as is.
     *
     */
    public Account() {}

    /**
     *Returns the ID for an Account
     *@return id The ID for the Account
     */
    public long getId() {
	return id;
    }

    /**
     *Returns the username for an Account
     *@return username The username for the Account
    */
    public String getUsername() {
	return username;
    }

   /**
     *Returns the password for an Account
     *@return password The password for the Account
     */
    public String getPassword() {
	return password;
    }

    /**
     *Sets the password for an Account
     *@param password The new password for an Account in plaintext
     */
    public void setPassword(String password) {
	this.password = password;
    }
}
