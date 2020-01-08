package org.cs309.backend.Military;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

//TODO add in error checking (infantry + airforce + navy cannot exceed population)

@Entity
@Table(name="military")
public class Military {
    @Id
    private Long id; //the ID of the nation

    @Column(name="infantry")
    private Long infantry;

    @Column(name="airforce")
    private Long airforce;

    @Column(name="navy")
    private Long navy;

    public Military() {
	this.infantry = new Long(0);
	this.airforce = new Long(0);
	this.navy = new Long(0);
    }

    public Military(Long id, Long infantry, Long airforce, Long navy) {
	this.id = id;
	this.infantry = infantry;
	this.airforce = airforce;
	this.navy = navy;
    }

    public Long getId() {
	return this.id;
    }
    
    public Long getInfantry() {
	return this.infantry;
    }

    public Long getAirforce() {
	return this.airforce;
    }

    public Long getNavy() {
	return this.navy;
    }

    public void setId(Long id) {
	this.id = id;
    }
    
    public void setInfantry(Long infantry) {
	this.infantry = infantry;
    }

    public void setAirforce(Long airforce) {
	this.airforce = airforce;
    }

    public void setNavy(Long navy) {
	this.navy = navy;
    }

    @Override
    public String toString() {
	String toReturn = new String();
	toReturn += "\"" + this.id + "\": ";
	toReturn += "{";
	toReturn += "\"infantry\": \"" + this.infantry + "\", " ;
	toReturn += "\"airforce\": \"" + this.airforce + "\", ";
	toReturn += "\"navy\": \"" + this.navy + "\"}";
	return toReturn;
    }
}
    
