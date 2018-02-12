package org.dselent.scheduling.server.miscellaneous;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Register all custom SQL query files here
 * 
 * @author dselent
 *
 */


public class QueryPathConstants
{
	private static String BASE_QUERY_PATH = "sql" + File.separator + "Active" + File.separator;
	private static String SQL_EXTENSION = ".sql";

	private static String USERS_WITH_ROLE_PATH = BASE_QUERY_PATH + "CustomUsersWithRole" + SQL_EXTENSION;
	private static String FETCH_SECTIONS_PATH = BASE_QUERY_PATH + "FetchSections" + SQL_EXTENSION;
	//private static String CHANGE_PASSWORD_PATH = BASE_QUERY_PATH + "ChangePassword" + SQL_EXTENSION;
	//private static String REQUEST_CHANGE_PATH = BASE_QUERY_PATH + "RequestChange" + SQL_EXTENSION;
	//private static String RESOLVE_MESSAGE_PATH = BASE_QUERY_PATH + "ResolveMessage" + SQL_EXTENSION;
	private static String SEARCH_ACADEMIC_TERM_PATH = BASE_QUERY_PATH + "SearchAcademicTerm" + SQL_EXTENSION;
	private static String SEARCH_ACADEMIC_YEAR_PATH = BASE_QUERY_PATH + "SearchAcademicYear" + SQL_EXTENSION;
	private static String SEARCH_COURSE_NAME_PATH = BASE_QUERY_PATH + "SearchCourseName" + SQL_EXTENSION;
	private static String SEARCH_COURSE_NUMBER_PATH = BASE_QUERY_PATH + "SearchCourseNumber" + SQL_EXTENSION;
	private static String SEARCH_CRN_PATH = BASE_QUERY_PATH + "SearchCRN" + SQL_EXTENSION;
	private static String SEARCH_DAYS_PER_WEEK_PATH = BASE_QUERY_PATH + "SearchDaysPerWeek" + SQL_EXTENSION;
	private static String SEARCH_DEPT_ID_PATH = BASE_QUERY_PATH + "SearchDeptId" + SQL_EXTENSION;
	private static String SEARCH_END_TIME_PATH = BASE_QUERY_PATH + "SearchEndTime" + SQL_EXTENSION;
	private static String SEARCH_EXPECTED_POPULATION_PATH = BASE_QUERY_PATH + "SearchExpectedPopulation" + SQL_EXTENSION;
	private static String SEARCH_REQUIRED_FREQUENCY_PATH = BASE_QUERY_PATH + "SearchRequiredFrequency" + SQL_EXTENSION;
	private static String SEARCH_START_TIME_PATH = BASE_QUERY_PATH + "SearchStartTime" + SQL_EXTENSION;
	private static String SEARCH_USERS_PATH = BASE_QUERY_PATH + "SearchUser" + SQL_EXTENSION;
	//private static String VIEW_INBOX_PATH = BASE_QUERY_PATH + "ViewInbox" + SQL_EXTENSION;
	private static String VIEW_SCHEDULE_PATH = BASE_QUERY_PATH + "ViewSchedule" + SQL_EXTENSION;
	private static String GET_COURSE_NAMES = BASE_QUERY_PATH + "GetCourseLabel" + SQL_EXTENSION;
			
	/////////////////////////////////////////////////////////////////////////////////////////////////
	

	public static String USERS_WITH_ROLE_QUERY = readFile(USERS_WITH_ROLE_PATH);
	public static String FETCH_SECTIONS_QUERY = readFile(FETCH_SECTIONS_PATH);
	//public static String CHANGE_PASSWORD_QUERY = readFile(CHANGE_PASSWORD_PATH);
	//public static String REQUEST_CHANGE_QUERY = readFile(REQUEST_CHANGE_PATH);
	//public static String RESOLVE_MESSAGE_QUERY = readFile(RESOLVE_MESSAGE_PATH);
	public static String SEARCH_ACADEMIC_TERM_QUERY = readFile(SEARCH_ACADEMIC_TERM_PATH);
	public static String SEARCH_ACADEMIC_YEAR_QUERY = readFile(SEARCH_ACADEMIC_YEAR_PATH);
	public static String SEARCH_COURSE_NAME_QUERY = readFile(SEARCH_COURSE_NAME_PATH);
	public static String SEARCH_COURSE_NUMBER_QUERY = readFile(SEARCH_COURSE_NUMBER_PATH);
	public static String SEARCH_CRN_QUERY = readFile(SEARCH_CRN_PATH);
	public static String SEARCH_DAYS_PER_WEEK_QUERY = readFile(SEARCH_DAYS_PER_WEEK_PATH);
	public static String SEARCH_DEPT_ID_QUERY = readFile(SEARCH_DEPT_ID_PATH);
	public static String SEARCH_END_TIME_QUERY = readFile(SEARCH_END_TIME_PATH);
	public static String SEARCH_EXPECTED_POPULATION_QUERY = readFile(SEARCH_EXPECTED_POPULATION_PATH);
	public static String SEARCH_REQUIRED_FREQUENCY_QUERY = readFile(SEARCH_REQUIRED_FREQUENCY_PATH);
	public static String SEARCH_START_TIME_QUERY = readFile(SEARCH_START_TIME_PATH);
	public static String SEARCH_USERS_QUERY = readFile(SEARCH_USERS_PATH);
	//public static String VIEW_INBOX_QUERY = readFile(VIEW_INBOX_PATH);
	public static String GET_SCHEDULE_QUERY = readFile(VIEW_SCHEDULE_PATH);
	public static String GET_COURSE_NAMES_QUERY = readFile(GET_COURSE_NAMES);
	
	
	private QueryPathConstants()
	{
		
	}
	
	public static String readFile(String path)
	{
		byte[] encodedbytes = null;
		
		try
		{
			Resource resource = new ClassPathResource(path);
			encodedbytes = Files.readAllBytes(Paths.get(resource.getURI()));
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		return new String(encodedbytes);
	}

}
