package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.UsersRolesLink;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
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
	    

		return 0;
	}

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Section model) {
		
		if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.ID))) {
			model.setId((Integer) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.COURSE_ID))) {
			model.setCourseID((Integer) keyMap.get(keyHolderColumnName));
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
			//model.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		}else if(keyHolderColumnName.equals(Section.getColumnName(Section.Columns.UPDATED_AT))) {
			//model.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		}else {
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
		
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Section model) {
		
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName,  false);
		
		if(insertColumnName.equals(Section.getColumnName(Section.Columns.ID))) {
			parameters.addValue(parameterName,  model.getId());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.COURSE_ID))) {
			parameters.addValue(parameterName,  model.getCourseID());
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.CRN))) {
			
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.SECTION_NAME))) {
			
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.SECTION_ID))) {
			
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.EXPECTED_POPULATION))) {
			
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.REQUIRED_FREQUENCY))) {
			
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.ACADEMIC_YEAR))) {
			
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.ACADEMIC_TERM))) {
			
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.START_TIME))) {
			
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.END_TIME))) {
			
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.DAYS_PER_WEEK))) {
			
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.COURSE_LOCATION))) {
			
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.CREATED_AT))) {
			
		}else if(insertColumnName.equals(Section.getColumnName(Section.Columns.UPDATED_AT))) {
			
		}else {
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}

	@Override
	public List<Section> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList,
			List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Section findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(List<QueryTerm> queryTermList) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
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
