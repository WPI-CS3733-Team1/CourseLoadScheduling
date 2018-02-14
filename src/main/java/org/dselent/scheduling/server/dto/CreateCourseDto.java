package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;
/**
 * DTO for bundling parameters when creating new courses.
 * @author Surya
 * 
 * -Noah - added DeptId and courseAbrv attribute. DID NOT update toString or hashcode functions, etc.
 * -only getters and builder with functions added
 *
 */
public class CreateCourseDto {
	private final String courseName;
	private final Integer deptId;
	private final String courseAbrv;
	private final String courseNumber;
	private final String numberOfLectures;
	private final String numberOfLabs;
	private final String numberOfConferences;
	
	
	
	@Generated("SparkTools")
	public CreateCourseDto(Builder builder) {
		super();
		this.courseName = builder.courseName;
		this.deptId = builder.deptId;
		this.courseAbrv = builder.courseAbrv;
		this.courseNumber = builder.courseNumber;
		this.numberOfLectures = builder.numberOfLectures;
		this.numberOfLabs = builder.numberOfLabs;
		this.numberOfConferences = builder.numberOfConferences; 
		if(this.courseName == null)
		{
			throw new IllegalStateException("Course name cannot be null");
		}
		else if(this.courseNumber == null)
		{
			throw new IllegalStateException("Course number cannot be null");
		}
		else if(this.numberOfLectures == null)
		{
			throw new IllegalStateException("Num. Lectures cannot be null");
		}
		else if(this.numberOfLabs == null)
		{
			throw new IllegalStateException("Num. Labs cannot be null");
		}
		else if(this.numberOfConferences == null)
		{
			throw new IllegalStateException("Num. Conferences cannot be null");
		}else if(this.deptId == null) {
			throw new IllegalStateException("deptId cannot be null!");
		}
	}

	public String getCourseName() {
		return courseName;
	}
	
	public Integer getDeptId() {
		return deptId;
	}

	public String getCourseAbrv() {
		return courseAbrv;
	}
	
	public String getCourseNumber() {
		return courseNumber;
	}

	public String getNumberOfLectures() {
		return numberOfLectures;
	}

	public String getNumberOfLabs() {
		return numberOfLabs;
	}

	public String getNumberOfConferences() {
		return numberOfConferences;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((courseNumber == null) ? 0 : courseNumber.hashCode());
		result = prime * result + ((numberOfConferences == null) ? 0 : numberOfConferences.hashCode());
		result = prime * result + ((numberOfLabs == null) ? 0 : numberOfLabs.hashCode());
		result = prime * result + ((numberOfLectures == null) ? 0 : numberOfLectures.hashCode());
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
		CreateCourseDto other = (CreateCourseDto) obj;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (courseNumber == null) {
			if (other.courseNumber != null)
				return false;
		} else if (!courseNumber.equals(other.courseNumber))
			return false;
		if (numberOfConferences == null) {
			if (other.numberOfConferences != null)
				return false;
		} else if (!numberOfConferences.equals(other.numberOfConferences))
			return false;
		if (numberOfLabs == null) {
			if (other.numberOfLabs != null)
				return false;
		} else if (!numberOfLabs.equals(other.numberOfLabs))
			return false;
		if (numberOfLectures == null) {
			if (other.numberOfLectures != null)
				return false;
		} else if (!numberOfLectures.equals(other.numberOfLectures))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreateCourseDto [courseName=" + courseName + ", courseNumber=" + courseNumber + ", numberOfLectures="
				+ numberOfLectures + ", numberOfLabs=" + numberOfLabs + ", numberOfConferences=" + numberOfConferences
				+ "]";
	}
	
	/**
	 * Creates builder to build {@link CreateCourseDto}. 
	 * @return finished builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}
	
	/**
	 * Builder to build {@link CreateCourseDto}
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private String courseName;
		private Integer deptId;
		private String courseAbrv;
		private String courseNumber;
		private String numberOfLectures;
		private String numberOfLabs;
		private String numberOfConferences;
		
		private Builder()
		{
		}

		public Builder withCourseName(String courseName)
		{
			this.courseName = courseName;
			return this;
		}
		public Builder withDeptId(Integer deptId) {
			this.deptId = deptId;
			return this;
		}
		public Builder withCourseAbrv(String abrv) {
			this.courseAbrv = abrv;
			return this;
		}
		public Builder withCourseNumber(String courseNumber)
		{
			this.courseNumber = courseNumber;
			return this;
		}
		public Builder withNumberOfLectures(String numberOfLectures)
		{
			this.numberOfLectures = numberOfLectures;
			return this;
		}
		public Builder withNumberOfLabs(String numberOfLabs)
		{
			this.numberOfLabs = numberOfLabs;
			return this;
		}
		public Builder withNumberOfConferences(String numberOfConferences)
		{
			this.numberOfConferences = numberOfConferences;
			return this;
		}
		public CreateCourseDto build()
		{
			return new CreateCourseDto(this);
		}
		
}
}
