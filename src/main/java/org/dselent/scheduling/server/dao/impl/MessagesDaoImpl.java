package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.MessagesDao;
import org.dselent.scheduling.server.extractor.MessagesExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Message;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MessagesDaoImpl extends BaseDaoImpl<Message> implements MessagesDao {
	@Override
	public int insert(Message model, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException {
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Message.TABLE_NAME, insertColumnNameList);
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

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Message model) {
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		if(insertColumnName.equals(Message.getColumnName(Message.Columns.ID))) {
			parameters.addValue(parameterName, model.getId());
		} else if(insertColumnName.equals(Message.getColumnName(Message.Columns.USER_ID))) {
			parameters.addValue(parameterName, model.getUserId());
		} else if(insertColumnName.equals(Message.getColumnName(Message.Columns.AUTHOR_USER_NAME))) {
			parameters.addValue(parameterName, model.getAuthorUserName());
		} else if(insertColumnName.equals(Message.getColumnName(Message.Columns.MESSAGE))) {
			parameters.addValue(parameterName, model.getMessage());
		} else if(insertColumnName.equals(Message.getColumnName(Message.Columns.RESOLVED))) {
			parameters.addValue(parameterName, model.isResolved());
		} else if(insertColumnName.equals(Message.getColumnName(Message.Columns.RECEIVED_AT))) {
			parameters.addValue(parameterName, model.getReceivedAt());
		} else if(insertColumnName.equals(Message.getColumnName(Message.Columns.DEPT_ID))) {
			parameters.addValue(parameterName, model.getDeptId());
		} else {
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Message model) {
		if(keyHolderColumnName.equals(Message.getColumnName(Message.Columns.ID))) {
			model.setId((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Message.getColumnName(Message.Columns.USER_ID))) {
			model.setUserId((Integer) keyMap.get(keyHolderColumnName));
		}  else if(keyHolderColumnName.equals(Message.getColumnName(Message.Columns.AUTHOR_USER_NAME))) {
			model.setAuthorUserName((String) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Message.getColumnName(Message.Columns.MESSAGE))) {
			model.setMessage((String) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Message.getColumnName(Message.Columns.RESOLVED))) {
			model.setResolved((Boolean) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Message.getColumnName(Message.Columns.RECEIVED_AT))) {
			model.setReceivedAt((Timestamp) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(Message.getColumnName(Message.Columns.DEPT_ID))) {
			model.setDeptId((Integer) keyMap.get(keyHolderColumnName));
		} else {
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
	}

	@Override
	public List<Message> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		MessagesExtractor extractor = new MessagesExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(Message.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		List<Message> messagesList = jdbcTemplate.query(queryTemplate, extractor, parameters);

		return messagesList;
	}

	@Override
	public Message findById(int id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(Message.getColumnName(Message.Columns.ID), false);
		List<String> selectColumnNames = Message.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);

		List<Message> messagesList = select(selectColumnNames, queryTermList, orderByList);

		Message message = null;

		if(!messagesList.isEmpty()) {
			message = messagesList.get(0);
		}

		return message;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
		String queryTemplate = QueryStringBuilder.generateUpdateString(Message.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(Message.TABLE_NAME, queryTermList);

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
		
		List<String> actualColumnNames = Message.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
