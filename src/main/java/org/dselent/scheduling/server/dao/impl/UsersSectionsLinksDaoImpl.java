package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.UsersSectionsLinksDao;
import org.dselent.scheduling.server.extractor.UsersSectionsLinksExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.UsersSectionsLink;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UsersSectionsLinksDaoImpl extends BaseDaoImpl<UsersSectionsLink> implements UsersSectionsLinksDao {

	@Override
	public int insert(UsersSectionsLink model, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException {
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(UsersSectionsLink.TABLE_NAME, insertColumnNameList);
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

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, UsersSectionsLink model) {
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		if(insertColumnName.equals(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.ID))) {
			parameters.addValue(parameterName, model.getId());
		} else if(insertColumnName.equals(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.USER_ID))) {
			parameters.addValue(parameterName, model.getUserId());
		} else if(insertColumnName.equals(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.SECTION_ID))) {
			parameters.addValue(parameterName, model.getSectionId());
		} else if(insertColumnName.equals(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.CREATED_AT))) {
			parameters.addValue(parameterName, model.getCreatedAt());
		} else if(insertColumnName.equals(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.UPDATED_AT))) {
			parameters.addValue(parameterName, model.getUpdatedAt());
		} else {
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, UsersSectionsLink model) {
		if(keyHolderColumnName.equals(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.ID))) {
			model.setId((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.USER_ID))) {
			model.setUserId((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.SECTION_ID))) {
			model.setSectionId((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.CREATED_AT))) {
			model.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.UPDATED_AT))) {
			model.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		} else {
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
	}

	@Override
	public List<UsersSectionsLink> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		UsersSectionsLinksExtractor extractor = new UsersSectionsLinksExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(UsersSectionsLink.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		List<UsersSectionsLink> coursesList = jdbcTemplate.query(queryTemplate, extractor, parameters);

		return coursesList;
	}

	@Override
	public UsersSectionsLink findById(int id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.ID), false);
		List<String> selectColumnNames = UsersSectionsLink.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);

		List<UsersSectionsLink> usersSectionsLinksList = select(selectColumnNames, queryTermList, orderByList);

		UsersSectionsLink usersSectionsLink = null;

		if(!usersSectionsLinksList.isEmpty()) {
			usersSectionsLink = usersSectionsLinksList.get(0);
		}

		return usersSectionsLink;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
		String queryTemplate = QueryStringBuilder.generateUpdateString(UsersSectionsLink.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(UsersSectionsLink.TABLE_NAME, queryTermList);

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
		
		List<String> actualColumnNames = UsersSectionsLink.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
