package com.telus.csm.esdg.database;

import java.sql.Connection;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating IEsdgDatabaseConnection objects.
 */
public interface IEsdgDatabaseConnectionFactory {
	
	/** The Constant ConnectionType_DataSource. */
	static final String ConnectionType_DataSource = "DS";
	
	/** The Constant ConnectionType_DriverManager. */
	static final String ConnectionType_DriverManager = "DM";
	
	/**
	 * Gets the DB connection.
	 *
	 * @return the DB connection
	 * @throws Exception the exception
	 */
	public abstract Connection getDBConnection() throws Exception;
	
	/**
	 * Invalidate db connection.
	 *
	 * @param conn the conn
	 */
	public void invalidateDBConnection(Connection conn);
	
	/**
	 * Gets the statistics.
	 *
	 * @return the statistics
	 */
	public abstract String getStatistics();
	
	/**
	 * The Class Factory.
	 */
	class Factory {
		
		/** The connection type. */
		public static String connectionType = ConnectionType_DataSource;
		
		/**
		 * Sets the connection type.
		 *
		 * @param type the new connection type
		 */
		public static void setConnectionType(String type) {
			connectionType = type;
		}
		
		/**
		 * New instance.
		 *
		 * @return the i esdg database connection factory
		 */
		public static IEsdgDatabaseConnectionFactory newInstance() {
			if( ConnectionType_DriverManager.equalsIgnoreCase(connectionType) ) {
				return new EsdgDatabaseDMConnectionFactory();
			} else {
				return new EsdgDatabaseDSConnectionFactory();
			}
		}
	}
}
