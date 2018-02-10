package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Message;
import org.dselent.scheduling.server.model.SidebarInfo;

public class CourseNamesExtractor extends Extractor<List<String>> {

	@Override
	public List<String> extractData(ResultSet rs) throws SQLException {
		
		List<String> resultList = new ArrayList<>();

		while(rs.next())
		{
			
			String result = "";
			
			
			
			
			if(rs.wasNull())
			{
				result = (null);
			}
			
			//String courseID = rs.getString("course_id");
			String abrv = rs.getString("course_abrv");
			String number = rs.getString("course_number");
			
			result = abrv + number;
				
			if(rs.wasNull())
			{
				result = (null);
			}
			
			resultList.add(result);
		}
			
		return resultList;
	}

}
