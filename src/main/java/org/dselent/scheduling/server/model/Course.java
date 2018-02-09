package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Course extends Model
{
	// table name
	public static final String TABLE_NAME = "courses";
		
	// column names
	public static enum Columns
	{
		ID,
		NAME,
		DEPT_ID,
		COURSE_NUMBER, 
		NUMBER_OF_LECTURES,
		NUMBER_OF_LABS,
		NUMBER_OF_CONFERENCES,
		CREATED_AT,
		UPDATED_AT
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
		COLUMN_TYPE_MAP.put(Columns.NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.DEPT_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.COURSE_NUMBER, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.NUMBER_OF_LECTURES, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.NUMBER_OF_LABS, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.NUMBER_OF_CONFERENCES, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		COLUMN_TYPE_MAP.put(Columns.UPDATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
	};
	
	// attributes
	
	private Integer id;
	private String name;
	private Integer deptId;
	private String courseNum;
	private String numLectures;
	private String numLabs;
	private String numConferences;
	private Instant createdAt;
	private Instant updatedAt;

	// methods
		
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
	
	//
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	public String getNumLectures() {
		return numLectures;
	}

	public void setNumLectures(String string) {
		this.numLectures = string;
	}

	public String getNumLabs() {
		return numLabs;
	}

	public void setNumLabs(String string) {
		this.numLabs = string;
	}

	public String getNumConferences() {
		return numConferences;
	}

	public void setNumConferences(String string) {
		this.numConferences = string;
	}

	public Instant getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt)
	{
		this.createdAt = createdAt;
	}
	
	public void setCreatedAt(Timestamp createdAt)
	{
		if(createdAt != null)
		{
			this.createdAt = createdAt.toInstant();
		}
	}

	public Instant getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt)
	{
		this.updatedAt = updatedAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt)
	{
		if(updatedAt != null)
		{
			this.updatedAt = updatedAt.toInstant();
		}
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseNum == null) ? 0 : courseNum.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numConferences == null) ? 0 : numConferences.hashCode());
		result = prime * result + ((numLabs == null) ? 0 : numLabs.hashCode());
		result = prime * result + ((numLectures == null) ? 0 : numLectures.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		Course other = (Course) obj;
		if (courseNum == null) {
			if (other.courseNum != null)
				return false;
		} else if (!courseNum.equals(other.courseNum))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numConferences == null) {
			if (other.numConferences != null)
				return false;
		} else if (!numConferences.equals(other.numConferences))
			return false;
		if (numLabs == null) {
			if (other.numLabs != null)
				return false;
		} else if (!numLabs.equals(other.numLabs))
			return false;
		if (numLectures == null) {
			if (other.numLectures != null)
				return false;
		} else if (!numLectures.equals(other.numLectures))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}
	

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Users [id]=");
		builder.append(id);
		builder.append(", Name=");
		builder.append(name);
		builder.append(", Department ID=");
		builder.append(deptId);
		builder.append(", Course Number=");
		builder.append(courseNum);
		builder.append(", Number of Lectures=");
		builder.append(numLectures);
		builder.append(", Number of Labs=");
		builder.append(numLabs);
		builder.append(", Number of Conferences=");
		builder.append(numConferences);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
	
}
