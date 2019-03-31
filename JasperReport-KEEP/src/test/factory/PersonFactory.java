package test.factory;

import java.util.Arrays;
import java.util.List;

import test.model.Person;

public class PersonFactory {

	private static Person[] data =
		{
			new Person("Mario", 23, "Italy"),
			new Person("Luigi", 31, "Italy"),
			new Person("Fabrizio", 36, "Italy"),			
			new Person("Gonzales", 18, "Mexico"),
			new Person("John", 15, "Canada"),
			new Person("Mark", 44, "Canada"),
		};
	
	public static List<Person> getPersons()
	{
		return Arrays.asList(data);
	}

}