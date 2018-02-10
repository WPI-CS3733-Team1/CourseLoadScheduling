package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.model.Message;
import org.dselent.scheduling.server.model.SidebarInfo;
import org.dselent.scheduling.server.model.User;
import org.springframework.stereotype.Service;


/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author dselent
 *
 */
@Service
public interface UserService
{
	/**
	 * Registers a user into the system
	 * Performs an insert into the users table and users_roles_links table as a transaction
	 * 
	 * @param registerUserDto DTO container information for the insertions
	 * @return A list of rows affected for each insert operation
	 * @throws SQLException */
	public List<Integer> registerUser(RegisterUserDto registerUserDto) throws SQLException;
    public User loginUser(String userName, String password);
    public Message getMessage(Integer messageID);
	void addMessage(String author, String content, Integer deptID);	
	void resetPasswordSV(String userName, String newPassword);
	void resetPasswordEmailSV(String userName, String email);
	void resolveMessage(String administratorUsername, Integer messageId);
	void createAdmin(String moderatorUsername, String facultyUsername);
	boolean checkClearanceStatus(String username, String role);
	void getInbox(String username);
	public SidebarInfo getSidebarInfo(String username);
}
