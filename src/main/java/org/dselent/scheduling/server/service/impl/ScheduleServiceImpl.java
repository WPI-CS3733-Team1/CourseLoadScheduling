package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.dto.CreateCourseDto;
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
	public List<Section> createSection(CreateSectionDto createSectionDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Course createCourse(CreateCourseDto dto) throws SQLException {
		Course course = new Course();
		course.setName(dto.getCourseName());
		course.setCourseNum(dto.getCourseNumber());
		course.setNumLectures(dto.getNumberOfLectures());
		course.setNumLabs(dto.getNumberOfLabs());
		course.setNumConferences(dto.getNumberOfConferences());
		
		List<String> courseInsertColumnNameList = new ArrayList<>();
		List<String> courseKeyHolderColumnNameList = new ArrayList<>();
		courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.NAME));
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.COURSE_NUMBER));
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.NUMBER_OF_LECTURES));
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.NUMBER_OF_LABS));
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.NUMBER_OF_CONFERENCES));
    	
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.ID));
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.CREATED_AT));
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.UPDATED_AT));
	
		return course;
		
	}


}