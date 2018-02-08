package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.GetCourses;
import org.dselent.scheduling.server.requests.Register;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface SchedulesHandler {
	@RequestMapping(method=RequestMethod.POST, value=GetCourses.REQUEST_NAME)
	public ResponseEntity<String> getCourses(@RequestBody Map<String, String> request) throws Exception;

}