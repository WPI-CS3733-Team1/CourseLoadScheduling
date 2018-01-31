package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Section;

public class SectionsExtractor extends Extractor<List<Section>> 
{
	@Override
	public List<Section> extractData(ResultSet rs) throws SQLException
	{
		List<Section> resultList = new ArrayList<>();

		while(rs.next())
		{
			Section result = new Section();
				
			result.setId(rs.getInt(Section.getColumnName(Section.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setCourseId(rs.getInt(Section.getColumnName(Section.Columns.COURSE_ID)));
			result.setCRN(rs.getInt(Section.getColumnName(Section.Columns.CRN)));
			result.setSectionName(rs.getString(Section.getColumnName(Section.Columns.SECTION_NAME)));
			result.setSectionId(rs.getInt(Section.getColumnName(Section.Columns.SECTION_ID)));
			result.setExpectedPop(rs.getInt(Section.getColumnName(Section.Columns.EXPECTED_POPULATION)));
			result.setRequiredFreq(rs.getInt(Section.getColumnName(Section.Columns.REQUIRED_FREQUENCY)));
			result.setAcademicYear(rs.getInt(Section.getColumnName(Section.Columns.ACADEMIC_YEAR)));
			result.setAcademicTerm(rs.getString(Section.getColumnName(Section.Columns.ACADEMIC_TERM)));
			result.setStartTime(rs.getInt(Section.getColumnName(Section.Columns.START_TIME)));
			result.setEndTime(rs.getInt(Section.getColumnName(Section.Columns.END_TIME)));
			result.setDaysPerWeek(rs.getString(Section.getColumnName(Section.Columns.DAYS_PER_WEEK)));
			result.setCourseLocation(rs.getString(Section.getColumnName(Section.Columns.COURSE_LOCATION)));
			result.setCreatedAt(rs.getTimestamp(Section.getColumnName(Section.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(Section.getColumnName(Section.Columns.UPDATED_AT)));

			if(rs.wasNull())
			{
				result.setCourseId(null);
			}

			resultList.add(result);
		}
			
		return resultList;
	}
}
