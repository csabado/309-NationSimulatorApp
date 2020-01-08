package game;
import java.util.Scanner;

public class TestNationClass {
	
	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		Nation_Class Hello_World = new Nation_Class("Hello World", 900, input);
		Hello_World.listStats();
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		while (!Hello_World.Over) {
			System.out.println("A new Year Begins under your reign");
			System.out.println();
			
			if (Hello_World.Taxrate > 0.25) {
				System.out.println("High taxes stirs your population against you");
				System.out.println();
				Hello_World.setPublicUnrest(Hello_World.PublicUnrest + 10);
			}
			if (Hello_World.MilitaryIndex < 10) {
				System.out.println("A  powerful neighboring nation atacks and kills some of your people");
				System.out.println();
				Hello_World.setPopulation(Hello_World.Population - 100);
			}
			
			if(Hello_World.ImmigrationPolicy == "none") {
				System.out.println("A huge number of people immigrate to your nation, which upsets the locals");
				System.out.println();
				Hello_World.setPublicUnrest(Hello_World.PublicUnrest + 1);
				Hello_World.setPopulation(Hello_World.Population + 100);
			}
			
			if(Hello_World.ImmigrationPolicy == "strict") {
				System.out.println("Not very many people immigrate to your nation, an abundance of jobs seems to make the locals happier");
				System.out.println();
				Hello_World.setPopulation(Hello_World.Population + 10);
				Hello_World.setPublicUnrest(Hello_World.PublicUnrest - 1);
			}
			if (Hello_World.ImmigrationPolicy == "open") {
				System.out.println("An average number of people immigrate to your nation, locals remain nuetral");
				System.out.println();
				Hello_World.setPopulation(Hello_World.Population + 50);

			}
			System.out.println("The year is over would you like to see the status of your country?");
			System.out.println("0 = yes, any other number = no");
			int n = input.nextInt();
			if (n == 0) {
				Hello_World.listStats();
				System.out.println();
			}
			if (Hello_World.PublicUnrest >= 100) {
				System.out.println("The public revolts and your reign is over");
				System.out.println();
				Hello_World.Over = true;
			}
			if(Hello_World.Population <= 0) {
				System.out.println("War has destroyed your population");
				System.out.println();
				Hello_World.Over = true;
			}
			

		}
		
	}

}

