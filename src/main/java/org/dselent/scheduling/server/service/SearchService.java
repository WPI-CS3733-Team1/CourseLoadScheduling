package org.dselent.scheduling.server.service;

import java.util.List;

import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;

public interface SearchService {
	
	//when searching department, might execute ALL functions applicable
	//OR right method for specifically searching by department
	
	//covers username, email
	public List<User> searchFaculty(String searchTerm, String keyValue);
	
	//?
	public List<Course> searchCourse(String keyValue);

	//covers CRN, Time, Term
	public List<Section> searchSection(String searchTerm, String keyValue);
	
	//list of all applicable models??? will require sort function...
	public List<Object> searchDepartment(String keyValue); 
	
}
