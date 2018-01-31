package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Message;

public class MessagesExtractor extends Extractor<List<Message>> 
{
	@Override
	public List<Message> extractData(ResultSet rs) throws SQLException
	{
		List<Message> resultList = new ArrayList<>();

		while(rs.next())
		{
			Message result = new Message();
				
			result.setId(rs.getInt(Message.getColumnName(Message.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setUserId(rs.getInt(Message.getColumnName(Message.Columns.USER_ID)));
			result.setAuthorUserName(rs.getString(Message.getColumnName(Message.Columns.AUTHOR_USER_NAME)));
			result.setMessage(rs.getString(Message.getColumnName(Message.Columns.MESSAGE)));
			result.setResolved(rs.getBoolean(Message.getColumnName(Message.Columns.RESOLVED)));
			result.setReceivedAt(rs.getTimestamp(Message.getColumnName(Message.Columns.RECEIVED_AT)));
			result.setDeptId(rs.getInt(Message.getColumnName(Message.Columns.DEPT_ID)));
			
			if(rs.wasNull())
			{
				result.setUserId(null);
			}
			
			resultList.add(result);
		}
			
		return resultList;
	}
}
