package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.dao.CoursesDao;
import org.dselent.scheduling.server.model.CompleteSection;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ScheduleServiceImpl implements ScheduleService
{
	@Autowired
	private CustomDao customDao;

	@Autowired
	private CoursesDao coursesDao;
	
	@Autowired
	private SectionsDao sectionsDao;
	
    public ScheduleServiceImpl()
    {
    	//
    }

	@Override
	public List<Section> getSchedule(String userName, String academicTerm) {
		// TODO Auto-generated method stub
		
		List<User> fetchedUser = customDao.getUser(userName);
		Integer userID = fetchedUser.get(0).getId();
		List<Section> outputSections = customDao.getSchedule(userID);
		
		return outputSections;
	}

	@Override
	public List<Course> getCoursesBySection(String userName) {
		List<User> fetchedUser = customDao.getUser(userName);
		Integer userID = fetchedUser.get(0).getId();
		List<Section> courseSections = customDao.getSchedule(userID);
		List<Course> outputCourses = new ArrayList<Course>(); 

		for(int i = 0; i < courseSections.size(); i++) {
			boolean courseNotListed = true;
			for(int j = 0; j < outputCourses.size(); j++) {
				if(courseSections.get(i).getCourseId() == outputCourses.get(j).getId()) {
					courseNotListed = false;
					break;
				}
			}

			if(courseNotListed) {
				try {
					outputCourses.add(coursesDao.findById(courseSections.get(i).getCourseId()));
				} catch(SQLException e) {
					
				}
			}
		}

		return outputCourses;
	}

	@Override
	public void createSection(CreateSectionDto dto) throws SQLException {
		Section section = new Section();
		section.setCourseId(dto.getCourseId());
		section.setSectionName(dto.getSectionName());
		section.setSectionId(dto.getSectionId());
		section.setExpectedPop(dto.getExpectedPopulation());		
		section.setRequiredFreq(dto.getRequiredFrequency());
		section.setAcademicYear(dto.getAcademicYear());
		section.setAcademicTerm(dto.getAcademicTerm());
		section.setStartTime(dto.getStartTime());
		section.setEndTime(dto.getEndTime());
		section.setDaysPerWeek(dto.getDaysPerWeek());
		section.setCourseLocation(dto.getCourseLocation());
		
		List<String> sectionInsertColumnNameList = new ArrayList<>();
    	List<String> sectionKeyHolderColumnNameList = new ArrayList<>();
    	
    	// Add columns where data will be added by service
    	sectionInsertColumnNameList.add(Section.getColumnName(Section.Columns.COURSE_ID));
    	sectionInsertColumnNameList.add(Section.getColumnName(Section.Columns.CRN));
    	sectionInsertColumnNameList.add(Section.getColumnName(Section.Columns.SECTION_NAME));
    	sectionInsertColumnNameList.add(Section.getColumnName(Section.Columns.SECTION_ID));
    	sectionInsertColumnNameList.add(Section.getColumnName(Section.Columns.EXPECTED_POPULATION));
    	sectionInsertColumnNameList.add(Section.getColumnName(Section.Columns.REQUIRED_FREQUENCY));
    	sectionInsertColumnNameList.add(Section.getColumnName(Section.Columns.ACADEMIC_YEAR));
    	sectionInsertColumnNameList.add(Section.getColumnName(Section.Columns.ACADEMIC_TERM));
    	sectionInsertColumnNameList.add(Section.getColumnName(Section.Columns.START_TIME));
    	sectionInsertColumnNameList.add(Section.getColumnName(Section.Columns.END_TIME));
    	sectionInsertColumnNameList.add(Section.getColumnName(Section.Columns.DAYS_PER_WEEK));
    	sectionInsertColumnNameList.add(Section.getColumnName(Section.Columns.COURSE_LOCATION));
    	
    	// Add columns where data will not be added by service (and maybe returned)
    	sectionKeyHolderColumnNameList.add(Section.getColumnName(Section.Columns.ID));
    	sectionKeyHolderColumnNameList.add(Section.getColumnName(Section.Columns.CREATED_AT));
    	sectionKeyHolderColumnNameList.add(Section.getColumnName(Section.Columns.UPDATED_AT));
		
    	// Add using Dao
    	sectionsDao.insert(section, sectionInsertColumnNameList, sectionKeyHolderColumnNameList);
		
	}

}