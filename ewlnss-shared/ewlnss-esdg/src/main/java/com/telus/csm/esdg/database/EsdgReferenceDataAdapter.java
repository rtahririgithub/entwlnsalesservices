package com.telus.csm.esdg.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.telus.csm.esdg.EsdgReferenceSQLConstants;
import com.telus.csm.esdg.domain.EsdgReferenceDataDO;


/**
 * The Class EsdgReferenceDataAdapter.
 */
public class EsdgReferenceDataAdapter {
	
	/** The Constant ESS_VERSION_SUFFIX. */
	private static final String ESS_VERSION_SUFFIX = "_V5";
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(EsdgReferenceDataAdapter.class);
	
	/** The db user. */
	private String dbUser = "ESS" + ESS_VERSION_SUFFIX;
	
	/** The esdg database connection factory. */
	private IEsdgDatabaseConnectionFactory esdgDatabaseConnectionFactory = null;
	
	private Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	
	/**
	 * Instantiates a new esdg database EsdgReferenceDataAdapter.
	 */
	public EsdgReferenceDataAdapter() {
		this.esdgDatabaseConnectionFactory = IEsdgDatabaseConnectionFactory.Factory.newInstance();	
	}
	
	/**
	 * Instantiates a new esdg database EsdgReferenceDataAdapter.
	 *
	 * @param esdgDatabaseConnectionFactory the esdg database connection factory
	 */
	public EsdgReferenceDataAdapter(IEsdgDatabaseConnectionFactory esdgDatabaseConnectionFactory) {
		this.esdgDatabaseConnectionFactory = esdgDatabaseConnectionFactory;	
	}

	/**
	 * Sets the db user.
	 *
	 * @param dbUser the new db user
	 */
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser + ESS_VERSION_SUFFIX;
	}

	/**
	 * Search EsdgReferenceData by list of cache keys.
	 *
	 * @param partitionCd the cluster partition code
	 * @param cacheKey
	 * @return the map of key and EsdgReferenceDataDO
	 */
	public Map<String, EsdgReferenceDataDO> selectReferenceData(String partitionCd, Collection<String> cacheKeys) {		 
		HashMap<String, EsdgReferenceDataDO> rawDataMap = new HashMap<String, EsdgReferenceDataDO>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String keySetStr = null;
        try {
        	StringBuffer sb = new StringBuffer();
        	for( int i = 0; i < cacheKeys.size(); i++ ) {
        		sb.append("?,");
        	}
        	keySetStr = sb.toString();
        	keySetStr = keySetStr.substring(0, keySetStr.length() - 1);
        	conn = esdgDatabaseConnectionFactory.getDBConnection();
        	          
            ps = conn.prepareStatement(EsdgReferenceSQLConstants.SELECT_SALES_CONTEXT_TXN_DATA_BY_KEY_SET_STMT + "(" + keySetStr + ")");
            int idx = 1;
	        ps.setString(idx++, partitionCd);

	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, -15);
	        
	        ps.setTimestamp( idx++, getTimestamp(cal.getTime()), gmtCal );

	        for (String cacheKey : cacheKeys) {
	        	ps.setString(idx++, cacheKey);
	        }

            rs = ps.executeQuery();
            while ( rs.next() ) {
            	EsdgReferenceDataDO esdgReferenceDataDO = new EsdgReferenceDataDO();	        	
	        	mapEsdgReferenceDataItem(rs, esdgReferenceDataDO);
	        	rawDataMap.put(esdgReferenceDataDO.getExternalTxnRefId(), esdgReferenceDataDO);
	            logger.debug( "--ESDG-- selected SALES_CONTEXT_TXN_DATA: " + esdgReferenceDataDO.getLogInfo());
            }

        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);        	
        	logger.error("--ESDG-- Exception caught when reading raw data set from database. key set = " + cacheKeys.toString(), ex);            
        } finally {
            closeConnection(conn, ps, rs);
        }

        return rawDataMap;		
	}
	
	private void mapEsdgReferenceDataItem(ResultSet rs,	EsdgReferenceDataDO result)  throws SQLException  {
		
		result.setSalesContextTxnDataId( rs.getInt("SALES_CONTEXT_TXN_DATA_ID") );
		result.setInteractStartTS( rs.getTimestamp("INTRACTN_START_TS", gmtCal) );
		result.setExternalTxnRefId( rs.getString("EXTERNAL_TXN_REF_ID") );
		result.setSalesContextDataPartitionCd( rs.getString("SALES_CONTEXT_DATA_PARTITION_C"));
		result.setSalesTxnData(rs.getBytes("SALES_TXN_DATA")); 

		result.setUsageCount( rs.getInt("USAGE_COUNT") );
		result.setCreateUserId( rs.getString("CREATE_USER_ID") );
		result.setCreateTS( rs.getTimestamp("CREATE_TS", gmtCal) );
		result.setLastUpdateUserId( rs.getString("LAST_UPDT_USER_ID") );
		result.setLastUpdateTS( rs.getTimestamp("LAST_UPDT_TS", gmtCal) );
		result.setLastReadTS( rs.getTimestamp("LAST_READ_TS", gmtCal) );
		
	}

	public void saveReferenceData(Collection<EsdgReferenceDataDO> esdgReferenceDataDOs) {

		for (EsdgReferenceDataDO esdgReferenceDataDO : esdgReferenceDataDOs) {
			saveReferenceData(esdgReferenceDataDO);
		}
	}

	public void saveReferenceData(EsdgReferenceDataDO oValue) {
		
		Integer txnId = getReferenceDataTxnId( oValue.getSalesContextDataPartitionCd(), oValue.getExternalTxnRefId() ); 

		if (txnId != null) {
			oValue.setSalesContextTxnDataId(txnId);
			updateReferenceData(oValue);
		} else {
       		insertReferenceData(oValue);
 		}
	
	}

	private void insertReferenceData( EsdgReferenceDataDO oValue ) {
		
        PreparedStatement ps = null;
        Connection conn = null;
        
        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();
	        ps = conn.prepareStatement( EsdgReferenceSQLConstants.INSERT_SALES_CONTEXT_TXN_DATA_STMT );  
	        int idx = 1;
	        ps.setTimestamp( idx++, getTimestamp(oValue.getInteractStartTS()), gmtCal );
	        ps.setString( idx++, oValue.getExternalTxnRefId() );
	        ps.setString( idx++, oValue.getSalesContextDataPartitionCd() );
	        ps.setString( idx++, oValue.getSalesContextDataTypeCd() );
	        ps.setBytes( idx++, oValue.getSalesTxnData() );
	        ps.setLong( idx++, oValue.getUsageCount() );
	        ps.setString( idx++, dbUser );
	        ps.setString( idx++, dbUser );
	        ps.setTimestamp( idx++, getTimestamp(oValue.getLastUpdateTS()), gmtCal );
	        ps.setTimestamp( idx++, getTimestamp(oValue.getLastReadTS()), gmtCal );
	        
	        ps.executeUpdate();
	        conn.commit();
	        
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when inserting ReferenceData. cacheKey = " + oValue.getExternalTxnRefId(), ex);
	        
        } finally {
        	closeConnection(conn, ps, null);
        }
        
        logger.debug( "--ESDG-- inserted SALES_CONTEXT_TXN_DATA: " + oValue.getLogInfo());

	}
	
	private void updateReferenceData(EsdgReferenceDataDO oValue) {
        
		PreparedStatement ps = null;
		Connection conn = null;

        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();
	        ps = conn.prepareStatement( EsdgReferenceSQLConstants.UPDATE_SALES_CONTEXT_TXN_DATA_STMT );  
	        int idx = 1;
	        ps.setBytes( idx++, oValue.getSalesTxnData() );
	        ps.setLong( idx++, oValue.getUsageCount() );
	        ps.setString( idx++, dbUser );
	        ps.setTimestamp( idx++, getTimestamp(oValue.getLastUpdateTS()), gmtCal );
	        ps.setTimestamp( idx++, getTimestamp(oValue.getLastReadTS()), gmtCal );
	        ps.setString( idx++, oValue.getSalesContextDataTypeCd() );

	        ps.setLong( idx++, oValue.getSalesContextTxnDataId() );

	        ps.executeUpdate();
	        conn.commit();
	        
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when updating ReferenceData. cacheKey = " + oValue.getExternalTxnRefId(), ex);	        
        } finally {
        	closeConnection(conn, ps, null);
        }
        
        logger.debug( "--ESDG-- updated SALES_CONTEXT_TXN_DATA: " + oValue.getLogInfo());

	}

	/**
	 * Search EsdgReferenceData by cache key.
	 *
	 * @param partitionCd the cluster partition code
	 * @param cacheKey
	 * @return the txn Id if the record exists 
	 */
	private Integer getReferenceDataTxnId( String partitionCd, String cacheKey ) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		
        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();
        	ps = conn.prepareStatement( EsdgReferenceSQLConstants.SELECT_ID_SALES_CONTEXT_TXN_DATA_STMT );
        	
        	int idx = 1;
	        ps.setString( idx++, partitionCd);
	        ps.setString( idx++, cacheKey);
	        
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, -15);
	        
	        ps.setTimestamp( idx++, getTimestamp(cal.getTime()), gmtCal );

	        rs = ps.executeQuery();
	        
	        if ( rs.next() ) {
	        	return rs.getInt("SALES_CONTEXT_TXN_DATA_ID");        	
	        }
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);        	
        	logger.error("--ESDG-- Exception caught when querying existence of ReferenceData. partitionCd = " + partitionCd + "cacheKey = " + cacheKey, ex);            
	        
        } finally {	        
        	closeConnection(conn, ps, rs);
        }
        
        return null;
	}

	private Timestamp getTimestamp(Date parm) {
		if (parm == null)
			return null;
		
		return new Timestamp(parm.getTime());
	}

	
	/**
	 * Close connection.
	 *
	 * @param conn the conn
	 * @param ps the ps
	 * @param rs the rs
	 */
	private void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			logger.error("--ESDG-- Exception caught when closing connection to database.", ex);
		}
	}

}