package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Course.Columns;

public class CoursesExtractor extends Extractor<List<Course>>
{
	@Override
	public List<Course> extractData(ResultSet rs) throws SQLException
	{
		List<Course> resultList = new ArrayList<>();

		while(rs.next())
		{
			Course result = new Course();
				
			result.setId(rs.getInt(Course.getColumnName(Course.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setName(rs.getString(Course.getColumnName(Course.Columns.NAME)));
			result.setColumnAbrv(rs.getString(Course.getColumnName(Course.Columns.COLUMN_ABRV)));
			result.setDeptId(rs.getInt(Course.getColumnName(Course.Columns.DEPT_ID)));
			result.setCourseNum(rs.getString(Course.getColumnName(Course.Columns.COURSE_NUMBER)));
			result.setNumLectures(rs.getString(Course.getColumnName(Course.Columns.NUMBER_OF_LECTURES)));
			result.setNumLabs(rs.getString(Course.getColumnName(Course.Columns.NUMBER_OF_LABS)));
			result.setNumConferences(rs.getString(Course.getColumnName(Course.Columns.NUMBER_OF_CONFERENCES)));
			
			result.setCreatedAt(rs.getTimestamp(Course.getColumnName(Course.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(Course.getColumnName(Course.Columns.UPDATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
