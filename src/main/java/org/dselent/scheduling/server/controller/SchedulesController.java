package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.GetCourses;
import org.dselent.scheduling.server.requests.ConfirmSchedule;
import org.dselent.scheduling.server.requests.GetSchedule;
import org.dselent.scheduling.server.requests.Register;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/schedule")
public interface SchedulesController {
	@RequestMapping(method=RequestMethod.POST, value=GetCourses.REQUEST_NAME)
	public ResponseEntity<String> getCourses(@RequestBody Map<String, String> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=GetSchedule.REQUEST_NAME)
	public ResponseEntity<String> getSchedule(@RequestBody Map<String, String> request) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=ConfirmSchedule.REQUEST_NAME)
	public ResponseEntity<String> confirmSchedule(@RequestBody Map<String, String> request) throws Exception;
}
