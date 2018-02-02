package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CourseLoadDao;
import org.dselent.scheduling.server.extractor.CourseLoadExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.CourseLoad;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class CourseLoadDaoImpl extends BaseDaoImpl<CourseLoad> implements CourseLoadDao {

	@Override
	public int insert(CourseLoad model, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException {
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(CourseLoad.TABLE_NAME, insertColumnNameList);
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

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseLoad model) {
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.ID))) {
			parameters.addValue(parameterName, model.getId());
		} else if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.COURSE_LOAD_TYPE))) {
			parameters.addValue(parameterName, model.getLoadType());
		} else if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.USER_ID))){
			parameters.addValue(parameterName, model.getUserId());
		} else if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.AMOUNT))) {
			parameters.addValue(parameterName, model.getAmount());
		} else if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.CREATED_AT))) {
			parameters.addValue(parameterName, model.getCreatedAt());
		} else if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.UPDATED_AT))) {
			parameters.addValue(parameterName, model.getUpdatedAt());
		} else {
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseLoad model) {
		if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.ID))) {
			model.setId((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.COURSE_LOAD_TYPE))) {
			model.setLoadType((String) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.USER_ID))) {
			model.setUserId((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.AMOUNT))) {
			model.setAmount((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.CREATED_AT))) {
			model.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.UPDATED_AT))) {
			model.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		} else {
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
	}

	@Override
	public List<CourseLoad> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		CourseLoadExtractor extractor = new CourseLoadExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(CourseLoad.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		List<CourseLoad> courseLoadList = jdbcTemplate.query(queryTemplate, extractor, parameters);

		return courseLoadList;
	}

	@Override
	public CourseLoad findById(int id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(CourseLoad.getColumnName(CourseLoad.Columns.ID), false);
		List<String> selectColumnNames = CourseLoad.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);

		List<CourseLoad> courseLoadList = select(selectColumnNames, queryTermList, orderByList);

		CourseLoad courseLoad = null;

		if(!courseLoadList.isEmpty()) {
			courseLoad = courseLoadList.get(0);
		}

		return courseLoad;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
		String queryTemplate = QueryStringBuilder.generateUpdateString(CourseLoad.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(CourseLoad.TABLE_NAME, queryTermList);

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
		
		List<String> actualColumnNames = CourseLoad.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
