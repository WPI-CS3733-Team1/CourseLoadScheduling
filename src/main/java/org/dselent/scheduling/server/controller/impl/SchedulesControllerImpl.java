package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SchedulesController;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateCourse;
import org.dselent.scheduling.server.requests.Login;
import org.springframework.http.ResponseEntity;

public class SchedulesControllerImpl implements SchedulesController {

	@Override
	public ResponseEntity<String> getCourses(Map<String, String> request) throws Exception {
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
