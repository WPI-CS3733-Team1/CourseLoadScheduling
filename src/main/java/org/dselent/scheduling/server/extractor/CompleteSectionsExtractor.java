package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CompleteSection;
//TODO: Add getSection() -> returns a section model from an instance of this class
public class CompleteSectionsExtractor extends Extractor<List<CompleteSection>>
{
	@Override
	public List<CompleteSection> extractData(ResultSet rs) throws SQLException
	{
		List<CompleteSection> resultList = new ArrayList<>();

		while(rs.next())
		{
			CompleteSection result = new CompleteSection();
				
			result.setId(rs.getInt(CompleteSection.getColumnName(CompleteSection.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setAcademicTerm(rs.getString(CompleteSection.getColumnName(CompleteSection.Columns.ACADEMIC_TERM)));
			result.setAcadmicYear(rs.getInt(CompleteSection.getColumnName(CompleteSection.Columns.ACADEMIC_YEAR)));
			result.setCourseLocation(rs.getString(CompleteSection.getColumnName(CompleteSection.Columns.COURSE_LOCATION)));
			result.setCourseNum(rs.getString(CompleteSection.getColumnName(CompleteSection.Columns.COURSE_NUMBER)));
			result.setCrn(rs.getInt(CompleteSection.getColumnName(CompleteSection.Columns.CRN)));
			result.setDaysPerWeek(rs.getString(CompleteSection.getColumnName(CompleteSection.Columns.DAYS_PER_WEEK)));
			result.setDeptId(rs.getInt(CompleteSection.getColumnName(CompleteSection.Columns.DEPT_ID)));
			result.setEndTime(rs.getInt(CompleteSection.getColumnName(CompleteSection.Columns.END_TIME)));
			result.setExpectedPopulation(rs.getInt(CompleteSection.getColumnName(CompleteSection.Columns.EXPECTED_POPULATION)));
			result.setRequiredFrequency(rs.getInt(CompleteSection.getColumnName(CompleteSection.Columns.REQUIRED_FREQUENCY)));
			result.setSectionId(rs.getInt(CompleteSection.getColumnName(CompleteSection.Columns.SECTION_ID)));
			result.setSectionName(rs.getString(CompleteSection.getColumnName(CompleteSection.Columns.SECTION_NAME)));
			result.setStartTime(rs.getInt(CompleteSection.getColumnName(CompleteSection.Columns.START_TIME)));
			result.setName(rs.getString(CompleteSection.getColumnName(CompleteSection.Columns.NAME)));
			result.setName(rs.getString(CompleteSection.getColumnName(CompleteSection.Columns.COLUMN_ABRV)));
			
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
