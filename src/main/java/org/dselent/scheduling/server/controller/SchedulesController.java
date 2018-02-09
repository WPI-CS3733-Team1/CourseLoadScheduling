package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.CreateCourse;
import org.dselent.scheduling.server.requests.GetCourses;
import org.dselent.scheduling.server.requests.CreateSection;
import org.dselent.scheduling.server.requests.ConfirmSchedule;
import org.dselent.scheduling.server.requests.GetSchedule;
import org.dselent.scheduling.server.requests.Register;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface SchedulesController {
	@RequestMapping(method=RequestMethod.POST, value=GetCourses.REQUEST_NAME)
	public ResponseEntity<String> getCourses(@RequestBody Map<String, String> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=CreateSection.REQUEST_NAME)
	public ResponseEntity<String> createSection(@RequestBody Map<String, String> request) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=GetSchedule.REQUEST_NAME)
	public ResponseEntity<String> getSchedule(@RequestBody Map<String, String> request) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=CreateCourse.REQUEST_NAME)
	public ResponseEntity<String> createCourse(@RequestBody Map<String, String> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=ConfirmSchedule.REQUEST_NAME)
	public ResponseEntity<String> confirmSchedule(@RequestBody Map<String, String> request) throws Exception;

}
