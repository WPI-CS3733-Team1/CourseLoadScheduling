package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SchedulesController;
import org.dselent.scheduling.server.dto.CreateSectionDto;
//import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateSection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SchedulesControllerImpl implements SchedulesController {

	@Override
	public ResponseEntity<String> getCourses(Map<String, String> request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public ResponseEntity<String> createSection(Map<String, String> request) throws Exception {
		// Print is for testing purposes
				System.out.println("Schedule controller reached; create section being called");
		    	
				// add any objects that need to be returned to the success list
				String response = "";
				List<Object> success = new ArrayList<Object>();
				
				String courseId = request.get(CreateSection.getBodyName(CreateSection.BodyKey.COURSE_ID));
				String sectionName = request.get(CreateSection.getBodyName(CreateSection.BodyKey.SECTION_NAME));
				String sectionId = request.get(CreateSection.getBodyName(CreateSection.BodyKey.SECTION_ID));
				String expectedPopulation = request.get(CreateSection.getBodyName(CreateSection.BodyKey.EXPECTED_POP));
				String requiredFrequency = request.get(CreateSection.getBodyName(CreateSection.BodyKey.REQUIRED_FREQ));
				String academicYear = request.get(CreateSection.getBodyName(CreateSection.BodyKey.ACADEMIC_YEAR));
				String academicTerm = request.get(CreateSection.getBodyName(CreateSection.BodyKey.ACADEMIC_TERM));
				String startTime = request.get(CreateSection.getBodyName(CreateSection.BodyKey.START_TIME));
				String endTime = request.get(CreateSection.getBodyName(CreateSection.BodyKey.END_TIME));
				String daysPerWeek = request.get(CreateSection.getBodyName(CreateSection.BodyKey.DAYS_PER_WEEK));
				String courseLocation = request.get(CreateSection.getBodyName(CreateSection.BodyKey.COURSE_LOCATION));
				
				
				CreateSectionDto.Builder builder = CreateSectionDto.builder();
				//RegisterUserDto registerUserDto = builder.withUserName(userName)
				//.withFirstName(firstName)
				//.withLastName(lastName)
				//.withEmail(email)
				//.withPassword(password)
				//.build();
				
				//userService.registerUser(registerUserDto);
				//response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

				return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
