package org.dselent.scheduling.server.dao.impl;

import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.extractor.SectionsExtractor;
import org.dselent.scheduling.server.extractor.UsersExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CustomDaoImpl implements CustomDao
{
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// can make custom models and extractors as needed or reuse existing ones if applicable
	
	@Override
	public List<User> getAllUsersWithRole(int roleId)
	{
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.USERS_WITH_ROLE_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("roleId", roleId);
	    List<User> usersWithRoleList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return usersWithRoleList;
	}
	
	public List<Section> getAvailableSectionsInDept(int deptID) {
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.FETCH_SECTIONS_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("deptID", deptID);
		List<Section> availableSections = namedParameterJdbcTemplate.query(queryTemplate, extractor);
		
		return availableSections;
	}
}
