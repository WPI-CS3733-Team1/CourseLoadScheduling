package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.SectionTypesDao;
import org.dselent.scheduling.server.extractor.SectionTypesExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.SectionType;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class SectionTypesDaoImpl extends BaseDaoImpl<SectionType> implements SectionTypesDao{

	@Override
	public int insert(SectionType model, List<String> insertColumnNameList, List<String> keyHolderColumnNameList)
			throws SQLException {
			
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(SectionType.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, model);
	    }

	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, model);
	    }
	    
		return rowsAffected;
	}

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, SectionType model) {
		
		if(keyHolderColumnName.equals(SectionType.getColumnName(SectionType.Columns.ID))) {
			model.setId((Integer) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(SectionType.getColumnName(SectionType.Columns.TYPE_NAME))) {
			model.setSectionType((String) keyMap.get(keyHolderColumnName));
		}else {
			throw new IllegalArgumentException("Invalid column name provided: "+keyHolderColumnName);
		}
		
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, SectionType model) {
		
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName,  false);
		
		if(insertColumnName.equals(SectionType.getColumnName(SectionType.Columns.ID))) {
			parameters.addValue(parameterName, model.getId());
		}else if(insertColumnName.equals(SectionType.getColumnName(SectionType.Columns.ID))) {
			parameters.addValue(parameterName, model.getSectionType());
		}else {
			throw new IllegalArgumentException("Invalid column name provided: "+insertColumnName);
		}
	}

	@Override
	public List<SectionType> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList,
			List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		
		SectionTypesExtractor extractor = new SectionTypesExtractor();
		
		String queryTemplate = QueryStringBuilder.generateSelectString(SectionType.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);
		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();
		
	    List<SectionType> sectionTypesList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
		return sectionTypesList;
	}

	@Override
	public SectionType findById(int id) throws SQLException {
		
		String columnName = QueryStringBuilder.convertColumnName(SectionType.getColumnName(SectionType.Columns.ID), false);
		List<String> selectColumnNames = SectionType.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<SectionType> sectionTypesList = select(selectColumnNames, queryTermList, orderByList);
		
		SectionType sectionType = null;
		
		if(!sectionTypesList.isEmpty()) {
			sectionType = sectionTypesList.get(0);
		}
		
		return sectionType;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
		
		String queryTemplate = QueryStringBuilder.generateUpdateString(SectionType.TABLE_NAME, columnName, queryTermList);

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
		
		String queryTemplate = QueryStringBuilder.generateDeleteString(SectionType.TABLE_NAME, queryTermList);

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
		
		List<String> actualColumnNames = SectionType.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}

}
