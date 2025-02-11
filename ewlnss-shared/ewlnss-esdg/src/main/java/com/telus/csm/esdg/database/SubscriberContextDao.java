package com.telus.csm.esdg.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;

import com.telus.csm.esdg.domain.EsdgSubscriberContextDO;

/**
 * DAO class for persisting / retrieving InteractionDO from / to tables:
 * 
 * SALES_SUB_CONTEXT SALES_SUB_CONTEXT_TXN_DATA
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SubscriberContextDao extends EsdgDao {

	private static Logger logger = Logger.getLogger(SubscriberContextDao.class);
	private static Calendar GMT_CAL = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	private static final String SELECT_SUB_CONTEXT_ID_STMT = "SELECT SALES_SUB_CONTEXT_ID from SALES_SUB_CONTEXT WHERE "
			+ "SALES_CONTEXT_ID = ? " + "AND  CONTEXT_TYPE_CD = ? ";

	private static final String SELECT_SUB_CONTEXT_STMT = "SELECT T1.SALES_SUB_CONTEXT_ID, T1.INTRACTN_START_TS, T1.SALES_CONTEXT_ID, T1.BILLING_ACCOUNT_NUM, "
			+ " T1.SUBSCRIBER_PHONE_NUM, T1.CONTEXT_TYPE_CD, T1.CONTEXT_STATUS_CD, "
			+ " T2.SALES_SUB_CONTEXT_TXN_DATA_ID, T2.CONTEXT_TXN_DATA " + " FROM SALES_SUB_CONTEXT T1 "
			+ " LEFT OUTER JOIN SALES_SUB_CONTEXT_TXN_DATA T2 ON T1.SALES_SUB_CONTEXT_ID=T2.SALES_SUB_CONTEXT_ID AND T1.INTRACTN_START_TS=T2.INTRACTN_START_TS "
			+ " AND T2.CREATE_TS = (select max( create_ts) from SALES_SUB_CONTEXT_TXN_DATA T3 where  T1.SALES_SUB_CONTEXT_ID=T3.SALES_SUB_CONTEXT_ID AND T1.INTRACTN_START_TS=T3.INTRACTN_START_TS) "
			+ " WHERE " + " (T1.SALES_CONTEXT_ID,CONTEXT_TYPE_CD)  ";

	private static final String SELECT_SUB_CONTEXT_SEQ_NEXTVAL_STMT = "SELECT SALES_SUB_CONTEXT_SEQ.NEXTVAL FROM DUAL";

	private static final String INSERT_SUBSCRIBER_CONTEXT_STMT = "INSERT INTO SALES_SUB_CONTEXT ( "
			+ " SALES_SUB_CONTEXT_ID, SALES_CONTEXT_ID, INTRACTN_START_TS, BILLING_ACCOUNT_NUM, SUBSCRIBER_PHONE_NUM, "
			+ " CONTEXT_TYPE_CD, CONTEXT_STATUS_CD, " + " CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ " VALUES (?,?,?,?,?,?,?,?, SYSDATE,?,SYSDATE) ";

	private static final String INSERT_SUB_CONTEXT_RAW_DATA_STMT = "INSERT INTO SALES_SUB_CONTEXT_TXN_DATA ("
			+ "SALES_SUB_CONTEXT_TXN_DATA_ID, SALES_SUB_CONTEXT_ID, " + "INTRACTN_START_TS, CONTEXT_TXN_DATA, "
			+ "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS ) "
			+ "VALUES (SALES_SUB_CONTEXT_TXN_DATA_SEQ.NEXTVAL,?,?,?,?,SYSDATE,?,SYSDATE)";

	private static final String SEARCH_SUB_CONTEXT_KEYS_BY_CRITERIA_STMT = "SELECT SALES_CONTEXT_ID, CONTEXT_TYPE_CD FROM SALES_SUB_CONTEXT WHERE BILLING_ACCOUNT_NUM=? ";
	private static final String SEARCH_SUB_CONTEXT_KEYS_BY_SALESID_CRITERIA_STMT = "SELECT SALES_CONTEXT_ID, CONTEXT_TYPE_CD FROM SALES_SUB_CONTEXT WHERE SALES_CONTEXT_ID=? ";

	private static final String UPDATE_SUB_CONTEXT_STMT = "UPDATE SALES_SUB_CONTEXT SET  " + "CONTEXT_STATUS_CD=?,  "
			+ "LAST_UPDT_USER_ID=?, LAST_UPDT_TS=SYSDATE " + "WHERE SALES_SUB_CONTEXT_ID = ?  ";

	public SubscriberContextDao(Connection conn, String dbUser) {
		super(conn, dbUser);
	}

	/**
	 * Get existing SubscriberContextId for given criteria, if cannot find, grab a
	 * new sequence number from database SubscriberContextId
	 * 
	 * @param salesOrderId
	 * @param ban
	 * @param phoneNumber
	 * @param type
	 * @return
	 */
	public String getSubscriberContextId(String salesOrderId, String ban, String phoneNumber, String type)
			throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = getConnection().prepareStatement(SELECT_SUB_CONTEXT_ID_STMT);

			int idx = 1;
			ps.setString(idx++, salesOrderId);
			ps.setString(idx++, ban);
			ps.setString(idx++, phoneNumber);
			ps.setString(idx++, type);

			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query exisiting SUB_CONTEXT_ID, criteria "
						+ Arrays.asList(salesOrderId, ban, phoneNumber, type) + " SQL: " + SELECT_SUB_CONTEXT_ID_STMT);
			}

			rs = ps.executeQuery();

			if (rs.next()) {
				// return existing PK of subscriber context (salesId, ban, phoneNumber, type)
				return rs.getString(1);

			} else {

				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
				}

				logger.info("--ESDG-- exisiting SALES_SUB_CONTEXT record not found for " + salesOrderId + "/" + ban
						+ "/" + phoneNumber + "/" + type + ", generating new id.");

				ps = getConnection().prepareStatement(SELECT_SUB_CONTEXT_SEQ_NEXTVAL_STMT);
				rs = ps.executeQuery();

				if (!rs.next()) {
					String errorMsg = "Failed to get next sequence for interaction in database. externalRefId = "
							+ salesOrderId;
					logger.error("--ESDG-- " + errorMsg);
					throw new Exception(errorMsg);

				} else {
					String newId = rs.getString(1);
					if (logger.isDebugEnabled()) {
						logger.debug("--ESDG-- SALES_SUB_CONTEXT_ID is generated for salesOrder=" + salesOrderId
								+ ", new id= " + newId);
					}
					return newId;
				}
			}

		} finally {
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

	public void saveSubscriberContext(EsdgSubscriberContextDO subscriberContext) throws Exception {

		EsdgSubscriberContextDO doFromDB = selectSubscirberContextByKey(subscriberContext.getCacheKey());

		if (doFromDB == null) {

			logger.info("--ESDG-- Cannot find SALES_SUB_CONTEXT_TXN_DATA for cachekey = "
					+ subscriberContext.getCacheKey() + ", do insert.");

			subscriberContext.setSalesInteractionStartTimeInMills(Calendar.getInstance().getTimeInMillis());
			insertSubscriberContext(subscriberContext);

			if (subscriberContext.getJsonSalesTXNData() != null) {
				insertTxnData(subscriberContext);
			}
		} else {

			subscriberContext.setSubscriberContextId(doFromDB.getSubscriberContextId());
			// make sure when insert new TxnData, keep the SalesInteractionStartTimeInMills
			// in sync with the original record
			subscriberContext.setSalesInteractionStartTimeInMills(doFromDB.getSalesInteractionStartTimeInMills());

			if (subscriberContext.getStatus() != null // this indicates no changes of status
					&& doFromDB.getStatus().equals(subscriberContext.getStatus()) == false) {

				logger.info("--ESDG-- SALES_SUB_CONTEXT " + subscriberContext.getSubscriberContextId()
						+ " ' staus changed  do update.");

				updateSubscriberContext(subscriberContext);
			}

			if (doFromDB.getJsonSalesTXNData() != null && subscriberContext.getJsonSalesTXNData() != null
					&& doFromDB.getJsonSalesTXNData().equals(subscriberContext.getJsonSalesTXNData()) == false) {

				logger.info("--ESDG-- SALES_SUB_CONTEXT_TXN_DATA changed, insert new record for SUB_CONTEXT_ID = "
						+ subscriberContext.getSubscriberContextId());

				insertTxnData(subscriberContext);
			}
		}
	}

	private void insertSubscriberContext(EsdgSubscriberContextDO subscriberContext) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(SELECT_SUB_CONTEXT_SEQ_NEXTVAL_STMT);
			rs = ps.executeQuery();

			if (!rs.next()) {
				String errorMsg = "Failed to get next sequence for interaction in database. salesContextId = "
						+ subscriberContext.getSalesContextId();
				logger.error("--ESDG-- " + errorMsg);

			} else {
				subscriberContext.setSubscriberContextId(rs.getString(1));
				if (logger.isDebugEnabled()) {
					logger.debug("--ESDG-- SALES_SUB_CONTEXT_ID is generated for salesOrder="
							+ subscriberContext.getSalesContextId() + ", new id= "
							+ subscriberContext.getSubscriberContextId());
				}

				rs.close();
				ps.close();

				ps = getConnection().prepareStatement(INSERT_SUBSCRIBER_CONTEXT_STMT);

				int idx = 1;
				ps.setString(idx++, subscriberContext.getSubscriberContextId()); // SALES_SUB_CONTEXT_ID
				ps.setString(idx++, subscriberContext.getSalesContextId()); // SALES_CONTEXT_ID

				Timestamp interactStartTime = new Timestamp(subscriberContext.getSalesInteractionStartTimeInMills());
				ps.setTimestamp(idx++, interactStartTime, GMT_CAL); // partition key - always use GMT timezone

				ps.setString(idx++, subscriberContext.getAccountNumber()); // BILLING_ACCOUNT_NUM
				ps.setString(idx++, subscriberContext.getPhoneNumber()); // SUBSCRIBER_PHONE_NUM
				ps.setString(idx++, subscriberContext.getType()); // CONTEXT_TYPE_CD
				ps.setString(idx++, subscriberContext.getStatus()); // CONTEXT_STATUS_CD
				ps.setString(idx++, getDbUser());
				ps.setString(idx++, getDbUser());

				ps.executeUpdate();
			}

		} finally {
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
	}

	private void insertTxnData(EsdgSubscriberContextDO subscriberContext) throws SQLException {

		PreparedStatement ps = null;

		try {
			ps = getConnection().prepareStatement(INSERT_SUB_CONTEXT_RAW_DATA_STMT);
			int idx = 1;
			ps.setString(idx++, subscriberContext.getSubscriberContextId()); // SALES_SUB_CONTEXT_ID
			ps.setTimestamp(idx++, new Timestamp(subscriberContext.getSalesInteractionStartTimeInMills()), GMT_CAL); // partition
																														// key
																														// :
																														// INTRACTN_START_TS

			ps.setBytes(idx++, SerializationUtils.serialize(subscriberContext.getJsonSalesTXNData())); // CONTEXT_TXN_DATA
			ps.setString(idx++, getDbUser());
			ps.setString(idx++, getDbUser());

			ps.executeUpdate();

		} finally {
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
	}

	private void updateSubscriberContext(EsdgSubscriberContextDO subscriberContext) throws SQLException {

		PreparedStatement ps = null;

		try {
			ps = getConnection().prepareStatement(UPDATE_SUB_CONTEXT_STMT);
			int idx = 1;
			ps.setString(idx++, subscriberContext.getStatus()); // CONTEXT_STATUS_CD
			ps.setString(idx++, getDbUser());

			ps.setString(idx++, subscriberContext.getSubscriberContextId()); // record identifier: SALES_SUB_CONTEXT_ID
			ps.executeUpdate();

		} finally {
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
	}

//	private static final String SELECT_TXN_DATA_ID_STMT = "SELECT T2.SALES_SUB_CONTEXT_TXN_DATA_ID FROM SALES_SUB_CONTEXT_TXN_DATA T2 "
//			+ "  JOIN SALES_SUB_CONTEXT T1 ON T1.SALES_SUB_CONTEXT_ID=T2.SALES_SUB_CONTEXT_ID AND T1.INTRACTN_START_TS=T2.INTRACTN_START_TS  "
//			+ " WHERE T1.SALES_SUB_CONTEXT_ID=?"
//			;
//	private void updateTxnData( EsdgSubscriberContextDO subscriberContext )  throws SQLException  {
//		
//		PreparedStatement ps = null;
//        
//        try {
//	        ps = getConnection().prepareStatement( UPDATE_SUB_CONTEXT_TXN_DATA_STMT );
//	        
//	        int idx = 1;
//	        ps.setBytes( idx++, SerializationUtils.serialize(subscriberContext.getJsonSalesTXNData()) ); //CONTEXT_TXN_DATA      
//	        ps.setString( idx++, getDbUser() );  //LAST_UPDT_USER_ID
//	        
//	        ps.setString( idx++, subscriberContext.getTxnDataId() );  //record identifierSALES_SUB_CONTEXT_TXN_DATA_ID
//	        ps.executeUpdate(); 
//	        
//        } finally {
//	        if( ps != null ) {ps.close(); ps = null;}
//        }
//    }

//	private static final String UPDATE_SUB_CONTEXT_TXN_DATA_STMT = "UPDATE SALES_SUB_CONTEXT_TXN_DATA SET  "
//			+ "CONTEXT_TXN_DATA=?,  "
//			+ "LAST_UPDT_USER_ID=?, LAST_UPDT_TS=SYSDATE "
//			+ "WHERE SALES_SUB_CONTEXT_TXN_DATA_ID = ?  "
//			;
//	private String getTxnDataIdBySubContextId(String subContextId) throws SQLException {
//		
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		String txnDataId = null;
//        try {
//        	ps = getConnection().prepareStatement( SELECT_TXN_DATA_ID_STMT );
//        	
//	        ps.setString( 1, subContextId);
//
//	        rs = ps.executeQuery();
//	        
//	        if ( rs.next() ) {
//	        	txnDataId = rs.getString( 1 );
//	        }
//	        
//        } finally {
//	        
//        	if( rs != null ) {rs.close(); rs = null;}
//	        if( ps != null ) {ps.close(); ps = null;}
//        }
//		return txnDataId;
//	}

	public EsdgSubscriberContextDO selectSubscirberContextByKey(String subscriberContextKey) throws SQLException {

		EsdgSubscriberContextDO result = null;

		Map resultMap = selectSubscirberContextsByKeys(Arrays.asList(subscriberContextKey));
		if (resultMap.size() == 1) {
			result = (EsdgSubscriberContextDO) resultMap.get(subscriberContextKey);
		}

		return result;
	}

	public Map selectSubscirberContextsByKeys(Collection<String> keys) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;

		long startTime = System.currentTimeMillis();

		Map result = new HashMap();
		String sqlTxt = null;
		try {

			StringBuilder sb = new StringBuilder(SELECT_SUB_CONTEXT_STMT).append(" in (");
			for (int i = 0; i < keys.size() - 1; i++) {
				sb.append("(?,?),");
			}
			sb.append("(?,?)) ");

			sqlTxt = sb.toString();
			ps = getConnection().prepareStatement(sqlTxt);

			int idx = 1;
			for (String subscriberContextKey : keys) {
				String[] params = EsdgSubscriberContextDO.parse(subscriberContextKey);
				ps.setString(idx++, params[0]); // salesContextId
				ps.setString(idx++, params[1]); // contextType
			}

			rs = ps.executeQuery();

			while (rs.next()) {

				EsdgSubscriberContextDO entry = new EsdgSubscriberContextDO();

				mapSubscriberContext(rs, entry);

				result.put(entry.getCacheKey(), entry);
			}

		} finally {

			long elapsedTime = System.currentTimeMillis() - startTime;

			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query subscriberContext elapsedTime=" + elapsedTime + ", keys " + keys
						+ " , SQL:" + sqlTxt);
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

	private void mapSubscriberContext(ResultSet rs, EsdgSubscriberContextDO result) throws SQLException {

		result.setSubscriberContextId(rs.getString("SALES_SUB_CONTEXT_ID"));
		result.setSalesContextId(rs.getString("SALES_CONTEXT_ID"));
		result.setSalesInteractionStartTimeInMills(rs.getTimestamp("INTRACTN_START_TS", GMT_CAL).getTime()); // make
																												// sure
																												// we
																												// use
																												// GMT
		result.setAccountNumber(rs.getString("BILLING_ACCOUNT_NUM"));
		result.setPhoneNumber(rs.getString("SUBSCRIBER_PHONE_NUM"));
		result.setType(rs.getString("CONTEXT_TYPE_CD"));
		result.setStatus(rs.getString("CONTEXT_STATUS_CD"));

		result.setTxnDataId(rs.getString("SALES_SUB_CONTEXT_TXN_DATA_ID"));

		byte[] bytes = rs.getBytes("CONTEXT_TXN_DATA");
		if (bytes != null && bytes.length > 0) {
			result.setJsonSalesTXNData((String) SerializationUtils.deserialize(bytes));
		}
	}

	public List<String> searchSubscriberContextKeys(String ban, String type, String status, int days)
			throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> result = new ArrayList<String>();

		try {

			SubscriberContextQueryBuilder queryBuilder = new SubscriberContextQueryBuilder(ban);

			if (type != null) {
				queryBuilder.addEqualFilter("CONTEXT_TYPE_CD", type);
			}

			if (status != null) {
				queryBuilder.addEqualFilter("CONTEXT_STATUS_CD", status);
			}

			if (days > 0) {
				queryBuilder.addDaysCriteria(days);
			}

			ps = queryBuilder.getStatement();
			rs = ps.executeQuery();

			while (rs.next()) {
				result.add(EsdgSubscriberContextDO.composeCacheKey(rs.getString(1), rs.getString(2)));
			}

		} finally {

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

	public List<String> searchSubscriberContextKeysBySalesId(String salesId, String type, String status)
			throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> result = new ArrayList<String>();

		try {

			SubscriberContextQueryBuilder queryBuilder = new SubscriberContextQueryBuilder(BY.SALES_ID , salesId);

			if (type != null) {
				queryBuilder.addEqualFilter("CONTEXT_TYPE_CD", type);
			}

			if (status != null) {
				queryBuilder.addEqualFilter("CONTEXT_STATUS_CD", status);
			}

			ps = queryBuilder.getStatement();
			rs = ps.executeQuery();

			while (rs.next()) {
				result.add(EsdgSubscriberContextDO.composeCacheKey(rs.getString(1), rs.getString(2)));
			}

		} finally {

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
	/**
	 * A helper class to facilitate build dynamic PreparedStatement
	 */
	private class SubscriberContextQueryBuilder {

		private StringBuilder sql = new StringBuilder(SEARCH_SUB_CONTEXT_KEYS_BY_CRITERIA_STMT);
		private ArrayList<Object> criteria = new ArrayList<Object>();

		SubscriberContextQueryBuilder(String ban) {
			criteria.add(ban);
		}
		SubscriberContextQueryBuilder(BY by, String salesId) {
			if(BY.SALES_ID.equals(by)) {
				sql = new StringBuilder(SEARCH_SUB_CONTEXT_KEYS_BY_SALESID_CRITERIA_STMT);
			}
			criteria.add(salesId);
		}
		void addEqualFilter(String colunmnName, Object value) {
			sql.append(" and ").append(colunmnName).append("=? ");
			criteria.add(value);
		}

		void addDaysCriteria(int value) {
			sql.append(" and INTRACTN_START_TS >= ?");

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -value);
			cal.clear(Calendar.HOUR);
			cal.clear(Calendar.MINUTE);
			cal.clear(Calendar.SECOND);
			cal.clear(Calendar.MILLISECOND);

			criteria.add(new Timestamp(cal.getTimeInMillis()));
		}

		PreparedStatement getStatement() throws SQLException {

			String sqlTxt = sql.toString();

			PreparedStatement ps = getConnection().prepareStatement(sqlTxt);

			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query subscriberContextId by criteria " + criteria + " , SQL:" + sqlTxt);
			}

			int idx = 1;
			for (Object value : criteria) {
				if (value instanceof Timestamp) {
					// special handling for timestamp , add GMT timezone
					ps.setTimestamp(idx, (Timestamp) value, GMT_CAL);
				} else {
					ps.setObject(idx++, value);
				}
			}

			return ps;
		}
	}
	
	static enum BY{ BAN, SALES_ID };
}
