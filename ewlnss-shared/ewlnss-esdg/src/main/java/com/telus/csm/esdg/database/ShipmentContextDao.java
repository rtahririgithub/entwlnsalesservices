package com.telus.csm.esdg.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.esdg.domain.EsdgShipmentDO;
import com.telus.csm.esdg.domain.EsdgShipmentInteractionDO;

public class ShipmentContextDao extends EsdgDao {
	private static Logger logger = Logger.getLogger(ShipmentContextDao.class);
	private static final int LOOK_UP_PERIOD = 90;
	private static final String SELECT_SALES_INTRACTN_SHIPMENT_SEQ_NEXTVAL_STMT = "SELECT SALESORDERADM.SALES_INTRACTN_SHIPMENT_SEQ.NEXTVAL FROM DUAL";
	private static final String SELECT_SALES_INTRACTN_SHIPMENT_ID_STMT = "select SALES_INT_SHIPMENT_ID from SALESORDERADM.SALES_INTRACTN_SHIPMENT "
			+ "where SALES_INTRACTN_ID = ? and INTRACTN_START_TS > ?";
	private static final String SELECT_INTRACTN_START_TS_BY_ID_STMT = "SELECT INTRACTN_START_TS FROM SALES_INTRACTN WHERE "
			+ "SALES_INTRACTN_ID = ? AND INTRACTN_START_TS > ?";
	private static final String SELECT_SALES_INTRACTN_SHIPMENT_STMT = "SELECT SALES_INT_SHIPMENT_ID, SALES_INTRACTN_ID, INTRACTN_START_TS, SALES_SHIPMENT_STATUS_CD, SALES_INT_SHIPMENT_TXN_DATA, EXTERNAL_TRANSACTION_REFERENCE, SHIPMENT_TRACKING_NUM "
			+ "from SALESORDERADM.SALES_INTRACTN_SHIPMENT where SALES_INT_SHIPMENT_ID = ? AND INTRACTN_START_TS > ?";
	private static final String SELECT_SALES_SHIPMENT_STMT = "SELECT SALES_SHIPMENT_ID, SALES_CONTEXT_ID, INTRACTN_START_TS, SALES_SHIPMENT_STATUS_CD, STATUS_EFFECTIVE_START_TS, EXTERNAL_TRANSACTION_REFERENCE, "
			+ " SHIPMENT_TRACKING_NUM, SALES_INT_SHIPMENT_ID "
			+ "FROM SALESORDERADM.SALES_SHIPMENT  WHERE SALES_INT_SHIPMENT_ID = ? AND INTRACTN_START_TS > ?";
	private static final String MERGE_SALES_SHIPMENT_STMT = "MERGE into SALESORDERADM.SALES_SHIPMENT s1" + 
			"  USING (select ? as sales_context_id from dual) s2" + 
			"  ON (s1.sales_context_id = S2.sales_context_id AND s1.INTRACTN_START_TS > ?)" + 
			"  WHEN MATCHED THEN UPDATE SET SALES_SHIPMENT_STATUS_CD = ?, EXTERNAL_TRANSACTION_REFERENCE = ?, SHIPMENT_TRACKING_NUM = ?, SALES_INT_SHIPMENT_ID = ?, LAST_UPDT_USER_ID = ?, LAST_UPDT_TS = SYSDATE" + 
			"  WHEN NOT MATCHED THEN INSERT (SALES_SHIPMENT_ID, SALES_CONTEXT_ID, INTRACTN_START_TS, SALES_SHIPMENT_STATUS_CD, STATUS_EFFECTIVE_START_TS, EXTERNAL_TRANSACTION_REFERENCE," + 
			"    SHIPMENT_TRACKING_NUM, CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS, SALES_INT_SHIPMENT_ID)" + 
			"    values (SALES_SHIPMENT_SEQ.NEXTVAL, ?, ?, ?, sysdate, ?, " + 
			"    ?, ?, SYSDATE, ?, SYSDATE, ?)";
	private static final String MERGE_SALES_INTRACTN_SHIPMENT_STMT = "MERGE into SALESORDERADM.SALES_INTRACTN_SHIPMENT is1" + 
			"  USING (select ? as SALES_INT_SHIPMENT_ID from dual) is2" + 
			"  ON (is1.SALES_INT_SHIPMENT_ID = is2.SALES_INT_SHIPMENT_ID AND is1.INTRACTN_START_TS > ?)" + 
			"  WHEN MATCHED THEN UPDATE SET SALES_SHIPMENT_STATUS_CD = ?, SALES_INT_SHIPMENT_TXN_DATA = ?, EXTERNAL_TRANSACTION_REFERENCE = ?, SHIPMENT_TRACKING_NUM = ?, LAST_UPDT_USER_ID = ?, LAST_UPDT_TS = SYSDATE" + 
			"  WHEN NOT MATCHED THEN INSERT (SALES_INT_SHIPMENT_ID, SALES_INTRACTN_ID, INTRACTN_START_TS, SALES_SHIPMENT_STATUS_CD, SALES_INT_SHIPMENT_TXN_DATA, EXTERNAL_TRANSACTION_REFERENCE, " + 
			"    SHIPMENT_TRACKING_NUM, CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) " + 
			"    values (?, ?, ?, ?, ?, ?, " + 
			"      ?, ?, SYSDATE, ?, SYSDATE)";
	public ShipmentContextDao(Connection conn, String dbUser) {
		super(conn, dbUser);
	}

	public String createNextSalesInteractionShipmentId() throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();
        String salesInteractionShipmentId = null;

		try {
			ps = getConnection().prepareStatement(SELECT_SALES_INTRACTN_SHIPMENT_SEQ_NEXTVAL_STMT);
			rs = ps.executeQuery();
            if ( !rs.next() ) {
            	logger.error( "--ESDG-- Failed to get next sequence for SALES_INTRACTN_SHIPMENT in database.");
            } else {
            	salesInteractionShipmentId = rs.getString(1);
            }

		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query payment by id : salesInteractionShipmentId=" + salesInteractionShipmentId + "; elapsedTime=" + elapsedTime);
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
		return salesInteractionShipmentId;
	}

	public List<String> findInteractionShipmentIdByInteractionId(String interactionId) throws SQLException {
		List<String> result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();

		try {
			ps = getConnection().prepareStatement(SELECT_SALES_INTRACTN_SHIPMENT_ID_STMT);
			int idx = 1;
			ps.setString(idx++, interactionId);
			ps.setTimestamp(idx++, EsdgDatabaseAdapter_1.getBackTime(LOOK_UP_PERIOD));
			rs = ps.executeQuery();

			while(rs.next()) {
				String salesIteracShipId = rs.getString("SALES_INT_SHIPMENT_ID");
				if(!StringUtils.isEmpty(salesIteracShipId)) {
					if(result == null) {
						result = new ArrayList<String>();
					}
					result.add(salesIteracShipId);
				}
			} 
			if(result == null) {
				logger.warn("--ESDG-- shipment is not found in database. interactionId = " + interactionId);
			}

		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query shipment id by interactionId : interactionId=" + interactionId + "; elapsedTime=" + elapsedTime);
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

	private Timestamp findInteractionTimestamp(String salesInteractionId) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();

		try {
			ps = getConnection().prepareStatement(SELECT_INTRACTN_START_TS_BY_ID_STMT);
			int idx = 1;
			ps.setString(idx++, salesInteractionId);
			ps.setTimestamp(idx++, EsdgDatabaseAdapter_1.getBackTime(LOOK_UP_PERIOD));
			rs = ps.executeQuery();

			if (rs.next()) {
				Timestamp salesInteractionTimestamp = rs.getTimestamp(1);
				return salesInteractionTimestamp;
			} else {
				String errMsg = "--ESDG-- Cannot find salesInteractionTimestamp for SalesInteractionId = " + salesInteractionId;
				throw new Exception(errMsg);
			}

		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query salesInteractionTimestamp by id : SalesInteractionId = " + salesInteractionId + "; elapsedTime=" + elapsedTime);
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

	public void saveSalesInteractionShipment(EsdgShipmentInteractionDO esdgShipmentInteractionDO) throws SQLException, Exception {
		if(esdgShipmentInteractionDO.getSalesInteractionStartTimeInMills() <= 0) {
			Timestamp interactionTimestamp = findInteractionTimestamp(esdgShipmentInteractionDO.getSalesInteractionId());
			esdgShipmentInteractionDO.setSalesInteractionStartTimeInMills(interactionTimestamp.getTime());
		}
		upsertSalesIntractnShipment(esdgShipmentInteractionDO);
		if(esdgShipmentInteractionDO.getShipments() != null) {
			for(EsdgShipmentDO si: esdgShipmentInteractionDO.getShipments()) {
				si.setSalesInteractionStartTimeInMills(esdgShipmentInteractionDO.getSalesInteractionStartTimeInMills());
				si.setSalesInteractionShipmentId(esdgShipmentInteractionDO.getSalesInteractionShipmentId());
				upsertSalesShipment(si);
			}
		}
	}

	private void upsertSalesShipment(EsdgShipmentDO esdgShipmentDO) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();
		boolean success = false;
		try {
			ps = getConnection().prepareStatement(MERGE_SALES_SHIPMENT_STMT);
			int idx = 1;
			ps.setString(idx++, esdgShipmentDO.getSalesContextId());//SALES_CONTEXT_ID
			ps.setTimestamp(idx++, EsdgDatabaseAdapter_1.getBackTime(LOOK_UP_PERIOD));
			ps.setString(idx++, esdgShipmentDO.getSalesShipmentStatusCd());//SALES_SHIPMENT_STATUS_CD
			ps.setString(idx++, esdgShipmentDO.getExternalTransactionReference());//EXTERNAL_TRANSACTION_REFERENCE
			ps.setString(idx++, esdgShipmentDO.getShipmentTrackingNum());//SHIPMENT_TRACKING_NUM
			ps.setString(idx++, esdgShipmentDO.getSalesInteractionShipmentId());//SALES_INT_SHIPMENT_ID
			ps.setString(idx++, getDbUser());//LAST_UPDT_USER_ID
			//SYSDATE LAST_UPDT_TS
			
			ps.setString(idx++, esdgShipmentDO.getSalesContextId());//SALES_CONTEXT_ID
			ps.setTimestamp(idx++, new Timestamp(esdgShipmentDO.getSalesInteractionStartTimeInMills()));//INTRACTN_START_TS
			ps.setString(idx++, esdgShipmentDO.getSalesShipmentStatusCd());//SALES_SHIPMENT_STATUS_CD
			//sysdate STATUS_EFFECTIVE_START_TS
			ps.setString(idx++, esdgShipmentDO.getExternalTransactionReference());//EXTERNAL_TRANSACTION_REFERENCE, "
			ps.setString(idx++, esdgShipmentDO.getShipmentTrackingNum());//SHIPMENT_TRACKING_NUM
			ps.setString(idx++, getDbUser());//CREATE_USER_ID
			//CREATE_TS
			ps.setString(idx++, getDbUser());//LAST_UPDT_USER_ID
			//LAST_UPDT_TS
			ps.setString(idx++, esdgShipmentDO.getSalesInteractionShipmentId());//SALES_INT_SHIPMENT_ID

			success = ps.executeUpdate() == 1;
		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
			}
			if(success) {
				if (logger.isDebugEnabled()) {
					logger.debug("--ESDG-- Merge to SALES_SHIPMENT is a success. SalesContextId = " + esdgShipmentDO.getSalesContextId() + "; elapsedTime=" + elapsedTime);
				}
			} else {
				logger.warn("--ESDG-- Merge to SALES_SHIPMENT has failed. SalesContextId = " + esdgShipmentDO.getSalesContextId() + "; elapsedTime=" + elapsedTime);
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

	private void upsertSalesIntractnShipment(EsdgShipmentInteractionDO esdgShipmentInteractionDO) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();
		boolean success = false;
		try {
			ps = getConnection().prepareStatement(MERGE_SALES_INTRACTN_SHIPMENT_STMT);
			int idx = 1;
			ps.setString(idx++, esdgShipmentInteractionDO.getSalesInteractionShipmentId());//SALES_INT_SHIPMENT_ID
			ps.setTimestamp(idx++, EsdgDatabaseAdapter_1.getBackTime(LOOK_UP_PERIOD));
			ps.setString(idx++, esdgShipmentInteractionDO.getSalesShipmentStatusCd());//SALES_SHIPMENT_STATUS_CD
			ps.setBytes( idx++, SerializationUtils.serialize(esdgShipmentInteractionDO.getJsonShipmentTXNData()));//SALES_INT_SHIPMENT_TXN_DATA, 
			ps.setString(idx++, esdgShipmentInteractionDO.getExternalTransactionReference());//EXTERNAL_TRANSACTION_REFERENCE
			ps.setString(idx++, esdgShipmentInteractionDO.getShipmentTrackingNum());//SHIPMENT_TRACKING_NUM
			ps.setString(idx++, getDbUser());//CREATE_USER_ID
			//LAST_UPDT_TS
			
			ps.setString(idx++, esdgShipmentInteractionDO.getSalesInteractionShipmentId());//SALES_INT_SHIPMENT_ID
			ps.setString(idx++, esdgShipmentInteractionDO.getSalesInteractionId());//SALES_INTRACTN_ID
			ps.setTimestamp(idx++, new Timestamp(esdgShipmentInteractionDO.getSalesInteractionStartTimeInMills()));//INTRACTN_START_TS
			ps.setString(idx++, esdgShipmentInteractionDO.getSalesShipmentStatusCd());//SALES_SHIPMENT_STATUS_CD
			ps.setBytes( idx++, SerializationUtils.serialize(esdgShipmentInteractionDO.getJsonShipmentTXNData()));//SALES_INT_SHIPMENT_TXN_DATA, 
			ps.setString(idx++, esdgShipmentInteractionDO.getExternalTransactionReference());//EXTERNAL_TRANSACTION_REFERENCE		
			ps.setString(idx++, esdgShipmentInteractionDO.getShipmentTrackingNum());//SHIPMENT_TRACKING_NUM
			ps.setString(idx++, getDbUser());//CREATE_USER_ID
			//CREATE_TS
			ps.setString(idx++, getDbUser());//LAST_UPDT_USER_ID
			//LAST_UPDT_TS
			success = ps.executeUpdate() == 1;			
		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if(success) {
				if (logger.isDebugEnabled()) {
					logger.debug("--ESDG-- Merge to SALES_INTRACTN_SHIPMENT is a success. SalesInteractionShipmentId = " + esdgShipmentInteractionDO.getSalesInteractionShipmentId() + "; elapsedTime=" + elapsedTime);
				}
			} else {
				logger.warn("--ESDG-- Merge to SALES_INTRACTN_SHIPMENT has failed. SalesInteractionShipmentId = " + esdgShipmentInteractionDO.getSalesInteractionShipmentId() + "; elapsedTime=" + elapsedTime);
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

	public EsdgShipmentInteractionDO selectInteractionShipment(String salesInteractionShipmentId) throws SQLException {
		EsdgShipmentInteractionDO result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();

		try {
			ps = getConnection().prepareStatement(SELECT_SALES_INTRACTN_SHIPMENT_STMT);
			int idx = 1;
			ps.setString(idx++, salesInteractionShipmentId);
			ps.setTimestamp(idx++, EsdgDatabaseAdapter_1.getBackTime(LOOK_UP_PERIOD));
			rs = ps.executeQuery();

			if (rs.next()) {
				result = mapSalesInteractionShipment(rs);
				result.setShipments(selectShipmentItems(salesInteractionShipmentId));
			} else {
				logger.warn("--ESDG-- SalesInteractionShipment is not found in database. salesInteractionShipmentId = " + salesInteractionShipmentId);
			}

		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query SalesInteractionShipment by salesInteractionShipmentId=" + salesInteractionShipmentId + "; elapsedTime=" + elapsedTime);
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

	private List<EsdgShipmentDO> selectShipmentItems(String salesInteractionShipmentId) throws SQLException {
		List<EsdgShipmentDO> result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();

		try {
			ps = getConnection().prepareStatement(SELECT_SALES_SHIPMENT_STMT);
			int idx = 1;
			ps.setString(idx++, salesInteractionShipmentId);
			ps.setTimestamp(idx++, EsdgDatabaseAdapter_1.getBackTime(LOOK_UP_PERIOD));
			rs = ps.executeQuery();

			while(rs.next()) {
				if(result == null) {
					result = new ArrayList<EsdgShipmentDO>();
				}
				EsdgShipmentDO itm = mapSalesShipmentItem(rs);
				result.add(itm);
			} 
			if(result == null ){
				logger.warn("--ESDG-- SalesShipment is not found in database. salesInteractionShipmentId = " + salesInteractionShipmentId);
			}

		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query SalesShipment by salesInteractionShipmentId=" + salesInteractionShipmentId + "; elapsedTime=" + elapsedTime);
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

	private EsdgShipmentDO mapSalesShipmentItem(ResultSet rs) throws SQLException {
		EsdgShipmentDO ret = new EsdgShipmentDO();
		ret.setSalesShipmentId(rs.getString("SALES_SHIPMENT_ID"));
		ret.setSalesContextId(rs.getString("SALES_CONTEXT_ID"));
		ret.setSalesInteractionStartTimeInMills(rs.getTimestamp("INTRACTN_START_TS").getTime());
		ret.setSalesShipmentStatusCd(rs.getString("SALES_SHIPMENT_STATUS_CD"));
		ret.setStatusEffectiveStartTs(rs.getTimestamp("STATUS_EFFECTIVE_START_TS").getTime());
		ret.setExternalTransactionReference(rs.getString("EXTERNAL_TRANSACTION_REFERENCE"));
		ret.setShipmentTrackingNum(rs.getString("SHIPMENT_TRACKING_NUM"));
		ret.setSalesInteractionShipmentId(rs.getString("SALES_INT_SHIPMENT_ID"));
		return ret;
	}

	private EsdgShipmentInteractionDO mapSalesInteractionShipment(ResultSet rs) throws SQLException {
		EsdgShipmentInteractionDO ret = new EsdgShipmentInteractionDO();
		ret.setSalesInteractionShipmentId(rs.getString("SALES_INT_SHIPMENT_ID"));
		ret.setSalesInteractionId(rs.getString("SALES_INTRACTN_ID"));
		ret.setSalesInteractionStartTimeInMills(rs.getTimestamp("INTRACTN_START_TS").getTime());
		ret.setSalesShipmentStatusCd(rs.getString("SALES_SHIPMENT_STATUS_CD"));
		byte[] bytes = rs.getBytes("SALES_INT_SHIPMENT_TXN_DATA");
		if (bytes != null && bytes.length > 0) {
			ret.setJsonShipmentTXNData((String) SerializationUtils.deserialize(bytes));
		}
		ret.setExternalTransactionReference(rs.getString("EXTERNAL_TRANSACTION_REFERENCE"));
		ret.setShipmentTrackingNum(rs.getString("SHIPMENT_TRACKING_NUM"));
		return ret;
	}

}
