package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.SectionType;

public class SectionTypesExtractor extends Extractor<List<SectionType>>{

	@Override
	public List<SectionType> extractData(ResultSet rs) throws SQLException {
		
		List<SectionType> resultList = new ArrayList<>();

		while(rs.next())
		{
			SectionType result = new SectionType();
				
			result.setId(rs.getInt(SectionType.getColumnName(SectionType.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setSectionType(rs.getString(SectionType.getColumnName(SectionType.Columns.TYPE_NAME)));
			
			
			if(rs.wasNull())
			{
				result.setSectionType(null);
			}
			
			resultList.add(result);
		}
			
		return resultList;
	}

}
