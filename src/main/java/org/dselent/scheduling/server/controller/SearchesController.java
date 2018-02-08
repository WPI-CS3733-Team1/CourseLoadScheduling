package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.Search;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface SearchesController {

	@RequestMapping(method=RequestMethod.POST, value=Search.REQUEST_NAME)
	public ResponseEntity<String> search(@RequestBody Map<String, String> request) throws Exception;

}
