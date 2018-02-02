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
	private static String FETCH_SCHEDULE_PATH = BASE_QUERY_PATH + "FetchSchedule" + SQL_EXTENSION;
	private static String CHANGE_PASSWORD_PATH = BASE_QUERY_PATH + "ChangePassword" + SQL_EXTENSION;
	private static String REQUEST_CHANGE_PATH = BASE_QUERY_PATH + "RequestChange" + SQL_EXTENSION;
	private static String RESOLVE_MESSAGE_PATH = BASE_QUERY_PATH + "ResolveMessage" + SQL_EXTENSION;
	private static String SEARCH_ACADEMIC_TERM_PATH = BASE_QUERY_PATH + "SearchAcademicTerm" + SQL_EXTENSION;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	

	public static String USERS_WITH_ROLE_QUERY = readFile(USERS_WITH_ROLE_PATH);
	public static String FETCH_SCHEDULE_QUERY = readFile(FETCH_SCHEDULE_PATH);
	public static String CHANGE_PASSWORD_QUERY = readFile(CHANGE_PASSWORD_PATH);
	public static String REQUEST_CHANGE_QUERY = readFile(REQUEST_CHANGE_PATH);
	public static String RESOLVE_MESSAGE_QUERY = readFile(RESOLVE_MESSAGE_PATH);
	public static String SEARCH_ACADEMIC_TERM_QUERY = readFile(SEARCH_ACADEMIC_TERM_PATH);
	
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
