package org.cs309.backend.Alliance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.ArrayList;

/**
 *The database model for the Alliance class,
 *Users should be able to create and manage alliances
 *@author Kevin Liu
 *@author Joshua Kalyanapu
 */



@Entity
@Table(name = "alliance")
public class Alliance{
    @Id
    @Column (name = "id")
    @GeneratedValue
    private Long id;
    private Long creatorId;
    private ArrayList<String>  members;


    /**
     *Sets the id for the Alliance object
     *@param id The id of the alliance object
     */
    public void setId(Long id){
	this.id = id;
    }

    /**
     *Gets the id for the Alliance object
     *@return the id for the object
     */

    public Long getId() {
	return this.id;
    }

    /**
     *Sets the creatorid for the object
     *@param id the creator id for the object
     */
    public void setCreatorId(Long id){
	this.creatorId = id;
    }

    /**
     *Gets the creator id for the object
     *@return the creator id for the object
     */

    public Long getCreatorId()
    {
	return this.creatorId;
    }
    
    /**
     *sets the members of the object
     *@param members arraylist to be set
     */
    
    public void setMembers(ArrayList<String> members)
    {
	this.members = members;
    }

    /**
     *adds a member to the object, does not anything if the member already exists
     *@param member the member to be added
     */
    public boolean addMember(String member)
    {
	if (!members.contains(member))
	{
	    this.members.add(member);
	    return true;
	}
	else{
	    return false;
	}
    }

    /**
     *removes a member from the list, does not do anything if the given member is the creator
     *@param member the member to be removed
     */
    public boolean removeMember (String smember)
    {
	if (Long.valueOf(smember) != this.creatorId)
	    {
		this.members.remove(smember);
		return true;
	    }
	else{
	return false;
	}
    }
    /**
     *returns the list of members in string format
     *@return the string of the mbmers
     */
    public String getMembers()
    {
	return this.members.toString();
    }

     @Override
     public String toString() {
	 String toReturn = new String();
	 toReturn += "\"" + this.id + "\": ";
	 toReturn += "{";
	 toReturn += "\"creatorId\": \"" + this.creatorId + "\", ";
	 toReturn += "\"members\": \"" + this.getMembers() + "\"";
	 toReturn += "}";
	 return toReturn;
    }
    
}

	
