package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SchedulesController;
import org.dselent.scheduling.server.service.ScheduleService;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.requests.GetCourses;
import org.dselent.scheduling.server.requests.ConfirmSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

}
