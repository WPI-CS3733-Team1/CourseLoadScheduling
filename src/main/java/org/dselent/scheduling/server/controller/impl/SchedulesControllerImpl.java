package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SchedulesController;
<<<<<<< HEAD
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateCourse;
import org.dselent.scheduling.server.requests.Login;
=======
import org.dselent.scheduling.server.service.ScheduleService;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.requests.GetCourses;
import org.dselent.scheduling.server.requests.ConfirmSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
>>>>>>> f8ccd9993eadb4cd80a564dc2724a4874d8136cf
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

public class SchedulesControllerImpl implements SchedulesController {

	@Autowired
	private ScheduleService scheduleService;

	@Override
	public ResponseEntity<String> getCourses(@RequestBody Map<String, String> request) throws Exception {
		String response = "";
		List<Object> success = new ArrayList<Object>();

		String userName = request.get(GetCourses.getBodyName(GetCourses.BodyKey.USERNAME));

		List<Course> courses = scheduleService.getCoursesBySection(userName);

		for(int i = 0; i < courses.size(); i++) {
			success.add(courses.get(i).getName());
		}

		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> confirmSchedule(@RequestBody Map<String, String> request) throws Exception {
		String response = "";
		List<Object> success = new ArrayList<Object>();

		String userName = request.get(ConfirmSchedule.getBodyName(ConfirmSchedule.BodyKey.USER_NAME));
		String removeSectionIds = request.get(ConfirmSchedule.getBodyName(ConfirmSchedule.BodyKey.REMOVE_SECTION_IDS));
		String addSectionIds = request.get(ConfirmSchedule.getBodyName(ConfirmSchedule.BodyKey.ADD_SECTION_IDS));

		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getSchedule(Map<String, String> request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> createCourse(Map<String, String> request) throws Exception {
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String name = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.NAME));
		String course_number  = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.COURSE_NUMBER));
		String number_of_lectures = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.NUMBER_OF_LECTURES));
		String number_of_labs = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.NUMBER_OF_LABS));
		String number_of_conferences = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.NUMBER_OF_CONFERENCES));
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		
		return null;
	}

}
