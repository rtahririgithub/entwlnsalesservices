package com.telus.csm.esdg.database;

import static com.telus.csm.esdg.database.EsdgDatabaseAdapter_1.BATCH_ORDER_LOOK_UP_PERIOD;
import static com.telus.csm.esdg.database.EsdgDatabaseAdapter_1.CONTEXT_STATUS_CD_OPEN;
import static com.telus.csm.esdg.database.EsdgDatabaseAdapter_1.INSERT_SALES_INTERACTION_LIFECYCLE_STMT;
import static com.telus.csm.esdg.database.EsdgDatabaseAdapter_1.SELECT_SALES_INTERACTION_SEQ_NEXTVAL_STMT;
import static com.telus.csm.esdg.database.EsdgDatabaseAdapter_1.getBackTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.esdg.domain.EsdgInteractionDO_1;
import com.telus.csm.esdg.domain.EsdgInteractionKeyValueDO;
import com.telus.csm.esdg.domain.EsdgSalesOrderLifeCycleDO;

/**
 * DAO class for persisting / retrieving InteractionDO from / to tables:
 *  
 * SALES_INTRACTN 
 * SALES_INTRACTN_LIFECYCL
 * SALES_INTRACTN_TXN_DATA 
 * 
 */
public class InteractionDao extends EsdgDao {
	
	private static Logger logger = Logger.getLogger(InteractionDao.class);
	private static final int LOOK_UP_PERIOD = 90;
	
	/** The Constant INSERT_SALES_INTERACTION_STMT. */
	private static final String INSERT_SALES_INTERACTION_STMT = "INSERT INTO SALES_INTRACTN ("
			+ "SALES_INTRACTN_ID, INTRACTN_START_TS, SALES_INTRACTN_TYPE_CD, CUST_BILLING_ACCOUNT_NUM, CUST_CNTCT_PHONE_NUM, EXTERNAL_SALES_REF_ID, "
			+ "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ "VALUES (?,?,?,?,?,?,?, SYSDATE,?,SYSDATE)";

	/** The Constant INSERT_SALES_CONTEXT_RAW_DATA_STMT. */
	private static final String INSERT_SALES_INTERACTION_RAW_DATA_STMT = "INSERT INTO SALES_INTRACTN_TXN_DATA ("
            + "SALES_INTRACTN_TXN_DATA_ID, SALES_INTRACTN_ID,"
            + "INTRACTN_START_TS, SALES_TXN_TS, EXTERNAL_TXN_REF_ID, SALES_TXN_DATA,"
            + "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
            + "VALUES (SALES_INTRACTN_TXN_DATA_SEQ.NEXTVAL,?,?,?,?,?,?,SYSDATE,?,SYSDATE)";
	
	
	private static final String SELECT_LATEST_INTERACTION_STATUS_STMT = "SELECT T1.SALES_INTRACTN_ID,T1.SALES_INTRACTN_TYPE_CD, T1.INTRACTN_START_TS, T1.EXTERNAL_SALES_REF_ID,  "
			+ "  T1.CUST_BILLING_ACCOUNT_NUM, T1.CUST_CNTCT_PHONE_NUM, T1.CREATE_USER_ID,  "
			+ "  T2.SALES_INTRACTN_LIFECYCL_ID, T2.CHANNEL_ORG_TYP_CD, T2.CHANNEL_ORGANIZATION_ID, T2.CHANNEL_PERSON_ID, T2.CHANNEL_PERSON_TYP_CD,  "
			+ "  T2.INTRACTN_STATUS_CD, T2.EFFECTIVE_START_TS   "
			+ "FROM SALES_INTRACTN T1, SALES_INTRACTN_LIFECYCL T2   "
			+ "WHERE T1.SALES_INTRACTN_ID = T2.SALES_INTRACTN_ID   "
			+ "AND T1.INTRACTN_START_TS = T2.INTRACTN_START_TS  "
			+ "AND T2.EFFECTIVE_START_TS = (SELECT MAX(EFFECTIVE_START_TS) FROM SALES_INTRACTN_LIFECYCL d2 WHERE d2.SALES_INTRACTN_ID = T1.SALES_INTRACTN_ID AND d2.INTRACTN_START_TS = t1.INTRACTN_START_TS ) "
			+ "AND T1.SALES_INTRACTN_ID = ? "
			+ "AND t1.INTRACTN_START_TS >= ? "
			;
	
	private static final String SELECT_LATEST_INTERACTIO_RAW_DATA_STMT = "SELECT SALES_INTRACTN_TXN_DATA_ID,EXTERNAL_TXN_REF_ID, SALES_TXN_DATA, SALES_TXN_TS   "
			+ "FROM SALES_INTRACTN_TXN_DATA  "
			+ "WHERE SALES_INTRACTN_ID = ?  "
			+ "AND SALES_TXN_TS = (SELECT MAX(SALES_TXN_TS) FROM SALES_INTRACTN_TXN_DATA d2 WHERE d2.SALES_INTRACTN_ID = ? AND d2.INTRACTN_START_TS >= ? ) "
			+ "AND INTRACTN_START_TS >= ? "
			;
	
	
	private static final String SELECT_LATEST_INTERACTION_WHOLE = "SELECT T1.SALES_INTRACTN_ID,T1.SALES_INTRACTN_TYPE_CD, T1.INTRACTN_START_TS, T1.EXTERNAL_SALES_REF_ID,  "
			+ "  T1.CUST_BILLING_ACCOUNT_NUM, T1.CUST_CNTCT_PHONE_NUM, T1.CREATE_USER_ID,  "
			+ "  T2.SALES_INTRACTN_LIFECYCL_ID, T2.CHANNEL_ORG_TYP_CD, T2.CHANNEL_ORGANIZATION_ID, T2.CHANNEL_PERSON_ID, T2.CHANNEL_PERSON_TYP_CD,  "
			+ "  T2.INTRACTN_STATUS_CD, T2.EFFECTIVE_START_TS  "
			+ "  ,T3.SALES_INTRACTN_TXN_DATA_ID,T3.EXTERNAL_TXN_REF_ID, T3.SALES_TXN_DATA, T3.SALES_TXN_TS "
			+ "FROM SALES_INTRACTN T1 "
			+ "JOIN  SALES_INTRACTN_LIFECYCL T2 ON ( "
			+ "    T1.SALES_INTRACTN_ID = T2.SALES_INTRACTN_ID AND T1.INTRACTN_START_TS = T2.INTRACTN_START_TS  "
			+ "    AND T2.EFFECTIVE_START_TS = (SELECT MAX(EFFECTIVE_START_TS) FROM SALES_INTRACTN_LIFECYCL d1 WHERE d1.SALES_INTRACTN_ID=T1.SALES_INTRACTN_ID AND d1.INTRACTN_START_TS = t1.INTRACTN_START_TS ) ) "
			+ "LEFT OUTER JOIN SALES_INTRACTN_TXN_DATA T3  ON ( "
			+ "    T1.SALES_INTRACTN_ID = T3.SALES_INTRACTN_ID AND T1.INTRACTN_START_TS = T3.INTRACTN_START_TS  "
			+ "    AND T3.SALES_TXN_TS = (SELECT MAX(SALES_TXN_TS) FROM SALES_INTRACTN_TXN_DATA d2 WHERE d2.SALES_INTRACTN_ID=T1.SALES_INTRACTN_ID AND d2.INTRACTN_START_TS =t1.INTRACTN_START_TS ) ) "
			+ "WHERE   "
//REMOVE 15 days constraint			+ " T1.SALES_INTRACTN_ID=?  AND t1.INTRACTN_START_TS>=? "
			+ " T1.SALES_INTRACTN_ID=?  "
			;

	private static final String UPDATE_SALES_INTERACTION_STMT = "UPDATE SALES_INTRACTN SET  "
			+ "SALES_INTRACTN_TYPE_CD=?, CUST_BILLING_ACCOUNT_NUM=?, CUST_CNTCT_PHONE_NUM=?, EXTERNAL_SALES_REF_ID=?, "
			+ "LAST_UPDT_USER_ID=?, LAST_UPDT_TS=SYSDATE "
			+ "WHERE SALES_INTRACTN_ID = ?  ";

	private static final String MERGE_SALES_INTERACTION_KEYVALUE_STMT = "MERGE into SALESORDERADM.SALES_INTRACTN_KEYVALUE kv1"
			+ "  USING (select ? as SALES_INTRACTN_ID, ? as KEYNAME, ? as LOOKUP_DATE from dual) kv2"
			+ "  ON (kv1.SALES_INTRACTN_ID = kv2.SALES_INTRACTN_ID AND kv1.KEYNAME = kv2.KEYNAME AND kv1.INTRACTN_START_TS > kv2.LOOKUP_DATE )"
			+ "  WHEN MATCHED THEN UPDATE SET VALUEDATA = ?, LAST_UPDT_USER_ID = ?, LAST_UPDT_TS = SYSDATE"
			+ "  WHEN NOT MATCHED THEN INSERT (SALES_INTRACTN_KEYVALUE_ID, SALES_INTRACTN_ID, INTRACTN_START_TS, KEYNAME, VALUEDATA, CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS)"
			+ "  	values (SALESORDERADM.SALES_INTRACTN_KEYVALUE_SEQ.NEXTVAL, kv2.SALES_INTRACTN_ID, (select INTRACTN_START_TS from SALESORDERADM.SALES_INTRACTN where SALES_INTRACTN_ID = kv2.SALES_INTRACTN_ID and INTRACTN_START_TS > kv2.LOOKUP_DATE), kv2.KEYNAME, ?, ?, SYSDATE, ?, SYSDATE)";
	
	private static final String SELECT_SALES_INTRACTN_KEYVALUE_STMT = "SELECT SALES_INTRACTN_KEYVALUE_ID, SALES_INTRACTN_ID, INTRACTN_START_TS, KEYNAME, VALUEDATA from  SALESORDERADM.SALES_INTRACTN_KEYVALUE"
			+ "  WHERE SALES_INTRACTN_ID = ? AND KEYNAME = ? AND INTRACTN_START_TS > ?";

	private static final String SELECT_SALES_INTRACTN_ID_BY_KEYNAME_STMT = "SELECT SALES_INTRACTN_ID from SALESORDERADM.SALES_INTRACTN_KEYVALUE WHERE KEYNAME = ?";
	
	private static final String SELECT_SALES_ORDER_LIFE_CYCLE_STMT = "SELECT B.SALES_CONTEXT_ID, C.SALES_ORDER_STATUS_CD, C.EFFECTIVE_START_TS"
			+ " FROM SALES_CONTEXT A, SALES_ORDER B, SALES_ORDER_LIFECYCL C"
			+ " WHERE A.SALES_INTRACTN_ID=? AND A.SALES_CONTEXT_ID=B.SALES_CONTEXT_ID AND B.SALES_ORDER_ID=C.SALES_ORDER_ID"
			+ " ORDER BY B.SALES_CONTEXT_ID, C.SALES_ORDER_LIFECYCL_ID"; 
	    
	public InteractionDao( Connection conn, String dbUser ) {
		super ( conn, dbUser );
	}

	
	private String getNewInteractionId( String externalRefId ) throws Exception {
		
        PreparedStatement ps = null;
        ResultSet rs = null;
        
    	//get next sequence for interaction
        try {
			ps = getConnection().prepareStatement(SELECT_SALES_INTERACTION_SEQ_NEXTVAL_STMT);
	    	rs = ps.executeQuery();        	
	        
	    	if ( !rs.next() ) {
	        	String errorMsg = "Failed to get next sequence for interaction in database. externalRefId = " + externalRefId;
	            logger.error( "--ESDG-- " + errorMsg);
	            throw new Exception(errorMsg);
	        }
	        return rs.getString(1);
	        
        } finally {
        	if( ps != null ) {ps.close(); ps = null;}
			if( rs != null ) {rs.close(); rs = null;} 
        }
	}

	public void saveInteraction( EsdgInteractionDO_1 interactionDO) throws Exception {
		
		EsdgInteractionDO_1 latestDOFromDb = null;
		
		if ( interactionDO.getSalesInteractionId()==null ) {
			String interactionId  = getNewInteractionId( interactionDO.getExternalSalesRefId() );
			interactionDO.setSalesInteractionId( interactionId );
			//initialize this partition key 
			interactionDO.setSalesInteractionStartTimeInMills( System.currentTimeMillis() );
		} else {
			latestDOFromDb = selectInteraction( interactionDO.getSalesInteractionId() );
		}
		
		if ( latestDOFromDb==null ) {
			
			//for brand new interaction, do insert in three tables 
			insertInteraction ( interactionDO );
			insertInteractionLifecycle ( interactionDO );
			//insertInteractionRawData ( interactionDO );
			
		} else {
			
			//existing interaction
			
			//always sync-up this attribute with database, this is partition key for all three table
			interactionDO.setSalesInteractionStartTimeInMills( latestDOFromDb.getSalesInteractionStartTimeInMills() );
			
			if ( isInteractionChanged ( interactionDO, latestDOFromDb ) && !interactionDO.isSkipUpdateInteration()/*fix QC74387*/) {
				updateInteraction ( interactionDO );
			}
			
			if ( isLifecycleInfoChanged ( interactionDO, latestDOFromDb ) ) {
				insertInteractionLifecycle ( interactionDO );
			}
			
			if ( isRawDataChanged(interactionDO, latestDOFromDb) ) {
				insertInteractionRawData( interactionDO );
			}
		}
	}

	public static boolean isInteractionChanged( EsdgInteractionDO_1 interactionDO,	EsdgInteractionDO_1 interactionDO2) {
		
		boolean isSame = 
				ObjectUtils.equals(interactionDO.getExternalSalesRefId(), interactionDO2.getExternalSalesRefId() )
				&& ObjectUtils.equals( interactionDO.getBillingAccountNum(), interactionDO2.getBillingAccountNum() )
				&& ObjectUtils.equals( interactionDO.getContactPhoneNum(), interactionDO2.getContactPhoneNum() )
				;
		
		if (logger.isDebugEnabled()) logger.debug("--ESDG-- salesInteraction(" + interactionDO.getSalesInteractionId() + ") main record are same:" + isSame);
		
		return ! isSame;
	}
	
	public static boolean isLifecycleInfoChanged(EsdgInteractionDO_1 interactionDO, EsdgInteractionDO_1 interactionDO2) {
		
		boolean isSame = 
				ObjectUtils.equals(interactionDO.getStatus(), interactionDO2.getStatus() )
				&& ObjectUtils.equals(interactionDO.getChannelOrgID(), interactionDO2.getChannelOrgID() )
				&& ObjectUtils.equals( interactionDO.getChannelOrgTypeCD(), interactionDO2.getChannelOrgTypeCD() )
				&& ObjectUtils.equals( interactionDO.getChannelPersonID(), interactionDO2.getChannelPersonID() )
				&& ObjectUtils.equals( interactionDO.getChannelPersonTypeCD(), interactionDO2.getChannelPersonTypeCD() )
				;

		if (logger.isDebugEnabled()) logger.debug("--ESDG-- salesInteraction(" + interactionDO.getSalesInteractionId() + ") lifecycle record are same:" + isSame);
		
		return ! isSame;
	}
	
	public static boolean isRawDataChanged(EsdgInteractionDO_1 interactionDO, EsdgInteractionDO_1 interactionDO2) {
		
		boolean isSame = ObjectUtils.equals( interactionDO.getJsonSalesTXNData(), interactionDO2.getJsonSalesTXNData() );

		if (logger.isDebugEnabled()) logger.debug("--ESDG-- salesInteraction(" + interactionDO.getSalesInteractionId() + ") raw txn data record are same:" + isSame);
		
		return !isSame;
	}

	private void insertInteraction( EsdgInteractionDO_1 interactionDO ) throws SQLException {
		
        PreparedStatement ps = null;

        try {
	        ps = getConnection().prepareStatement( INSERT_SALES_INTERACTION_STMT );  
	        int idx = 1;
	        ps.setString( idx++, interactionDO.getSalesInteractionId() );
			ps.setTimestamp( idx++, new Timestamp(interactionDO.getSalesInteractionStartTimeInMills())); //partition key 

			ps.setString( idx++, interactionDO.getInteractionTypeCD() );
	        ps.setString( idx++, interactionDO.getBillingAccountNum() );
	        ps.setString( idx++, interactionDO.getContactPhoneNum() );  
	        ps.setString( idx++, getExnteralId ( interactionDO.getExternalSalesRefId() , 100)  ); //external reference key max length    
	        ps.setString( idx++, getDbUser() );
	        ps.setString( idx++, getDbUser() );
	        ps.executeUpdate(); 
	        
        } finally {
	        if( ps != null ) {ps.close(); ps = null;}
        }
	}
	
	private void updateInteraction(EsdgInteractionDO_1 interactionDO) throws SQLException {
        
		PreparedStatement ps = null;
        
        try {
	        
	        ps = getConnection().prepareStatement( UPDATE_SALES_INTERACTION_STMT );  
	        int idx = 1;
	        ps.setString( idx++, interactionDO.getInteractionTypeCD() );
	        ps.setString( idx++, interactionDO.getBillingAccountNum() );
	        ps.setString( idx++, interactionDO.getContactPhoneNum() );    
	        ps.setString( idx++, getExnteralId ( interactionDO.getExternalSalesRefId() , 100) ); //external reference key max length    
	        ps.setString( idx++, getDbUser() );
	        ps.setString( idx++, interactionDO.getSalesInteractionId() );
	        ps.executeUpdate(); 
	        
        } finally {
	        if( ps != null ) {ps.close(); ps = null;}
        }
	}
	
	private void insertInteractionLifecycle( EsdgInteractionDO_1 interactionDO ) throws SQLException {
		
		PreparedStatement ps = null;
		
        try {
        	ps = getConnection().prepareStatement(INSERT_SALES_INTERACTION_LIFECYCLE_STMT);
        	
        	String status = interactionDO.getStatus()==null ? CONTEXT_STATUS_CD_OPEN : interactionDO.getStatus();
        	
			int idx = 1;
			ps.setString( idx++, interactionDO.getSalesInteractionId() );                  
			ps.setTimestamp( idx++, new Timestamp(interactionDO.getSalesInteractionStartTimeInMills())); //partition key 
			
			ps.setString( idx++, interactionDO.getChannelOrgTypeCD() );
			ps.setString( idx++, interactionDO.getChannelOrgID() ) ;
			ps.setString( idx++, interactionDO.getChannelPersonTypeCD() );
			ps.setString( idx++, interactionDO.getChannelPersonID() );
			ps.setString( idx++, status );
			ps.setTimestamp( idx++, new Timestamp( System.currentTimeMillis()) ); //EFFECTIVE_START_TS  
			ps.setString( idx++, getDbUser() );
			ps.setString( idx++, getDbUser() );
			ps.executeUpdate();
		
        } finally {
	        if( ps != null ) {ps.close(); ps = null;}
        }
	}
	
	private void insertInteractionRawData( EsdgInteractionDO_1 interactionDO ) throws SQLException {
		
		if ( StringUtils.isBlank( interactionDO.getJsonSalesTXNData() ) ) {
			return;
		}
		
		PreparedStatement ps = null;  
        try {
        	ps = getConnection().prepareStatement(INSERT_SALES_INTERACTION_RAW_DATA_STMT);
			int idx = 1;
			ps.setString( idx++, interactionDO.getSalesInteractionId());                  
			ps.setTimestamp( idx++, new Timestamp(interactionDO.getSalesInteractionStartTimeInMills())); //partition key
			
			ps.setTimestamp( idx++, new Timestamp( System.currentTimeMillis() ) );  //SALES_TXN_TS
	        ps.setString( idx++, getExnteralId ( interactionDO.getExternalTxnRefID() , 100) );  //external reference key max length    
	        ps.setBytes( idx++, SerializationUtils.serialize(interactionDO.getJsonSalesTXNData()) );  
	        ps.setString( idx++, getDbUser() );
	        ps.setString( idx++, getDbUser() );
	        ps.executeUpdate();
	        
        } finally {
	        if( ps != null ) {ps.close(); ps = null;}
        }
	}
	
	
	public EsdgInteractionDO_1 selectInteractionTwoStep( String salesInteractionId )  throws SQLException  {
		
		EsdgInteractionDO_1 interactionDO = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	ps = getConnection().prepareStatement(SELECT_LATEST_INTERACTION_STATUS_STMT);
        	
	        Timestamp startTs = getBackTime(BATCH_ORDER_LOOK_UP_PERIOD);
        	int idx = 1;
	        ps.setString( idx++, salesInteractionId);
	        ps.setTimestamp( idx++, startTs );
	        

	        rs = ps.executeQuery();
	        
	        if ( rs.next() ) {
	        	interactionDO = new EsdgInteractionDO_1();
	        	
	        	mapInteractionAndLifecycle(salesInteractionId, rs,
						interactionDO);
	        	
	    		if (logger.isDebugEnabled()) logger.debug("interaction record found: interactionId=" + salesInteractionId + ", lifecycleId=" +  rs.getString("SALES_INTRACTN_LIFECYCL_ID") );
	    		
	        	selectInteractionRawData( interactionDO );
	        	
	        } else {
	        	logger.warn( "--ESDG-- salesInteraction not found in database. salesInteractionId = " + salesInteractionId );
	        }
	        
        } finally {
        	if( rs != null ) {rs.close(); rs = null;}
	        if( ps != null ) {ps.close(); ps = null;}
        }

        return interactionDO; 
	}

	private void selectInteractionRawData(EsdgInteractionDO_1 interactionDO)  throws SQLException  {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = getConnection().prepareStatement(SELECT_LATEST_INTERACTIO_RAW_DATA_STMT);
			
			int idx=1;
	        ps.setString( idx++, interactionDO.getSalesInteractionId() );
	        ps.setString( idx++, interactionDO.getSalesInteractionId() );

	        Timestamp startTs = getBackTime(BATCH_ORDER_LOOK_UP_PERIOD);
	        ps.setTimestamp( idx++, startTs );
	        ps.setTimestamp( idx++, startTs );
	        
	        rs = ps.executeQuery();
	        
	        if ( rs.next() ) {
	        	
	       		mapRawData(rs, interactionDO);
	       		
	    		if (logger.isDebugEnabled()) logger.debug("interaction raw record found: interactionId=" + interactionDO.getSalesInteractionId() + ", .txnDataId=" +  rs.getString("SALES_INTRACTN_TXN_DATA_ID") );
	        	
	        } else {
	        	
	        	if (logger.isDebugEnabled() ) logger.debug( "--ESDG-- salesInteractionRaw not found in database. salesInteractionId = " + interactionDO.getSalesInteractionId() );
	        }
	        
        } finally {
        	if( rs != null ) {rs.close(); rs = null;}
	        if( ps != null ) {ps.close(); ps = null;}
        }
	}
	
	public EsdgInteractionDO_1 selectInteraction( String salesInteractionId )  throws SQLException  {
		
		EsdgInteractionDO_1 interactionDO = null;
		
		String txnDataId =  null;
		String lifecycleId = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		long startTime = System.currentTimeMillis();

        try {
        	ps = getConnection().prepareStatement(SELECT_LATEST_INTERACTION_WHOLE);

 	        //Timestamp startTs = getBackTime(BATCH_ORDER_LOOK_UP_PERIOD);
        	int idx = 1;
	        ps.setString( idx++, salesInteractionId);
	        //ps.setTimestamp( idx++, startTs );

	        rs = ps.executeQuery();
	        
	        if ( rs.next() ) {
	        	
	        	interactionDO = new EsdgInteractionDO_1();
	        	
	        	mapInteractionAndLifecycle(salesInteractionId, rs,	interactionDO);
	    		
    			lifecycleId = rs.getString("SALES_INTRACTN_LIFECYCL_ID");
	    		txnDataId = rs.getString("SALES_INTRACTN_TXN_DATA_ID");
	    		
	    		if ( txnDataId!=null ) {
		       		mapRawData(rs, interactionDO);        	
	    		}
	        	
	        } else {
	        	logger.warn( "--ESDG-- salesInteraction not found in database. salesInteractionId = " + salesInteractionId );
	        }
	        
        } finally {
	        
    		long elapsedTime  = System.currentTimeMillis()-startTime;
    		
        	if (logger.isDebugEnabled()) {
	        	
				logger.debug("--ESDG-- query salesInteraction by id : interactionId=" + salesInteractionId 
    				+ ", lifecycleId=" +  lifecycleId 
    				+ ", txnDataId=" + txnDataId
    				+ "; elapsedTime=" + elapsedTime );
			}

        	if( rs != null ) {rs.close(); rs = null;}
	        if( ps != null ) {ps.close(); ps = null;}
        }

        return interactionDO; 
	}


	private void mapRawData(ResultSet rs, EsdgInteractionDO_1 interactionDO) throws SQLException {
		
		interactionDO.setExternalTxnRefID( rs.getString("EXTERNAL_TXN_REF_ID") );            	
		
		//if raw data timestamp is newer, then use it as data generation ts
		long rawDataTs = rs.getTimestamp("SALES_TXN_TS").getTime();
		if ( rawDataTs > interactionDO.getDataGenerationTimeInMills() ) {
			interactionDO.setDataGenerationTimeInMills( rawDataTs );
		}
		
		byte[] bytes = rs.getBytes("SALES_TXN_DATA");
		if( bytes != null && bytes.length > 0 ) {    		             		    	
			interactionDO.setJsonSalesTXNData( (String) SerializationUtils.deserialize(bytes) ); 
		}
	}


	private void mapInteractionAndLifecycle(String salesInteractionId,	ResultSet rs, EsdgInteractionDO_1 interactionDO) throws SQLException {
		
		//interaction table
		interactionDO.setSalesInteractionId( salesInteractionId );
		interactionDO.setInteractionTypeCD( rs.getString("SALES_INTRACTN_TYPE_CD") );
		interactionDO.setExternalSalesRefId( rs.getString("EXTERNAL_SALES_REF_ID") );
		interactionDO.setBillingAccountNum( rs.getString("CUST_BILLING_ACCOUNT_NUM") );
		interactionDO.setContactPhoneNum( rs.getString("CUST_CNTCT_PHONE_NUM") );
		interactionDO.setSalesInteractionStartTimeInMills( rs.getTimestamp("INTRACTN_START_TS").getTime() );
		//interactionDO.setCreateUserID( rs.getString("CREATE_USER_ID"));
		
		//lifecycle table
		interactionDO.setChannelOrgTypeCD( rs.getString("CHANNEL_ORG_TYP_CD") );
		interactionDO.setChannelOrgID( rs.getString("CHANNEL_ORGANIZATION_ID") );
		interactionDO.setChannelPersonID( rs.getString("CHANNEL_PERSON_ID") );
		interactionDO.setChannelPersonTypeCD( rs.getString("CHANNEL_PERSON_TYP_CD") );
		interactionDO.setStatus( rs.getString("INTRACTN_STATUS_CD") );
		interactionDO.setDataGenerationTimeInMills( rs.getTimestamp("EFFECTIVE_START_TS").getTime() );
	}


	public void upsertSalesInteractionKeyValue(EsdgInteractionKeyValueDO keyValueDO) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();
		boolean success = false;
		try {
			ps = getConnection().prepareStatement(MERGE_SALES_INTERACTION_KEYVALUE_STMT);
			int idx = 1;
			ps.setString(idx++, keyValueDO.getSalesInteractionId());//SALES_INTRACTN_ID
			ps.setString(idx++, keyValueDO.getKeyName());//KEYNAME
			ps.setTimestamp(idx++, EsdgDatabaseAdapter_1.getBackTime(LOOK_UP_PERIOD));//LOOKUP_DATE
			ps.setString(idx++, keyValueDO.getValueData());//VALUEDATA
			ps.setString(idx++, getDbUser());//LAST_UPDT_USER_ID

			ps.setString(idx++, keyValueDO.getValueData());//VALUEDATA
			ps.setString(idx++, getDbUser());//CREATE_USER_ID
			ps.setString(idx++, getDbUser());//LAST_UPDT_USER_ID
		
			success = ps.executeUpdate() == 1;			
		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if(success) {
				if (logger.isDebugEnabled()) {
					logger.debug("--ESDG-- Merge to SALES_INTERACTION_KEYVALUE is a success. SalesInteractionId = " + keyValueDO.getSalesInteractionId() + ", KeyName = " + keyValueDO.getKeyName() + "; elapsedTime=" + elapsedTime);
				}
			} else {
				logger.warn("--ESDG-- Merge to SALES_INTERACTION_KEYVALUE has failed. SalesInteractionId = " + keyValueDO.getSalesInteractionId() + ", KeyName = " + keyValueDO.getKeyName() + "; elapsedTime=" + elapsedTime);
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


	public EsdgInteractionKeyValueDO selectSalesInteractionKeyValue(String salesInteractionId, String keyName) throws SQLException  {
		EsdgInteractionKeyValueDO result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();

		try {
			ps = getConnection().prepareStatement(SELECT_SALES_INTRACTN_KEYVALUE_STMT);
			int idx = 1;
			ps.setString(idx++, salesInteractionId);
			ps.setString(idx++, keyName);
			ps.setTimestamp(idx++, EsdgDatabaseAdapter_1.getBackTime(LOOK_UP_PERIOD));
			rs = ps.executeQuery();

			if (rs.next()) {
				result = mapEsdgInteractionKeyValueDO(rs);
			} else {
				logger.warn("--ESDG-- SalesInteractionKeyValue is not found in database.  SalesInteractionId = " + salesInteractionId + ", KeyName = " + keyName);
			}

		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query SalesInteractionKeyValue by SalesInteractionId = " + salesInteractionId + ", KeyName = " + keyName + "; elapsedTime=" + elapsedTime);
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


	private EsdgInteractionKeyValueDO mapEsdgInteractionKeyValueDO(ResultSet rs) throws SQLException {
		EsdgInteractionKeyValueDO ret = new EsdgInteractionKeyValueDO();
		ret.setSalesInteractionKeyValueId(rs.getString("SALES_INTRACTN_KEYVALUE_ID"));
		ret.setSalesInteractionId(rs.getString("SALES_INTRACTN_ID"));
		ret.setSalesInteractionStartTimeInMills(rs.getTimestamp("INTRACTN_START_TS").getTime());
		ret.setKeyName(rs.getString("KEYNAME"));
		ret.setValueData(rs.getString("VALUEDATA"));
		return ret;
	}


	public Set<String> searchInteractionIdByKeyName(String keyName) throws SQLException {
		Set<String> result = new HashSet<String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();

		try {
			ps = getConnection().prepareStatement(SELECT_SALES_INTRACTN_ID_BY_KEYNAME_STMT);
			int idx = 1;
			ps.setString(idx++, keyName);
			rs = ps.executeQuery();
			
            while ( rs.next() ) {
            	String orderId = rs.getString(1);
            	result.add(orderId);
            }

		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query SalesInteractionKeyValue by KeyName = " + keyName + "; elapsedTime=" + elapsedTime);
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
	
	public List<EsdgSalesOrderLifeCycleDO> selectSalesOrderLifeCycle(String salesInteractionId) throws SQLException  {
		List<EsdgSalesOrderLifeCycleDO> result = new ArrayList<EsdgSalesOrderLifeCycleDO>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();

		try {
			ps = getConnection().prepareStatement(SELECT_SALES_ORDER_LIFE_CYCLE_STMT);
			int idx = 1;
			ps.setString(idx++, salesInteractionId);
			rs = ps.executeQuery();

            while ( rs.next() ) {
            	result.add(mapEsdgSalesOrderLifeCycleDO(rs));
            }

			if (result.isEmpty() ) {
				logger.warn("--ESDG-- SalesOrderLifeCycle is not found in database.  SalesInteractionId = " + salesInteractionId);
			}

		} finally {
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("--ESDG-- query SalesOrderLifeCycle by SalesInteractionId = " + salesInteractionId + "; elapsedTime=" + elapsedTime);
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

	private EsdgSalesOrderLifeCycleDO mapEsdgSalesOrderLifeCycleDO(ResultSet rs) throws SQLException {
		
		EsdgSalesOrderLifeCycleDO result = new EsdgSalesOrderLifeCycleDO();
		
		result.setSalesContextId(rs.getString("SALES_CONTEXT_ID"));
		result.setSalesOrderStatusCd(rs.getString("SALES_ORDER_STATUS_CD"));
		result.setLastUpdtTs(rs.getTimestamp("EFFECTIVE_START_TS"));
		
		return result;
	}





}
