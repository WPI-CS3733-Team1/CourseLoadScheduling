package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SearchesController;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.requests.GetSections;
import org.dselent.scheduling.server.requests.Search;
import org.dselent.scheduling.server.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/search")
@Controller
public class SearchesControllerImpl implements SearchesController {

	@Autowired
	private SearchService searchService;
	
	
	@Override
	public ResponseEntity<String> search(@RequestBody Map<String, String> request) throws Exception {
		//code
		
		System.out.println("[Search] search request method reached.");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String searchType = request.get(Search.getBodyName(Search.BodyKey.REQUEST_TYPE));
		
		String keyValue = request.get(Search.getBodyName(Search.BodyKey.KEY_VALUE));
		
		switch(searchType) {
			case "users":
			//method in search service layer
				//method for users with matching dept id
				List<User> users = searchService.searchFaculty(keyValue);
				success.add(users);
				break;
			case "courses":
				List<Course> courses = searchService.searchCourse(keyValue);
				success.add(courses);
				break;
		}
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<String> getSections(@RequestBody Map<String, String> request) throws Exception {
		
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String courseID = request.get(GetSections.getBodyName(GetSections.BodyKey.COURSEID)); 
		Integer id = Integer.parseInt(courseID);
		
		List<Section> sections = searchService.getSectionsByCourseID(id);
		
		success.add(sections);
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
