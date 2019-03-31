package test.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import test.model.Company;

public class CompanyFactory {

	static Company[] buildData()
	{
		List<Company> companies = new ArrayList<Company>();
		Company output = new Company();
		output.setName("Starred Spa");
		output.setAddress("Via Nomentana, 800, Rome");
		output.setPersons(PersonFactory.getPersons());
		
		companies.add(output);
		
		return companies.toArray(new Company[companies.size()]);
	}
	
	static Company[] data = buildData(); 
	
	public static List<Company> getCompanies()
	{
		return Arrays.asList(data);
	}
	
}