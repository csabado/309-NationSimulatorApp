package game;

import java.util.Scanner;

public class Decision {
	public String NationName;
	public int Population;
	public int PublicUnrest;
	public int MilitaryIndex = Population/100;
	public String ImmigrationPolicy;
	public float Taxrate;
	public boolean Over;
	

	public Decision (Nation_Class nation) {
		this.NationName = nation.NationName;
		this.ImmigrationPolicy = nation.ImmigrationPolicy;
		this.Taxrate = nation.Taxrate;
	}
	
	void Change_Name(Scanner input, Nation_Class nation) {
		System.out.println("Would you like to change your name?");
		System.out.println("yes = 0, no = 1");
		int n = input.nextInt();
		if (n == 0) {
			System.out.println("Ender new NationName");
			String name = input.nextLine();
			nation.NationName = name;
		}
	}
	
	
	void Change_IP (Scanner input, Nation_Class nation) {
		System.out.println("Would you like to change your Immigration Policy?");
		System.out.println("yes = 0, no = 1");
		int n = input.nextInt();
		if (n == 0) {
			nation.setImmigrationPolicy(input);
		}
	}
	
	void Change_Taxrate (Scanner input, Nation_Class nation) {
		System.out.println("Would you like to change your taxrate?");
		System.out.println("yes = 0, no = 1");
		int n = input.nextInt();
		if (n == 0) {
			nation.SetTax(input);
		}
	}
}
