package org.dselent.scheduling.server.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.requests.CreateSection;
import org.dselent.scheduling.server.requests.Search;
import org.dselent.scheduling.server.requests.GetSections;
import org.json.JSONException;
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
public class SearchesControllerTest {
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
   
	@Test
	public void testSearchCourses() throws Exception {
		//test searching for courses
		JSONObject jsonObject = new JSONObject();
    	jsonObject.put(Search.getBodyName(Search.BodyKey.REQUEST_TYPE), "courses");
    	jsonObject.put(Search.getBodyName(Search.BodyKey.KEY_VALUE), "1"); //dept id
    	
    	String jsonString = jsonObject.toString();
        
    	System.out.println(jsonString);
    	
    	//double check this
        this.mockMvc.perform(post("/search/search").content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());

		
	}
	
	@Test
	public void testSearchSections() throws Exception {
		//test getting sections from courseid
		JSONObject jsonObject = new JSONObject();
    	jsonObject.put(GetSections.getBodyName(GetSections.BodyKey.COURSEID), "1");
    	
    	String jsonString = jsonObject.toString();
    	
    	this.mockMvc.perform(post("/search/get_sections").content(jsonString)
    	        .contentType(MediaType.APPLICATION_JSON_VALUE)
    	        .characterEncoding("utf-8"))
    	        .andDo(MockMvcResultHandlers.print())
    	        .andExpect(status().isOk());
	}
	
	@Test
	public void testSearchUsers() throws Exception {
		JSONObject jsonObject = new JSONObject();
    	jsonObject.put(Search.getBodyName(Search.BodyKey.REQUEST_TYPE), "users");
    	jsonObject.put(Search.getBodyName(Search.BodyKey.KEY_VALUE), "6"); //dept id
    	
    	
    	String jsonString = jsonObject.toString();
        
    	System.out.println(jsonString);
    	
    	//double check this
        this.mockMvc.perform(post("/search/search").content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());

	}
	
	
}
