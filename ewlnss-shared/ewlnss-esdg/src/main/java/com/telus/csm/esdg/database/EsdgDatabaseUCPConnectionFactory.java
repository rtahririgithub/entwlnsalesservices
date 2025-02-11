package com.telus.csm.esdg.database;

import java.sql.Connection;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import oracle.ucp.jdbc.JDBCConnectionPoolStatistics;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import oracle.ucp.jdbc.ValidConnection;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating EsdgDatabaseUCPConnection objects.
 */
public final class EsdgDatabaseUCPConnectionFactory implements IEsdgDatabaseConnectionFactory {
	
	/** The log. */
	protected static Logger log = Logger.getLogger(EsdgDatabaseUCPConnectionFactory.class);
	
	/** The jdbc driver class name. */
	private String jdbcDriverClassName;
	
	/** The jdbc url. */
	private String jdbcUrl;
	
	/** The jdbc user. */
	private String jdbcUser;
	
	/** The jdbc password. */
	private String jdbcPassword;
	
	/** The initial pool size. */
	private String initialPoolSize;
	
	/** The pds. */
	private static PoolDataSource pds = null;
	
	/**
	 * Instantiates a new esdg database ucp connection factory.
	 */
	public EsdgDatabaseUCPConnectionFactory() {
		
	}
	
	/**
	 * Instantiates a new esdg database ucp connection factory.
	 *
	 * @param jdbcDriverClassName the jdbc driver class name
	 * @param jdbcUrl the jdbc url
	 * @param jdbcUser the jdbc user
	 * @param jdbcPassword the jdbc password
	 * @param initialPoolSize the initial pool size
	 */
	public EsdgDatabaseUCPConnectionFactory(String jdbcDriverClassName, String jdbcUrl, String jdbcUser, String jdbcPassword, String initialPoolSize) {
		this.jdbcDriverClassName = jdbcDriverClassName;
		this.jdbcUrl = jdbcUrl;
		this.jdbcUser = jdbcUser;
		this.jdbcPassword = jdbcPassword;
		this.initialPoolSize = initialPoolSize;
	}
	
	/**
	 * Gets the pool data source.
	 *
	 * @return the pool data source
	 * @throws Exception the exception
	 */
	private synchronized PoolDataSource getPoolDataSource() throws Exception {
		if ( pds == null ) {
			pds = PoolDataSourceFactory.getPoolDataSource();

			// set the connection properties on the data source.
			pds.setConnectionFactoryClassName(jdbcDriverClassName);
			pds.setURL(jdbcUrl);
			pds.setUser(jdbcUser);
			pds.setPassword(jdbcPassword);

			// Override any pool properties.
			if( !StringUtils.isEmpty(initialPoolSize) ) {
				try {
					pds.setInitialPoolSize(Integer.valueOf(initialPoolSize));
				} catch (Exception ex) {
					log.error("--ESDG-- exception when setting connection pool size = " + initialPoolSize, ex);
				}
			}			
		}
		return pds;
	}
	
	/** The last stats report time in mills. */
	private static long lastStatsReportTimeInMills = 0;
	
	/* (non-Javadoc)
	 * @see com.telus.csm.esdg.database.IEsdgDatabaseConnectionFactory#getDBConnection()
	 */
	@Override
	public Connection getDBConnection() throws Exception {
		Connection conn = getPoolDataSource().getConnection();
		conn.setAutoCommit(false);
		
		long now = Calendar.getInstance().getTimeInMillis();
		if( now - lastStatsReportTimeInMills > 30000) {
			log.info(getStatistics());
			lastStatsReportTimeInMills = now;
		}
		return conn;
	}

	/* (non-Javadoc)
	 * @see com.telus.csm.esdg.database.IEsdgDatabaseConnectionFactory#invalidateDBConnection(java.sql.Connection)
	 */
	public void invalidateDBConnection(Connection conn) {
		if( conn == null ) return;
		try {
			ValidConnection thisConn = (ValidConnection)conn;
			if( !thisConn.isValid() ) {
				thisConn.setInvalid();
        	}
		} catch (Exception ex) {
			log.error("--ESDG-- invalidating connection failed. Alert EM to rolling recycle ESDG coherence.", ex);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.telus.csm.esdg.database.IEsdgDatabaseConnectionFactory#getStatistics()
	 */
	public String getStatistics() {
		String statsString = null;
		try {
			JDBCConnectionPoolStatistics stats = pds.getStatistics();
			statsString = new StringBuffer()
			.append("--ESDG-- UCP stats = total count = ").append(stats.getTotalConnectionsCount())
			.append(" : available count = ").append(stats.getAvailableConnectionsCount())
			.append(" : borrowed count = ").append(stats.getBorrowedConnectionsCount())
			.append(" : peak count = ").append(stats.getPeakConnectionsCount())
			.append(" : remaining pool capacity count = ").append(stats.getRemainingPoolCapacityCount())
			.toString();			
		} catch (Exception ex) {			
		}
		return statsString;
	}
}
