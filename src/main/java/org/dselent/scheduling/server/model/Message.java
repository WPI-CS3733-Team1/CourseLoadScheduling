package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Model for the Message class which is what the Inbox table is comprised of.
public class Message extends Model
{
	// table name
	public static final String TABLE_NAME = "Inbox";
		
	// column names
	public static enum Columns
	{
		ID,
		USER_ID,
		AUTHOR_USER_NAME,
		MESSAGE,
		RESOLVED,
		RECEIVED_AT,
		DEPT_ID
	}
	
	// enum list
	private static final List<Columns> COLUMN_LIST = new ArrayList<>();
	
	// type mapping
	private static final Map<Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();
	
	static
	{
		for(Columns key : Columns.values())
		{
			COLUMN_LIST.add(key);
		}
		
		COLUMN_TYPE_MAP.put(Columns.ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.USER_ID, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.AUTHOR_USER_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.MESSAGE, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.RESOLVED, JDBCType.BOOLEAN);
		COLUMN_TYPE_MAP.put(Columns.RECEIVED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		COLUMN_TYPE_MAP.put(Columns.DEPT_ID, JDBCType.INTEGER);
	};
	
	// attributes
	
	private Integer id;
	private Integer userId;
	private String authorUserName;
	private String message;
	private boolean resolved;
	private Instant receivedAt;
	private Integer deptId;

	// methods
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
		
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAuthorUserName() {
		return authorUserName;
	}

	public void setAuthorUserName(String authorUserName) {
		this.authorUserName = authorUserName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	public Instant getReceivedAt() {
		return receivedAt;
	}
	
	public void setReceivedAt(Timestamp receivedAt) 
	{
		if (receivedAt != null)
		{
			this.receivedAt = receivedAt.toInstant();
		}
	}

	public void setReceivedAt(Instant receivedAt) {
		this.receivedAt = receivedAt;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public static JDBCType getColumnType(Columns column)
	{
		return COLUMN_TYPE_MAP.get(column);
	}
	
	public static String getColumnName(Columns column)
	{
		return column.toString().toLowerCase();
	}
	
	public static List<String> getColumnNameList()
	{
		List<String> columnNameList = new ArrayList<>();
		
		for(Columns column : COLUMN_LIST)
		{
			columnNameList.add(getColumnName(column));
		}
		
		return columnNameList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorUserName == null) ? 0 : authorUserName.hashCode());
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((receivedAt == null) ? 0 : receivedAt.hashCode());
		result = prime * result + (resolved ? 1231 : 1237);
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (authorUserName == null) {
			if (other.authorUserName != null)
				return false;
		} else if (!authorUserName.equals(other.authorUserName))
			return false;
		if (deptId == null) {
			if (other.deptId != null)
				return false;
		} else if (!deptId.equals(other.deptId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (receivedAt == null) {
			if (other.receivedAt != null)
				return false;
		} else if (!receivedAt.equals(other.receivedAt))
			return false;
		if (resolved != other.resolved)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Users [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", authorUserName=");
		builder.append(authorUserName);
		builder.append(", message=");
		builder.append(message);
		builder.append(", resolved=");
		builder.append(resolved);
		builder.append(", receivedAt=");
		builder.append(receivedAt);
		builder.append(", deptId=");
		builder.append(deptId);
		builder.append("]");
		return builder.toString();
	}
	
}
