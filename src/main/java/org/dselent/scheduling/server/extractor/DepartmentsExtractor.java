package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Department;

public class DepartmentsExtractor extends Extractor<List<Department>> {

	
	@Override
	public List<Department> extractData(ResultSet rs) throws SQLException {
		{
			List<Department> resultList = new ArrayList<>();

			while(rs.next())
			{
				Department result = new Department();
					
				result.setId(rs.getInt(Department.getColumnName(Department.Columns.ID)));
				
				if(rs.wasNull())
				{
					result.setId(null);
				}
				result.setDepartmentID(rs.getString(Department.getColumnName(Department.Columns.DEPARTMENT_ID)));
				
				if(rs.wasNull())
				{
					result.setDepartmentID(null);
				}
				resultList.add(result);
			}
				
			return resultList;
		}

	}

}
