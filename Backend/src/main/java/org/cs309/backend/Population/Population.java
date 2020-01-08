package org.cs309.backend.Population;

import javax.persistence.*;

@Entity
@Table(name="population")
public class Population {
    @Id
    private Long id;
    
    @Column(name="lowerclass")
    private Long lowerclass;

    @Column(name="middleclass")
    private Long middleclass;

    @Column(name="upperclass")
    private Long upperclass;

    public Population(Long id, Long lowerclass, Long middleclass, Long upperclass) {
	this.id = id;
	this.lowerclass = lowerclass;
	this.middleclass = middleclass;
	this.upperclass = upperclass;
    }

    public Population() {

    }

    public Long getId() {
	return this.id;
    }
    
    public Long getLowerclassPopulation() {
	return lowerclass;
    }
    public Long getMiddleclassPopulation() {
	return middleclass;
    }
    public Long getUpperclassPopulation() {
	return upperclass;
    }

    public void setId(Long id) {
	this.id = id;
    }
    
    public void setLowerclassPopulation(Long value) {
	this.lowerclass = value;
    }

    public void setMiddleclassPopulation(Long value) {
	this.middleclass = value;
    }

    public void setUpperclassPopulation(Long value) {
	this.upperclass = value;
    }

    @Override
    public String toString() {
	String toReturn = new String();
	toReturn += "\"" + this.id + "\": ";
	toReturn += "{";
	toReturn += "\"lowerclass\": \"" + this.lowerclass + "\", " ;
	toReturn += "\"middleclass\": \"" + this.middleclass + "\", ";
	toReturn += "\"upperclass\": \"" + this.upperclass + "\"}";
	return toReturn;
    }
}
