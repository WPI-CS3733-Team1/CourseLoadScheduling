package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.model.User.Columns;

public class Section extends Model
{
	// table name
	public static final String TABLE_NAME = "sections";
		
	// column names
	public static enum Columns
	{
		ID,
		COURSE_ID,
		CRN,
		SECTION_NAME,
		SECTION_ID,	
		EXPECTED_POPULATION,
		REQUIRED_FREQUENCY,
		ACADEMIC_YEAR,
		ACADEMIC_TERM,
		START_TIME,
		END_TIME,
		DAYS_PER_WEEK,
		COURSE_LOCATION,
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
		COLUMN_TYPE_MAP.put(Columns.COURSE_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.CRN, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.SECTION_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.SECTION_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.EXPECTED_POPULATION, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.REQUIRED_FREQUENCY, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.ACADEMIC_YEAR, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.ACADEMIC_TERM, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.START_TIME, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.END_TIME, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.DAYS_PER_WEEK, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.COURSE_LOCATION, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		COLUMN_TYPE_MAP.put(Columns.UPDATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
	};
	
	// attributes
	
	private Integer id;
	private Integer courseId;
	private Integer CRN;
	private String sectionName;
	private Integer sectionId;
	private Integer expectedPop;
	private Integer requiredFreq;
	private Integer academicYear;
	private String academicTerm;
	private Integer startTime;
	private Integer endTime;
	private String daysPerWeek;
	private String courseLocation;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getCRN() {
		return CRN;
	}

	public void setCRN(Integer cRN) {
		CRN = cRN;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getExpectedPop() {
		return expectedPop;
	}

	public void setExpectedPop(Integer expectedPop) {
		this.expectedPop = expectedPop;
	}

	public Integer getRequiredFreq() {
		return requiredFreq;
	}

	public void setRequiredFreq(Integer requiredFreq) {
		this.requiredFreq = requiredFreq;
	}

	public Integer getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}

	public String getAcademicTerm() {
		return academicTerm;
	}

	public void setAcademicTerm(String academicTerm) {
		this.academicTerm = academicTerm;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public String getDaysPerWeek() {
		return daysPerWeek;
	}

	public void setDaysPerWeek(String daysPerWeek) {
		this.daysPerWeek = daysPerWeek;
	}

	public String getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setCreatedAt(Timestamp createdAt) 
	{
		if (createdAt != null)
		{
			this.createdAt = createdAt.toInstant();
		}
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt) 
	{
		if (updatedAt != null)
		{
			this.updatedAt = updatedAt.toInstant();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CRN == null) ? 0 : CRN.hashCode());
		result = prime * result + ((academicTerm == null) ? 0 : academicTerm.hashCode());
		result = prime * result + ((academicYear == null) ? 0 : academicYear.hashCode());
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((courseLocation == null) ? 0 : courseLocation.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((daysPerWeek == null) ? 0 : daysPerWeek.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((expectedPop == null) ? 0 : expectedPop.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((requiredFreq == null) ? 0 : requiredFreq.hashCode());
		result = prime * result + ((sectionId == null) ? 0 : sectionId.hashCode());
		result = prime * result + ((sectionName == null) ? 0 : sectionName.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		Section other = (Section) obj;
		if (CRN == null) {
			if (other.CRN != null)
				return false;
		} else if (!CRN.equals(other.CRN))
			return false;
		if (academicTerm == null) {
			if (other.academicTerm != null)
				return false;
		} else if (!academicTerm.equals(other.academicTerm))
			return false;
		if (academicYear == null) {
			if (other.academicYear != null)
				return false;
		} else if (!academicYear.equals(other.academicYear))
			return false;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		if (courseLocation == null) {
			if (other.courseLocation != null)
				return false;
		} else if (!courseLocation.equals(other.courseLocation))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (daysPerWeek == null) {
			if (other.daysPerWeek != null)
				return false;
		} else if (!daysPerWeek.equals(other.daysPerWeek))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (expectedPop == null) {
			if (other.expectedPop != null)
				return false;
		} else if (!expectedPop.equals(other.expectedPop))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (requiredFreq == null) {
			if (other.requiredFreq != null)
				return false;
		} else if (!requiredFreq.equals(other.requiredFreq))
			return false;
		if (sectionId == null) {
			if (other.sectionId != null)
				return false;
		} else if (!sectionId.equals(other.sectionId))
			return false;
		if (sectionName == null) {
			if (other.sectionName != null)
				return false;
		} else if (!sectionName.equals(other.sectionName))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", courseID=" + courseId + ", CRN=" + CRN + ", sectionName=" + sectionName
				+ ", sectionId=" + sectionId + ", expectedPop=" + expectedPop + ", requiredFreq=" + requiredFreq
				+ ", academicYear=" + academicYear + ", academicTerm=" + academicTerm + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", daysPerWeek=" + daysPerWeek + ", courseLocation=" + courseLocation
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
}
