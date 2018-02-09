package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.MessagesDao;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.dao.UsersRolesLinksDao;
import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.UsersRolesLink;
import org.dselent.scheduling.server.model.Message;
import org.dselent.scheduling.server.service.UserService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private MessagesDao messagesDao;
	
	@Autowired
	private UsersRolesLinksDao usersRolesLinksDao;
	
    public UserServiceImpl()
    {
    	//
    }
    
    /*
     * (non-Javadoc)
     * @see org.dselent.scheduling.server.service.UserService#registerUser(org.dselent.scheduling.server.dto.RegisterUserDto)
     */
    @Transactional
    @Override
	public List<Integer> registerUser(RegisterUserDto dto) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();
		
		// TODO validate business constraints
			// ^^students should do this^^
			// password strength requirements
			// email requirements
			// null values
			// etc...
		
		String salt = KeyGenerators.string().generateKey();
		String saltedPassword = dto.getPassword() + salt;
		PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncorder.encode(saltedPassword);
		
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setEncryptedPassword(encryptedPassword);
		user.setSalt(salt);
    	user.setUserStateId(1);
    	
    	List<String> userInsertColumnNameList = new ArrayList<>();
    	List<String> userKeyHolderColumnNameList = new ArrayList<>();
    	
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.USER_NAME));
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.FIRST_NAME));
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.LAST_NAME));
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.EMAIL));
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.ENCRYPTED_PASSWORD));
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.SALT));
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.USER_STATE_ID));
    	
    	userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.ID));
    	userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.CREATED_AT));
    	userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.UPDATED_AT));
		
    	rowsAffectedList.add(usersDao.insert(user, userInsertColumnNameList, userKeyHolderColumnNameList));

		//
     	
    	// for now, assume users can only register with default role id
    	// may change in the future
    	
		UsersRolesLink usersRolesLink = new UsersRolesLink();
		usersRolesLink.setUserId(user.getId());
		usersRolesLink.setRoleId(1); // hard coded as regular user
    	
    	List<String> usersRolesLinksInsertColumnNameList = new ArrayList<>();
    	List<String> usersRolesLinksKeyHolderColumnNameList = new ArrayList<>();
    	
    	usersRolesLinksInsertColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.USER_ID));
    	usersRolesLinksInsertColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.ROLE_ID));
    	
    	usersRolesLinksKeyHolderColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.ID));
    	usersRolesLinksKeyHolderColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.CREATED_AT));
    	usersRolesLinksKeyHolderColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.DELETED));
		
    	rowsAffectedList.add(usersRolesLinksDao.insert(usersRolesLink, usersRolesLinksInsertColumnNameList, usersRolesLinksKeyHolderColumnNameList));
		
		return rowsAffectedList;
	}
	
	//

	@Override
	public User loginUser(String userName, String password)
	{
		
		
		return null;
	}

	@Override
	public void addMessage(String author, String content, Integer deptID) {
		// TODO Auto-generated method stub
		Message message = new Message();
		message.setAuthorUserName(author);
		message.setMessage(content);
		message.setDeptId(deptID);
		
		List<String> insertColumnNameList = new ArrayList<>();
		List<String> keyHolderColumnNameList = new ArrayList<>();
		
		insertColumnNameList.add(Message.getColumnName(Message.Columns.AUTHOR_USER_NAME));
		insertColumnNameList.add(Message.getColumnName(Message.Columns.MESSAGE));
		insertColumnNameList.add(Message.getColumnName(Message.Columns.DEPT_ID));
		
		keyHolderColumnNameList.add(Message.getColumnName(Message.Columns.ID));
		keyHolderColumnNameList.add(Message.getColumnName(Message.Columns.RECEIVED_AT));
		keyHolderColumnNameList.add(Message.getColumnName(Message.Columns.RESOLVED));
		
		try {
			messagesDao.insert(message, insertColumnNameList, keyHolderColumnNameList);
		} catch (SQLException e) {
			System.out.println("[UserService] something went wrong when trying to push message! author: "+author);
			e.printStackTrace();
		}
		
	}

	@Override
	public Message getMessage(Integer messageID) {
		Message message = new Message();
		String selectColumnName = Message.getColumnName(Message.Columns.ID);
		
		List<QueryTerm> selectQueryTermList = new ArrayList<>();
		QueryTerm selectIDTerm = new QueryTerm();
		selectIDTerm.setColumnName(selectColumnName);
		selectIDTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		selectIDTerm.setValue(messageID);
		selectQueryTermList.add(selectIDTerm);
		
		List<String> selectColumnNameList = Message.getColumnNameList();
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
    	List<Message> selectedMessage = null;
    	try {
			selectedMessage = messagesDao.select(selectColumnNameList, selectQueryTermList, orderByList);
		} catch (SQLException e) {
			System.out.println("Something went horribly wrong when attempting to fetch message with ID: "+messageID);
			e.printStackTrace();
		}
		
		
		return selectedMessage.get(0);
	} 
	
	public void resetPassword(String userName, String newPassword) {
		
		String selectColumnName = User.getColumnName(User.Columns.USER_NAME);
		String updateColumnName = User.getColumnName(User.Columns.ENCRYPTED_PASSWORD);
		
		List<QueryTerm> selectQueryTermList = new ArrayList<>();
		QueryTerm updateUNTerm = new QueryTerm();
		updateUNTerm.setColumnName(selectColumnName);
		updateUNTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		updateUNTerm.setValue(userName);
		selectQueryTermList.add(updateUNTerm);
		
    	try {
			usersDao.update(updateColumnName, newPassword, selectQueryTermList);
		} catch (SQLException e) {
			System.out.println("Something went horribly wrong when attempting to update password for User: "+userName);
			e.printStackTrace();
		}

	}

	@Override
	public void resetPasswordEmail(String userName, String email) {
		// Fill in when proper method has been determined. -Alex
		
	}

}
