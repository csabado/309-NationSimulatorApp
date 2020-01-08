package org.cs309.backend.Gov;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Column;

import org.springframework.core.style.ToStringCreator;

/**
 *The database model that represents a nation's government. Currently tracks the type of government and the amount of funds that the government has
 *@author Joshua Kalyanapu
 *@author Kevin Liu
 */

@Entity
@Table(name = "gov")
public class Gov {
    @Id
    @Column (name="id")
    private Long id;

    private String type;

    private Long money;
    
    /**Gov constructor for testing*/
    public Gov(Long id, String type, Long money)
    {
        this.id = id;
        this.type = type;
        this.money = money;
    }

    /**
     *Gets the type of government
     *@return The String representation of the type of government
     */
    public String getType()
    {
	return this.type;
    }

    /**
     *Sets the type of government
     *@param type A String representation of the type of government
     */
    public void setType(String type)
    {
	this.type = type;
    }

    /**
     *Gets the amount of money the nation has
     *@return The amount of money the nation has
     */
    public Long getMoney() {
	return this.money;
    }

    /**
     *Sets the amount of money the nation has
     *@param money The new amount of money the nation has
     */
    public void setMoney(Long money) {
	this.money = money;
    }

    /**
     *Returns a JSON representation without the outer braces (to make usage of this function in the controllers easier).
     *To convert this to a proper JSON String, add a "{" and "}" to the beginning and end of the String
     *e.g With a Gov variable called g, a proper JSON String would be '{' + g.toString() + '}'
     *@return A partial JSON representation of the Gov object, without the outer braces. 
     */
    @Override
    public String toString() {
	String toReturn = new String();
	toReturn += "\"" + this.id + "\": ";
	toReturn += "{";
	toReturn += "\"type\": \"" + this.type + "\", ";
	toReturn += "\"money\": \"" + this.money + "\"";
	toReturn += "}";
	return toReturn;
    }
}
