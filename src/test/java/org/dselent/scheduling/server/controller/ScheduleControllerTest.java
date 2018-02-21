package org.dselent.scheduling.server.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.requests.CreateSection;
import org.dselent.scheduling.server.requests.GetCourses;
import org.dselent.scheduling.server.requests.GetMessage;
import org.dselent.scheduling.server.requests.GetSchedule;
import org.dselent.scheduling.server.requests.ConfirmSchedule;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class ScheduleControllerTest {
	@Autowired
	UsersDao usersDao;
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup()
	{
		//initializes controllers and dependencies
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
   
	/*@Test
    public void testScheduleController() throws Exception
    {        
		JSONObject jsonObject = new JSONObject();
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.COURSE_ID), "8");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.CRN), "10134");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.SECTION_NAME), "A01");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.SECTION_ID), "1");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.EXPECTED_POP), "20");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.REQUIRED_FREQ), "3");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.ACADEMIC_YEAR), "2018");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.ACADEMIC_TERM), "C");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.START_TIME), "1000");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.END_TIME), "1200");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.DAYS_PER_WEEK), "MTRF");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.COURSE_LOCATION), "SL115");
    	
    	String jsonString = jsonObject.toString();
        
    	System.out.println(jsonString);
    	
        this.mockMvc.perform(post("/schedule/create_section").content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isInternalServerError());
        //.andExpect(content().contentType("application/json"));
    }*/
	
	@Test
	public void testGetCourse() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GetCourses.getBodyName(GetCourses.BodyKey.USER_NAME), "cshue");
		
		String jsonString = jsonObject.toString();
		
		System.out.println(jsonString);
		this.mockMvc.perform(post("/schedule/view_course_creation").content(jsonString)
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .characterEncoding("utf-8"))
		        .andDo(MockMvcResultHandlers.print());
		        
	}
	
	/*
	@Test
	public void testGetSchedule() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GetSchedule.getBodyName(GetSchedule.BodyKey.USER_NAME), "cshue");
		jsonObject.put(GetSchedule.getBodyName(GetSchedule.BodyKey.ACADEMIC_TERM), "A");
		
		String jsonString = jsonObject.toString();
		
		System.out.println(jsonObject.toString());		
		this.mockMvc.perform(post("/schedule/view_schedule").content(jsonString)
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .characterEncoding("utf-8"))
		        .andDo(MockMvcResultHandlers.print())
		        .andExpect(status().isInternalServerError());
	}
	*/

	/*@Test
	public void testConfirmSchedule() throws Exception {
		JSONObject jsonObject = new JSONObject();
		String userName = "cshue";

		//Creates User to test on
		User confirmTestUser = new User();
		confirmTestUser.setUserName("confirmTestUser");
		confirmTestUser.setFirstName("Confirm");
		confirmTestUser.setLastName("Test");
		confirmTestUser.setEmail("confirmtest@wpi.edu");
		confirmTestUser.setEncryptedPassword("11111111"); // simplified for now
		confirmTestUser.setSalt("11111111"); // also simplified for now
		confirmTestUser.setUserStateId(1); // assumes 1 = activated
		
		List<String> insertColumnNameList = new ArrayList<>();
		List<String> keyHolderColumnNameList = new ArrayList<>();
		
		insertColumnNameList.add(User.getColumnName(User.Columns.USER_NAME));
		insertColumnNameList.add(User.getColumnName(User.Columns.FIRST_NAME));
		insertColumnNameList.add(User.getColumnName(User.Columns.LAST_NAME));
		insertColumnNameList.add(User.getColumnName(User.Columns.EMAIL));
		insertColumnNameList.add(User.getColumnName(User.Columns.ENCRYPTED_PASSWORD));
		insertColumnNameList.add(User.getColumnName(User.Columns.SALT));
		insertColumnNameList.add(User.getColumnName(User.Columns.USER_STATE_ID));
		
		keyHolderColumnNameList.add(User.getColumnName(User.Columns.ID));
		keyHolderColumnNameList.add(User.getColumnName(User.Columns.CREATED_AT));
		keyHolderColumnNameList.add(User.getColumnName(User.Columns.UPDATED_AT));
	
		usersDao.insert(confirmTestUser, insertColumnNameList, keyHolderColumnNameList);

		//Test
		JSONObject jsonObject = new JSONObject();
		String userName = "confirmTestUser";
		List<Integer> addIdList = new ArrayList<Integer>();
		List<Integer> removeIdList = new ArrayList<Integer>();
		addIdList.add(2);
		addIdList.add(3);
		addIdList.add(4);
		jsonObject.put(ConfirmSchedule.getBodyName(ConfirmSchedule.BodyKey.USER_NAME), userName);
		jsonObject.put(ConfirmSchedule.getBodyName(ConfirmSchedule.BodyKey.ADD_SECTION_IDS), addIdList.toString());
		jsonObject.put(ConfirmSchedule.getBodyName(ConfirmSchedule.BodyKey.REMOVE_SECTION_IDS), removeIdList.toString());
		
		
		System.out.println(jsonObject.toString());
		this.mockMvc.perform(post("/schedule/confirm_schedule").content(jsonObject.toString())
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.characterEncoding("utf-8"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk());
		  
	}*/
}
