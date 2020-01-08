package org.cs309.backend.Stats;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Column;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "stats")
public class Stats {
    @Id
    private Long id;
    @NotEmpty
    private Integer civilUnrest;
    @NotEmpty
    private Integer economicIndex;
    @NotEmpty
    private Integer militaryIndex;

    public Stats(Long id) {
	this.id = id;
    }

    public Stats() {

    }

    public Long getId() {
	return this.id;
    }
    public void setId(Long id){
	this.id = id;
    }

    public Integer getCivilUnrest() {
	return this.civilUnrest;
    }

    public void setCivilUnrest(Integer civilUnrest) {
	this.civilUnrest = civilUnrest;
    }

    public Integer getEconomicIndex() {
	return this.economicIndex;
    }

    public void setEconomicIndex(Integer economicIndex) {
	this.economicIndex = economicIndex;
    }

    public Integer getMilitaryIndex() {
	return this.militaryIndex;
    }

    public void setMilitaryIndex(Integer militaryIndex) {
	this.militaryIndex = militaryIndex;
    }

    @Override
    public String toString() {
	String toReturn = new String();
	toReturn += "\"" + this.id + "\": ";
	toReturn += "{";
	toReturn += ("\"economicIndex\": \"" + this.economicIndex + "\", ");
	toReturn += ("\"militaryIndex\": \"" + this.militaryIndex + "\", ");
	toReturn += ("\"civilUnrest\": \"" + this.civilUnrest + "\"");
	toReturn += "}";
	return toReturn;
    }
}
