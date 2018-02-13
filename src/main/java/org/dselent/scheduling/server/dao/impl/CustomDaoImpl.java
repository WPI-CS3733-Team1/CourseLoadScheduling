package org.dselent.scheduling.server.dao.impl;

import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.extractor.CompleteSectionsExtractor;
import org.dselent.scheduling.server.extractor.CourseNamesExtractor;
import org.dselent.scheduling.server.extractor.SectionsExtractor;
import org.dselent.scheduling.server.extractor.UsersExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.CompleteSection;
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
	public List<CompleteSection> getSectionByDeptId(int deptID) {
		
		CompleteSectionsExtractor extractor = new CompleteSectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_DEPT_ID_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("deptID", deptID);
		List<CompleteSection> availableSections = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		
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
	public List<CompleteSection> getAllSectionsInAcademicTerm(String academicTerm)
	{
		CompleteSectionsExtractor extractor = new CompleteSectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_ACADEMIC_TERM_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("academicTerm", academicTerm);
		List<CompleteSection> sectionsInAcademicTermsList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsInAcademicTermsList;
	}
	
	@Override
	public List<CompleteSection> getSectionsWithStartTime(int startTime)
	{
		CompleteSectionsExtractor extractor = new CompleteSectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_START_TIME_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("startTime", startTime);
		List<CompleteSection> sectionsWithStartTime = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsWithStartTime;
	}
	
	@Override
	public List<CompleteSection> getAllSectionsInAcademicYear(int academicYear)
	{
		CompleteSectionsExtractor extractor = new CompleteSectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_ACADEMIC_YEAR_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("academicYear", academicYear);
		List<CompleteSection> sectionsInAcademicYearList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsInAcademicYearList;
	}

	@Override //ex. MTRF 
	public List<CompleteSection> getSectionsWithReqFreq(int reqFreq)
	{
		CompleteSectionsExtractor extractor = new CompleteSectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_REQUIRED_FREQUENCY_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("requiredFrequency", reqFreq);
		List<CompleteSection> sectionsWithReqFreg = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsWithReqFreg;
	}
	
	@Override
	public List<CompleteSection> getCourseByName(String courseName)
	{
		CompleteSectionsExtractor extractor = new CompleteSectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_COURSE_NAME_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("courseName", courseName);
		List<CompleteSection> searchedSections = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return searchedSections;	
	}
	
	
	@Override
	public List<CompleteSection> getByCrn(int CRN)
	{
		CompleteSectionsExtractor extractor = new CompleteSectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_CRN_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("CRN", CRN);
		List<CompleteSection> sectionsWithCrn = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsWithCrn;
		
	}
	
	@Override
	public List<CompleteSection> getAllCourseWithNumber(String courseNumber)
	{
		CompleteSectionsExtractor extractor = new CompleteSectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_COURSE_NUMBER_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("courseNumber", courseNumber);
		List<CompleteSection> coursesWithNumberList = namedParameterJdbcTemplate.query(queryTemplate,parameters, extractor);
		return coursesWithNumberList;
	}
	
	@Override
	public List<CompleteSection> getAllSectionsWithEndTime(int endTime)
	{
		CompleteSectionsExtractor extractor = new CompleteSectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_END_TIME_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("endTime", endTime);
		List<CompleteSection> sectionsWithEndTimeList = namedParameterJdbcTemplate.query(queryTemplate,parameters, extractor);
		return sectionsWithEndTimeList;
	}
	
	@Override
	public List<CompleteSection> getAllSectionsWithExpectedPopulation(int expectedPopulation)
	{
		CompleteSectionsExtractor extractor = new CompleteSectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_EXPECTED_POPULATION_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("expectedPopulation", expectedPopulation);
		List<CompleteSection> sectionsWithExpectedPopulationList = namedParameterJdbcTemplate.query(queryTemplate,parameters, extractor);
		return sectionsWithExpectedPopulationList;
	}
	
	@Override
	public List<User> getUser(String userName)
	{
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.SEARCH_USERS_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userName", userName);
		List<User> usersList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return usersList;
	}
	
	@Override
	public List<Section> getSchedule(int userID) {
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_SCHEDULE_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userID", userID);
		List<Section> schedule = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		
		return schedule;
	}

	@Override
	public List<String> getCourseNames(String userId) {
		CourseNamesExtractor ex = new CourseNamesExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_COURSE_NAMES_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userID", Integer.parseInt(userId));
		List<String> value = namedParameterJdbcTemplate.query(queryTemplate, parameters, ex);
		
		return value;
	}

	@Override
	public int updateUserRoleID(Integer userID, Integer roleID) {
		
		String queryTemplate = new String(QueryPathConstants.UPDATE_USER_ROLE_ID_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userID", userID);
		parameters.addValue("roleID", roleID);
		
		int rows = namedParameterJdbcTemplate.update(queryTemplate, parameters);
		
		
		return rows;
	}
	
}
