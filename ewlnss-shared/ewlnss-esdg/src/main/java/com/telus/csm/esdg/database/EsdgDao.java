package com.telus.csm.esdg.database;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

public class EsdgDao {

	private Connection connection;
	private String dbUser;

	public EsdgDao(  Connection conn, String dbUser ) {
		this.connection = conn;
		this.dbUser = dbUser;	}

	public Connection getConnection() {
		return connection;
	}

	public String getDbUser() {
		return dbUser;
	}

//	public void setConnection(Connection connection) {
//		this.connection = connection;
//	}
//
//	public void setDbUser(String dbUser) {
//		this.dbUser = dbUser;
//	}

	public static String getExnteralId(String externalId, int maxLength) {
		
	    return StringUtils.isEmpty( externalId) ? "NA" : externalId.substring(0, Math.min(maxLength, externalId.length()));
	}

}