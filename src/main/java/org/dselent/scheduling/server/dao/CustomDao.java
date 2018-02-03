package org.dselent.scheduling.server.dao;

import java.util.List;

import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.springframework.stereotype.Repository;

/**
 * Interface for all daos for custom queries.
 * 
 * @author dselent
 *
 */
@Repository
public interface CustomDao
{
	
	public List<User> getAllUsersWithRole(int roleId);

	public List<Course> getAllCourseWithNumber(String courseNumber);

	List<Section> getAvailableSectionsInDept(int deptID);

	List<Section> getSectionByDeptId(int deptID);

	List<User> searchUserByUsername(String userName);

	List<Section> getAllSectionsInAcademicTerm(String academicTerm);

	List<Section> getSectionsWithStartTime(int startTime);

	List<Section> getAllSectionsInAcademicYear(int academicYear);

	List<Section> getSectionsWithReqFreq(int reqFreq);

	List<Course> getCourseByName(String courseName);

	List<Section> getByCrn(int CRN);

	List<Section> getAllSectionsWithEndTime(int endTime);

	List<Section> getAllSectionsWithExpectedPopulation(int expectedPopulation);

	List<User> getUser(String userName);
}
