package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.model.CompleteSection;
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


	}