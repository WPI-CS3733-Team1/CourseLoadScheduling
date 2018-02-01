package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.extractor.SectionsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class SectionsDaoImpl extends BaseDaoImpl<Section> implements SectionsDao {

	@Override
	public int insert(Section model, List<String> insertColumnNameList, List<String> keyHolderColumnNameList)
			throws SQLException {
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Section.TABLE_NAME, insertColumnNameList);
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

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Section model) {
		
		if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.ID))) {
			model.setId((Integer) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.COURSE_ID))) {
			model.setCourseId((Integer) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.CRN))) {
			model.setCRN((Integer) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.SECTION_NAME))) {
			model.setSectionName((String) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.SECTION_ID))) {
			model.setSectionId((Integer) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.EXPECTED_POPULATION))) {
			model.setExpectedPop((Integer) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.REQUIRED_FREQUENCY))) {
			model.setRequiredFreq((Integer) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.ACADEMIC_YEAR))) {
			model.setAcademicYear((Integer) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.ACADEMIC_TERM))) {
			model.setAcademicTerm((String) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.START_TIME))) {
			model.setStartTime((Integer) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.END_TIME))) {
			model.setEndTime((Integer) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.DAYS_PER_WEEK))) {
			model.setDaysPerWeek((String) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.COURSE_LOCATION))) {
			model.setCourseLocation((String) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.CREATED_AT))) {
			model.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.UPDATED_AT))) {
			model.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		}else {
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
		
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Section model) {
		
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName,  false);
		
		if(insertColumnName.equals(Section.getColumnName(Section.Columns.ID))) {
			parameters.addValue(parameterName,  model.getId());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.COURSE_ID))) {
			parameters.addValue(parameterName,  model.getCourseId());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.CRN))) {
			parameters.addValue(parameterName, model.getCRN());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.SECTION_NAME))) {
			parameters.addValue(parameterName, model.getSectionName());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.SECTION_ID))) {
			parameters.addValue(parameterName, model.getSectionId());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.EXPECTED_POPULATION))) {
			parameters.addValue(parameterName, model.getExpectedPop());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.REQUIRED_FREQUENCY))) {
			parameters.addValue(parameterName, model.getRequiredFreq());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.ACADEMIC_YEAR))) {
			parameters.addValue(parameterName, model.getAcademicYear());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.ACADEMIC_TERM))) {
			parameters.addValue(parameterName, model.getAcademicTerm());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.START_TIME))) {
			parameters.addValue(parameterName, model.getStartTime());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.END_TIME))) {
			parameters.addValue(parameterName, model.getEndTime());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.DAYS_PER_WEEK))) {
			parameters.addValue(parameterName, model.getDaysPerWeek());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.COURSE_LOCATION))) {
			parameters.addValue(parameterName, model.getCourseLocation());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.CREATED_AT))) {
			parameters.addValue(parameterName, model.getCreatedAt());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.UPDATED_AT))) {
			parameters.addValue(parameterName, model.getUpdatedAt());
		}else {
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}

	@Override
	public List<Section> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList,
			List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
			SectionsExtractor extractor = new SectionsExtractor();
			
			String queryTemplate = QueryStringBuilder.generateSelectString(Section.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);
			List<Object> objectList = new ArrayList<Object>();

			for(QueryTerm queryTerm : queryTermList)
			{
				objectList.add(queryTerm.getValue());
			}
			
		    Object[] parameters = objectList.toArray();
			
		    List<Section> sectionsList = jdbcTemplate.query(queryTemplate, extractor, parameters);
		    
		    return sectionsList;
	}

	@Override
	public Section findById(int id) throws SQLException {
		
		String columnName = QueryStringBuilder.convertColumnName(Section.getColumnName(Section.Columns.ID), false);
		List<String> selectColumnNames = Section.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Section> sectionsList = select(selectColumnNames, queryTermList, orderByList);
		
		Section section = null;
		
		if(!sectionsList.isEmpty()) {
			section = sectionsList.get(0);
		}
		
		return section;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {

		String queryTemplate = QueryStringBuilder.generateUpdateString(Section.TABLE_NAME, columnName, queryTermList);

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
		
		String queryTemplate = QueryStringBuilder.generateDeleteString(Section.TABLE_NAME, queryTermList);

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
		List<String> actualColumnNames = Section.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
	
}
