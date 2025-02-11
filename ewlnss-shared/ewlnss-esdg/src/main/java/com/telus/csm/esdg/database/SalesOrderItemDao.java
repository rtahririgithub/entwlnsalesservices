package com.telus.csm.esdg.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;

import com.telus.csm.esdg.domain.EsdgSalesItemDO;

/**
 * DAO class for persisting / retrieving InteractionDO from / to tables:
 *  
 * SALES_ORDER_ITEM 
 * SALES_ORDER_ITEM_TXN_DATA
 * 
 */
public class SalesOrderItemDao extends EsdgDao {
	
	private static Logger logger = Logger.getLogger(SalesOrderItemDao.class);
	
	private static final String SELECT_SALES_ITEM_SEQ_NEXTVAL_STMT = "SELECT SALES_ORDER_ITEM_SEQ.NEXTVAL FROM DUAL";
	
	private static final String INSERT_SALES_ITEM_STMT = "INSERT INTO SALES_ORDER_ITEM ("
			+ "SALES_ORDER_ITEM_ID, SALES_CONTEXT_ID, INTRACTN_START_TS, SALES_ORDER_ITEM_TYPE, SALES_ORDER_ITEM_STATUS_CD, STATUS_EFFECTIVE_START_TS, EXTERNAL_TRANSACTION_REFERENCE, "
			+ "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ "VALUES (?,?,?,?,?,?,?,?, SYSDATE,?,SYSDATE)";

	private static final String INSERT_SALES_ITEM_RAW_DATA_STMT = "INSERT INTO SALES_ORDER_ITEM_TXN_DATA ("
            + "SALES_ORDER_ITEM_TXN_DATA_ID, SALES_ORDER_ITEM_ID,"
            + "INTRACTN_START_TS, SALES_ORDER_ITEM_DATA,"
            + "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
            + "VALUES (SALES_INTRACTN_TXN_DATA_SEQ.NEXTVAL,?,?,?,?,SYSDATE,?,SYSDATE)";
	
	private static final String SELECT_SALES_ITEM_STMT = "SELECT T1.SALES_ORDER_ITEM_ID, T1.SALES_CONTEXT_ID, T1.INTRACTN_START_TS, T1.SALES_ORDER_ITEM_TYPE, T1.SALES_ORDER_ITEM_STATUS_CD, T1.EXTERNAL_TRANSACTION_REFERENCE, "
			+ " T2.SALES_ORDER_ITEM_TXN_DATA_ID, T2.SALES_ORDER_ITEM_DATA "
			+ " FROM SALES_ORDER_ITEM T1 "
			+ " JOIN SALES_ORDER_ITEM_TXN_DATA T2 ON T2.SALES_ORDER_ITEM_ID=T1.SALES_ORDER_ITEM_ID AND T2.INTRACTN_START_TS=T1.INTRACTN_START_TS  "
			+ " WHERE T1.SALES_ORDER_ITEM_ID = ? "
			;
	private static final String SELECT_SALES_ITEM_TXN_DATA_ID_STMT = "SELECT T2.SALES_ORDER_ITEM_TXN_DATA_ID FROM SALES_ORDER_ITEM_TXN_DATA T2 "
			+ "  JOIN SALES_ORDER_ITEM T1 ON T1.SALES_ORDER_ITEM_ID=T2.SALES_ORDER_ITEM_ID AND T1.INTRACTN_START_TS=T2.INTRACTN_START_TS  "
			+ " WHERE T1.SALES_ORDER_ITEM_ID=?"
			;
	
	private static final String SELECT_SALES_ITEM_IDS_STMT = "SELECT SALES_ORDER_ITEM_ID FROM SALES_ORDER_ITEM "
			+ "  WHERE SALES_CONTEXT_ID=? and INTRACTN_START_TS>sysdate-30 "
			;
	
	private static final String SELECT_SALES_ITEM_IDS_BY_TYPE_STMT = "SELECT SALES_ORDER_ITEM_ID FROM SALES_ORDER_ITEM "
			+ "  WHERE SALES_CONTEXT_ID=? AND SALES_ORDER_ITEM_TYPE=? and INTRACTN_START_TS>sysdate-30 "
			;
	
	private static final String UPDATE_SALES_ITEM_STMT = "UPDATE SALES_ORDER_ITEM SET  "
			+ "SALES_ORDER_ITEM_TYPE=?, SALES_ORDER_ITEM_STATUS_CD=?, SALES_CONTEXT_ID=?, EXTERNAL_TRANSACTION_REFERENCE=?, "
			+ "LAST_UPDT_USER_ID=?, LAST_UPDT_TS=SYSDATE "
			+ "WHERE SALES_ORDER_ITEM_ID = ?  "
			;

	private static final String UPDATE_SALES_ITEM_TXN_DATA_STMT = "UPDATE SALES_ORDER_ITEM_TXN_DATA SET  "
			+ "SALES_ORDER_ITEM_DATA=?,  "
			+ "LAST_UPDT_USER_ID=?, LAST_UPDT_TS=SYSDATE "
			+ "WHERE SALES_ORDER_ITEM_TXN_DATA_ID = ?  "
			;

	public SalesOrderItemDao( Connection conn, String dbUser ) {
		super ( conn, dbUser );
	}
	
	public String getNewSalesItemId( String salesOrderId ) throws Exception {
		
        PreparedStatement ps = null;
        ResultSet rs = null;
        
    	//get next sequence for interaction
        try {
        	
 			ps = getConnection().prepareStatement(SELECT_SALES_ITEM_SEQ_NEXTVAL_STMT);
	    	rs = ps.executeQuery();        	
	        
	    	if ( !rs.next() ) {
	        	String errorMsg = "Failed to get next sequence for interaction in database. externalRefId = " + salesOrderId;
	            logger.error( "--ESDG-- " + errorMsg);
	            throw new Exception(errorMsg);
	        } else {
	        	String newId  = rs.getString(1);
	        	if (logger.isDebugEnabled() ) { logger.debug( "--ESDG-- salesItemId is generated for salesOrder=" + salesOrderId + ", new id= " + newId ); } 
	        	return newId;
	        }
	        
        } finally {
        	if( ps != null ) {ps.close(); ps = null;}
			if( rs != null ) {rs.close(); rs = null;} 
        }
	}

	public void saveSalesItem( EsdgSalesItemDO salesItem ) throws Exception {
		
		String itemTxnDataId = getItemTxnDataIdByItemId( salesItem.getItemId() );
		if ( itemTxnDataId==null) {
        	logger.info( "--ESDG-- Cannot find SALES_ORDER_ITEM_TXN_DATA_ID for salesItemId = " + salesItem.getItemId() + ", do insert." );
        	
        	salesItem.setSalesInteractionStartTimeInMills( Calendar.getInstance().getTimeInMillis() ); // TODO: fix the partition key
        	insertSalesOrderItem( salesItem );
			insertSalesItemTnxData( salesItem );
		} else {
        	logger.info( "--ESDG-- Found SALES_ORDER_ITEM_TXN_DATA_ID for salesItemId = " + salesItem.getItemId() + ", do update." );
        	
			salesItem.setItemTxnDataId(itemTxnDataId);
			updateSalesItem(salesItem);
			updateSalesItemTxnData(salesItem);
		}
	}

	private void insertSalesOrderItem( EsdgSalesItemDO salesItem ) throws SQLException {
		
        PreparedStatement ps = null;

        try {
	        ps = getConnection().prepareStatement( INSERT_SALES_ITEM_STMT );  
	        int idx = 1;
	        ps.setString( idx++, salesItem.getItemId() );  //SALES_ORDER_ITEM_ID
	        ps.setString( idx++, salesItem.getSalesContextId() ); //SALES_CONTEXT_ID
	        
			Timestamp interactStartTime = new Timestamp( salesItem.getSalesInteractionStartTimeInMills() );
			ps.setTimestamp( idx++, interactStartTime ); //partition key 

			ps.setString( idx++, salesItem.getItemType() );  //SALES_ORDER_ITEM_TYPE
	        ps.setString( idx++, salesItem.getStatus() ); //SALES_ORDER_ITEM_STATUS_CD
	        ps.setTimestamp( idx++, interactStartTime );  //STATUS_EFFECTIVE_START_TS
	        ps.setString( idx++, salesItem.getExternalTxnReference()  ); //EXTERNAL_TRANSACTION_REFERENCE     
	        ps.setString( idx++, getDbUser() );
	        ps.setString( idx++, getDbUser() );
	        
	        ps.executeUpdate(); 
        } finally {
	        if( ps != null ) {ps.close(); ps = null;}
        }
	}
	
	private void insertSalesItemTnxData( EsdgSalesItemDO salesItem ) throws SQLException {
		
        PreparedStatement ps = null;

        try {
	        ps = getConnection().prepareStatement( INSERT_SALES_ITEM_RAW_DATA_STMT );  
	        int idx = 1;
	        ps.setString( idx++, salesItem.getItemId() );  //SALES_ORDER_ITEM_ID
			ps.setTimestamp( idx++, new Timestamp( salesItem.getSalesInteractionStartTimeInMills() ) ); //partition key : INTRACTN_START_TS

	        ps.setBytes( idx++, SerializationUtils.serialize(salesItem.getJsonSalesTXNData()) ); //SALES_ORDER_ITEM_DATA      
	        ps.setString( idx++, getDbUser() );
	        ps.setString( idx++, getDbUser() );
	        
	        ps.executeUpdate(); 
	        
        } finally {
	        if( ps != null ) {ps.close(); ps = null;}
        }
	}
	
	private void updateSalesItem(EsdgSalesItemDO salesItem) throws SQLException {
        
		PreparedStatement ps = null;
        
        try {
	        ps = getConnection().prepareStatement( UPDATE_SALES_ITEM_STMT );  
	        int idx = 1;
			ps.setString( idx++, salesItem.getItemType() );  //SALES_ORDER_ITEM_TYPE
	        ps.setString( idx++, salesItem.getStatus() ); //SALES_ORDER_ITEM_STATUS_CD
	        ps.setString( idx++, salesItem.getSalesContextId() ); //SALES_CONTEXT_ID
	        ps.setString( idx++, salesItem.getExternalTxnReference() ); //EXTERNAL_TRANSACTION_REFERENCE
	        ps.setString( idx++, getDbUser() );
	        
	        ps.setString( idx++, salesItem.getItemId() ); //SALES_ORDER_ITEM_ID
	        ps.executeUpdate(); 
	        
        } finally {
	        if( ps != null ) {ps.close(); ps = null;}
        }
	}
	
	private void updateSalesItemTxnData( EsdgSalesItemDO salesItem )  throws SQLException  {
		
		PreparedStatement ps = null;
        
        try {
	        ps = getConnection().prepareStatement( UPDATE_SALES_ITEM_TXN_DATA_STMT );
	        
	        int idx = 1;
	        ps.setBytes( idx++, SerializationUtils.serialize(salesItem.getJsonSalesTXNData()) ); //SALES_ORDER_ITEM_DATA      
	        ps.setString( idx++, getDbUser() );  //LAST_UPDT_USER_ID
	        ps.setString( idx++, salesItem.getItemTxnDataId() );  //SALES_ORDER_ITEM_TXN_DATA_ID
	        ps.executeUpdate(); 
	        
        } finally {
	        if( ps != null ) {ps.close(); ps = null;}
        }
    }


	public EsdgSalesItemDO selectSalesItemByItemId( String salesItemId ) throws SQLException  {
		
		EsdgSalesItemDO result = null;
		
		String txnDataId =  null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		long startTime = System.currentTimeMillis();

        try {
        	
        	ps = getConnection().prepareStatement( SELECT_SALES_ITEM_STMT );
        	
        	int idx = 1;
	        ps.setString( idx++, salesItemId);

	        rs = ps.executeQuery();
	        
	        if ( rs.next() ) {
	        	
	        	result = new EsdgSalesItemDO();
	        	
	        	mapSalesItem( salesItemId, rs,	result);
	        	
	        	txnDataId = result.getItemTxnDataId();
	        	
	        } else {
	        	logger.warn( "--ESDG-- salesItem not found in database. salesItemId = " + salesItemId );
	        }
	        
        } finally {
	        
    		long elapsedTime  = System.currentTimeMillis()-startTime;
    		
        	if (logger.isDebugEnabled()) {
	        	
				logger.debug("--ESDG-- query salesItem by id : salesItemId=" + salesItemId 
    				+ ", txnDataId=" + txnDataId
    				+ "; elapsedTime=" + elapsedTime );
			}

        	if( rs != null ) {rs.close(); rs = null;}
	        if( ps != null ) {ps.close(); ps = null;}
        }

        return result; 
	}
		
	private void mapSalesItem(String salesItemId, ResultSet rs,	EsdgSalesItemDO result)  throws SQLException  {
		
		result.setItemId( salesItemId );
		result.setSalesContextId( rs.getString("SALES_CONTEXT_ID") );
		result.setItemType( rs.getString("SALES_ORDER_ITEM_TYPE") );
		result.setStatus( rs.getString("SALES_ORDER_ITEM_STATUS_CD") );
		result.setExternalTxnReference( rs.getString("EXTERNAL_TRANSACTION_REFERENCE"));
		result.setSalesInteractionStartTimeInMills( rs.getTimestamp("INTRACTN_START_TS").getTime() );
		
		result.setItemTxnDataId( rs.getString("SALES_ORDER_ITEM_TXN_DATA_ID") );
		byte[] bytes = rs.getBytes("SALES_ORDER_ITEM_DATA");
		if( bytes != null && bytes.length > 0 ) {    		             		    	
			result.setJsonSalesTXNData( (String) SerializationUtils.deserialize(bytes) ); 
		}
	}

	private String getItemTxnDataIdByItemId(String itemId) throws SQLException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String txnDataId = null;
        try {
        	ps = getConnection().prepareStatement( SELECT_SALES_ITEM_TXN_DATA_ID_STMT );
        	
        	int idx = 1;
	        ps.setString( idx++, itemId);

	        rs = ps.executeQuery();
	        
	        if ( rs.next() ) {
	        	txnDataId = rs.getString( 1 );
	        }
	        
        } finally {
	        
        	if( rs != null ) {rs.close(); rs = null;}
	        if( ps != null ) {ps.close(); ps = null;}
        }
		return txnDataId;
	}

	public List<String> searchSalesItemIdBySalesOrderId(String salesOrderId,	String itemType)  throws SQLException  {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> itemIdList= new ArrayList<String> ();
        
		try {
			String sql = null;
	        if (itemType!=null) {
	        	sql = SELECT_SALES_ITEM_IDS_BY_TYPE_STMT ;
	        } else {
	        	sql = SELECT_SALES_ITEM_IDS_STMT ;
	        }
			logger.info("--ESDG-- search salesItem id for [salesOrderId="+ salesOrderId + ", type=" + itemType + "]; SQL: " + sql);
	        
        	ps = getConnection().prepareStatement( sql );
        	
        	int idx = 1;
	        ps.setString( idx++, salesOrderId);
	        if (itemType!=null) {
		        ps.setString( idx++, itemType);
	        }

	        rs = ps.executeQuery();
	        
	        while( rs.next() ) {
	        	itemIdList.add( rs.getString( 1 ) );
	        }
	        
        } finally {
	        
        	if( rs != null ) {rs.close(); rs = null;}
	        if( ps != null ) {ps.close(); ps = null;}
        }
		return itemIdList;
	}

}
