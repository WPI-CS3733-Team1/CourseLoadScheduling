package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CoursesDao;
import org.dselent.scheduling.server.extractor.CoursesExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class CoursesDaoImpl extends BaseDaoImpl<Course> implements CoursesDao {

	@Override
	public int insert(Course model, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException {
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Course.TABLE_NAME, insertColumnNameList);
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		List<Map<String, Object>> keyList = new ArrayList<>();
		KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

		for(String insertColumnName : insertColumnNameList) {
			addParameterMapValue(parameters, insertColumnName, model);
		}

		int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

		Map<String, Object> keyMap = keyHolder.getKeys();
		
		for(String keyHolderColumnName : keyHolderColumnNameList) {
			addObjectValue(keyMap, keyHolderColumnName, model);
		}

		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Course model) {
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		if(insertColumnName.equals(Course.getColumnName(Course.Columns.ID))) {
			parameters.addValue(parameterName, model.getId());
		} else if(insertColumnName.equals(Course.getColumnName(Course.Columns.NAME))) {
			parameters.addValue(parameterName, model.getName());
		} else if(insertColumnName.equals(Course.getColumnName(Course.Columns.DEPT_ID))) {
			parameters.addValue(parameterName, model.getDeptId());
		} else if(insertColumnName.equals(Course.getColumnName(Course.Columns.COURSE_NUMBER))) {
			parameters.addValue(parameterName, model.getCourseNum());
		} else if(insertColumnName.equals(Course.getColumnName(Course.Columns.NUMBER_OF_LECTURES))) {
			parameters.addValue(parameterName, model.getNumLectures());
		} else if(insertColumnName.equals(Course.getColumnName(Course.Columns.NUMBER_OF_LABS))) {
			parameters.addValue(parameterName, model.getNumLabs());
		} else if(insertColumnName.equals(Course.getColumnName(Course.Columns.NUMBER_OF_CONFERENCES))) {
			parameters.addValue(parameterName, model.getNumConferences());
		} else if(insertColumnName.equals(Course.getColumnName(Course.Columns.CREATED_AT))) {
			parameters.addValue(parameterName, model.getCreatedAt());
		} else if(insertColumnName.equals(Course.getColumnName(Course.Columns.UPDATED_AT))) {
			parameters.addValue(parameterName, model.getUpdatedAt());
		} else {
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Course model) {
		if(keyHolderColumnName.equals(Course.getColumnName(Course.Columns.ID))) {
			model.setId((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Course.getColumnName(Course.Columns.NAME))) {
			model.setName((String) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Course.getColumnName(Course.Columns.DEPT_ID))) {
			model.setDeptId((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Course.getColumnName(Course.Columns.COURSE_NUMBER))) {
			model.setCourseNum((String) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Course.getColumnName(Course.Columns.NUMBER_OF_LECTURES))) {
			model.setNumLectures((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Course.getColumnName(Course.Columns.NUMBER_OF_LABS))) {
			model.setNumLabs((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Course.getColumnName(Course.Columns.NUMBER_OF_CONFERENCES))) {
			model.setNumConferences((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Course.getColumnName(Course.Columns.CREATED_AT))) {
			model.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Course.getColumnName(Course.Columns.UPDATED_AT))) {
			model.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		} else {
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
	}

	@Override
	public List<Course> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		CoursesExtractor extractor = new CoursesExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(Course.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		List<Course> coursesList = jdbcTemplate.query(queryTemplate, extractor, parameters);

		return coursesList;
	}

	@Override
	public Course findById(int id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(Course.getColumnName(Course.Columns.ID), false);
		List<String> selectColumnNames = Course.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);

		List<Course> coursesList = select(selectColumnNames, queryTermList, orderByList);

		Course course = null;

		if(!coursesList.isEmpty()) {
			course = coursesList.get(0);
		}

		return course;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
		String queryTemplate = QueryStringBuilder.generateUpdateString(Course.TABLE_NAME, columnName, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		objectList.add(newValue);

		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

		return rowsAffected;
	}

	@Override
	public int delete(List<QueryTerm> queryTermList) throws SQLException {
		String queryTemplate = QueryStringBuilder.generateDeleteString(Course.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	@Override
	public void validateColumnNames(List<String> columnNameList) {
		
		List<String> actualColumnNames = Course.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
