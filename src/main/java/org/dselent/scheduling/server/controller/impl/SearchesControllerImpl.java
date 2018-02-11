package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SearchesController;
import org.dselent.scheduling.server.requests.Search;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class SearchesControllerImpl implements SearchesController {

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
			case "username":
			//method in search service layer
				break;
			case "email":
				//different method/different parameter
				break;
			case "crn":
				break;
			case "sectionID":
				break;
			case "department":
				break;
			case "time":
				break;
			case "term":
				break;
		}
		
		return null;
	}

	
	
}
