package com.telus.csm.esdg.database;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating EsdgDatabaseDSConnection objects.
 */
public class EsdgDatabaseDSConnectionFactory implements IEsdgDatabaseConnectionFactory {
	
	/** The Constant DATASOURCE. */
	private static final String DATASOURCE = "com.telus.csm.ess.jdbc.esdg";
	
	/** The data source. */
	private static DataSource dataSource = null;
	
	/* (non-Javadoc)
	 * @see com.telus.csm.esdg.database.IEsdgDatabaseConnectionFactory#getDBConnection()
	 */
	@Override
	public Connection getDBConnection() throws Exception {
		DataSource ds = dataSource;
		if( ds == null ) {
			ds = getDataSource();
		}
		Connection conn = ds.getConnection();
		conn.setAutoCommit(false);
		return conn;
	}
	
	/**
	 * Gets the data source.
	 *
	 * @return the data source
	 * @throws Exception the exception
	 */
	private static synchronized DataSource getDataSource() throws Exception {
		if( dataSource == null ) {
			InitialContext ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup(DATASOURCE);
		}
		return dataSource;
	}

	/* (non-Javadoc)
	 * @see com.telus.csm.esdg.database.IEsdgDatabaseConnectionFactory#getStatistics()
	 */
	@Override
	public String getStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.telus.csm.esdg.database.IEsdgDatabaseConnectionFactory#invalidateDBConnection(java.sql.Connection)
	 */
	@Override
	public void invalidateDBConnection(Connection conn) {
		// TODO Auto-generated method stub
		
	}
}
