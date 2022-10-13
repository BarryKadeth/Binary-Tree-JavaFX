package application;

public class Person {
	
	private String firstName;
	private String lastName;
	private int age;
	
	
	public Person(String firstName, String lastName, int age) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAge(age);
	}
	
	public String toString () {
		String personString = getFirstName() + " " + getLastName() + " " + getAge() ;
		return personString;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	

}
