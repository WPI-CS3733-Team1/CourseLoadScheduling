package org.dselent.scheduling.server.dao;

import java.util.List;

import org.dselent.scheduling.server.model.CompleteSection;
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

	public List<CompleteSection> getAllCourseWithNumber(String courseNumber);

	List<Section> getAvailableSectionsInDept(int deptID);

	List<CompleteSection> getSectionByDeptId(int deptID);

	List<User> searchUserByUsername(String userName);

	List<CompleteSection> getAllSectionsInAcademicTerm(String academicTerm);

	List<CompleteSection> getSectionsWithStartTime(int startTime);

	List<CompleteSection> getAllSectionsInAcademicYear(int academicYear);

	List<CompleteSection> getSectionsWithReqFreq(int reqFreq);

	List<CompleteSection> getCourseByName(String courseName);

	List<CompleteSection> getByCrn(int CRN);

	List<CompleteSection> getAllSectionsWithEndTime(int endTime);

	List<CompleteSection> getAllSectionsWithExpectedPopulation(int expectedPopulation);

	List<User> getUser(String userName);

	List<Section> getSchedule(int userID);
}
