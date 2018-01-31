package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Model;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.stereotype.Repository;

/**
 * Base Dao interface.  Dao interfaces specify the behavior on how to get data.
 * Dao implementations specify exactly how the data is retrieved.
 * 
 * @author dselent
 *
 * @param <M> The model for the Dao
 */
@Repository
public interface Dao<M extends Model>
{
	/*
	 * Basic operations on data
	 * Only meant for basic SQL statements
	 * More complicated statements should use custom scripts
	 */
	
	public int insert(M model, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException;
	
	public List<M> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException;
	public M findById(int id) throws SQLException;
	
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException;
	public int delete(List<QueryTerm> queryTermList) throws SQLException;
	
	public void validateColumnNames(List<String> columnNameList);
}
