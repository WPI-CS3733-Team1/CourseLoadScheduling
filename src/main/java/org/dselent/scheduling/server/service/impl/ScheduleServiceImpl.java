package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.dao.UsersSectionsLinksDao;
import org.dselent.scheduling.server.dto.CreateCourseDto;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.dao.CoursesDao;
import org.dselent.scheduling.server.model.UsersSectionsLink;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
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

	@Autowired
	private UsersSectionsLinksDao usersSectionsLinkDao;
	
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
	public List<Course> getCoursesBySection(String userName) throws SQLException {
		List<User> fetchedUser = customDao.getUser(userName);
		System.out.println("[ScheduleService] userName given: "+userName);
		Integer userID = fetchedUser.get(0).getId();
		System.out.println("[ScheduleService] fetched user id: "+userID);
		List<Section> courseSections = customDao.getSchedule(userID);
		List<Course> outputCourses = new ArrayList<Course>(); 

		System.out.println("[ScheduleService] size of sections list: "+courseSections.size());
		
		List<Integer> ids = new ArrayList<Integer>();
		for(int i = 0; i < courseSections.size(); i++) {
			if(!ids.contains(courseSections.get(i).getCourseId())) {
				ids.add(courseSections.get(i).getCourseId());
			}
		}
		//ids populated with no duplicates
		
		for(int i = 0; i < ids.size(); i++) {
			outputCourses.add(coursesDao.findById(ids.get(i)));
		}
		//outputCourses should be populated
		
		System.out.println("[ScheduleService] outputCourses size: "+outputCourses.size());
		
		return outputCourses;
	}
	
	@Override
	public void confirmSchedule(String userName, List<String> removeSectionIdsList, List<String> addSectionIdsList) {
		List<User> fetchedUser = customDao.getUser(userName);
		Integer userID = fetchedUser.get(0).getId();
		
		for(int i = 0; i < removeSectionIdsList.size(); i++) {
			Integer id = Integer.parseInt(removeSectionIdsList.get(i));

			String deleteColumnName = UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.SECTION_ID);

			List<QueryTerm> deleteQueryTermList = new ArrayList<>();

			QueryTerm deleteUseNameTerm = new QueryTerm();
			deleteUseNameTerm.setColumnName(deleteColumnName);
			deleteUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
			deleteUseNameTerm.setValue(id);
			deleteQueryTermList.add(deleteUseNameTerm);
			try {
				usersSectionsLinkDao.delete(deleteQueryTermList);
			} catch(SQLException e) {
				
			}
		}

		for(int i = 0; i < addSectionIdsList.size(); i++) {
			Integer id = Integer.parseInt(addSectionIdsList.get(i));

			UsersSectionsLink usersSectionsLink = new UsersSectionsLink();
			usersSectionsLink.setUserId(userID);
			usersSectionsLink.setSectionId(id);

			List<String> insertColumnNameList = new ArrayList<>();
			List<String> keyHolderColumnNameList = new ArrayList<>();

			insertColumnNameList.add(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.USER_ID));
			insertColumnNameList.add(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.SECTION_ID));

			keyHolderColumnNameList.add(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.ID));
			keyHolderColumnNameList.add(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.CREATED_AT));
			keyHolderColumnNameList.add(UsersSectionsLink.getColumnName(UsersSectionsLink.Columns.UPDATED_AT));
			try {
				usersSectionsLinkDao.insert(usersSectionsLink, insertColumnNameList, keyHolderColumnNameList);
			} catch(SQLException e) {
				
			}
		}
	}

	@Override
	public void createSection(CreateSectionDto dto) throws SQLException {
		Section section = new Section();
		section.setCourseId(dto.getCourseId());
		section.setCRN(dto.getCRN());
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
	@Override
	public Course createCourse(CreateCourseDto dto) throws SQLException {
		Course course = new Course();
		course.setName(dto.getCourseName());
		course.setDeptId(dto.getDeptId()); //Noah
		course.setCourseAbrv(dto.getCourseAbrv()); //Noah
		course.setCourseNum(dto.getCourseNumber());
		course.setNumLectures(dto.getNumberOfLectures());
		course.setNumLabs(dto.getNumberOfLabs());
		course.setNumConferences(dto.getNumberOfConferences());
		
		
		
		List<String> courseInsertColumnNameList = new ArrayList<>();
		List<String> courseKeyHolderColumnNameList = new ArrayList<>();
		courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.NAME));
		
		courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.DEPT_ID));
		courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.COURSE_ABRV));
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.COURSE_NUMBER));
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.NUMBER_OF_LECTURES));
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.NUMBER_OF_LABS));
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.NUMBER_OF_CONFERENCES));
    	
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.ID));
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.CREATED_AT));
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.UPDATED_AT));
	
    	coursesDao.insert(course, courseInsertColumnNameList, courseKeyHolderColumnNameList);
    	
    	
		return course;
	}



}