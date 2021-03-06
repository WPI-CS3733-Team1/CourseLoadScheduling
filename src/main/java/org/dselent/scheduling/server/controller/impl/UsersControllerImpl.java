package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.UsersController;
import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.model.Message;
import org.dselent.scheduling.server.model.SidebarInfo;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.requests.AccessInbox;
import org.dselent.scheduling.server.requests.CreateAdmin;
import org.dselent.scheduling.server.requests.GetMessage;
import org.dselent.scheduling.server.requests.GetSidebarInfo;
import org.dselent.scheduling.server.requests.Login;
import org.dselent.scheduling.server.requests.Register;
import org.dselent.scheduling.server.requests.ResetPassword;
import org.dselent.scheduling.server.requests.ResetPasswordEmail;
import org.dselent.scheduling.server.requests.ResolveMessage;
import org.dselent.scheduling.server.requests.ScheduleChangeRequest;
import org.dselent.scheduling.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for handling requests related to the user such as logging in or registering.
 * Controller methods are the first methods reached by the request server-side (with special exception).
 * They parse the request and then call the appropriate service method to execute the business logic.
 * Any results or data is then sent back to the client.
 * 
 * @author dselent
 */
@Controller
public class UsersControllerImpl implements UsersController
{
	@Autowired
    private UserService userService;
    
	/**
	 * 
	 * @param request The body of the request expected to contain a map of String key-value pairs
	 * @return A ResponseEntity for the response object(s) and the status code
	 * @throws Exception 
	 */
	public ResponseEntity<String> register(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String userName = request.get(Register.getBodyName(Register.BodyKey.USER_NAME));
		String firstName = request.get(Register.getBodyName(Register.BodyKey.FIRST_NAME));
		String lastName = request.get(Register.getBodyName(Register.BodyKey.LAST_NAME));
		String email = request.get(Register.getBodyName(Register.BodyKey.EMAIL));
		String password = request.get(Register.getBodyName(Register.BodyKey.PASSWORD));

		RegisterUserDto.Builder builder = RegisterUserDto.builder();
		RegisterUserDto registerUserDto = builder.withUserName(userName)
		.withFirstName(firstName)
		.withLastName(lastName)
		.withEmail(email)
		.withPassword(password)
		.build();
		
		userService.registerUser(registerUserDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<String> getMessage(@RequestBody Map<String, String> request) throws Exception {
		
		String response = "";
		List<Object> success = new ArrayList<Object>();
		 
		
		String messageID = request.get(GetMessage.getBodyName(GetMessage.BodyKey.MESSAGE_ID));
		
		Integer id = Integer.parseInt(messageID);
		
		//method call to service layer to pull message, no DTO required.
		//success.add(return from service layer call);
		
		Message m = userService.getMessage(id);
		success.add(m);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> requestScheduleChange(@RequestBody Map<String, String> request) throws Exception {
		
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		
		String messageAuthor = request.get(ScheduleChangeRequest.getBodyName(ScheduleChangeRequest.BodyKey.USER_NAME));
		String messageContent = request.get(ScheduleChangeRequest.getBodyName(ScheduleChangeRequest.BodyKey.MESSAGE));
		String dept_id = request.get(ScheduleChangeRequest.getBodyName(ScheduleChangeRequest.BodyKey.DEPT_ID));
		String user_id = request.get(ScheduleChangeRequest.getBodyName(ScheduleChangeRequest.BodyKey.USER_ID));
		
		//method call to service layer to push message, no DTO required.
		userService.addMessage(user_id, messageAuthor, messageContent, (Integer.parseInt(dept_id)));
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> resetPasswordCT(@RequestBody Map<String, String> request) throws Exception {
		
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String username = request.get(ResetPassword.getBodyName(ResetPassword.BodyKey.USER_NAME));
		String newPassword = request.get(ResetPassword.getBodyName(ResetPassword.BodyKey.NEW_PASSWORD));
		
		//method to replace "username" 's password with "newPassword", DTO required?
		
		userService.resetPasswordSV(username, newPassword);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> resetPasswordEmailCT(@RequestBody Map<String, String> request) throws Exception {
		
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String username = request.get(ResetPasswordEmail.getBodyName(ResetPasswordEmail.BodyKey.USER_NAME));
		String email = request.get(ResetPasswordEmail.getBodyName(ResetPasswordEmail.BodyKey.EMAIL));
		
		//need to figure out how to cause an event to send an email to the given email address
		
		userService.resetPasswordEmailSV(username, email);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getSidebarInfo(@RequestBody Map<String, String> request) throws Exception {
		
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String username = request.get(GetSidebarInfo.getBodyName(GetSidebarInfo.BodyKey.USER_NAME));
		//get user
		System.out.println("[UsersController] getSidebarInfo() - username recieved: "+username);
		SidebarInfo info = userService.getSidebarInfo(username);
		
		System.out.println("info fetched: "+info.toString());
		success.add(info);
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> loginUser(@RequestBody Map<String, String> request) throws Exception {
		// TODO Auto-generated method stub
		String response = "";
 		List<Object> success = new ArrayList<Object>();
		
 		String username = request.get(Login.getBodyName(Login.BodyKey.USER_NAME));
 		String password = request.get(Login.getBodyName(Login.BodyKey.PASSWORD));
		
 		User loggedUser = userService.loginUser(username, password);
 		
 		System.out.println("Logged in user data: "+loggedUser.toString());
 		success.add(loggedUser);
 		
 		//doesn't return user data?
 		
 		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
 		
 		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<String> resolveMessage(@RequestBody Map<String, String> request) throws Exception {
		String response = "";
			List<Object> success = new ArrayList<Object>();
		
			String administratorUsername = request.get(ResolveMessage.getBodyName(ResolveMessage.BodyKey.ADMINISTRATOR_USERNAME));
			Integer messageId = Integer.parseInt(request.get(ResolveMessage.getBodyName(ResolveMessage.BodyKey.MESSAGE_ID)));
			
			//method to update a message to resolved
			userService.resolveMessage(administratorUsername, messageId);
			
			response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
			
			return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> createAdmin(@RequestBody Map<String, String> request) throws Exception {
			String response = "";
			List<Object> success = new ArrayList<Object>();
			
			System.out.println("[UsersController] createAdmin() - attempting to fetch value with name: "+CreateAdmin.getBodyName(CreateAdmin.BodyKey.FACULTY_USERNAME));
			String facultyUsername = request.get(CreateAdmin.getBodyName(CreateAdmin.BodyKey.FACULTY_USERNAME));
			String moderatorUsername = request.get(CreateAdmin.getBodyName(CreateAdmin.BodyKey.MODERATOR_USERNAME));
		
			//method to update a users status to administrator
			userService.createAdmin(moderatorUsername,facultyUsername);
			response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
			
			return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getInbox(@RequestBody Map<String, String> request) throws Exception {

			String response = "";
			List<Object> success = new ArrayList<Object>();
			
			String req = AccessInbox.getBodyName(AccessInbox.BodyKey.USER_NAME);
			System.out.println("requesting username at value: "+req);
			
			String userName = request.get(AccessInbox.getBodyName(AccessInbox.BodyKey.USER_NAME));
			
			List<Message> inbox = userService.getInbox(userName);
			
			success.add(inbox);
			
			response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
			
			return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}

	