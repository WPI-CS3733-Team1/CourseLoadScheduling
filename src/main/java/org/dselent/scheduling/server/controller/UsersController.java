package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.GetMessage;
import org.dselent.scheduling.server.requests.Login;
import org.dselent.scheduling.server.requests.GetSidebarInfo;
import org.dselent.scheduling.server.requests.Register;
import org.dselent.scheduling.server.requests.ResetPassword;
import org.dselent.scheduling.server.requests.ResetPasswordEmail;
import org.dselent.scheduling.server.requests.ScheduleChangeRequest;
import org.dselent.scheduling.server.requests.ResolveMessage;
import org.dselent.scheduling.server.requests.CreateAdmin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/user")
public interface UsersController
{
    
    @RequestMapping(method=RequestMethod.POST, value=Register.REQUEST_NAME)
	public ResponseEntity<String> register(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method=RequestMethod.POST, value=GetMessage.REQUEST_NAME)
    public ResponseEntity<String> getMessage(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=ScheduleChangeRequest.REQUEST_NAME)
    public ResponseEntity<String> requestScheduleChange(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=Login.REQUEST_NAME)
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method=RequestMethod.POST, value=ResetPassword.REQUEST_NAME)
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=ResetPasswordEmail.REQUEST_NAME)
    public ResponseEntity<String> resetPasswordEmail(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method=RequestMethod.POST, value=ResolveMessage.REQUEST_NAME)
    public ResponseEntity<String> resolveMessage(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method=RequestMethod.POST, value=CreateAdmin.REQUEST_NAME)
    public ResponseEntity<String> createAdmin(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=GetSidebarInfo.REQUEST_NAME)
    public ResponseEntity<String> getSidebarInfo(@RequestBody Map<String, String> request) throws Exception;
}
