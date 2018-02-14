package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CoursesDao;
import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CompleteSection;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.service.SearchService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private CoursesDao coursesDao;
	
	@Autowired
	private SectionsDao sectionsDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Override
	public List<User> searchFaculty(String deptID) {
		
		String selectColumnName = User.getColumnName(User.Columns.DEPT_ID);
    	String selectDeptID = deptID;
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectDeptIDTerm = new QueryTerm();
    	selectDeptIDTerm.setColumnName(selectColumnName);
    	selectDeptIDTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectDeptIDTerm.setValue(Integer.parseInt(selectDeptID));
    	selectQueryTermList.add(selectDeptIDTerm);
    	
    	List<String> selectColumnNameList = User.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
    	List<User> selectedUserList = null;
    	
		try {
			selectedUserList = usersDao.select(selectColumnNameList, selectQueryTermList, orderByList);
		} catch (SQLException e) {
			System.out.println("[SearchService] something failed when trying to fetch users with dept_id : "+deptID);
			e.printStackTrace();
		}
    	
		return selectedUserList;
	}

	@Override
	public List<Course> searchCourse(String deptID) {
		
		String selectColumnName = Course.getColumnName(Course.Columns.DEPT_ID);
    	String selectID = deptID;
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectDeptIDTerm = new QueryTerm();
    	selectDeptIDTerm.setColumnName(selectColumnName);
    	selectDeptIDTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectDeptIDTerm.setValue(Integer.parseInt(selectID));
    	selectQueryTermList.add(selectDeptIDTerm);
    	
    	List<String> selectColumnNameList = Course.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
    	List<Course> selectedCourseList = null;
		try {
			selectedCourseList = coursesDao.select(selectColumnNameList, selectQueryTermList, orderByList);
		} catch (SQLException e) {
			System.out.println("[SearchService] something went poorly when attempting to select all courses with department id: "+deptID);
			e.printStackTrace();
		}
    	
		
		return selectedCourseList;
	}

	@Override
	public List<Section> getSectionsByCourseID(Integer courseID) {
		
		String selectColumnName = Section.getColumnName(Section.Columns.COURSE_ID);
    	Integer selectCourseID = courseID;
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectCourseIDTerm = new QueryTerm();
    	selectCourseIDTerm.setColumnName(selectColumnName);
    	selectCourseIDTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectCourseIDTerm.setValue(selectCourseID);
    	selectQueryTermList.add(selectCourseIDTerm);
    	
    	List<String> selectColumnNameList = Section.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
    	List<Section> selectedSectionList = null;
    	
		
		try {
			selectedSectionList = sectionsDao.select(selectColumnNameList, selectQueryTermList, orderByList);
		} catch (SQLException e) {
			System.out.println("[SearchService] Exception when attempting to select all courses with course_id: "+courseID);
			e.printStackTrace();
		}
    	
		return selectedSectionList;
	}

}
