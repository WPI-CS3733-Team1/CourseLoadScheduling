package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.UserRolesDao;
import org.dselent.scheduling.server.extractor.UserRolesExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.UserRole;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class UserRolesDaoImpl extends BaseDaoImpl<UserRole> implements UserRolesDao {
	@Override
	public int insert(UserRole model, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException {
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(UserRole.TABLE_NAME, insertColumnNameList);
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

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, UserRole model) {
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		if(insertColumnName.equals(UserRole.getColumnName(UserRole.Columns.ID))) {
			parameters.addValue(parameterName, model.getId());
		} else if(insertColumnName.equals(UserRole.getColumnName(UserRole.Columns.ROLE_NAME))) {
			parameters.addValue(parameterName, model.getRoleName());
		}  else if(insertColumnName.equals(UserRole.getColumnName(UserRole.Columns.CREATED_AT))) {
			parameters.addValue(parameterName, model.getCreatedAt());
		} else if(insertColumnName.equals(UserRole.getColumnName(UserRole.Columns.UPDATED_AT))) {
			parameters.addValue(parameterName, model.getUpdatedAt());
		} else if(insertColumnName.equals(UserRole.getColumnName(UserRole.Columns.DELETED))) {
			parameters.addValue(parameterName, model.isDeleted());
		} else {
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, UserRole model) {
		if(keyHolderColumnName.equals(UserRole.getColumnName(UserRole.Columns.ID))) {
			model.setId((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(UserRole.getColumnName(UserRole.Columns.ROLE_NAME))) {
			model.setRoleName((String) keyMap.get(keyHolderColumnName));
		}  else if(keyHolderColumnName.equals(UserRole.getColumnName(UserRole.Columns.CREATED_AT))) {
			model.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(UserRole.getColumnName(UserRole.Columns.UPDATED_AT))) {
			model.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(UserRole.getColumnName(UserRole.Columns.DELETED))) {
			model.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
		} else {
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
	}

	@Override
	public List<UserRole> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		UserRolesExtractor extractor = new UserRolesExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(UserRole.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		List<UserRole> userRolesList = jdbcTemplate.query(queryTemplate, extractor, parameters);

		return userRolesList;
	}

	@Override
	public UserRole findById(int id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(UserRole.getColumnName(UserRole.Columns.ID), false);
		List<String> selectColumnNames = UserRole.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);

		List<UserRole> userRolesList = select(selectColumnNames, queryTermList, orderByList);

		UserRole userRole = null;

		if(!userRolesList.isEmpty()) {
			userRole = userRolesList.get(0);
		}

		return userRole;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
		String queryTemplate = QueryStringBuilder.generateUpdateString(UserRole.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(UserRole.TABLE_NAME, queryTermList);

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
		
		List<String> actualColumnNames = UserRole.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
