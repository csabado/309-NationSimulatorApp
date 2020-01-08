package org.cs309.backend.Issue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *The database model that represents an issue.
 *Depending on how the issue is answered, the civilUnrest, economicIndex, and militaryIndex of a nation will be affected
 *@author Joshua Kalyanapu
 *@author Kevin Liu
 */

@Entity
@Table(name="issues")
public class Issue {
    @Id
    @GeneratedValue
    private Long id;
    
    private String title;
    private String body;
    private Integer economicIndexEffectYes;
    private Integer economicIndexEffectNo;
    private Integer economicIndexEffectNothing;
    private Integer militaryIndexEffectYes;
    private Integer militaryIndexEffectNo;
    private Integer militaryIndexEffectNothing;
    private Integer civilUnrestEffectYes;
    private Integer civilUnrestEffectNo;
    private Integer civilUnrestEffectNothing;

    /**
     *Empty Issue constructor. For creating a new issue. 
     */
    public Issue() {
	
    }

    /**
     *Constructor for accessing an existing issue
     *@param id The id for the issue
     */
    public Issue(Long id) {
	this.id = id;
    }

    /**
     *Gets the ID for the issue
     *@return The id for the issue
     */
    public Long getId() {
	return this.id;
    }

    /**
     *Gets the title for the issue
     *@return The title for the issue
     */
    public String getTitle() {
	return this.title;
    }

    /**
     *Gets the body for the issue
     *@return The body for the issue
     */
    public String getBody() {
	return this.body;
    }

    /**
     *Gets the effect on the economic index if the user answers yes to the issue
     *@return The effect on the economic index if the user answers yes to the issue
     */
    public Integer getEconomicIndexEffectYes() {
	return this.economicIndexEffectYes;
    }

    /**
     *Gets the effect on the economic index if the user answers no to the issue
     *@return The effect on the economic index if the user answers no to the issue
     */
    public Integer getEconomicIndexEffectNo() {
	return this.economicIndexEffectNo;
    }
    
    /**
     *Gets the effect on the economic index if the user does not answer the issue
     *@return The effect on the economic index if the user does not answer the issue
     */
    public Integer getEconomicIndexEffectNothing() {
	return this.economicIndexEffectNothing;
    }

    /**
     *Gets the effect on the military index if the user answers yes to the issue
     *@return The effect on the military index if the user answers yes to the issue
     */
    public Integer getMilitaryIndexEffectYes() {
	return this.militaryIndexEffectYes;
    }

    /**
     *Gets the effect on the military index if the user answers no to the issue
     *@return The effect on the military index if the user answers no to the issue
     */    
    public Integer getMilitaryIndexEffectNo() {
	return this.militaryIndexEffectNo;
    }

    /**
     *Gets the effect on the military index if the user does not answer the issue
     *@return The effect on the military index if the user does not answer the issue
     */
    public Integer getMilitaryIndexEffectNothing() {
	return this.militaryIndexEffectNothing;
    }

    /**
     *Gets the effect on the civil unrest if the user answers yes to the issue
     *@return The effect on the civil unrest if the user answers yes to the issue
     */
    public Integer getCivilUnrestEffectYes() {
	return this.civilUnrestEffectYes;
    }

    /**
     *Gets the effect on the civil unrest if the user answers no to the issue
     *@return The effect on the civil unrest if the user answers no to the issue
     */    
    public Integer getCivilUnrestEffectNo() {
	return this.civilUnrestEffectNo;
    }

    /**
     *Gets the effect on the civil unrest if the user answers yes to the issue
     *@return The effect on the civil unrest if the user answers yes to the issue
     */    
    public Integer getCivilUnrestEffectNothing() {
	return this.civilUnrestEffectNothing;
    }

    /**
     *Sets the id of the Issue
     *@param id The id of the Issue
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     *Sets the title of the Issue
     *@param title The title of the issue
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     *Sets the body of the Issue
     *@param body The body of the issue
     */
    public void setBody(String body) {
	this.body = body;
    }

    /**
     *Sets the effect on the economic index if the user answers yes
     *@param economicIndexEffectYes The effect on the economic index if the user answers yes
     */
    public void setEconomicIndexEffectYes(Integer economicIndexEffectYes) {
	this.economicIndexEffectYes = economicIndexEffectYes;
    }

    /**
     *Sets the effect on the economic index if the user answers no
     *@param economicIndexEffectNo The effect on the economic index if the user answers no
     */    
    public void setEconomicIndexEffectNo(Integer economicIndexEffectNo) {
	this.economicIndexEffectNo = economicIndexEffectNo;
    }

    /**
     *Sets the effect on the economic index if the user does not answer the issue
     *@param economicIndexEffectNothing The effect on the economic index if the user does not answer the issue
     */
    public void setEconomicIndexEffectNothing(Integer economicIndexEffectNothing) {
	this.economicIndexEffectNothing = economicIndexEffectNothing;
    }

    /**
     *Sets the effect on the military index if the user answers yes
     *@param militaryIndexEffectYes The effect on the military index if the user answers yes
     */
    public void setMilitaryIndexEffectYes(Integer militaryIndexEffectYes) {
	this.militaryIndexEffectYes = militaryIndexEffectYes;
    }

    /**
     *Sets the effect on the military index if the user answers no
     *@param militaryIndexEffectNo The effect on the military index if the user answers no
     */    
    public void setMilitaryIndexEffectNo(Integer militaryIndexEffectNo) {
	this.militaryIndexEffectNo = militaryIndexEffectNo;
    }

    /**
     *Sets the effect on the military index if the user does not answer the issue
     *@param militaryIndexEffectNothing The effect on the military index if the user does not answer the issue
     */
    public void setMilitaryIndexEffectNothing(Integer militaryIndexEffectNothing) {
	this.militaryIndexEffectNothing = militaryIndexEffectNothing;
    }

    /**
     *Sets the effect on the civil unrest if the user answers yes
     *@param civilUnrestEffectYes The effect on the civil unrest if the user answers yes
     */
    public void setCivilUnrestEffectYes(Integer civilUnrestEffectYes) {
	this.civilUnrestEffectYes = civilUnrestEffectYes;
    }

    /**
     *Sets the effect on the civil unrest if the user answers no
     *@param civilUnrestEffectNo The effect on the civil unrest if the user answers no
     */    
    public void setCivilUnrestEffectNo(Integer civilUnrestEffectNo) {
	this.civilUnrestEffectNo = civilUnrestEffectNo;
    }

    /**
     *Sets the effect on the civil unrest if the user does not answer the issue
     *@param civilUnrestEffectNothing The effect on the civil unrest if the user does not answer the issue
     */
    public void setCivilUnrestEffectNothing(Integer civilUnrestEffectNothing) {
	this.civilUnrestEffectNothing = civilUnrestEffectNothing;
    }

    /**
     *Returns a JSON representation without the outer braces (to make usage of this function in the controllers easier).
     *To convert this to a proper JSON String, add a "{" and "}" to the beginning and end of the String
     *e.g With a Issue variable called i, a proper JSON String would be '{' + i.toString() + '}'
     *@return A partial JSON representation of the Issue object, without the outer braces. 
     */
    @Override
    public String toString() {
	String toReturn = new String();
	toReturn += "\"" + this.id + "\": ";
	toReturn += "{";
	toReturn += "\"title\": \"" + this.title + "\", " ;
	toReturn += "\"body\": \"" + this.body + "\", ";
	toReturn += "\"economicIndexEffectYes\": \"" + this.economicIndexEffectYes + "\", ";
	toReturn += "\"economicIndexEffectNo\": \"" + this.economicIndexEffectNo + "\", ";
	toReturn += "\"economicIndexEffectNothing\": \"" + this.economicIndexEffectNothing + "\", ";
	toReturn += "\"militaryIndexEffectYes\": \"" + this.militaryIndexEffectYes + "\", ";
	toReturn += "\"militaryIndexEffectNo\": \"" + this.militaryIndexEffectNo + "\", ";
	toReturn += "\"militaryIndexEffectNothing\": \"" + this.militaryIndexEffectNothing + "\", ";
	toReturn += "\"civilUnrestEffectYes\": \"" + this.civilUnrestEffectYes + "\", ";
	toReturn += "\"civilUnrestEffectNo\": \"" + this.civilUnrestEffectNo + "\", ";	
	toReturn += "\"civilUnrestEffectNothing\": \"" + this.civilUnrestEffectNothing + "\"";
	toReturn += "}";
	return toReturn;
    }
}
