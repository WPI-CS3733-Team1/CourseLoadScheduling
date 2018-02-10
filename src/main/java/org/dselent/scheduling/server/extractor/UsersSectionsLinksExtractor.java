package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.UsersSectionsLink;

public class UsersSectionsLinksExtractor extends Extractor<List<UsersSectionsLink>>
{
	@Override
	public List<UsersSectionsLink> extractData(ResultSet rs) throws SQLException
	{
		List<UsersSectionsLink> resultList = new ArrayList<>();

		while(rs.next())
		{
			UsersSectionsLink result = new UsersSectionsLink();
				
			result.setId(rs.getInt(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setUserId(rs.getInt(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.USER_ID)));
			result.setSectionId(rs.getInt(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.SECTION_ID)));
			result.setCreatedAt(rs.getTimestamp(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.UPDATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
