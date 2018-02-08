package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.GetCourses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface SchedulesController {
	@RequestMapping(method=RequestMethod.POST, value=GetCourses.REQUEST_NAME)
	public ResponseEntity<String> getCourses(@RequestBody Map<String, String> request) throws Exception;

}
