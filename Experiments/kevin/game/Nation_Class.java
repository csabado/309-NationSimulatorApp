package game;

import java.util.Scanner;

public class Nation_Class {
	//public variables for Nation statistics to be displayed nicely later
	//name of the Nation
	public String NationName;
	public int Population;
	public int PublicUnrest;
	public int MilitaryIndex = Population/100;
	public String ImmigrationPolicy;
	public float Taxrate;
	public boolean Over;
	
	//initializer
	public Nation_Class(String name, int initpop, Scanner scan) {
		setNationName(name);
		setPopulation(initpop);
		setPublicUnrest(0);
		setImmigrationPolicy(scan);
		SetTax(scan);
		Over = false;
	}
	
	//voids for setting the variables
	public void setNationName (String name) {
		this.NationName = name;
	}
	
	public void setPopulation(int pop) {
		this.Population = pop;
	}
	
	public void setPublicUnrest(int pu) {
		this.PublicUnrest = pu;
	}
	
	public void setMilitaryIndex(int mi) {
		this.MilitaryIndex = mi;
	}
	
	public void setImmigrationPolicy (Scanner poliRead) {
		String[] lop = {"none", "strict", "open"};
		boolean finished = false;
		while (!finished) {
			System.out.println("Select your immigration policy type");
			System.out.println("0 = none, 1 = strict, 2 = open");
			int selection = poliRead.nextInt();
			if (selection <0 || selection > 2) {
				System.out.println("Input out of range, please try again");
			}
			else {
				this.ImmigrationPolicy = lop[selection];
				finished = true;
			}
			
		}

	}
	
	public void setTaxes(float tax) {
		this.Taxrate = tax;
	}
	
	public void SetTax(Scanner taxRead) {
		System.out.println("Input tax rate percentage");
		System.out.println("EX 50% would be inputed as 0.50");
		float n = taxRead.nextFloat();
		setTaxes(n);
	}
	
	public void listStats() {
		System.out.println("Name: " + this.NationName + ", Population: " + this.Population + ", Public unrest: " 
				+ this.PublicUnrest + ", Military Index: " + this.MilitaryIndex + ", Tax rate: " + this.Taxrate + 
				", Immigration Policy: " + this.ImmigrationPolicy);
		
	}
}
