package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CompleteSection;
import org.dselent.scheduling.server.model.SidebarInfo;

public class SidebarInfoExtractor extends Extractor<List<SidebarInfo>> {

	@Override
	public List<SidebarInfo> extractData(ResultSet rs) throws SQLException {
		List<SidebarInfo> resultList = new ArrayList<>();

		while(rs.next())
		{
			SidebarInfo result = new SidebarInfo();
				
			result.setId(rs.getInt(CompleteSection.getColumnName(CompleteSection.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setId((Integer) rs.getInt(SidebarInfo.getColumnName(SidebarInfo.Columns.ID)));
			result.setFirstName(rs.getString(SidebarInfo.getColumnName(SidebarInfo.Columns.FIRST_NAME)));
			result.setLastName(rs.getString(SidebarInfo.getColumnName(SidebarInfo.Columns.LAST_NAME)));
			result.setEmail(rs.getString(SidebarInfo.getColumnName(SidebarInfo.Columns.EMAIL)));
			result.setRoleName(rs.getString(SidebarInfo.getColumnName(SidebarInfo.Columns.ROLE_NAME)));
			result.setDepartmentName((rs.getString(SidebarInfo.getColumnName(SidebarInfo.Columns.DEPARTMENT_NAME))));
			result.setCreatedAt(rs.getTimestamp(SidebarInfo.getColumnName(SidebarInfo.Columns.CREATED_AT)));
			
			
			
			resultList.add(result);
		}
			
		return resultList;
	}

}
