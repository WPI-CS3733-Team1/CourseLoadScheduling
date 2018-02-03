package org.dselent.scheduling.server.dao.impl;

import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.extractor.CoursesExtractor;
import org.dselent.scheduling.server.extractor.SectionsExtractor;
import org.dselent.scheduling.server.extractor.UsersExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CustomDaoImpl implements CustomDao
{
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// can make custom models and extractors as needed or reuse existing ones if applicable
	
	@Override
	public List<User> getAllUsersWithRole(int roleId)
	{
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.USERS_WITH_ROLE_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("roleID", roleId);
	    List<User> usersWithRoleList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return usersWithRoleList;
	}

	@Override
	public List<Section> getAvailableSectionsInDept(int deptID) {
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.FETCH_SECTIONS_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("deptID", deptID);
		List<Section> availableSections = namedParameterJdbcTemplate.query(queryTemplate, extractor);
		
		return availableSections;
	}
	
	@Override
	public List<Section> getSectionByDeptId(int deptID) {
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_DEPT_ID_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("deptID", deptID);
		List<Section> availableSections = namedParameterJdbcTemplate.query(queryTemplate, extractor);
		
		return availableSections;
	}
	
	@Override
	public List<User> searchUserByUsername(String userName)
	{
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_USERS_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userName", userName );
		List<User> searchedUsers = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return searchedUsers;
	}
	
	@Override
	public List<Section> getAllSectionsInAcademicTerm(String academicTerm)
	{
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_ACADEMIC_TERM_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("academicTerm", academicTerm);
		List<Section> sectionsInAcademicTermsList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsInAcademicTermsList;
	}
	
	@Override
	public List<Section> getSectionsWithStartTime(int startTime)
	{
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_START_TIME_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("startTime", startTime);
		List<Section> sectionsWithStartTime = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsWithStartTime;
	}
	
	@Override
	public List<Section> getAllSectionsInAcademicYear(int academicYear)
	{
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_ACADEMIC_YEAR_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("academicYear", academicYear);
		List<Section> sectionsInAcademicYearList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsInAcademicYearList;
	}

	@Override
	public List<Section> getSectionsWithReqFreq(int reqFreq)
	{
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_REQUIRED_FREQUENCY_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("requiredFrequency", reqFreq);
		List<Section> sectionsWithReqFreg = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsWithReqFreg;
	}
	
	@Override
	public List<Course> getCourseByName(String courseName)
	{
		CoursesExtractor extractor = new CoursesExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_COURSE_NAME_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("courseName", courseName);
		List<Course> searchedCourses = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return searchedCourses;	
	}
	
	
	@Override
	public List<Section> getByCrn(int CRN)
	{
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_CRN_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("CRN", CRN);
		List<Section> sectionsWithCrn = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsWithCrn;
		
	}
	
	@Override
	public List<Course> getAllCourseWithNumber(String courseNumber)
	{
		CoursesExtractor extractor = new CoursesExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_COURSE_NUMBER_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("courseNumber", courseNumber);
		List<Course> coursesWithNumberList = namedParameterJdbcTemplate.query(queryTemplate,parameters, extractor);
		return coursesWithNumberList;
	}
	
	@Override
	public List<Section> getAllSectionsWithEndTime(int endTime)
	{
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_END_TIME_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("endTime", endTime);
		List<Section> sectionsWithEndTimeList = namedParameterJdbcTemplate.query(queryTemplate,parameters, extractor);
		return sectionsWithEndTimeList;
	}
	
	@Override
	public List<Section> getAllSectionsWithExpectedPopulation(int expectedPopulation)
	{
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_EXPECTED_POPULATION_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("expectedPopulation", expectedPopulation);
		List<Section> sectionsWithExpectedPopulationList = namedParameterJdbcTemplate.query(queryTemplate,parameters, extractor);
		return sectionsWithExpectedPopulationList;
	}
	
	@Override
	public List<User> getUser(String userName)
	{
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_USERS_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userName", userName);
		List<User> usersList = namedParameterJdbcTemplate.query(queryTemplate,parameters, extractor);
		return usersList;
	}
	
}
