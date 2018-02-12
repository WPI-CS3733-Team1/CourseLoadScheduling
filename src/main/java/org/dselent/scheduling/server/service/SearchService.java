package org.dselent.scheduling.server.service;

import java.util.List;

import org.dselent.scheduling.server.model.CompleteSection;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;

public interface SearchService {
	
	//when searching department, might execute ALL functions applicable
	//OR right method for specifically searching by department
	
	//covers username, email
	public List<User> searchFaculty(String deptID);
	
	
	//department ID
	public List<Course> searchCourse(String deptID);
	
	
	public List<Section> getSectionsByCourseID(Integer courseID);
	
}
