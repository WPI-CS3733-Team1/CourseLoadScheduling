package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Model to represent a result of a custom query over courses/sections
//Includes ALL relevent sections data, with accompanying relevant course data
public class CompleteSection extends Model
{
	// table name
	//public static final String TABLE_NAME = "courses";
		
	// column names
	public static enum Columns
	{
		ID,
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
		COURSE_ABRV,
		NAME,
		DEPT_ID,
		COURSE_NUMBER,
		CRN
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
		COLUMN_TYPE_MAP.put(Columns.SECTION_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.EXPECTED_POPULATION, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.REQUIRED_FREQUENCY, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.ACADEMIC_YEAR, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.ACADEMIC_TERM, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.START_TIME, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.END_TIME, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.DAYS_PER_WEEK, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.COURSE_LOCATION, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.COURSE_ABRV, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.DEPT_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.COURSE_NUMBER, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.CRN, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.SECTION_ID, JDBCType.INTEGER);
	};
	
	// attributes
	
	private Integer id;
	private String courseAbrv;
	private String name;
	private Integer deptId;
	private String courseNum;
	private String sectionName;
	private Integer expectedPopulation;
	private Integer requiredFrequency;
	private Integer startTime;
	private Integer endTime;
	private Integer acadmicYear;
	private String academicTerm;
	private String daysPerWeek;
	private String courseLocation;
	private Integer crn;
	private Integer sectionId;

	// methods
		
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getCourseAbrv(){
		return courseAbrv;
	}
	
	public void setCourseAbrv(String courseAbrv){
		this.courseAbrv = courseAbrv;
	}
	
	public Integer getExpectedPopulation() {
		return expectedPopulation;
	}

	public void setExpectedPopulation(Integer expectedPopulation) {
		this.expectedPopulation = expectedPopulation;
	}

	public Integer getRequiredFrequency() {
		return requiredFrequency;
	}

	public void setRequiredFrequency(Integer requiredFrequency) {
		this.requiredFrequency = requiredFrequency;
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

	public Integer getAcadmicYear() {
		return acadmicYear;
	}

	public void setAcadmicYear(Integer acadmicYear) {
		this.acadmicYear = acadmicYear;
	}

	public String getAcademicTerm() {
		return academicTerm;
	}

	public void setAcademicTerm(String academicTerm) {
		this.academicTerm = academicTerm;
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

	public Integer getCrn() {
		return crn;
	}

	public void setCrn(Integer crn) {
		this.crn = crn;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public static String getTableName() {
		return null;
	}

	public static List<Columns> getColumnList() {
		return COLUMN_LIST;
	}

	public static Map<Columns, JDBCType> getColumnTypeMap() {
		return COLUMN_TYPE_MAP;
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((academicTerm == null) ? 0 : academicTerm.hashCode());
		result = prime * result + ((acadmicYear == null) ? 0 : acadmicYear.hashCode());
		result = prime * result + ((courseLocation == null) ? 0 : courseLocation.hashCode());
		result = prime * result + ((courseNum == null) ? 0 : courseNum.hashCode());
		result = prime * result + ((crn == null) ? 0 : crn.hashCode());
		result = prime * result + ((daysPerWeek == null) ? 0 : daysPerWeek.hashCode());
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((expectedPopulation == null) ? 0 : expectedPopulation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((courseAbrv == null) ? 0 : courseAbrv.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((requiredFrequency == null) ? 0 : requiredFrequency.hashCode());
		result = prime * result + ((sectionId == null) ? 0 : sectionId.hashCode());
		result = prime * result + ((sectionName == null) ? 0 : sectionName.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		CompleteSection other = (CompleteSection) obj;
		if (academicTerm == null) {
			if (other.academicTerm != null)
				return false;
		} else if (!academicTerm.equals(other.academicTerm))
			return false;
		if (acadmicYear == null) {
			if (other.acadmicYear != null)
				return false;
		} else if (!acadmicYear.equals(other.acadmicYear))
			return false;
		if (courseLocation == null) {
			if (other.courseLocation != null)
				return false;
		} else if (!courseLocation.equals(other.courseLocation))
			return false;
		if (courseNum == null) {
			if (other.courseNum != null)
				return false;
		} else if (!courseNum.equals(other.courseNum))
			return false;
		if (crn == null) {
			if (other.crn != null)
				return false;
		} else if (!crn.equals(other.crn))
			return false;
		if (daysPerWeek == null) {
			if (other.daysPerWeek != null)
				return false;
		} else if (!daysPerWeek.equals(other.daysPerWeek))
			return false;
		if (deptId == null) {
			if (other.deptId != null)
				return false;
		} else if (!deptId.equals(other.deptId))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (expectedPopulation == null) {
			if (other.expectedPopulation != null)
				return false;
		} else if (!expectedPopulation.equals(other.expectedPopulation))
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
		if (requiredFrequency == null) {
			if (other.requiredFrequency != null)
				return false;
		} else if (!requiredFrequency.equals(other.requiredFrequency))
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
		if (courseAbrv == null) {
			if (other.courseAbrv!= null)
				return false;
		} else if (!courseAbrv.equals(other.courseAbrv))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "CompleteSection [id=" + id + "course abbreviation=" + courseAbrv + ", name=" + name + ", deptId=" + deptId + ", courseNum=" + courseNum
				+ ", sectionName=" + sectionName + ", expectedPopulation=" + expectedPopulation + ", requiredFrequency="
				+ requiredFrequency + ", startTime=" + startTime + ", endTime=" + endTime + ", acadmicYear="
				+ acadmicYear + ", academicTerm=" + academicTerm + ", daysPerWeek=" + daysPerWeek + ", courseLocation="
				+ courseLocation + ", crn=" + crn + ", sectionId=" + sectionId + "]";
	}
	
}
