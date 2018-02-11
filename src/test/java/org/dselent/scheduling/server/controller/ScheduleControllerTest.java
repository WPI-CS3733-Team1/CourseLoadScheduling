package org.dselent.scheduling.server.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.requests.CreateSection;
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
        .andExpect(status().isOk());
        //.andExpect(content().contentType("application/json"));
    }

}
