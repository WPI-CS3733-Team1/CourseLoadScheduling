package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.UserStateDao;
import org.dselent.scheduling.server.extractor.UserStatesExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.UserState;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class UserStateDaoImpl extends BaseDaoImpl<UserState> implements UserStateDao {

	@Override
	public int insert(UserState model, List<String> insertColumnNameList, List<String> keyHolderColumnNameList)
			throws SQLException {
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);
		
		String queryTemplate = QueryStringBuilder.generateInsertString(UserState.TABLE_NAME, insertColumnNameList);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, model);
	    }
	    // new wy, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, model);
	    }
	    	    
	    return rowsAffected;
	}
	
	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, UserState model)
	{
		if(keyHolderColumnName.equals(UserState.getColumnName(UserState.Columns.ID))) {
			model.setId((Integer) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(UserState.getColumnName(UserState.Columns.STATE))) {
			model.setState((String) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(UserState.getColumnName(UserState.Columns.CREATED_AT))) {
			model.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(UserState.getColumnName(UserState.Columns.UPDATED_AT))) {
			model.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(UserState.getColumnName(UserState.Columns.DELETED))) {
			model.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
		}else {
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
	}
	
private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, UserState model) {
		
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName,  false);
		
		if(insertColumnName.equals(UserState.getColumnName(UserState.Columns.ID))) {
			parameters.addValue(parameterName,  model.getId());
		}else if(insertColumnName.equals(UserState.getColumnName(UserState.Columns.STATE))) {
			parameters.addValue(parameterName,  model.getState());
		}else if(insertColumnName.equals(UserState.getColumnName(UserState.Columns.CREATED_AT))) {
			parameters.addValue(parameterName, model.getCreatedAt());
		}else if(insertColumnName.equals(UserState.getColumnName(UserState.Columns.UPDATED_AT))) {
			parameters.addValue(parameterName, model.getUpdatedAt());
		}else if(insertColumnName.equals(UserState.getColumnName(UserState.Columns.DELETED))) {
			parameters.addValue(parameterName, model.isDeleted());
		}else {
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}
	
@Override
public List<UserState> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList,
		List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		UserStatesExtractor extractor = new UserStatesExtractor();
		
		String queryTemplate = QueryStringBuilder.generateSelectString(UserState.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);
		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		
	    List<UserState> userStatesList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return userStatesList;
}

	@Override
	public UserState findById(int id) throws SQLException {

		String columnName = QueryStringBuilder.convertColumnName(UserState.getColumnName(UserState.Columns.ID), false);
		List<String> selectColumnNames = UserState.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<UserState> userStatesList = select(selectColumnNames, queryTermList, orderByList);
		
		UserState userState = null;
		
		if(!userStatesList.isEmpty()) {
			userState = userStatesList.get(0);
		}
		
		return userState;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
		String queryTemplate = QueryStringBuilder.generateUpdateString(UserState.TABLE_NAME, columnName, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		objectList.add(newValue);
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	@Override
	public int delete(List<QueryTerm> queryTermList) throws SQLException {
		String queryTemplate = QueryStringBuilder.generateDeleteString(UserState.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

		return rowsAffected;
	}

	@Override
	public void validateColumnNames(List<String> columnNameList) {
		List<String> actualColumnNames = UserState.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
	

}
