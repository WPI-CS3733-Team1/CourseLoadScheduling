package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;

import org.dselent.scheduling.server.dto.RegisterUserDto.Builder;


/**
 * Data Transfer Object used to bundle parameters when 
 * creating a new section
 * 
 * @author Harsh
 *
 */

public class CreateSectionDto {
	
	private final String courseId;
	private final String sectionName;
	private final String sectionId;
	private final String expectedPopulation;
	private final String requiredFrequency;
	private final String academicYear;
	private final String academicTerm;
	private final String startTime;
	private final String endTime;
	private final String daysPerWeek;
	private final String courseLocation;
	
	// I added to the auto-generated code
	@Generated("SparkTools")
	private CreateSectionDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		
		this.courseId = builder.courseId;
		this.sectionName = builder.sectionName;
		this.sectionId = builder.sectionId;
		this.expectedPopulation = builder.expectedPopulation;
		this.requiredFrequency = builder.requiredFrequency;
		this.academicYear = builder.academicYear;
		this.academicTerm = builder.academicTerm;
		this.startTime = builder.startTime;
		this.endTime = builder.endTime;
		this.daysPerWeek = builder.daysPerWeek;
		this.courseLocation = builder.courseLocation;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		
		if(this.courseId == null)
		{
			throw new IllegalStateException("CourseId cannot be null");
		}
		else if(this.sectionName == null)
		{
			throw new IllegalStateException("sectionName cannot be null");
		}
		else if(this.sectionId == null)
		{
			throw new IllegalStateException("sectionId cannot be null");
		}
		else if(this.expectedPopulation == null)
		{
			throw new IllegalStateException("expectedPopulation cannot be null");
		}
		else if(this.requiredFrequency == null)
		{
			throw new IllegalStateException("requiredFrequency cannot be null");
		}
		if(this.academicYear == null)
		{
			throw new IllegalStateException("academicYear cannot be null");
		}
		else if(this.academicTerm == null)
		{
			throw new IllegalStateException("academicTerm cannot be null");
		}
		else if(this.startTime == null)
		{
			throw new IllegalStateException("startTime cannot be null");
		}
		else if(this.endTime == null)
		{
			throw new IllegalStateException("endTime cannot be null");
		}
		else if(this.daysPerWeek == null)
		{
			throw new IllegalStateException("daysPerWeek cannot be null");
		}
		else if(this.courseLocation == null)
		{
			throw new IllegalStateException("courseLocation cannot be null");
		}
		
	}

	public String getCourseId() {
		return courseId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public String getSectionId() {
		return sectionId;
	}

	public String getExpectedPopulation() {
		return expectedPopulation;
	}

	public String getRequiredFrequency() {
		return requiredFrequency;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public String getAcademicTerm() {
		return academicTerm;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getDaysPerWeek() {
		return daysPerWeek;
	}

	public String getCourseLocation() {
		return courseLocation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((academicTerm == null) ? 0 : academicTerm.hashCode());
		result = prime * result + ((academicYear == null) ? 0 : academicYear.hashCode());
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((courseLocation == null) ? 0 : courseLocation.hashCode());
		result = prime * result + ((daysPerWeek == null) ? 0 : daysPerWeek.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((expectedPopulation == null) ? 0 : expectedPopulation.hashCode());
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
		CreateSectionDto other = (CreateSectionDto) obj;
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
		if (expectedPopulation == null) {
			if (other.expectedPopulation != null)
				return false;
		} else if (!expectedPopulation.equals(other.expectedPopulation))
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
		return true;
	}

	@Override
	public String toString() {
		return "CreateSectionDto [courseId=" + courseId + ", sectionName=" + sectionName + ", sectionId=" + sectionId
				+ ", expectedPopulation=" + expectedPopulation + ", requiredFrequency=" + requiredFrequency
				+ ", academicYear=" + academicYear + ", academicTerm=" + academicTerm + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", daysPerWeek=" + daysPerWeek + ", courseLocation=" + courseLocation + "]";
	}
	
	/**
	 * Creates builder to build {@link CreateSectionDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}
	
	/**
	 * Builder to build {@link CreateSectionDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private String courseId;
		private String sectionName;
		private String sectionId;
		private String expectedPopulation;
		private String requiredFrequency;
		private String academicYear;
		private String academicTerm;
		private String startTime;
		private String endTime;
		private String daysPerWeek;
		private String courseLocation;
		

		private Builder()
		{
		}

		public Builder withCourseId(String courseId)
		{
			this.courseId = courseId;
			return this;
		}

		public Builder withSectionName(String sectionName)
		{
			this.sectionName = sectionName;
			return this;
		}

		public Builder withSectionId(String sectionId)
		{
			this.sectionId = sectionId;
			return this;
		}

		public Builder withExpectedPopulation(String expectedPopulation)
		{
			this.expectedPopulation = expectedPopulation;
			return this;
		}

		public Builder withRequiredFrequency(String requiredFrequency)
		{
			this.requiredFrequency = requiredFrequency;
			return this;
		}

		public Builder withAcademicYear(String academicYear)
		{
			this.academicYear = academicYear;
			return this;
		}

		public Builder withAcademicTerm(String academicTerm)
		{
			this.academicTerm = academicTerm;
			return this;
		}

		public Builder withStartTime(String startTime)
		{
			this.startTime = startTime;
			return this;
		}

		public Builder withEndTime(String endTime)
		{
			this.endTime = endTime;
			return this;
		}

		public Builder withDaysPerWeek(String daysPerWeek)
		{
			this.daysPerWeek = daysPerWeek;
			return this;
		}

		public Builder withCourseLocation(String courseLocation)
		{
			this.courseLocation = courseLocation;
			return this;
		}
		
		public CreateSectionDto build()
		{
			return new CreateSectionDto(this);
		}
	}
}
