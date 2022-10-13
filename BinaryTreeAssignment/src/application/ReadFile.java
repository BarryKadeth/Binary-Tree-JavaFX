package application;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	
	public ArrayList<Person> personList = new ArrayList <Person> ();
	private int numberOfPeople;

	public ReadFile(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
		openFile();
		System.out.println(personList.toString());
	}
	
	/**
	 * Method to open file of names
	 */
	public void openFile() {
		try {
			Scanner sc = new Scanner (new File ("Names1.txt"));
			
			//create count to take only number of people input
			int count = 0;
			while (sc.hasNext() && count < numberOfPeople ) {
				count++;
				String firstName = sc.next();
				String lastName = sc.next();
				int age = randomAge();
				Person person = new Person(firstName,lastName,age);
				personList.add(person);
				
				sc.nextLine();
				

			}
			sc.close();
			
		}
		catch (IOException e){
			System.out.println("Error: " + e);
		}
		
	}
	
	/**
	 * Generate random age: number between 0 and 110
	 */
	public int randomAge() {
		int age = (int) Math.floor(Math.random()*110);
		return age;
	}
	

}
