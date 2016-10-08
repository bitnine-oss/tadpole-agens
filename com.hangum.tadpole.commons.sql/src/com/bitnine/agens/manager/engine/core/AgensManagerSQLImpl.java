package com.bitnine.agens.manager.engine.core;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bitnine.agens.manager.engine.core.dao.domain.AlertMessage;
import com.bitnine.agens.manager.engine.core.dao.domain.Instance;
import com.hangum.tadpole.commons.exception.TadpoleSQLManagerException;
import com.hangum.tadpole.engine.manager.TadpoleSQLManager;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * agens amanger sql
 * 
 * @author hangum
 *
 */
public class AgensManagerSQLImpl {
	private static final Logger logger = Logger.getLogger(AgensManagerSQLImpl.class);

	/**
	 * get instance list 
	 * 
	 * @param userDB
	 */
	public static List<Instance> getInstanceInfo(UserDBDAO userDB) throws TadpoleSQLManagerException, SQLException {
		SqlMapClient sqlClient = TadpoleSQLManager.getInstance(userDB);
		return sqlClient.queryForList("listInstance");
	}
	
	/**
	 * get snapshot
	 * 
	 * @param userDB
	 */
	public static Integer getSnapshotInfo(UserDBDAO userDB, Instance instance) throws TadpoleSQLManagerException, SQLException {
		SqlMapClient sqlClient = TadpoleSQLManager.getInstance(userDB);
		return (Integer)sqlClient.queryForObject("maxSnapid", instance.getInstid());
	}
	
	/**
	 * get alert message
	 * 
	 * @param userDB
	 * @param instance
	 * @return
	 * @throws TadpoleSQLManagerException
	 * @throws SQLException
	 */
	public static List<AlertMessage> getAlertMessageInfo(UserDBDAO userDB, Instance instance) throws TadpoleSQLManagerException, SQLException {
		SqlMapClient sqlClient = TadpoleSQLManager.getInstance(userDB);
		return sqlClient.queryForList("listAlertMessage", instance);
	}
	
	/**
	 * get SQLMap Query
	 * 
	 * @param userDB
	 * @param queryName
	 * @param snapid
	 * @return
	 * @throws TadpoleSQLManagerException
	 * @throws SQLException
	 */
	public static List<Map> getSQLMapQueryInfo(UserDBDAO userDB, String queryName, int snapid) throws TadpoleSQLManagerException, SQLException {
		SqlMapClient sqlClient = TadpoleSQLManager.getInstance(userDB);
		return sqlClient.queryForList(queryName, snapid);
	}

	/**
	 * get SQLMap query
	 * 
	 * @param userDB
	 * @param queryName
	 * @param mapParameter
	 * @return
	 */
	public static List<Map> getSQLMapQueryInfo(UserDBDAO userDB, String queryName, Map<String, Integer> mapParameter) throws TadpoleSQLManagerException, SQLException {
		SqlMapClient sqlClient = TadpoleSQLManager.getInstance(userDB);
		return sqlClient.queryForList(queryName, mapParameter);
	}
}
