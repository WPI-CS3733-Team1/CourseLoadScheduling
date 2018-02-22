package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SchedulesController;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateCourse;
import org.dselent.scheduling.server.dto.CreateCourseDto;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.service.ScheduleService;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.requests.GetCourses;
import org.dselent.scheduling.server.requests.GetSchedule;
import org.dselent.scheduling.server.requests.ConfirmSchedule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.dselent.scheduling.server.requests.CreateSection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SchedulesControllerImpl implements SchedulesController {

	@Autowired
	private ScheduleService scheduleService;

	ObjectMapper mapper = new ObjectMapper();
	TypeFactory typeFactory = mapper.getTypeFactory();

	@Override
	public ResponseEntity<String> getCourses(@RequestBody Map<String, String> request) throws Exception {
		String response = "";
		List<Object> success = new ArrayList<Object>();

		String userName = request.get(GetCourses.getBodyName(GetCourses.BodyKey.USER_NAME));

		List<Course> courses = scheduleService.getCoursesBySection(userName);
		
		System.out.println("[SchedulesController] size of list: "+courses.size());
		
		for(int i = 0; i < courses.size(); i++) {
			success.add(courses.get(i).getName());
		}

		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> confirmSchedule(@RequestBody Map<String, String> request) throws Exception {
		
		String response = "newUserName";
		List<Object> success = new ArrayList<Object>();

		String userName = request.get(ConfirmSchedule.getBodyName(ConfirmSchedule.BodyKey.USER_NAME));
		String removeSectionIds = request.get(ConfirmSchedule.getBodyName(ConfirmSchedule.BodyKey.REMOVE_SECTION_IDS));
		String addSectionIds = request.get(ConfirmSchedule.getBodyName(ConfirmSchedule.BodyKey.ADD_SECTION_IDS));
		System.out.println("List String: "+addSectionIds);

		List<String> removeSectionIdsList = mapper.readValue(removeSectionIds, typeFactory.constructCollectionType(List.class, String.class));
		List<String> addSectionIdsList = mapper.readValue(addSectionIds, typeFactory.constructCollectionType(List.class, String.class));
		
		scheduleService.confirmSchedule(userName, removeSectionIdsList, addSectionIdsList);
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getSchedule(Map<String, String> request) throws Exception {
		
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String userName = request.get(GetSchedule.getBodyName(GetSchedule.BodyKey.USER_NAME));
		String term = request.get(GetSchedule.getBodyName(GetSchedule.BodyKey.ACADEMIC_TERM));
		
		
		List<Section> schedule = scheduleService.getSchedule(userName, term);
		
		success.add(userName); //sticking this in there to link to specific request in front-end
		success.add(schedule);
		
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<String> createSection(@RequestBody Map<String, String> request) throws Exception {
		// Print is for testing purposes
				System.out.println("Schedule controller reached; create SECTION being called");
		    	
				// add any objects that need to be returned to the success list
				String response = "";
				List<Object> success = new ArrayList<Object>();
				System.out.println(CreateSection.getBodyName(CreateSection.BodyKey.COURSE_ID));;
				String courseId = request.get(CreateSection.getBodyName(CreateSection.BodyKey.COURSE_ID));
				String crn = request.get(CreateSection.getBodyName(CreateSection.BodyKey.CRN));
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
				CreateSectionDto createSectionDto = builder.withCourseId(courseId)
				.withCRN(crn)
				.withSectionName(sectionName)
				.withSectionId(sectionId)
				.withExpectedPopulation(expectedPopulation)
				.withRequiredFrequency(requiredFrequency)
				.withAcademicYear(academicYear)
				.withAcademicTerm(academicTerm)
				.withStartTime(startTime)
				.withEndTime(endTime)
				.withDaysPerWeek(daysPerWeek)
				.withCourseLocation(courseLocation)
				.build();
				
				scheduleService.createSection(createSectionDto);
				response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

				return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> createCourse(@RequestBody Map<String, String> request) throws Exception {
		// Print is for testing purposes
		System.out.println("Schedule controller reached; create COURSE being called");
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String courseName = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.NAME));
		String deptId = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.DEPT_ID));
		String courseAbrv = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.COURSE_ABRV));
		String courseNumber  = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.COURSE_NUMBER));
		String numberOfLectures = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.NUMBER_OF_LECTURES));
		String numberOfLabs = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.NUMBER_OF_LABS));
		String numberOfConferences = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.NUMBER_OF_CONFERENCES));
		
		CreateCourseDto.Builder builder = CreateCourseDto.builder();
		CreateCourseDto createCourseDto = builder.withCourseName(courseName)
		.withDeptId(Integer.parseInt(deptId)) //Noah
		.withCourseNumber(courseNumber)
		.withCourseAbrv(courseAbrv)
		.withNumberOfLectures(numberOfLectures)
		.withNumberOfLabs(numberOfLabs)
		.withNumberOfConferences(numberOfConferences)
		.build();
		
		scheduleService.createCourse(createCourseDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
