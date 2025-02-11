package com.telus.csm.esdg.database;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

/**
 * A factory for creating EsdgDatabaseDMConnection objects.
 */
public class EsdgDatabaseDMConnectionFactory implements IEsdgDatabaseConnectionFactory {
	/** The log. */
	protected static Logger log = Logger.getLogger(EsdgDatabaseDMConnectionFactory.class);
	
	/** The jdbc driver class name. */
	private String jdbcDriverClassName;
	
	/** The jdbc url. */
	private String jdbcUrl;
	
	/** The jdbc user. */
	private String jdbcUser;
	
	/** The jdbc password. */
	private String jdbcPassword;
	
	public EsdgDatabaseDMConnectionFactory() {		
	}
	
	public EsdgDatabaseDMConnectionFactory(String jdbcDriverClassName, String jdbcUrl, String jdbcUser, String jdbcPassword) {
		this.jdbcDriverClassName = jdbcDriverClassName;
		this.jdbcUrl = jdbcUrl;
		this.jdbcUser = jdbcUser;
		this.jdbcPassword = jdbcPassword;
	}
	
	@Override
	public Connection getDBConnection() throws Exception {
		Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
		conn.setAutoCommit(false);
		return conn;
	}

	@Override
	public String getStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invalidateDBConnection(Connection conn) {
		// TODO Auto-generated method stub
		
	}

}
