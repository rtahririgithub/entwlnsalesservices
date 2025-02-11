package com.telus.csm.esdg.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;

import com.telus.csm.esdg.domain.EsdgPaymentDO;

public class PaymentContextDao extends EsdgDao {
	private static Logger logger = Logger.getLogger(PaymentContextDao.class);
	
	private static final int LOOK_UP_PERIOD = 90;
	private static final String SELECT_SALES_PAYMENT_SEQ_NEXTVAL_STMT = "SELECT SALES_PAYMENT_SEQ.NEXTVAL FROM DUAL";
	private static final String SELECT_PAYMENT_STMT = "SELECT T1.SALES_PAYMENT_ID , T1.SALES_INTRACTN_ID, T1.SALES_CONTEXT_ID, T1.INTRACTN_START_TS, T1.SALES_PAYMENT_REASON_CD, T1.SALES_PAYMENT_STATUS_CD, T1.EXTERNAL_TRANSACTION_REFERENCE,"
			+ " T2.SALES_PAYMENT_DATA "
			+ " FROM SALES_PAYMENT T1 "
			+ " JOIN SALES_PAYMENT_TXN_DATA T2 ON T2.SALES_PAYMENT_ID=T1.SALES_PAYMENT_ID AND T2.INTRACTN_START_TS=T1.INTRACTN_START_TS "
			+ " WHERE T1.SALES_PAYMENT_ID = ? AND T1.INTRACTN_START_TS > ?";
	private static final String SELECT_INTRACTN_START_TS_BY_ID_STMT = "SELECT INTRACTN_START_TS FROM SALES_INTRACTN WHERE "
			+ "SALES_INTRACTN_ID = ? AND INTRACTN_START_TS > ?";
	private static final String INSERT_SALES_PAYMENT_STMT = "INSERT INTO SALESORDERADM.SALES_PAYMENT " 
			+ "(SALES_PAYMENT_ID, SALES_INTRACTN_ID, SALES_CONTEXT_ID, INTRACTN_START_TS, SALES_PAYMENT_REASON_CD, SALES_PAYMENT_STATUS_CD, "
			+ "STATUS_EFFECTIVE_START_TS, EXTERNAL_TRANSACTION_REFERENCE, CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ "values (?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, SYSDATE, ?, SYSDATE)";
	private static final String INSERT_SALES_PAYMENT_TXN_DATA_STMT = "INSERT INTO SALESORDERADM.SALES_PAYMENT_TXN_DATA " 
			+ "(SALES_PAYMENT_TXN_DATA_ID, SALES_PAYMENT_ID, INTRACTN_START_TS, SALES_PAYMENT_DATA, CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ " values (SALES_ORDER_TXN_DATA_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?, SYSDATE)";
	private static final String UPDATE_SALES_PAYMENT_STMT = "UPDATE SALESORDERADM.SALES_PAYMENT SET "
			+ "SALES_PAYMENT_REASON_CD = ?, SALES_PAYMENT_STATUS_CD = ?, STATUS_EFFECTIVE_START_TS = ?, LAST_UPDT_USER_ID = ?, LAST_UPDT_TS = SYSDATE "
			+ "WHERE SALES_PAYMENT_ID = ? AND INTRACTN_START_TS = ?";
	private static final String UPDATE_SALES_PAYMENT_TXN_DATA_STMT = "UPDATE SALESORDERADM.SALES_PAYMENT_TXN_DATA SET "
			+ "SALES_PAYMENT_DATA = ?, LAST_UPDT_USER_ID = ?, LAST_UPDT_TS = SYSDATE "
			+ "WHERE SALES_PAYMENT_ID = ? AND INTRACTN_START_TS = ?";
	private static final String SELECT_SALES_PAYMENT_IDS_STMT = "select SALES_PAYMENT_ID from SALESORDERADM.SALES_PAYMENT "
			+ "where SALES_INTRACTN_ID = ? and INTRACTN_START_TS > ?";

	public PaymentContextDao(Connection conn, String dbUser) {
		super(conn, dbUser);
	}

	public void savePaymentContext(EsdgPaymentDO esdgPaymentDO) throws Exception {
		EsdgPaymentDO dbEsdgPaymentDO = selectPaymentById(esdgPaymentDO.getSalesPaymentId());
		if(dbEsdgPaymentDO == null) {
			insertPayment(esdgPaymentDO);
		} else {
			updatePayment(esdgPaymentDO);
		}
	}

	private void insertPayment(EsdgPaymentDO esdgPaymentDO) throws Exception {
		findAndSetInteractionTimestamp(esdgPaymentDO);
		insertToSalePaymentTable(esdgPaymentDO);
		insertToSalePaymentTxnDataTable(esdgPaymentDO);
	}

	private void insertToSalePaymentTxnDataTable(EsdgPaymentDO esdgPaymentDO) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();
		boolean success = false;
		try {
			ps = getConnection().prepareStatement(INSERT_SALES_PAYMENT_TXN_DATA_STMT);
			int idx = 1;
			  //SALES_PAYMENT_TXN_DATA_ID
			ps.setString(idx++, esdgPaymentDO.getSalesPaymentId());//SALES_PAYMENT_ID
			ps.setTimestamp(idx++, new Timestamp(esdgPaymentDO.getSalesInteractionStartTimeInMills()));//INTRACTN_START_TS
			ps.setBytes( idx++, SerializationUtils.serialize(esdgPaymentDO.getJsonPaymentTXNData()));//SALES_PAYMENT_DATA
			ps.setString(idx++, getDbUser());//CREATE_USER_ID
			  //CREATE_TS
			ps.setString(idx++, getDbUser());//LAST_UPDT_USER_ID
			  //LAST_UPDT_TS		
			success = ps.executeUpdate() == 1;
		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if(success) {
				if (logger.isDebugEnabled()) {
					logger.debug("--ESDG-- Insert to SALES_PAYMENT_TXN_DATA is a success. SalesPaymentId = " + esdgPaymentDO.getSalesPaymentId() + "; elapsedTime=" + elapsedTime);
				}
			} else {
				logger.warn("--ESDG-- Insert to SALES_PAYMENT_TXN_DATA has failed. SalesPaymentId = " + esdgPaymentDO.getSalesPaymentId() + "; elapsedTime=" + elapsedTime);
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
	}

	private void insertToSalePaymentTable(EsdgPaymentDO esdgPaymentDO) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();
		boolean success = false;
		try {
			ps = getConnection().prepareStatement(INSERT_SALES_PAYMENT_STMT);
			int idx = 1;
			ps.setString(idx++, esdgPaymentDO.getSalesPaymentId());//SALES_PAYMENT_ID
			ps.setString(idx++, esdgPaymentDO.getSalesInteractionId());//SALES_INTRACTN_ID
			ps.setString(idx++, esdgPaymentDO.getSalesContextId());//SALES_CONTEXT_ID
			ps.setTimestamp(idx++, new Timestamp(esdgPaymentDO.getSalesInteractionStartTimeInMills()));//INTRACTN_START_TS
			ps.setString(idx++, esdgPaymentDO.getSalesPaymentReasonCd());//SALES_PAYMENT_REASON_CD
			ps.setString(idx++, esdgPaymentDO.getSalesPaymentStatusCd());//SALES_PAYMENT_STATUS_CD
			ps.setTimestamp(idx++, new Timestamp(new Date().getTime()));//STATUS_EFFECTIVE_START_TS
			ps.setString(idx++, esdgPaymentDO.getExternalKey());//EXTERNAL_TRANSACTION_REFERENCE
			ps.setString(idx++, getDbUser());//CREATE_USER_ID
			//CREATE_TS
			ps.setString(idx++, getDbUser());//LAST_UPDT_USER_ID
			//LAST_UPDT_TS
			success = ps.executeUpdate() == 1;
		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if(success) {
				if (logger.isDebugEnabled()) {
					logger.debug("--ESDG-- Insert to SALES_PAYMENT is a success. SalesPaymentId = " + esdgPaymentDO.getSalesPaymentId() + "; elapsedTime=" + elapsedTime);
				}
			} else {
				logger.warn("--ESDG-- Insert to SALES_PAYMENT has failed. SalesPaymentId = " + esdgPaymentDO.getSalesPaymentId() + "; elapsedTime=" + elapsedTime);
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
		
	}

	private void findAndSetInteractionTimestamp(EsdgPaymentDO esdgPaymentDO) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();

		try {
			ps = getConnection().prepareStatement(SELECT_INTRACTN_START_TS_BY_ID_STMT);
			int idx = 1;
			ps.setString(idx++, esdgPaymentDO.getSalesInteractionId());
			ps.setTimestamp(idx, EsdgDatabaseAdapter_1.getBackTime(LOOK_UP_PERIOD));
			rs = ps.executeQuery();

			if (rs.next()) {
				Timestamp salesInteractionTimestamp = rs.getTimestamp(1);
				esdgPaymentDO.setSalesInteractionStartTimeInMills(salesInteractionTimestamp.getTime());
			} else {
				String errMsg = "--ESDG-- Cannot find salesInteractionTimestamp for SalesInteractionId = " + esdgPaymentDO.getSalesInteractionId();
				throw new Exception(errMsg);
			}

		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query salesInteractionTimestamp by id : SalesInteractionId = " + esdgPaymentDO.getSalesInteractionId() + "; elapsedTime=" + elapsedTime);
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
	}

	private void updatePayment(EsdgPaymentDO esdgPaymentDO) throws Exception {
		findAndSetInteractionTimestamp(esdgPaymentDO);
		updateSalePaymentTable(esdgPaymentDO);
		updateSalePaymentTxnDataTable(esdgPaymentDO);
	}

	private void updateSalePaymentTxnDataTable(EsdgPaymentDO esdgPaymentDO) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();
		boolean success = false;
		try {
			ps = getConnection().prepareStatement(UPDATE_SALES_PAYMENT_TXN_DATA_STMT);
			int idx = 1;
			
			ps.setBytes( idx++, SerializationUtils.serialize(esdgPaymentDO.getJsonPaymentTXNData()));//SALES_PAYMENT_DATA
			ps.setString(idx++, getDbUser());//LAST_UPDT_USER_ID
			//LAST_UPDT_TS = SYSDATE "
			ps.setString(idx++, esdgPaymentDO.getSalesPaymentId());//SALES_PAYMENT_ID
			ps.setTimestamp(idx++, new Timestamp(esdgPaymentDO.getSalesInteractionStartTimeInMills()));//INTRACTN_START_TS;
			success = ps.executeUpdate() == 1;
		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if(success) {
				if (logger.isDebugEnabled()) {
					logger.debug("--ESDG-- Update to SALES_PAYMENT_TXN_DATA is a success. SalesPaymentId = " + esdgPaymentDO.getSalesPaymentId() + "; elapsedTime=" + elapsedTime);
				}
			} else {
				logger.warn("--ESDG-- Update to SALES_PAYMENT_TXN_DATA has failed. SalesPaymentId = " + esdgPaymentDO.getSalesPaymentId() + "; elapsedTime=" + elapsedTime);
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
	}

	private void updateSalePaymentTable(EsdgPaymentDO esdgPaymentDO) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();
		boolean success = false;
		try {
			ps = getConnection().prepareStatement(UPDATE_SALES_PAYMENT_STMT);
			int idx = 1;
			ps.setString(idx++, esdgPaymentDO.getSalesPaymentReasonCd());//SALES_PAYMENT_REASON_CD
			ps.setString(idx++, esdgPaymentDO.getSalesPaymentStatusCd());//SALES_PAYMENT_STATUS_CD
			ps.setTimestamp(idx++, new Timestamp(new Date().getTime()));//STATUS_EFFECTIVE_START_TS
			ps.setString(idx++, getDbUser());//LAST_UPDT_USER_ID
			//LAST_UPDT_TS = SYSDATE "
			ps.setString(idx++, esdgPaymentDO.getSalesPaymentId());//SALES_PAYMENT_ID
			ps.setTimestamp(idx++, new Timestamp(esdgPaymentDO.getSalesInteractionStartTimeInMills()));//INTRACTN_START_TS

			success = ps.executeUpdate() == 1;
		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
			}
			if(success) {
				if (logger.isDebugEnabled()) {
					logger.debug("--ESDG-- Update to SALES_PAYMENT is a success. SalesPaymentId = " + esdgPaymentDO.getSalesPaymentId() + "; elapsedTime=" + elapsedTime);
				}
			} else {
				logger.warn("--ESDG-- Update to SALES_PAYMENT has failed. SalesPaymentId = " + esdgPaymentDO.getSalesPaymentId() + "; elapsedTime=" + elapsedTime);
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
	}

	public EsdgPaymentDO selectPaymentById(String salesPaymentId) throws SQLException {
		EsdgPaymentDO result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();

		try {
			ps = getConnection().prepareStatement(SELECT_PAYMENT_STMT);
			int idx = 1;
			ps.setString(idx++, salesPaymentId);
			ps.setTimestamp(idx, EsdgDatabaseAdapter_1.getBackTime(LOOK_UP_PERIOD));
			rs = ps.executeQuery();

			if (rs.next()) {
				result = mapPayment(rs);
			} else {
				logger.warn("--ESDG-- payment is not found in database. salesPaymentId = " + salesPaymentId);
			}

		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query payment by id : salesPaymentId=" + salesPaymentId + "; elapsedTime=" + elapsedTime);
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
		return result;
	}

	private EsdgPaymentDO mapPayment(ResultSet rs) throws SQLException {
		EsdgPaymentDO result = new EsdgPaymentDO();
		result.setSalesPaymentId(rs.getString("SALES_PAYMENT_ID"));
		result.setSalesContextId(rs.getString("SALES_CONTEXT_ID"));
		result.setSalesInteractionId(rs.getString("SALES_INTRACTN_ID"));
		result.setSalesInteractionStartTimeInMills(rs.getTimestamp("INTRACTN_START_TS").getTime());
		result.setSalesPaymentReasonCd(rs.getString("SALES_PAYMENT_REASON_CD"));
		result.setSalesPaymentStatusCd(rs.getString("SALES_PAYMENT_STATUS_CD"));
		result.setExternalKey(rs.getString("EXTERNAL_TRANSACTION_REFERENCE"));
		byte[] bytes = rs.getBytes("SALES_PAYMENT_DATA");
		if (bytes != null && bytes.length > 0) {
			result.setJsonPaymentTXNData((String) SerializationUtils.deserialize(bytes));
		}
		return result;
	}

	public String createNextPaymentId() throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();
        String salesPaymentId = null;

		try {
			ps = getConnection().prepareStatement(SELECT_SALES_PAYMENT_SEQ_NEXTVAL_STMT);
			rs = ps.executeQuery();
            if ( !rs.next() ) {
            	logger.error( "--ESDG-- Failed to get next sequence for SALES_PAYMENT in database.");
            } else {
            	salesPaymentId = rs.getString(1);
            }

		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query payment by id : salesPaymentId=" + salesPaymentId + "; elapsedTime=" + elapsedTime);
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
		return salesPaymentId;
	}

	public List<String> findPaymentIdsByInteractionId(String interactionId) throws SQLException {
		List<String> result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();

		try {
			ps = getConnection().prepareStatement(SELECT_SALES_PAYMENT_IDS_STMT);
			int idx = 1;
			ps.setString(idx++, interactionId);
			ps.setTimestamp(idx, EsdgDatabaseAdapter_1.getBackTime(LOOK_UP_PERIOD));
			rs = ps.executeQuery();

			while(rs.next()) {
				if(result == null) {
					result = new ArrayList<String>();
				}
				result.add(rs.getString("SALES_PAYMENT_ID"));
			} 
			if(result == null) {
				logger.warn("--ESDG-- payments are not found in database. interactionId = " + interactionId);
			}

		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query payment ids by interactionId : interactionId=" + interactionId + "; elapsedTime=" + elapsedTime);
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
		return result;
	}

}
