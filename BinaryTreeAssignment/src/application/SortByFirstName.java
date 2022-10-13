package application;
import java.util.Comparator;

public class SortByFirstName implements Comparator<Person> {
	
	//Add all the comparing into here
	//ANd return 0 if they are all equal. 

	public SortByFirstName()  {
	}

	@Override
	public int compare(Person a, Person b) {
		if (a.getFirstName().compareTo(b.getFirstName()) != 0) {
			return a.getFirstName().compareTo(b.getFirstName());
		} else if (a.getLastName().compareTo(b.getLastName()) != 0) {
			return a.getLastName().compareTo(b.getLastName());
		} else if (a.getAge() != (b.getAge())) {
			return a.getAge() - b.getAge();
		} else {
			//If they are the same name, automatically puts it at the bottom to the right. 
			return +1;
		}
	}
}
