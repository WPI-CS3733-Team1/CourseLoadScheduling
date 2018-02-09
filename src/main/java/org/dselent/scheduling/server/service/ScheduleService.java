package org.dselent.scheduling.server.service;

import java.util.List;

import org.dselent.scheduling.server.model.CompleteSection;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Section;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author mtschmitt
 *
 */

@Service
public interface ScheduleService {

	//this probably has to change to return list of CompleteSection
	public List<Section> getSchedule(String userName, String academicTerm);
	
	public List<Course> getCoursesBySection(String userName);
}
