package org.dselent.scheduling.server.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.dao.UsersRolesLinksDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Message;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.UsersRolesLink;
import org.dselent.scheduling.server.requests.GetMessage;
import org.dselent.scheduling.server.requests.GetSidebarInfo;
import org.dselent.scheduling.server.requests.Register;
import org.dselent.scheduling.server.requests.ResetPassword;
import org.dselent.scheduling.server.requests.ResolveMessage;
import org.dselent.scheduling.server.requests.ScheduleChangeRequest;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.jsonpath.internal.filter.LogicalOperator;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration

public class UsersControllerTest
{
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private UsersRolesLinksDao usersRolesLinksDao;
	
	@Autowired
	private CustomDao customDao;
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup()
	{
		// initializes controllers and dependencies
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
   /*
	@Test
	public void testUsersController() throws Exception
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(Register.getBodyName(Register.BodyKey.USER_NAME), "dselent");
		jsonObject.put(Register.getBodyName(Register.BodyKey.FIRST_NAME), "Doug");
		jsonObject.put(Register.getBodyName(Register.BodyKey.LAST_NAME), "Selent");
		jsonObject.put(Register.getBodyName(Register.BodyKey.PASSWORD), "password1");
		jsonObject.put(Register.getBodyName(Register.BodyKey.EMAIL), "dselent@wpi.edu");
		String jsonString = jsonObject.toString();
		
		System.out.println(jsonString);
		
		this.mockMvc.perform(post("/user/register").content(jsonString)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.characterEncoding("utf-8"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isInternalServerError());
		//.andExpect(content().contentType("application/json"));
		
	}*/
	
//	@Test
//	public void testAddMessage() throws Exception {
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put(ScheduleChangeRequest.getBodyName(ScheduleChangeRequest.BodyKey.USER_NAME), "bnelson");
//		jsonObject.put(ScheduleChangeRequest.getBodyName(ScheduleChangeRequest.BodyKey.DEPT_ID), "6");
//		jsonObject.put(ScheduleChangeRequest.getBodyName(ScheduleChangeRequest.BodyKey.USER_ID), "3");
//		jsonObject.put(ScheduleChangeRequest.getBodyName(ScheduleChangeRequest.BodyKey.MESSAGE), "I hate my schedule. Please change it.");
//		
//		
//		String jsonString = jsonObject.toString();
//		
//		this.mockMvc.perform(post("/user/schedule_change_request").content(jsonString)
//		        .contentType(MediaType.APPLICATION_JSON_VALUE)
//		        .characterEncoding("utf-8"))
//		        .andDo(MockMvcResultHandlers.print())
//		        .andExpect(status().isInternalServerError());
//	}
//	
//	@Test
//	public void testGetMessage() throws Exception {
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put(GetMessage.getBodyName(GetMessage.BodyKey.MESSAGE_ID), "4");
//		
//		String jsonString = jsonObject.toString();
//		
//		this.mockMvc.perform(post("/user/get_message").content(jsonString)
//		        .contentType(MediaType.APPLICATION_JSON_VALUE)
//		        .characterEncoding("utf-8"))
//		        .andDo(MockMvcResultHandlers.print())
//		        .andExpect(status().isInternalServerError());
//	}
//    
//    	 
//	@Test
//    public void testResetPassword() throws Exception
//    {
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put(ResetPassword.getBodyName(ResetPassword.BodyKey.USER_NAME), "dselent");
//		jsonObject.put(ResetPassword.getBodyName(ResetPassword.BodyKey.NEW_PASSWORD), "newpassword");
//		String jsonString = jsonObject.toString();
//
//		System.out.println(jsonString);
//
//		this.mockMvc.perform(post("/user/reset_password").content(jsonString)
//				.contentType(MediaType.APPLICATION_JSON_VALUE)
//				.characterEncoding("utf-8"))
//		.andDo(MockMvcResultHandlers.print())
//		.andExpect(status().isOk());
//		//.andExpect(content().contentType("application/json"));
//        
//    }
	
	@Test
	public void testGetSidebarInfo() throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GetSidebarInfo.getBodyName(GetSidebarInfo.BodyKey.USER_NAME), "dselent");
		String jsonString = jsonObject.toString();

		System.out.println(jsonString);

		this.mockMvc.perform(post("/user/sidebar_info").content(jsonString)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk());
		//.andExpect(content().contentType("application/json"));
	}

	@Test
	public void testResolveMessage() throws Exception
	{
		/*
		 * Add a new user to be promoted to Administrator Status
		 */
		User admin1 = new User();
		admin1.setUserName("admin1");
		admin1.setFirstName("admin");
		admin1.setLastName("one");
		admin1.setEmail("adminone@wpi.edu");
		admin1.setEncryptedPassword("2222222"); // simplified for now
		admin1.setSalt("2222222"); // also simplified for now
		admin1.setUserStateId(1); // assumes 1 = activated
		
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
		
		usersDao.insert(admin1, insertColumnNameList, keyHolderColumnNameList);

		/*
		 * Promote admin1 to administrator status
		 */

		List<User> fetchedUser = customDao.getUser("admin1");
		Integer newRoleId = 2;
		Integer userId = fetchedUser.get(0).getId();
		List<QueryTerm> updateQueryTermList = new ArrayList<>();
	
		String updateRoleColumnName = UsersRolesLink.getColumnName(UsersRolesLink.Columns.ROLE_ID);
		String checkUserIdColumnName = UsersRolesLink.getColumnName(UsersRolesLink.Columns.USER_ID);
		
		QueryTerm updateRoleIdTerm = new QueryTerm();
		updateRoleIdTerm.setColumnName(checkUserIdColumnName);
		updateRoleIdTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		updateRoleIdTerm.setValue(userId);
		updateQueryTermList.add(updateRoleIdTerm);
	
		usersRolesLinksDao.update(updateRoleColumnName, newRoleId, updateQueryTermList);

		/*
		 * Add a message to inbox table
		 */

		Message message1 = new Message();
		message1.setAuthorUserName("dselent");
		message1.setDeptId(6);
		message1.setMessage("Testing the Inbox");
		message1.setUserId(12);
		message1.setId(1);

		/*
		 * Actual Test starts here?
		 */

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(ResolveMessage.getBodyName(ResolveMessage.BodyKey.ADMINISTRATOR_USERNAME), "admin1");
		jsonObject.put(ResolveMessage.getBodyName(ResolveMessage.BodyKey.MESSAGE_ID), "1");
		String jsonString = jsonObject.toString();
		
		System.out.println(jsonString);
		
		this.mockMvc.perform(post("/user/resolve_message").content(jsonString)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.characterEncoding("utf-8"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk());

		//.andExpect(content().contentType("application/json"));

	}
}
