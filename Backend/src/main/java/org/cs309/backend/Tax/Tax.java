package org.cs309.backend.Tax;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="taxes")
public class Tax {
    @Id
    private Long id;

    @Column(name="lowerclass")
    private double lowerclass;

    @Column(name="middleclass")
    private double middleclass;

    @Column(name="upperclass")
    private double upperclass;

    public Tax() {}

    public Tax(Long id, Double lowerclass, Double middleclass, Double upperclass) {
	this.id = id;
	this.lowerclass = lowerclass;
	this.middleclass = middleclass;
	this.upperclass = upperclass;
    }

    public Tax(Double lowerclass, Double middleclass, Double upperclass) {
	this.lowerclass = lowerclass;
	this.middleclass = middleclass;
	this.upperclass = upperclass;
    }
    
    long getId() {
	return id;
    }

    double getLowerclass() {
	return lowerclass;
    }

    double getMiddleclass() {
	return middleclass;
    }

    double getUpperclass() {
	return upperclass;
    }

    void setId(Long id) {
	this.id = id;
    }
    
    void setLowerclass(double d) {
	this.lowerclass = d;
    }

    void setMiddleclass(double d) {
	this.middleclass = d;
    }

    void setUpperclass(double d) {
	this.upperclass = d;
    }

    @Override
    public String toString() {
	String toReturn = new String();
	toReturn += "\"" + this.id + "\": ";
	toReturn += "{";
	toReturn += "\"lowerclass\": \"" + this.lowerclass + "\", " ;
	toReturn += "\"middleclass\": \"" + this.middleclass + "\", ";
	toReturn += "\"upperclass\": \"" + this.upperclass + "\"";
	toReturn += "}";
	return toReturn;
    }
}
