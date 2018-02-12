package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.DepartmentsDao;
import org.dselent.scheduling.server.dao.MessagesDao;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.dao.UsersRolesLinksDao;
import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.UsersRolesLink;
import org.dselent.scheduling.server.model.Department;
import org.dselent.scheduling.server.model.Message;
import org.dselent.scheduling.server.model.SidebarInfo;
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
	
	@Autowired
	private DepartmentsDao departmentsDao;
	
	@Autowired
	private CustomDao customDao;
	
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
		
		List<User> fetchedUser = customDao.getUser(userName);
		User theUser = fetchedUser.get(0);
		String saltPassword = fetchedUser.get(0).getEncryptedPassword();
		String theSalt = fetchedUser.get(0).getSalt();
		String saltyPassword = password + theSalt;
		if(theUser == null) {
			System.out.println("Username provided failed to authenticate. Try again.");
		}
		else if (saltyPassword.equals(saltPassword)) {
			System.out.println("Congratulations on logging in.");
		}
			
		return theUser;
		
	}

	@Override
	public void addMessage(String user_id, String author, String content, Integer deptID) {
		// TODO Auto-generated method stub
		Message message = new Message();
		message.setAuthorUserName(author);
		message.setMessage(content);
		message.setDeptId(deptID);
		message.setUserId(Integer.parseInt(user_id));
		
		List<String> insertColumnNameList = new ArrayList<>();
		List<String> keyHolderColumnNameList = new ArrayList<>();
		
		insertColumnNameList.add(Message.getColumnName(Message.Columns.AUTHOR_USER_NAME));
		insertColumnNameList.add(Message.getColumnName(Message.Columns.MESSAGE));
		insertColumnNameList.add(Message.getColumnName(Message.Columns.DEPT_ID));
		insertColumnNameList.add(Message.getColumnName(Message.Columns.USER_ID));
		
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
	
	public void resetPasswordSV(String userName, String newPassword) {
		
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
	public void resetPasswordEmailSV(String userName, String email) {
		// Determine how to cause an event to send an email to the given email
		
	}
	
	@Override
	public void resolveMessage(String administratorUsername, Integer messageId) {

		if(checkClearanceStatus(administratorUsername, "Administator")){
			String selectColumnName = Message.getColumnName(Message.Columns.ID);
			String updateColumnName = Message.getColumnName(Message.Columns.RESOLVED);
		
			List<QueryTerm> selectQueryTermList = new ArrayList<>();
			QueryTerm updateUNTerm = new QueryTerm();
			updateUNTerm.setColumnName(selectColumnName);
			updateUNTerm.setComparisonOperator(ComparisonOperator.EQUAL);
			updateUNTerm.setValue(messageId);
			selectQueryTermList.add(updateUNTerm);
			
			try {
				usersDao.update(updateColumnName, true, selectQueryTermList);
			} catch (SQLException e) {
				System.out.println("Something went horribly wrong when attempting to the message for message number: "+messageId);
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Cannot resolve message because user: " +administratorUsername +" is not an Administrator");
		}
	}



	@Override
	public void createAdmin(String moderatorUsername, String facultyUsername) {
		Integer userId;
		Integer administrator = 2;
		
		if (checkClearanceStatus(moderatorUsername, "Moderator")){
			String idSearchColumnName = User.getColumnName(User.Columns.USER_NAME);
			List<User> selectedUserList = new ArrayList<>();
			String selectColumnName = UsersRolesLink.getColumnName(UsersRolesLink.Columns.ROLE_ID);
			
			List<QueryTerm> selectIdQueryTermList = new ArrayList<>();
			QueryTerm selectUserId = new QueryTerm();
			selectUserId.setColumnName(idSearchColumnName);
			selectUserId.setComparisonOperator(ComparisonOperator.EQUAL);
			selectUserId.setValue(facultyUsername);
			selectIdQueryTermList.add(selectUserId);
    	
			List<String> idColumnNameList = new ArrayList<>();
			idColumnNameList.add(User.getColumnName(User.Columns.ID));
    	
			List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
			Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(idSearchColumnName, ColumnOrder.ASC);
			orderByList.add(orderPair1);
    	
			try{
				selectedUserList = usersDao.select(idColumnNameList, selectIdQueryTermList, orderByList);
			} catch (SQLException e) {
				System.out.println("Something went horribly wrong when trying to promote: "+facultyUsername + " to administrator status.");
				e.printStackTrace();
			}
		
			userId = selectedUserList.get(0).getId();
				
			List<QueryTerm> selectQueryTermList = new ArrayList<>();
			QueryTerm updateUNTerm = new QueryTerm();
			updateUNTerm.setColumnName(selectColumnName);
			updateUNTerm.setComparisonOperator(ComparisonOperator.EQUAL);
			updateUNTerm.setValue(userId);
			selectQueryTermList.add(updateUNTerm);
			
			try {
				usersDao.update(selectColumnName, administrator, selectQueryTermList);
			} catch (SQLException e) {
				System.out.println("Something went horribly wrong when trying to promote: "+facultyUsername + " to administrator status.");
				e.printStackTrace();
			}	
		}else{
			System.out.println("Cannot promote user: "+ facultyUsername +" to role of Admin, because user: " +moderatorUsername +" is not a  Moderator.");
		}
	}
	
	@Override
	public boolean checkClearanceStatus(String username, String role){
		boolean rs = false;
		Integer admin = 2;
		Integer moderator = 3;
		String selectColumnName = User.getColumnName(User.Columns.USER_NAME);
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectUseNameTerm = new QueryTerm();
    	selectUseNameTerm.setColumnName(selectColumnName);
    	selectUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectUseNameTerm.setValue(username);
    	selectQueryTermList.add(selectUseNameTerm);
    	
    	List<String> selectColumnNameList = User.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
    	List<User> selectedUserList = new ArrayList<>();
    	
		try {
		selectedUserList = usersDao.select(selectColumnNameList, selectQueryTermList, orderByList);
		} catch (SQLException e) {
			System.out.println("Something went wrong getting the Id for: " +username +".");
			e.printStackTrace();
		}
		
		Integer idToTest = selectedUserList.get(0).getId();
    	
		String selectRoleColumnName = UsersRolesLink.getColumnName(UsersRolesLink.Columns.ROLE_ID);
    	
    	List<QueryTerm> selectRoleQueryList = new ArrayList<>();
    	
    	QueryTerm selectUserRoleTerm = new QueryTerm();
    	selectUserRoleTerm.setColumnName(selectRoleColumnName);
    	selectUserRoleTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectUserRoleTerm.setValue(idToTest);
    	selectRoleQueryList.add(selectUseNameTerm);
    	
    	List<String> selectUserRoleColumnList = UsersRolesLink.getColumnNameList();
    	
    	List<UsersRolesLink> selectedUserRoleList = new ArrayList<>();
    	
		try {
		selectedUserRoleList = usersRolesLinksDao.select(selectUserRoleColumnList, selectQueryTermList, orderByList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer userRole = selectedUserRoleList.get(0).getRoleId();
		
		if(role.equals("Moderator")){
		rs = (userRole == moderator);		
		}else if(role.equals("Administrator")){
		rs = (userRole == admin);
		}
		return rs;
	}

	@Override
	public void getInbox(String username) {
		String messagesID = Message.getColumnName(Message.Columns.ID);
		getMessage(Integer.parseInt(messagesID));
		
	}

	@Override
	public SidebarInfo getSidebarInfo(String username) {
		//this function is disgusting and needs to be revised but it should work
		
		List<User> users = customDao.getUser(username);
		User user = users.get(0);
		
		Integer id = user.getId();
		
		SidebarInfo info = new SidebarInfo();
		//info.setCreatedAt(user.getCreatedAt());
		
		//info.setCreatedAt(user.getCreatedAt());
		//info.setUpdatedAt(user.getUpdatedAt());
		
		//make select from department_id
		//info.setDepartment();
		
		String selectColumnName = Department.getColumnName(Department.Columns.DEPARTMENT_NAME);
    	String selectDeptID = ""+user.getId(); //sorry professor :/
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectUseNameTerm = new QueryTerm();
    	selectUseNameTerm.setColumnName(selectColumnName);
    	selectUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectUseNameTerm.setValue(selectDeptID);
    	selectQueryTermList.add(selectUseNameTerm);
    	
    	List<String> selectColumnNameList = Department.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
    	List<Department> selectedDeptList = null;
    	
		try {
			selectedDeptList = departmentsDao.select(selectColumnNameList, selectQueryTermList, orderByList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		Department department = selectedDeptList.get(0);
		
		info.setDepartmentName(department.getDepartmentName());
		
		info.setEmail(user.getEmail());
		
		info.setFirstName(user.getFirstName());
		info.setLastName(user.getLastName());
		info.setId(user.getId());
		info.setCourses(customDao.getCourseNames(""+id));
		
		return info;
	}

	
}