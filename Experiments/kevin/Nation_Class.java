package game;

public class Nation_Class {
	//public variables for Nation statistics to be displayed nicely later
	//name of the Nation
	public String NationName;
	public int Population;
	public int PublicUnrest;
	public int MilitaryIndex;
	public String ImmigrationPolicy;
	public float Taxrate;
	public boolean Over;
	
	//initializer
	public Nation_Class(String name, int initpop) {
		setNationName(name);
		setPopulation(initpop);
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
	
	public void setImmigrationPolicy (int selection) {
		String[] lop = {"none", "strict", "open"};
		if (selection > 2 || selection < 0) {
			System.out.println( "input out of range, please try again");
		}
		else {
			this.ImmigrationPolicy = lop[selection];
		}
	}
	public void setTaxes(float tax) {
		this.Taxrate = tax;
	}
	
}
