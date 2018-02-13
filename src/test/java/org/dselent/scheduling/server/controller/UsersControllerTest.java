package org.dselent.scheduling.server.controller;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.requests.GetMessage;
import org.dselent.scheduling.server.requests.Register;
import org.dselent.scheduling.server.requests.ResetPassword;
import org.dselent.scheduling.server.requests.ScheduleChangeRequest;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class UsersControllerTest
{
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
	
	@Test
	public void testAddMessage() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(ScheduleChangeRequest.getBodyName(ScheduleChangeRequest.BodyKey.USER_NAME), "bnelson");
		jsonObject.put(ScheduleChangeRequest.getBodyName(ScheduleChangeRequest.BodyKey.DEPT_ID), "6");
		jsonObject.put(ScheduleChangeRequest.getBodyName(ScheduleChangeRequest.BodyKey.USER_ID), "3");
		jsonObject.put(ScheduleChangeRequest.getBodyName(ScheduleChangeRequest.BodyKey.MESSAGE), "I hate my schedule. Please change it.");
		
		
		String jsonString = jsonObject.toString();
		
		this.mockMvc.perform(post("/user/schedule_change_request").content(jsonString)
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .characterEncoding("utf-8"))
		        .andDo(MockMvcResultHandlers.print())
		        .andExpect(status().isInternalServerError());
	}
	
	@Test
	public void testGetMessage() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(GetMessage.getBodyName(GetMessage.BodyKey.MESSAGE_ID), "4");
		
		String jsonString = jsonObject.toString();
		
		this.mockMvc.perform(post("/user/get_message").content(jsonString)
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .characterEncoding("utf-8"))
		        .andDo(MockMvcResultHandlers.print())
		        .andExpect(status().isInternalServerError());
	}
    
    /*	 
	@Test
    public void testResetPassword() throws Exception
    {
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put(ResetPassword.getBodyName(ResetPassword.BodyKey.USER_NAME), "dselent");
    	jsonObject.put(ResetPassword.getBodyName(ResetPassword.BodyKey.NEW_PASSWORD), "newpassword");
    	String jsonString = jsonObject.toString();
        
    	System.out.println(jsonString);
    	
        this.mockMvc.perform(post("/user/reset_password").content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
        //.andExpect(content().contentType("application/json"));
        
    }*/
}
