package com.telus.csm.esdg.database;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.esdg.EsdgConstants;
import com.telus.csm.esdg.domain.EsdgContextRawDataDO_1;
import com.telus.csm.esdg.domain.EsdgInteractionDO_1;
import com.telus.csm.esdg.domain.EsdgInteractionKeyValueDO;
import com.telus.csm.esdg.domain.EsdgOrderContractDO;
import com.telus.csm.esdg.domain.EsdgOrderContractDO_1;
import com.telus.csm.esdg.domain.EsdgOrderDO_1;
import com.telus.csm.esdg.domain.EsdgOrderFulfillmentStatusHistoryDO;
import com.telus.csm.esdg.domain.EsdgOrderStatusHistoryDO;
import com.telus.csm.esdg.domain.EsdgPaymentDO;
import com.telus.csm.esdg.domain.EsdgSalesItemDO;
import com.telus.csm.esdg.domain.EsdgSalesOrderLifeCycleDO;
import com.telus.csm.esdg.domain.EsdgShipmentInteractionDO;
import com.telus.csm.esdg.domain.EsdgSubscriberContextDO;


/**
 * The Class EsdgDatabaseAdapter_1.
 */
public class EsdgDatabaseAdapter_1 {

	/** The Constant ESS_VERSION_SUFFIX. */
	private static final String ESS_VERSION_SUFFIX = "_V5";
	//default look back 15 days when search a batch order
	public static final int BATCH_ORDER_LOOK_UP_PERIOD = 15;

	/** The logger. */
	static Logger logger = Logger.getLogger(EsdgDatabaseAdapter_1.class);

	/** The Constant CONTEXT_ID_PREFIX. */
	public static final String CONTEXT_ID_PREFIX = "ES";

	/** The Constant for Interaction Id PREFIX. */
	public static final String INTERACTION_ID_PREFIX = "SC";

	/** The Constant sales Item ID PREFIX. */
	public static final String SALES_ITEM_ID_PREFIX = "CSI";

	/** The db user. */
	private String dbUser = "ESS" + ESS_VERSION_SUFFIX;

	/** The Constant CONTEXT_STATUS_CD_OPEN. */
	public static final String CONTEXT_STATUS_CD_OPEN = "OPEN";

	/** The esdg database connection factory. */
	private IEsdgDatabaseConnectionFactory esdgDatabaseConnectionFactory = null;

	/** The Constant SELECT_SALES_INTERACTION_SEQ_NEXTVAL_STMT. */
	static final String SELECT_SALES_INTERACTION_SEQ_NEXTVAL_STMT = "SELECT SALES_INTRACTN_SEQ.NEXTVAL FROM DUAL";


	/** The Constant SELECT_SALES_CONTEXT_SEQ_NEXTVAL_STMT. */
	private static final String SELECT_SALES_CONTEXT_SEQ_NEXTVAL_STMT = "SELECT SALES_CONTEXT_SEQ.NEXTVAL FROM DUAL";

	/** The Constant SELECT_SALES_ORDER_SEQ_NEXTVAL_STMT. */
	private static final String SELECT_SALES_ORDER_SEQ_NEXTVAL_STMT = "SELECT SALES_ORDER_SEQ.NEXTVAL FROM DUAL";

	/** The Constant SELECT_SALES_ORDER_CONTRACT_SEQ_NEXTVAL_STMT. */
	private static final String SELECT_SALES_ORDER_CONTRACT_SEQ_NEXTVAL_STMT = "SELECT SALES_ORDER_CONTRACT_DATA_SEQ.NEXTVAL FROM DUAL";

	/** The Constant SELECT_SALES_FULFILLMENT_SEQ_NEXTVAL_STMT. */
	private static final String SELECT_SALES_FULFILLMENT_SEQ_NEXTVAL_STMT = "SELECT SALES_FULFILLMENT_SEQ.NEXTVAL FROM DUAL";
	
	/** The Constant INSERT_SALES_INTERACTION_STMT. */
	static final String INSERT_SALES_INTERACTION_STMT = "INSERT INTO SALES_INTRACTN ("
			+ "SALES_INTRACTN_ID, CUST_BILLING_ACCOUNT_NUM, CUST_CNTCT_PHONE_NUM, INTRACTN_START_TS, EXTERNAL_SALES_REF_ID,"
			+ "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ "VALUES (?,?,?,?,?,?,SYSDATE,?,SYSDATE)";

	/** The Constant INSERT_SALES_INTERACTION_LIFECYCLE_STMT. */
	static final String INSERT_SALES_INTERACTION_LIFECYCLE_STMT = "INSERT INTO SALES_INTRACTN_LIFECYCL ("
			+ "SALES_INTRACTN_LIFECYCL_ID, SALES_INTRACTN_ID, INTRACTN_START_TS, CHANNEL_ORG_TYP_CD, CHANNEL_ORGANIZATION_ID, CHANNEL_PERSON_TYP_CD, CHANNEL_PERSON_ID, INTRACTN_STATUS_CD, EFFECTIVE_START_TS, CURRENT_IND,"
			+ "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ "VALUES (SALES_INTRACTN_LIFECYCL_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,'N',?,SYSDATE,?,SYSDATE)";

	/** The Constant INSERT_SALES_CONTEXT_STMT. */
	private static final String INSERT_SALES_CONTEXT_STMT = "INSERT INTO SALES_CONTEXT ("
			+ "SALES_CONTEXT_ID, SALES_INTRACTN_ID, INTRACTN_START_TS, EXTERNAL_SALES_REF_ID, SUBSCRIBER_PHONE_NUM,"
			+ "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS, BRAND_CD) "
			+ "VALUES (?,?,?,?,?,?,SYSDATE,?,SYSDATE,?)";

	/** The Constant INSERT_SALES_CONTEXT_LIFECYCLE_STMT. */
	private static final String INSERT_SALES_CONTEXT_LIFECYCLE_STMT = "INSERT INTO SALES_CONTEXT_LIFECYCL ("
			+ "SALES_CONTEXT_LIFECYCL_ID, SALES_CONTEXT_ID, INTRACTN_START_TS, SALES_CONTEXT_STATUS_CD, EFFECTIVE_START_TS, CURRENT_IND,"
			+ "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ "VALUES (SALES_CONTEXT_LIFECYCL_SEQ.NEXTVAL,?,?,?,?,'N',?,SYSDATE,?,SYSDATE)";

	/** The Constant INSERT_SALES_ORDER_STMT. */
	private static final String INSERT_SALES_ORDER_STMT = "INSERT INTO SALES_ORDER ("
			+ "SALES_ORDER_ID, SALES_CONTEXT_ID, INTRACTN_START_TS,"
			+ "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS, TYPE_CD) "
			+ "VALUES (?,?,?,?,SYSDATE,?,SYSDATE,?)";

	/** The Constant INSERT_SALES_ORDER_LIFECYCLE_STMT. */
	private static final String INSERT_SALES_ORDER_LIFECYCLE_STMT = "INSERT INTO SALES_ORDER_LIFECYCL ("
			+ "SALES_ORDER_LIFECYCL_ID, SALES_ORDER_ID, INTRACTN_START_TS, SALES_ORDER_VERSION_NUM, SALES_ORDER_STATUS_CD, EFFECTIVE_START_TS, CURRENT_IND,"
			+ "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ "VALUES (SALES_ORDER_LIFECYCL_SEQ.NEXTVAL,?,?,(SELECT NVL(MAX(SALES_ORDER_VERSION_NUM), '0') + 1 FROM SALES_ORDER_LIFECYCL t2 WHERE t2.SALES_ORDER_ID = ?),?,?,'N',?,SYSDATE,?,SYSDATE)";

	/** The Constant INSERT_SALES_CONTEXT_RAW_DATA_STMT. */
	private static final String INSERT_SALES_CONTEXT_RAW_DATA_STMT = "INSERT INTO SALES_CONTEXT_TXN_DATA ("
            + "SALES_CONTEXT_TXN_DATA_ID, SALES_CONTEXT_ID,"
            + "INTRACTN_START_TS, SALES_TXN_TS, EXTERNAL_TXN_REF_ID, SALES_TXN_DATA,"
            + "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
            + "VALUES (SALES_CONTEXT_TXN_DATA_SEQ.NEXTVAL,?,?,?,?,?,?,SYSDATE,?,SYSDATE)";

	/** The Constant INSERT_SALES_ORDER_RAW_DATA_STMT. */
	private static final String INSERT_SALES_ORDER_RAW_DATA_STMT = "INSERT INTO SALES_ORDER_TXN_DATA ("
            + "SALES_ORDER_TXN_DATA_ID, SALES_ORDER_ID,"
            + "INTRACTN_START_TS, SALES_TXN_TS, EXTERNAL_TXN_REF_ID, SALES_TXN_DATA,"
            + "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
            + "VALUES (SALES_ORDER_TXN_DATA_SEQ.NEXTVAL,?,?,?,?,?,?,SYSDATE,?,SYSDATE)";

	/** The Constant INSERT_SALES_ORDER_CONTRACT_DATA. */
	private static final String INSERT_SALES_ORDER_CONTRACT_DATA = "INSERT INTO SALES_ORDER_CONTRACT_DATA ("
			+ "SALES_ORDER_CONTRACT_DATA_ID,SALES_ORDER_ID,INTRACTN_START_TS,SALES_TXN_TS,EXTERNAL_TXN_REF_ID,"
			+ "SALES_CONTRACT_DATA,CREATE_USER_ID,CREATE_TS,LAST_UPDT_USER_ID,LAST_UPDT_TS) "
			+ "VALUES(?,?,?,?,?,?,?,SYSDATE,?,SYSDATE)";

	/** The Constant INSERT_SALES_FULFILLMENT_STMT. */
	private static final String INSERT_SALES_FULFILLMENT_STMT = "INSERT INTO SALES_FULFILLMENT ("
			+ "SALES_FULFILLMENT_ID, SALES_CONTEXT_ID, INTRACTN_START_TS, SALES_FULFILLMENT_STATUS_CD, EFFECTIVE_START_TS, RETRY_CNT,"
			+ "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ "VALUES (?,?,?,?,?,'0',?,SYSDATE,?,SYSDATE)";

	/** The Constant UPDATE_SALES_CONTEXT_STMT. */
	private static final String UPDATE_SALES_INTERACTION_STMT = "UPDATE SALES_INTRACTN SET "
			+ "CUST_BILLING_ACCOUNT_NUM = ?, "
			+ "LAST_UPDT_USER_ID = ?, LAST_UPDT_TS = SYSDATE "
			+ "WHERE SALES_INTRACTN_ID = ?";

	/** The Constant UPDATE_SALES_CONTEXT_STMT. */
	private static final String UPDATE_SALES_CONTEXT_STMT = "UPDATE SALES_CONTEXT SET "
			+ "EXTERNAL_SALES_REF_ID = ?, "
			+ "LAST_UPDT_USER_ID = ?, LAST_UPDT_TS = SYSDATE "
			+ "WHERE SALES_CONTEXT_ID = ?";

	/** The Constant UPDATE_SALES_CONTEXT_WITH_PHONE_STMT. */
	private static final String UPDATE_SALES_CONTEXT_WITH_PHONE_STMT = "UPDATE SALES_CONTEXT SET "
			+ "SUBSCRIBER_PHONE_NUM = ?, "
			+ "LAST_UPDT_USER_ID = ?, LAST_UPDT_TS = SYSDATE "
			+ "WHERE SALES_CONTEXT_ID = ?";

	/** The Constant UPDATE_SALES_CONTEXT_LIFECYCL_STMT. */
	private static final String UPDATE_SALES_CONTEXT_LIFECYCL_STMT = "UPDATE SALES_CONTEXT_LIFECYCL SET "
			+ "EFFECTIVE_START_TS = ?, "
			+ "LAST_UPDT_USER_ID = ?, LAST_UPDT_TS = SYSDATE "
			+ "WHERE SALES_CONTEXT_ID = ?";

	/** The Constant UPDATE_SALES_FULFILLMENT_STMT. */
	private static final String UPDATE_SALES_FULFILLMENT_STMT = "UPDATE SALES_FULFILLMENT SET "
			+ "SALES_FULFILLMENT_STATUS_CD = ?, EFFECTIVE_START_TS = ?, "
			+ "LAST_UPDT_USER_ID = ?, LAST_UPDT_TS = SYSDATE "
			+ "WHERE SALES_FULFILLMENT_ID = ?";

	/** The Constant INSERT_SALES_FULFILLMENT_TXN_DATA_STMT. */
	private static final String INSERT_SALES_FULFILLMENT_TXN_DATA_STMT = "INSERT INTO SALES_FULFILLMENT_TXN_DATA ("
			+ "SALES_FULFILLMENT_TXN_DATA_ID, SALES_FULFILLMENT_ID, INTRACTN_START_TS, SALES_FULFILLMENT_TXN_TYP_CD,"
			+ "SALES_FULFILLMENT_TXN_TS, SALES_FULFILLMENT_TXN_DATA, ERROR_IND, RETRY_CNT,"
			+ "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ "VALUES (SALES_FULFILLMENT_TXN_DATA_SEQ.NEXTVAL,?,?,?,?,?,?,'0',?,SYSDATE,?,SYSDATE)";

	/** The Constant UPDATE_SALES_FULFILLMENT_TXN_DATA_STMT. */
	private static final String UPDATE_SALES_FULFILLMENT_TXN_DATA_STMT = "UPDATE SALES_FULFILLMENT_TXN_DATA SET "
			+ "SALES_FULFILLMENT_TXN_TS = ?, SALES_FULFILLMENT_TXN_DATA = ?, ERROR_IND = ?, RETRY_CNT = ?,"
			+ "LAST_UPDT_USER_ID = ?, LAST_UPDT_TS = SYSDATE "
			+ "WHERE SALES_FULFILLMENT_TXN_DATA_ID = ?";

	/** The Constant INSERT_SALES_CLASSIFIER_STMT. */
	private static final String INSERT_SALES_CLASSIFIER_STMT = "INSERT INTO SALES_CLASSIFIER ("
			+ "SALES_CLASSIFIER_ID, SALES_CONTEXT_ID, INTRACTN_START_TS,"
			+ "SALES_CLASSIFIER_TYP_CD, SALES_CLASSIFIER_VALUE,"
			+ "CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ "VALUES (SALES_CLASSIFIER_SEQ.NEXTVAL,?,?,?,?,?,SYSDATE,?,SYSDATE)";

	/** The Constant SELECT_SALES_INTERACTION_BY_KEY_STMT. */
	private static final String SELECT_SALES_INTERACTION_BY_KEY_STMT = "SELECT INTRACTN_START_TS FROM SALES_INTRACTN WHERE "
			+ "SALES_INTRACTN_ID = ?";

	/** The Constant SELECT_SALES_CONTEXT_RAW_DATA_BY_KEY_STMT. */
	private static final String SELECT_SALES_CONTEXT_RAW_DATA_BY_KEY_STMT = "SELECT SALES_CONTEXT_ID, SALES_TXN_TS, SALES_TXN_DATA FROM SALES_CONTEXT_TXN_DATA WHERE "
			+ "EXTERNAL_TXN_REF_ID = ?";

	/** The Constant SELECT_SALES_CONTEXT_RAW_DATA_BY_KEY_SET_STMT. */
	private static final String SELECT_SALES_CONTEXT_RAW_DATA_BY_KEY_SET_STMT = "SELECT SALES_CONTEXT_ID, SALES_TXN_TS, EXTERNAL_TXN_REF_ID, SALES_TXN_DATA FROM SALES_CONTEXT_TXN_DATA WHERE "
			+ "EXTERNAL_TXN_REF_ID IN ";

	/** The Constant SELECT_SALES_INTRACTN_START_TS_BY_CONTEXT_ID_STMT. */
	private static final String SELECT_SALES_INTRACTN_START_TS_BY_CONTEXT_ID_STMT = "SELECT a.SALES_INTRACTN_ID, a.INTRACTN_START_TS, a.EXTERNAL_SALES_REF_ID, a.SUBSCRIBER_PHONE_NUM, b.CUST_BILLING_ACCOUNT_NUM "
			+ "FROM SALES_CONTEXT a, SALES_INTRACTN b "
			+ "WHERE a.SALES_INTRACTN_ID = b.SALES_INTRACTN_ID and SALES_CONTEXT_ID = ?";

	/** The Constant SELECT_SALES_ORDER_ID_BY_CONTEXT_STMT. */
	private static final String SELECT_SALES_ORDER_ID_BY_CONTEXT_STMT = "SELECT SALES_ORDER_ID FROM SALES_ORDER "
			+ "WHERE SALES_CONTEXT_ID = ?";

	/** The Constant SELECT_LATEST_EFFECTIVE_ORDER_STATUS_STMT. */
	private static final String SELECT_LATEST_EFFECTIVE_ORDER_STATUS_STMT = "SELECT sales_order_status_cd, effective_start_ts FROM sales_order_lifecycl "
			+ "WHERE sales_order_id = ? "
			+ "AND sales_order_lifecycl.effective_start_ts = (SELECT MAX(effective_start_ts) FROM sales_order_lifecycl d2 WHERE d2.sales_order_id = ?)";

	/** The Constant SELECT_LATEST_ORDER_DATA_STMT. */
	private static final String SELECT_LATEST_ORDER_DATA_STMT = "SELECT SALES_TXN_DATA FROM SALES_ORDER_TXN_DATA "
			+ "WHERE SALES_ORDER_TXN_DATA.SALES_ORDER_ID = ? "
			+ "AND SALES_ORDER_TXN_DATA.SALES_TXN_TS = (SELECT MAX(SALES_TXN_TS) FROM SALES_ORDER_TXN_DATA d2 WHERE d2.SALES_ORDER_ID = ?)";

	/** The Constant SELECT_ORDER_DATA_STMT. */
	private static final String SELECT_ORDER_DATA_STMT = "SELECT SALES_TXN_DATA FROM SALES_ORDER_TXN_DATA "
			+ "WHERE SALES_ORDER_TXN_DATA.SALES_ORDER_ID = ?";

	/** The Constant SELECT_LATEST_ORDER_STATUS_STMT. */
	private static final String SELECT_LATEST_ORDER_STATUS_STMT = "SELECT SALES_ORDER_LIFECYCL.SALES_ORDER_ID, SALES_ORDER_LIFECYCL.SALES_ORDER_VERSION_NUM, SALES_ORDER_LIFECYCL.SALES_ORDER_STATUS_CD, SALES_ORDER_LIFECYCL.INTRACTN_START_TS, SALES_ORDER_LIFECYCL.EFFECTIVE_START_TS, SALES_ORDER.TYPE_CD "
			+ "FROM SALES_ORDER, SALES_ORDER_LIFECYCL "
			+ "WHERE SALES_ORDER.SALES_ORDER_ID = SALES_ORDER_LIFECYCL.SALES_ORDER_ID "
			+ "AND SALES_ORDER_LIFECYCL.EFFECTIVE_START_TS = (SELECT MAX(EFFECTIVE_START_TS) FROM SALES_ORDER_LIFECYCL d2 WHERE d2.SALES_ORDER_ID = SALES_ORDER_LIFECYCL.SALES_ORDER_ID) "
			+ "AND SALES_ORDER.SALES_CONTEXT_ID = ? "
			+ "ORDER BY SALES_ORDER_LIFECYCL.SALES_ORDER_LIFECYCL_ID DESC";

	/** The Constant SELECT_ORDER_CONTRACT_STMT. */
	private static final String SELECT_ORDER_CONTRACT_STMT = "SELECT CDATA.SALES_ORDER_CONTRACT_DATA_ID, CDATA.SALES_ORDER_ID, CDATA.INTRACTN_START_TS, "
			+ "CDATA.SALES_TXN_TS, CDATA.EXTERNAL_TXN_REF_ID, CDATA.SALES_CONTRACT_DATA, CDATA.CREATE_USER_ID, CDATA.CREATE_TS, "
			+ "CDATA.LAST_UPDT_USER_ID, CDATA.LAST_UPDT_TS "
			+ "FROM SALES_ORDER_CONTRACT_DATA CDATA, "
			+ "(SELECT MAX(SALES_ORDER_CONTRACT_DATA_ID) IDMAX,EXTERNAL_TXN_REF_ID "
			+ "FROM SALES_ORDER_CONTRACT_DATA "
			+ "WHERE SALES_ORDER_ID = ? "
			+ "GROUP BY EXTERNAL_TXN_REF_ID ) CMAX "
			+ "WHERE CDATA.SALES_ORDER_CONTRACT_DATA_ID = CMAX.IDMAX "
			+ "AND CDATA.SALES_ORDER_ID = ? "
			+ "ORDER BY CDATA.EXTERNAL_TXN_REF_ID ASC";

	/** The Constant SELECT_LATEST_ORDER_STMT. */
	private static final String SELECT_LATEST_ORDER_STMT = "SELECT EXTERNAL_TXN_REF_ID, SALES_TXN_DATA, SALES_TXN_TS "
			+ "FROM SALES_ORDER_TXN_DATA "
			+ "WHERE SALES_ORDER_ID = ? "
			+ "AND SALES_ORDER_TXN_DATA.SALES_TXN_TS = (SELECT MAX(SALES_TXN_TS) FROM SALES_ORDER_TXN_DATA d2 WHERE d2.SALES_ORDER_ID = ?)";

	/** The Constant SELECT_SALES_FULFILLMENT_STMT. */
	private static final String SELECT_SALES_FULFILLMENT_STMT = "SELECT SALES_FULFILLMENT_ID, SALES_FULFILLMENT_STATUS_CD, EFFECTIVE_START_TS "
			+ "FROM SALES_FULFILLMENT "
			+ "WHERE SALES_CONTEXT_ID = ?";

	/** The Constant SELECT_SALES_FULFILLMENT_TXN_DATA_STMT. */
	private static final String SELECT_SALES_FULFILLMENT_TXN_DATA_STMT = "SELECT SALES_FULFILLMENT_TXN_DATA_ID, SALES_FULFILLMENT_TXN_TYP_CD, SALES_FULFILLMENT_TXN_TS, RETRY_CNT "
			+ "FROM SALES_FULFILLMENT_TXN_DATA "
			+ "WHERE SALES_FULFILLMENT_ID = ?";

	/** The Constant SELECT_SALES_FULFILLMENT_TXN_TYPE_STMT. */
	private static final String SELECT_SALES_FULFILLMENT_TXN_TYPE_STMT = "SELECT DISTINCT SALES_FULFILLMENT_TXN_TYP_CD "
			+ "FROM SALES_FULFILLMENT, SALES_FULFILLMENT_TXN_DATA "
			+ "WHERE SALES_FULFILLMENT.SALES_FULFILLMENT_ID = SALES_FULFILLMENT_TXN_DATA.SALES_FULFILLMENT_ID "
			+ "AND SALES_FULFILLMENT_TXN_DATA.ERROR_IND = 'Y' "
			+ "AND SALES_FULFILLMENT.SALES_CONTEXT_ID = ?";

	/** The Constant SELECT_SALES_CLASSIFIERS_STMT. */
	private static final String SELECT_SALES_CLASSIFIERS_STMT = "SELECT SALES_CLASSIFIER_TYP_CD, SALES_CLASSIFIER_VALUE "
			+ "FROM SALES_CLASSIFIER "
			+ "WHERE SALES_CONTEXT_ID = ?";

	/** The Constant SELECT_SALES_CONTEXT_BY_LIFECYCL_EFFECTIVE_START_TS. - INCLUDES WLS only for Reporting*/
	private static final String SELECT_SALES_CONTEXT_BY_LIFECYCL_EFFECTIVE_START_TS = "SELECT DISTINCT SALES_CONTEXT_LIFECYCL.SALES_CONTEXT_ID "
			+ "FROM SALES_CONTEXT_LIFECYCL, SALES_ORDER, SALES_CONTEXT, SALES_INTRACTN  "
			+ "WHERE SALES_CONTEXT_LIFECYCL.SALES_CONTEXT_ID = SALES_CONTEXT.SALES_CONTEXT_ID "
			+ "AND SALES_CONTEXT_LIFECYCL.INTRACTN_START_TS = SALES_CONTEXT.INTRACTN_START_TS "
			+ "AND SALES_CONTEXT.SALES_CONTEXT_ID = SALES_ORDER.SALES_CONTEXT_ID "
			+ "AND SALES_CONTEXT.INTRACTN_START_TS = SALES_ORDER.INTRACTN_START_TS "
			+ "AND SALES_INTRACTN.SALES_INTRACTN_ID = SALES_CONTEXT.SALES_INTRACTN_ID "
			+ "AND SALES_INTRACTN.INTRACTN_START_TS = SALES_CONTEXT.INTRACTN_START_TS "
			+ "AND SALES_CONTEXT_LIFECYCL.EFFECTIVE_START_TS >= ? "
			+ "AND SALES_CONTEXT_LIFECYCL.EFFECTIVE_START_TS <= ? "
			+ "AND SALES_ORDER.INTRACTN_START_TS >= ? "
			+ "AND (SALES_INTRACTN.SALES_INTRACTN_TYPE_CD IS NULL OR SALES_INTRACTN.SALES_INTRACTN_TYPE_CD <> 'ESCART_WLN') ";

	private static final String SELECT_SALES_INTRACTN_BY_LIFECYCL_EFFECTIVE_START_TS = "SELECT DISTINCT SALES_INTRACTN_LIFECYCL.SALES_INTRACTN_ID "
			+ "FROM SALES_INTRACTN_LIFECYCL, SALES_INTRACTN "
			+ "WHERE SALES_INTRACTN_LIFECYCL.SALES_INTRACTN_ID = SALES_INTRACTN.SALES_INTRACTN_ID "
			+ "AND SALES_INTRACTN.SALES_INTRACTN_TYPE_CD = ? "
			//+ "AND SALES_INTRACTN_LIFECYCL.INTRACTN_STATUS_CD IN (?,?,?)
			+ "AND SALES_INTRACTN_LIFECYCL.EFFECTIVE_START_TS >= ? "
			+ "AND SALES_INTRACTN_LIFECYCL.EFFECTIVE_START_TS <= ? "
			+ "AND SALES_INTRACTN_LIFECYCL.INTRACTN_START_TS >= ?";

	/** The Constant SELECT_ORDER_BY_SEARCH_CRITERIA. */
	private static final String SELECT_ORDER_BY_SEARCH_CRITERIA = "SELECT SALES_CONTEXT.SALES_CONTEXT_ID, SALES_ORDER.SALES_ORDER_ID, MAX(SALES_ORDER_LIFECYCL.EFFECTIVE_START_TS) "
			+ "FROM SALES_CONTEXT, SALES_ORDER, SALES_ORDER_LIFECYCL "
			+ "WHERE SALES_CONTEXT.SALES_CONTEXT_ID = SALES_ORDER.SALES_CONTEXT_ID "
			+ "AND SALES_ORDER.SALES_ORDER_ID = SALES_ORDER_LIFECYCL.SALES_ORDER_ID "
			+ "AND SALES_CONTEXT.SUBSCRIBER_PHONE_NUM = ? "
			+ "AND SALES_ORDER_LIFECYCL.EFFECTIVE_START_TS >= ? "
			+ "AND SALES_ORDER_LIFECYCL.EFFECTIVE_START_TS <= ? "
			+ "AND SALES_ORDER_LIFECYCL.SALES_ORDER_STATUS_CD IN ";

	/** The Constant SELECT_ORDER_BY_SEARCH_CRITERIA_GROUP_BY. */
	private static final String SELECT_ORDER_BY_SEARCH_CRITERIA_GROUP_BY = "GROUP BY SALES_CONTEXT.SALES_CONTEXT_ID, SALES_ORDER.SALES_ORDER_ID";

	/** The Constant SELECT_SALES_INTRACTN_ID_BY_EXTERNAL_SALES_REF_ID_AND_BAN. */
	private static final String SELECT_SALES_INTRACTN_ID_BY_EXTERNAL_SALES_REF_ID_AND_BAN = "SELECT SALES_INTRACTN_ID "
			+ "FROM SALES_INTRACTN "
			+ "WHERE EXTERNAL_SALES_REF_ID = ? "
			+ "AND CUST_BILLING_ACCOUNT_NUM = ? "
			+ "AND INTRACTN_START_TS >= ? "
			;

	/** The Constant SELECT_SALES_INTRACTN_ID_BY_EXTERNAL_SALES_REF_ID. */
	private static final String SELECT_SALES_INTRACTN_ID_BY_EXTERNAL_SALES_REF_ID = "SELECT SALES_INTRACTN_ID "
			+ "FROM SALES_INTRACTN "
			+ "WHERE EXTERNAL_SALES_REF_ID = ? "
			+ "AND INTRACTN_START_TS >= ? "
			+ "AND SALES_INTRACTN_TYPE_CD NOT IN ('','')"
			;

	/** The Constant SELECT_SALES_INTRACTN_DATA_BY_SALES_INTRACTN_ID. */
	private static final String SELECT_SALES_INTRACTN_DATA_BY_SALES_INTRACTN_ID = "SELECT SALES_TXN_DATA "
			+ "FROM SALES_INTRACTN_TXN_DATA "
			+ "WHERE SALES_INTRACTN_ID = ? "
			+ "AND INTRACTN_START_TS >= ? "
			+ "ORDER BY SALES_TXN_TS DESC";

	/** Cecil Oct The Constant SELECT_ORDER_BY_SALES_ORDER_STATUS. */
	/*private static final String SELECT_ORDER_BY_SALES_ORDER_STATUS = "SELECT SALES_CONTEXT.SALES_CONTEXT_ID "
			+ "FROM SALES_CONTEXT, SALES_ORDER, SALES_ORDER_LIFECYCL "
			+ "WHERE SALES_CONTEXT.SALES_CONTEXT_ID = SALES_ORDER.SALES_CONTEXT_ID "
			+ "AND SALES_ORDER.SALES_ORDER_ID = SALES_ORDER_LIFECYCL.SALES_ORDER_ID "
			+ "AND SALES_ORDER_LIFECYCL.EFFECTIVE_START_TS >= ? "
			+ "AND SALES_ORDER_LIFECYCL.EFFECTIVE_START_TS <= ? "
			+ "AND SALES_ORDER_LIFECYCL.SALES_ORDER_STATUS_CD IN ";*/

	/** Cecil Oct The Constant SELECT_SALES_CONTEXT_ID_BY_SALES_CLASSFIER. */
	private static final String SELECT_SALES_CONTEXT_ID_BY_SALES_CLASSFIER = "SELECT SALES_CONTEXT_ID "
			+ "FROM SALES_CLASSIFIER "
			+ "WHERE SALES_CLASSIFIER_TYP_CD = ? "
			+ "AND SALES_CLASSIFIER_VALUE = ? "
			+ "AND INTRACTN_START_TS >= ? "
			+ "AND INTRACTN_START_TS <= ? ";

	/**
	 * Spruce project: Insert MTS order into MTS_SALES_ORDER table
	 */
	private static final String INSERT_MTS_SALES_ORDER_STMT = "INSERT INTO MTS_SALES_ORDER ("
			+ "PORTIN_PHONE_NUM, BILLING_ACCOUNT_NUM, SALES_CONTEXT_ID, SALES_INTRACTN_ID, "
			+ "INTRACTN_START_TS, EXTERNAL_SALES_REF_ID, SALES_ORDER_STATUS_CD,"
			+ "SALES_ORDER_STATUS_TS, CREATE_USER_ID, CREATE_TS, LAST_UPDT_USER_ID, LAST_UPDT_TS) "
			+ "VALUES (?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,?,SYSDATE)";

	/**
	 * Spruce project: query MTS order from MTS_SALES_ORDER table by Phone number and BAN
	 */
	private static final String SELECT_MTS_SALES_ORDER_BY_PHONE_AND_BAN = "SELECT SALES_CONTEXT_ID, SALES_ORDER_STATUS_CD "
			+ "FROM MTS_SALES_ORDER WHERE PORTIN_PHONE_NUM = ? "
			+ "AND BILLING_ACCOUNT_NUM = ?";

	/**
	 * Spruce project: Update MTS order from MTS_SALES_ORDER table by Phone number and BAN
	 */
	private static final String UPDATE_MTS_SALES_ORDER_STMT = "UPDATE MTS_SALES_ORDER SET SALES_CONTEXT_ID=?, "
			+ "SALES_INTRACTN_ID=?, INTRACTN_START_TS=?, EXTERNAL_SALES_REF_ID=?, SALES_ORDER_STATUS_CD=?, "
			+ "SALES_ORDER_STATUS_TS=SYSDATE, LAST_UPDT_USER_ID=?, LAST_UPDT_TS=SYSDATE "
			+ "WHERE PORTIN_PHONE_NUM = ? AND BILLING_ACCOUNT_NUM = ?";

	/**
	 * Spruce project: get MTS order from MTS_SALES_ORDER table by BAN
	 */
	private static final String SELECT_MTS_SALES_ORDER_BY_BAN = "SELECT SALES_CONTEXT_ID, SALES_ORDER_STATUS_CD "
			+ "FROM MTS_SALES_ORDER WHERE BILLING_ACCOUNT_NUM = ?";

	//SAP - Waybill
	public static final String MERGE_SALES_SHIPMENT_STMT = "MERGE INTO SALES_SHIPMENT B "
			+ "USING (SELECT ? LAST_UPDT_TS, ? LAST_UPDT_USER_ID, ? CREATE_TS, ? CREATE_USER_ID, ? SHIPMENT_TRACKING_NUM, ? EXTERNAL_TRANSACTION_REFERENCE, ? STATUS_EFFECTIVE_START_TS, ? SALES_SHIPMENT_STATUS_CD, ? INTRACTN_START_TS, ? SALES_CONTEXT_ID FROM DUAL) S "
			+ "ON (B.SALES_CONTEXT_ID = S.SALES_CONTEXT_ID) "
			+ "WHEN MATCHED THEN "
			+ "UPDATE SET B.LAST_UPDT_TS = S.LAST_UPDT_TS, B.LAST_UPDT_USER_ID = S.LAST_UPDT_USER_ID, B.SHIPMENT_TRACKING_NUM = S.SHIPMENT_TRACKING_NUM, B.EXTERNAL_TRANSACTION_REFERENCE = S.EXTERNAL_TRANSACTION_REFERENCE, B.STATUS_EFFECTIVE_START_TS = S.STATUS_EFFECTIVE_START_TS, B.SALES_SHIPMENT_STATUS_CD = S.SALES_SHIPMENT_STATUS_CD "
			+ "WHEN NOT MATCHED THEN "
			+ "INSERT (B.SALES_SHIPMENT_ID, B.LAST_UPDT_TS, B.LAST_UPDT_USER_ID, B.CREATE_TS, B.CREATE_USER_ID, B.SHIPMENT_TRACKING_NUM, B.EXTERNAL_TRANSACTION_REFERENCE, B.STATUS_EFFECTIVE_START_TS, B.SALES_SHIPMENT_STATUS_CD, B.INTRACTN_START_TS, B.SALES_CONTEXT_ID) "
			+ "VALUES (SALES_SHIPMENT_SEQ.NEXTVAL, S.LAST_UPDT_TS, S.LAST_UPDT_USER_ID, S.CREATE_TS, S.CREATE_USER_ID, S.SHIPMENT_TRACKING_NUM, S.EXTERNAL_TRANSACTION_REFERENCE, S.STATUS_EFFECTIVE_START_TS, S.SALES_SHIPMENT_STATUS_CD, S.INTRACTN_START_TS, S.SALES_CONTEXT_ID)";

	// Tallboy 
	private static final String SELECT_SALES_INTRACTN_ID_BY_SALES_CONTEXT_ID = "SELECT SALES_INTRACTN_ID "
            + "FROM SALES_CONTEXT "
            + "WHERE SALES_CONTEXT_ID = ?";
	
	/**
	 * Save and Resume: resumable conditions
	 */
	private static final String RESUMABLE_CONDITIONS =
			//Latest order status is new / prepared and interaction start date is later than a given date
			"EXISTS ( "
            + "SELECT 1 "
            + "FROM SALES_ORDER SO, SALES_ORDER_LIFECYCL SOL "
            + "WHERE SO.SALES_ORDER_ID = SOL.SALES_ORDER_ID "
            + "AND SO.SALES_CONTEXT_ID = SC.SALES_CONTEXT_ID "
            + "AND SC.INTRACTN_START_TS = SO.INTRACTN_START_TS "
            + "AND SOL.INTRACTN_START_TS = SO.INTRACTN_START_TS "
            + "AND SOL.SALES_ORDER_STATUS_CD IN ('new', 'prepared', 'reserved' ) "
            + "AND SOL.EFFECTIVE_START_TS = (SELECT MAX(SOL2.EFFECTIVE_START_TS) FROM SALES_ORDER_LIFECYCL SOL2 WHERE SO.SALES_ORDER_ID = SOL2.SALES_ORDER_ID)) "
            //Not Committed
            + "AND NOT EXISTS ( "
            + "SELECT 1 "
            + "FROM SALES_CLASSIFIER SCLS "
            + "WHERE SCLS.SALES_CONTEXT_ID = SC.SALES_CONTEXT_ID "
            + "AND SC.INTRACTN_START_TS = SCLS.INTRACTN_START_TS "
            + "AND SCLS.SALES_CLASSIFIER_TYP_CD = 'ORDER_STAGE_CD' "
            + "AND SCLS.SALES_CLASSIFIER_VALUE = 'COMMITED')";

    /**
	 * Save and Resume: get resumable Sales Ids for a given phone number
	 */
	private static final String SELECT_RESUMABLE_SALES_ID_BY_PHONE =
			"SELECT DISTINCT SC.SALES_CONTEXT_ID "
			+ "FROM SALES_CONTEXT SC "
			+ "WHERE SC.SUBSCRIBER_PHONE_NUM = ? "
			+ "AND SC.INTRACTN_START_TS > ? "
			+ " AND " + RESUMABLE_CONDITIONS;

	/**
	 * Save and Resume: get resumable Sales Ids for a given account number
	 */
	private static final String SELECT_RESUMABLE_SALES_ID_BY_ACCOUNT =
			"SELECT DISTINCT SC.SALES_CONTEXT_ID "
			+ "FROM SALES_CONTEXT SC, SALES_INTRACTN SI "
			+ "WHERE SC.SALES_INTRACTN_ID = SI.SALES_INTRACTN_ID "
            + "AND SC.INTRACTN_START_TS > ? "
            + "AND SC.INTRACTN_START_TS = SI.INTRACTN_START_TS "
			+ "AND SI.CUST_BILLING_ACCOUNT_NUM = ? "
			+ "AND " + RESUMABLE_CONDITIONS;

	private static final String SEELECT_SALES_ID_STATUS_BY_INTERACTION_ID =
			"select  "
					+ " t1.sales_context_id ctx_id "
					+ ",S2.SALES_ORDER_STATUS_CD status "
					+ "from sales_context t1 "
					+ "join sales_order s1 on  (S1.SALES_CONTEXT_ID = t1.SALES_CONTEXT_ID and s1.INTRACTN_START_TS=T1.INTRACTN_START_TS ) "
					+ "join sales_order_lifecycl s2 on (s2.SALES_order_ID=s1.SALES_order_ID and s2.INTRACTN_START_TS=T1.INTRACTN_START_TS "
					+ "      AND s2.effective_start_ts = (SELECT MAX(effective_start_ts) FROM sales_order_lifecycl d1 WHERE d1.SALES_order_ID=s1.SALES_order_ID and d1.INTRACTN_START_TS=T1.INTRACTN_START_TS) "
					+ "      ) "
					+ "where  "
					+ "t1.SALES_INTRACTN_ID=? ";

	private static final String SEELECT_SALES_CONTEXT_ID_BY_INTERACTION_ID =
			"select  "
					+ "SALES_CONTEXT_ID "
					+ "from sales_context "
					+ "where  "
					+ "SALES_INTRACTN_ID=?";

	private static final String SELECT_SALES_ID_BY_ACCOUNT =
			"SELECT DISTINCT SC.SALES_CONTEXT_ID "
			+ "FROM SALES_CONTEXT SC, SALES_INTRACTN SI "
			+ "WHERE SC.SALES_INTRACTN_ID = SI.SALES_INTRACTN_ID "
            + "AND SC.INTRACTN_START_TS > ? "
            + "AND SI.INTRACTN_START_TS > ? "
			+ "AND SI.CUST_BILLING_ACCOUNT_NUM = ? ";
	
	/**
	 * Instantiates a new esdg database adapter_1.
	 */
	public EsdgDatabaseAdapter_1() {
		this.esdgDatabaseConnectionFactory = IEsdgDatabaseConnectionFactory.Factory.newInstance();
	}

	/**
	 * Instantiates a new esdg database adapter_1.
	 *
	 * @param esdgDatabaseConnectionFactory the esdg database connection factory
	 */
	public EsdgDatabaseAdapter_1(IEsdgDatabaseConnectionFactory esdgDatabaseConnectionFactory) {
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
	 * Search sales context by lifecycle effective start ts.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the collection
	 */
	public Collection<String> searchSalesContextByLifecycleEffectiveStartTs(Date startDate, Date endDate) {
		ArrayList<String> resultList = new ArrayList<String>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		try {
        	if( startDate == null ) {
        		logger.warn("--ESDG-- Abort to query order database due to missing mandatory inputs.");
        		return null;
        	}
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SELECT_SALES_CONTEXT_BY_LIFECYCL_EFFECTIVE_START_TS);
            int idx = 1;
            ps.setTimestamp(idx++, new Timestamp(startDate.getTime()));
            ps.setTimestamp(idx++, new Timestamp(endDate == null ? Calendar.getInstance().getTimeInMillis() : endDate.getTime()));
            //set partition key for
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.DAY_OF_YEAR, -30); // default past 30 days
            ps.setTimestamp(idx++, new Timestamp(cal.getTimeInMillis()));

            rs = ps.executeQuery();
            while ( rs.next() ) {
            	resultList.add(rs.getString(1));
            }

            if( logger.isDebugEnabled() ) logger.debug("--ESDG-- Query sales context by date range. Found (" + resultList.size() + "). date range: " + startDate + " - " + endDate);
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.debug("--ESDG-- Exception caught when querying sales context by date range. Found (" + resultList.size() + "). date range: " + startDate + " - " + endDate, ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

		if( resultList.size() > 0 ) {
			return resultList;
		} else {
			return null;
		}
	}

	/**
	 * Search order.
	 *
	 * @param phoneNumber the phone number
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param statusList the status list
	 * @return the collection
	 */
	public Collection<EsdgOrderDO_1> searchOrder(String phoneNumber, Date startDate, Date endDate, List<String> statusList) {
		HashMap<String, EsdgOrderDO_1> orderMap = new HashMap<String, EsdgOrderDO_1>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String statusListStr = null;
        try {
        	if( StringUtils.isEmpty(phoneNumber) || startDate == null || endDate == null || statusList == null || statusList.size() < 1 ) {
        		logger.warn("--ESDG-- Abort to query order database due to missing mandatory inputs.");
        		return null;
        	}
        	StringBuffer sbStatusList = new StringBuffer();
        	StringBuffer sbStatusSqlInClause = new StringBuffer();
        	if( statusList != null && statusList.size() > 0 ) {
    			for( Iterator<String> i = statusList.iterator(); i.hasNext(); ) {
    				sbStatusList.append(i.next()).append(", ");
    				sbStatusSqlInClause.append("?,");
    			}
    		}
        	statusListStr = sbStatusList.toString();
        	statusListStr = statusListStr.substring(0, statusListStr.length() - 2) + ")";

        	String statusSqlInClauseStr = sbStatusSqlInClause.toString();
        	statusSqlInClauseStr = statusSqlInClauseStr.substring(0, statusSqlInClauseStr.length() - 1);
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SELECT_ORDER_BY_SEARCH_CRITERIA + "(" + statusSqlInClauseStr + ") " + SELECT_ORDER_BY_SEARCH_CRITERIA_GROUP_BY);
            int idx = 1;
            ps.setString(idx++, phoneNumber);
            ps.setTimestamp(idx++, new Timestamp(startDate.getTime()));
            ps.setTimestamp(idx++, new Timestamp(endDate.getTime()));
            for( Iterator<String> i = statusList.iterator(); i.hasNext(); ) {
            	ps.setString(idx++, (String)i.next());
            }

            rs = ps.executeQuery();
            while ( rs.next() ) {
            	String ctxId = rs.getString(1);
            	String orderId = rs.getString(2);
            	long dataGenerationTimestamp = rs.getTimestamp(3).getTime();
            	EsdgOrderDO_1 savedOrder = orderMap.get(ctxId);
            	if( savedOrder != null ) {
            		logger.warn("--ESDG-- Duplicated order detected under the same context. salesContextId = " + ctxId);
            		if( savedOrder.getDataGenerationTimeInMills() > dataGenerationTimestamp ) {
            			continue;
            		}

            	}

        		savedOrder = new EsdgOrderDO_1();
        		savedOrder.setSalesContextId(ctxId);
        		savedOrder.setOrderId(orderId);
        		savedOrder.setDataGenerationTimeInMills(dataGenerationTimestamp);
        		orderMap.put(ctxId, savedOrder);
            }

            if( logger.isDebugEnabled() ) logger.debug("--ESDG-- Query order database. Found (" + orderMap.size() + "). Parameters: " + getQueryParameterLogString(phoneNumber, startDate, endDate, statusListStr));
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.debug("--ESDG-- Exception caught when querying order database. " + getQueryParameterLogString(phoneNumber, startDate, endDate, statusListStr), ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

		if( orderMap.size() > 0 ) {
			return orderMap.values();
		} else {
			return null;
		}
	}

	/**
	 * Gets the query parameter log string.
	 *
	 * @param phoneNumber the phone number
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param statusList the status list
	 * @return the query parameter log string
	 */
	private String getQueryParameterLogString(String phoneNumber, Date startDate, Date endDate, String statusList) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		StringBuffer sb = new StringBuffer().append("phoneNumber = ").append(phoneNumber);
		sb.append(" : startDate = ").append(startDate == null ? "null" : formatter.format(startDate));
		sb.append(" : endDate = ").append(endDate == null ? "null" : formatter.format(endDate));
		sb.append(" : status = ").append(statusList);
		return sb.toString();
	}

	/**
	 * Insert sales context.
	 *
	 * @param channelOrgTypeCd the channel org type cd
	 * @param channelOrgId the channel org id
	 * @param outletId the outlet id
	 * @param salesPersonId the sales person id
	 * @param salesPersonTypeCd the sales person type cd
	 * @param externalRefId the external ref id
	 * @param salesTransactionId the sales transaction id
	 * @param billingAccountNum the billing account num
	 * @param subscriberPhoneNum the subscriber phone num
	 * @return the esdg order d o_1
	 */
	public EsdgOrderDO_1 insertSalesContext(String channelOrgTypeCd, String channelOrgId, String outletId, String salesPersonId, String salesPersonTypeCd, String externalRefId, String salesTransactionId, String billingAccountNum, String subscriberPhoneNum) {
		return insertSalesContext(null, null, externalRefId, channelOrgTypeCd, channelOrgId, outletId, salesPersonId, salesPersonTypeCd, salesTransactionId, billingAccountNum, null, subscriberPhoneNum, null);
	}

	/**
	 * Insert sales context.
	 *
	 * @param salesInteractionId the sales interaction id
	 * @param salesInteractionExternalRefId the sales interaction external ref id
	 * @param salesContextExternalRefId the sales context external ref id
	 * @param channelOrgTypeCd the channel org type cd
	 * @param channelOrgId the channel org id
	 * @param outletId the outlet id
	 * @param salesPersonId the sales person id
	 * @param salesPersonTypeCd the sales person type cd
	 * @param salesTransactionId the sales transaction id
	 * @param billingAccountNum the billing account num
	 * @param contactPhoneNum the contact phone num
	 * @param subscriberPhoneNum the subscriber phone num
	 * @return the esdg order d o_1
	 */
	public EsdgOrderDO_1 insertSalesContext(String salesInteractionId, String salesInteractionExternalRefId, String salesContextExternalRefId,
			String channelOrgTypeCd, String channelOrgId, String outletId, String salesPersonId, String salesPersonTypeCd,
			String salesTransactionId, String billingAccountNum, String contactPhoneNum, String subscriberPhoneNum, String brandCd) {
		EsdgOrderDO_1 esdgOrder = new EsdgOrderDO_1();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

        	//get next sequence for interaction
        	if( StringUtils.isEmpty(salesInteractionId) ) {
        		ps = conn.prepareStatement(SELECT_SALES_INTERACTION_SEQ_NEXTVAL_STMT);
            	rs = ps.executeQuery();
                if ( !rs.next() ) {
                	if( ps != null ) {ps.close(); ps = null;}
        			if( rs != null ) {rs.close(); rs = null;}
                	String errorMsg = "Failed to get next sequence for interaction in database. salesTxId = " + salesTransactionId;
                    logger.error( "--ESDG-- " + errorMsg);
                    throw new Exception(errorMsg);
                }

                esdgOrder.setSalesInteractionId(rs.getString(1));
                esdgOrder.setSalesInteractionStartTimeInMills(Calendar.getInstance().getTimeInMillis());
                if( ps != null ) {ps.close(); ps = null;}
    			if( rs != null ) {rs.close(); rs = null;}

    			//insert new interaction
                ps = conn.prepareStatement(INSERT_SALES_INTERACTION_STMT);
                int idx = 1;
                ps.setString(idx++, esdgOrder.getSalesInteractionId());
                ps.setString(idx++, billingAccountNum);
                ps.setString(idx++, contactPhoneNum);
                ps.setTimestamp(idx++, new Timestamp(esdgOrder.getSalesInteractionStartTimeInMills()));
                ps.setString(idx++, StringUtils.isEmpty(salesInteractionExternalRefId) ? "NA" : salesInteractionExternalRefId.substring(0, Math.min(1000, salesInteractionExternalRefId.length()))); //external reference key max length
                ps.setString(idx++, this.dbUser);
                ps.setString(idx++, this.dbUser);
                int exeResult = ps.executeUpdate();
                if( ps != null ) {ps.close(); ps = null;}

                //insert new interaction status
                ps = conn.prepareStatement(INSERT_SALES_INTERACTION_LIFECYCLE_STMT);
                idx = 1;
                ps.setString(idx++, esdgOrder.getSalesInteractionId());
                ps.setTimestamp(idx++, new Timestamp(esdgOrder.getSalesInteractionStartTimeInMills()));
                ps.setString(idx++, channelOrgTypeCd);
                ps.setString(idx++, channelOrgId);
                ps.setString(idx++, salesPersonTypeCd);
                ps.setString(idx++, salesPersonId);
                ps.setString(idx++, CONTEXT_STATUS_CD_OPEN);
                ps.setTimestamp(idx++, new Timestamp(Calendar.getInstance().getTimeInMillis()));
                ps.setString(idx++, this.dbUser);
                ps.setString(idx++, this.dbUser);
                exeResult = ps.executeUpdate();
                if( ps != null ) {ps.close(); ps = null;}

                if ( logger.isDebugEnabled() ) logger.debug("--ESDG-- Saved new interaction to database (" + exeResult + "). salesInteractionId = " + esdgOrder.getSalesInteractionId() + " : salesTxId = " + salesTransactionId);
        	} else {
        		ps = conn.prepareStatement(SELECT_SALES_INTERACTION_BY_KEY_STMT);
        		int idx = 1;
                ps.setString(idx++, salesInteractionId);
            	rs = ps.executeQuery();
                if ( !rs.next() ) {
                	if( ps != null ) {ps.close(); ps = null;}
        			if( rs != null ) {rs.close(); rs = null;}
                	String errorMsg = "Failed to get interaction from database. salesInteractionId = " + salesInteractionId + " : salesTxId = " + salesTransactionId;
                    logger.error( "--ESDG-- " + errorMsg);
                    throw new Exception(errorMsg);
                }
            	Timestamp salesInteractionTimestamp = rs.getTimestamp(1);
            	esdgOrder.setSalesInteractionId(salesInteractionId);
                esdgOrder.setSalesInteractionStartTimeInMills(salesInteractionTimestamp.getTime());
                if( ps != null ) ps.close();
    			if( rs != null ) rs.close();

                if ( logger.isDebugEnabled() ) logger.debug("--ESDG-- Get existing interaction from database. salesInteractionId = " + esdgOrder.getSalesInteractionId() + " : salesTxId = " + salesTransactionId);
        	}

        	//get next sequence for context
        	ps = conn.prepareStatement(SELECT_SALES_CONTEXT_SEQ_NEXTVAL_STMT);
        	rs = ps.executeQuery();
            if ( !rs.next() ) {
            	if( ps != null ) {ps.close(); ps = null;}
    			if( rs != null ) {rs.close(); rs = null;}
            	String errorMsg = "Failed to get next sequence for context in database. salesInteractionId = " + esdgOrder.getSalesInteractionId() + " : salesTxId = " + salesTransactionId;
                logger.error( "--ESDG-- " + errorMsg);
                throw new Exception(errorMsg);
            }

            esdgOrder.setSalesContextId(rs.getString(1));
            esdgOrder.setExternalKey(salesContextExternalRefId);
            esdgOrder.setSubscriberPhoneNumber(subscriberPhoneNum);
            if( ps != null ) ps.close();
			if( rs != null ) rs.close();

            //insert new context
            ps = conn.prepareStatement(INSERT_SALES_CONTEXT_STMT);
            int idx = 1;
            ps.setString(idx++, esdgOrder.getSalesContextId());
            ps.setString(idx++, esdgOrder.getSalesInteractionId());
            ps.setTimestamp(idx++, new Timestamp(esdgOrder.getSalesInteractionStartTimeInMills()));
            ps.setString(idx++, StringUtils.isEmpty(esdgOrder.getExternalKey()) ? "NA" : esdgOrder.getExternalKey().substring(0, Math.min(100, esdgOrder.getExternalKey().length()))); //external key max length
            ps.setString(idx++, esdgOrder.getSubscriberPhoneNumber());
            ps.setString(idx++, this.dbUser);
            ps.setString(idx++, this.dbUser);
            ps.setString(idx++, brandCd);
            ps.executeUpdate();
            if( ps != null ) {ps.close(); ps = null;}

            //insert new context status
            ps = conn.prepareStatement(INSERT_SALES_CONTEXT_LIFECYCLE_STMT);
            idx = 1;
            ps.setString(idx++, esdgOrder.getSalesContextId());
            ps.setTimestamp(idx++, new Timestamp(esdgOrder.getSalesInteractionStartTimeInMills()));
            ps.setString(idx++, CONTEXT_STATUS_CD_OPEN);
            ps.setTimestamp(idx++, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            ps.setString(idx++, this.dbUser);
            ps.setString(idx++, this.dbUser);
            int exeResult = ps.executeUpdate();
            if( ps != null ) {ps.close(); ps = null;}

            conn.commit();

            esdgOrder.setSalesContextId(CONTEXT_ID_PREFIX + esdgOrder.getSalesContextId());
            if ( logger.isDebugEnabled() ) logger.debug("--ESDG-- Saved new context to database (" + exeResult + "). salesInteractionId = " + esdgOrder.getSalesInteractionId() + " : salesCtxId = " + esdgOrder.getSalesContextId() + " : salesTxId = " + salesTransactionId);
        } catch ( Exception ex ) {
        	try {
        		if (null != conn) {
        			conn.rollback();
        		}
        	} catch (Exception exx) {}

        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when saving new context to database. salesTxId = " + salesTransactionId, ex);
            return null;
        } finally {
            closeConnection(conn, ps, rs);
        }

        return esdgOrder;
	}

	/**
	 * Insert sales context raw data.
	 *
	 * @param rawData the raw data
	 * @return true, if successful
	 */
	public boolean insertSalesContextRawData(EsdgContextRawDataDO_1 rawData) {
	    HashSet<EsdgContextRawDataDO_1> rawDataSet = new HashSet<EsdgContextRawDataDO_1>();
	    rawDataSet.add(rawData);
	    return insertSalesContextRawData(rawDataSet);
	}

	/**
	 * Insert sales context raw data.
	 *
	 * @param rawDataCollection the raw data collection
	 * @return true, if successful
	 */
	public boolean insertSalesContextRawData(Collection<EsdgContextRawDataDO_1> rawDataCollection) {
		boolean result = true;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

        	ps = conn.prepareStatement(INSERT_SALES_CONTEXT_RAW_DATA_STMT);
        	for( Iterator<EsdgContextRawDataDO_1> i = rawDataCollection.iterator(); i.hasNext(); ) {
        		EsdgContextRawDataDO_1 rawData = (EsdgContextRawDataDO_1)i.next();
        		int idx = 1;
                ps.setString(idx++, removeContextPrefix(rawData.getSalesContextId()));
                ps.setTimestamp(idx++, new Timestamp(rawData.getSalesInteractionStartTimeInMills()));
                ps.setTimestamp(idx++, new Timestamp(rawData.getDataGenerationTimeInMills()));
                ps.setString(idx++, rawData.getExternalKey());
                ps.setBytes(idx++, SerializationUtils.serialize(rawData.getRawData()));
                ps.setString(idx++, this.dbUser);
                ps.setString(idx++, this.dbUser);
                ps.addBatch();
        	}

            int[] exeResults = ps.executeBatch();
            conn.commit();
            if ( logger.isDebugEnabled() ) {
            	StringBuffer sb = new StringBuffer();
            	for( int i = 0; i < exeResults.length; i++ ) {
            		sb.append(exeResults[i]).append(",");
            	}
            	if( logger.isDebugEnabled() ) logger.debug("--ESDG-- Saved raw data set to database (" + sb.toString() + "). size = " + rawDataCollection.size());
            }
        } catch ( Exception ex ) {
        	try {
        		if (null != conn) {
        			conn.rollback();
        		}
        	} catch (Exception exx) {}

        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when saving raw data set to database. size = " + rawDataCollection.size(), ex);
            result = false;
        } finally {
            closeConnection(conn, ps, null);
        }

        return result;
	}

	/**
	 * Select sales context raw data.
	 *
	 * @param key the key
	 * @return the esdg context raw data d o_1
	 */
	public EsdgContextRawDataDO_1 selectSalesContextRawData(String key) {
		EsdgContextRawDataDO_1 rawData = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SELECT_SALES_CONTEXT_RAW_DATA_BY_KEY_STMT);
            int idx = 1;
            ps.setString(idx++, key);

            rs = ps.executeQuery();
            if ( !rs.next() ) {
                logger.warn( "--ESDG-- Raw data not found in database. key = " + key);
            } else {
            	String ctxId = rs.getString(1);
            	Timestamp dataGenerationTimestamp = rs.getTimestamp(2);
            	byte[] bytes = rs.getBytes(3);
            	if( bytes == null || bytes.length < 1 ) {
            		logger.warn( "--ESDG-- Raw data contains emtpy data in database. key = " + key);
            	} else {
            		rawData = new EsdgContextRawDataDO_1();
            		rawData.setRawData((Serializable)SerializationUtils.deserialize(bytes));
            		rawData.setSalesContextId(CONTEXT_ID_PREFIX + ctxId);
            		rawData.setExternalKey(key);
            		if( dataGenerationTimestamp != null ) rawData.setDataGenerationTimeInMills(dataGenerationTimestamp.getTime());
            		if ( logger.isDebugEnabled() ) logger.debug("--ESDG-- Retrieved raw data from database. key = " + key + " : contextId = " + ctxId);
            	}
            }
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when reading raw data from database. key = " + key, ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return rawData;
	}

	/**
	 * Select sales context raw data.
	 *
	 * @param oCollection the o collection
	 * @return the map
	 */
	public Map selectSalesContextRawData(Collection oCollection) {
		HashMap rawDataMap = new HashMap();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String keySetStr = null;
        try {
        	StringBuffer sb = new StringBuffer();
        	for( int i = 0; i < oCollection.size(); i++ ) {
        		sb.append("?,");
        	}
        	keySetStr = sb.toString();
        	keySetStr = keySetStr.substring(0, keySetStr.length() - 1);
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SELECT_SALES_CONTEXT_RAW_DATA_BY_KEY_SET_STMT + "(" + keySetStr + ")");
            int idx = 1;
            for( Iterator i = oCollection.iterator(); i.hasNext(); ) {
            	ps.setString(idx++, (String)i.next());
            }

            rs = ps.executeQuery();
            while ( rs.next() ) {
            	String ctxId = rs.getString(1);
            	Timestamp dataGenerationTimestamp = rs.getTimestamp(2);
            	String key = rs.getString(3);
            	byte[] bytes = rs.getBytes(4);
            	if( bytes == null || bytes.length < 1 ) {
            		logger.warn( "--ESDG-- Raw data contains emtpy data in database. key = " + key);
            	} else {
            		EsdgContextRawDataDO_1 rawData = new EsdgContextRawDataDO_1();
            		rawData.setRawData((Serializable)SerializationUtils.deserialize(bytes));
            		rawData.setSalesContextId(CONTEXT_ID_PREFIX + ctxId);
            		rawData.setExternalKey(key);
            		if( dataGenerationTimestamp != null ) rawData.setDataGenerationTimeInMills(dataGenerationTimestamp.getTime());

            		rawDataMap.put(key, rawData);
            		if ( logger.isDebugEnabled() ) logger.debug("--ESDG-- Retrieved raw data from database. key = " + key + " : contextId = " + ctxId);
            	}
            }
            if( rawDataMap.size() < 1 ) rawDataMap = null;
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when reading raw data set from database. key set = " + keySetStr, ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return rawDataMap;
	}

	/**
	 * Insert order.
	 *
	 * @param esdgOrder the esdg order
	 * @return true, if successful
	 */
	public boolean insertOrder(EsdgOrderDO_1 esdgOrder) {
		boolean result = true;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Clob clob = null;
        String orderId = null;
        String salesContextId = esdgOrder.getSalesContextId();
        String salesContextIdWithoutPrefix = removeContextPrefix(salesContextId);
        long salesInteractionStartTimeInMills = 0;
        String salesContextExtRefId = null;
        String lastSalesOrderStatusCd = null;
        long lastSalesOrderStatusEffectiveTimeInMills = 0;
        String salesFulfillmentId = null;
        String lastSalesFulfillmentStatusCd = null;
        long lastSalesFulfillmentStatusTimeInMills = 0;
        String lastSalesOrderJsonString = null;
        String ban = null;
        String phone = null;

        long methodStartTs = Calendar.getInstance().getTimeInMillis();
        long insertOrderTime = 0;
        long insertOrderStatusTime = 0;
        long insertOrderBinaryTime = 0;
        long insertOrderFulfillmentTime = 0;
        long insertOrderClassifierTime = 0;

        String salesInteractionId = null;

        if( StringUtils.isEmpty(salesContextId) ||
    		esdgOrder.getDataGenerationTimeInMills() < 1 ) {
        	logger.error("--ESDG-- Failed to save order to database due to missing input data. salesCtxId = " + esdgOrder.getSalesContextId());
        	return false;
        }

        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

        	//validate sales context id and get partition key
        	ps = conn.prepareStatement(SELECT_SALES_INTRACTN_START_TS_BY_CONTEXT_ID_STMT);
            int idx = 1;
            ps.setString(idx++, salesContextIdWithoutPrefix);

            rs = ps.executeQuery();
            if ( rs.next() ) {
            	idx = 1;
            	salesInteractionId = rs.getString(idx++);
            	salesInteractionStartTimeInMills = rs.getTimestamp(idx++).getTime();
            	salesContextExtRefId = rs.getString(idx++);
            	//if not exist, fallback to data creation timestamp for partition key
            	if( salesInteractionStartTimeInMills < 0 ) {
            		salesInteractionStartTimeInMills = esdgOrder.getDataGenerationTimeInMills();
            	}
            	phone = rs.getString(idx++);
            	ban = rs.getString(idx++);
            } else {
            	if( ps != null ) {ps.close(); ps = null;}
    			if( rs != null ) {rs.close(); rs = null;}
            	logger.error( "--ESDG-- Failed to save order to database due to unrecognized sales context id. salesCtxId = " + salesContextId);
            	return false;
            }
			if( ps != null ) {ps.close(); ps = null;}
			if( rs != null ) {rs.close(); rs = null;}

			logDebugInfo(salesContextId, "--ESDG-- esdgOrder.getSalesContextExternalRefId() [" + esdgOrder.getSalesContextExternalRefId() +
					"] salesContextExtRefId [" + salesContextExtRefId + "] ban=" + ban + ", phone=" + phone);
			//update sales context external reference id
			if( !StringUtils.isEmpty(esdgOrder.getSalesContextExternalRefId()) && !StringUtils.isEmpty(salesContextExtRefId) &&
					!salesContextExtRefId.equals(salesContextExtRefId) ) {
				salesContextExtRefId = esdgOrder.getSalesContextExternalRefId();
				ps = conn.prepareStatement(UPDATE_SALES_CONTEXT_STMT);
                idx = 1;
                ps.setString(idx++, salesContextExtRefId.substring(0, Math.min(100, salesContextExtRefId.length())));  //status_cd max length
                ps.setString(idx++, this.dbUser);
                ps.setString(idx++, salesContextIdWithoutPrefix);
                ps.executeUpdate();
                if( ps != null ) {ps.close(); ps = null;}
                logDebugInfo(salesContextId, "--ESDG-- UPDATE_SALES_CONTEXT_STMT");
			}

			if (esdgOrder.getSubscriberPhoneNumber() != null && !esdgOrder.getSubscriberPhoneNumber().equals(phone)) {
				ps = conn.prepareStatement(UPDATE_SALES_CONTEXT_WITH_PHONE_STMT);
                idx = 1;
               	ps.setString(idx++, esdgOrder.getSubscriberPhoneNumber());
                ps.setString(idx++, this.dbUser);
                ps.setString(idx++, salesContextIdWithoutPrefix);
                ps.executeUpdate();
                if( ps != null ) {ps.close(); ps = null;}
                logDebugInfo(salesContextId, "--ESDG-- UPDATE_SALES_CONTEXT_WITH_PHONE_STMT");
			}

			// update account in the interaction table if it's different
			if (salesInteractionId != null && esdgOrder.getBillingAccountNumber() != null && !esdgOrder.getBillingAccountNumber().equals(ban) ) {
				ps = conn.prepareStatement(UPDATE_SALES_INTERACTION_STMT);
	            idx = 1;
	            ps.setString(idx++, esdgOrder.getBillingAccountNumber());
	            ps.setString(idx++, this.dbUser);
	            ps.setString(idx++, salesInteractionId);
	            ps.executeUpdate();
	            if( ps != null ) {ps.close(); ps = null;}
	            logDebugInfo(salesContextId, "--ESDG-- UPDATE_SALES_INTERACTION_STMT");
			}

			//get latest order
            ps = conn.prepareStatement(SELECT_SALES_ORDER_ID_BY_CONTEXT_STMT);
            idx = 1;
            ps.setString(idx++, salesContextIdWithoutPrefix);

            rs = ps.executeQuery();
            if ( !rs.next() ) {
            	if( ps != null ) {ps.close(); ps = null;}
    			if( rs != null ) {rs.close(); rs = null;}

            	//get next sequence for order
            	ps = conn.prepareStatement(SELECT_SALES_ORDER_SEQ_NEXTVAL_STMT);
            	rs = ps.executeQuery();
                if ( !rs.next() ) {
                	String errorMsg = "Failed to get next sequence for order in database. salesInteractionId = " + esdgOrder.getSalesInteractionId() + " : salesCtxId = " + esdgOrder.getSalesContextId();
                    logger.error( "--ESDG-- " + errorMsg);
                    throw new Exception(errorMsg);
                }

                orderId = rs.getString(1);
                if( ps != null ) {ps.close(); ps = null;}
    			if( rs != null ) {rs.close(); rs = null;}

            	//insert new order
    			long startTs = Calendar.getInstance().getTimeInMillis();
                ps = conn.prepareStatement(INSERT_SALES_ORDER_STMT);
                idx = 1;
                ps.setString(idx++, orderId);
                ps.setString(idx++, salesContextIdWithoutPrefix);
                ps.setTimestamp(idx++, new Timestamp(salesInteractionStartTimeInMills));
                ps.setString(idx++, this.dbUser);
                ps.setString(idx++, this.dbUser);
                ps.setString(idx++, esdgOrder.getTypeCode());
                ps.executeUpdate();
                if( ps != null ) {ps.close(); ps = null;}
                insertOrderTime = Calendar.getInstance().getTimeInMillis() - startTs;
                logDebugInfo(salesContextId, "--ESDG-- INSERT_SALES_ORDER_STMT");
            } else {
            	//get order id
            	orderId = rs.getString(1);
            	if( ps != null ) {ps.close(); ps = null;}
    			if( rs != null ) {rs.close(); rs = null;}

    			//get latest order status, if there is subsequent update
            	if( esdgOrder.getOrderStatusChangeList() != null ) {
	            	ps = conn.prepareStatement(SELECT_LATEST_EFFECTIVE_ORDER_STATUS_STMT);
	            	idx = 1;
	                ps.setString(idx++, orderId);
	                ps.setString(idx++, orderId);
	                rs = ps.executeQuery();
	                if ( rs.next() ) {
	                	idx = 1;
	                	lastSalesOrderStatusCd = rs.getString(idx++);
	                	lastSalesOrderStatusEffectiveTimeInMills = rs.getTimestamp(idx++).getTime();
	                }
	                if( ps != null ) {ps.close(); ps = null;}
	    			if( rs != null ) {rs.close(); rs = null;}
            	}

            	//get latest order binary, if there is subsequent update
                if( !StringUtils.isEmpty(esdgOrder.getJsonOrder()) ) {
	                ps = conn.prepareStatement(SELECT_LATEST_ORDER_DATA_STMT);
	                idx = 1;
	                ps.setString(idx++, orderId);
	                ps.setString(idx++, orderId);
	                rs = ps.executeQuery();
	                if ( rs.next() ) {
	                	byte[] bytes = rs.getBytes(1);
	                	if( bytes != null && bytes.length > 0 ) {
	                		lastSalesOrderJsonString = (String)SerializationUtils.deserialize(bytes);
	                	}
	                }
	                if( ps != null ) {ps.close(); ps = null;}
	    			if( rs != null ) {rs.close(); rs = null;}
                }

                //get latest fulfillment, if there is subsequent update
                if( !StringUtils.isEmpty(esdgOrder.getFulfillmentStatus()) || esdgOrder.getOrderFulfillmentStatusChangeList() != null ) {
	            	ps = conn.prepareStatement(SELECT_SALES_FULFILLMENT_STMT);
	                ps.setString(1, salesContextIdWithoutPrefix);
	                rs = ps.executeQuery();
	                if ( rs.next() ) {
	                	idx = 1;
	                	salesFulfillmentId = rs.getString(idx++);
	                	lastSalesFulfillmentStatusCd = rs.getString(idx++);
	                	lastSalesFulfillmentStatusTimeInMills = rs.getTimestamp(idx++).getTime();
	                }
	                if( ps != null ) {ps.close(); ps = null;}
	    			if( rs != null ) {rs.close(); rs = null;}
                }
            }

            int exeResult = 0;
            boolean isStatusChanged = false;
            ArrayList<EsdgOrderStatusHistoryDO> statusChangeList = esdgOrder.getOrderStatusChangeList();
            if( statusChangeList != null ) {
            	long startTs = Calendar.getInstance().getTimeInMillis();
            	ps = conn.prepareStatement(INSERT_SALES_ORDER_LIFECYCLE_STMT);
            	for( EsdgOrderStatusHistoryDO statusHistory : statusChangeList ) {
            		if( statusHistory.getOrderStatusTimeInMills() > lastSalesOrderStatusEffectiveTimeInMills ) {
            			logDebugInfo(salesContextId, "--ESDG-- statusHistory.getOrderStatus() [" + statusHistory.getOrderStatus() +
            					"] lastSalesOrderStatusCd [" + lastSalesOrderStatusCd + "]");
            			if( !StringUtils.isEmpty(statusHistory.getOrderStatus()) && !statusHistory.getOrderStatus().equals(lastSalesOrderStatusCd) ) {
	            			//insert new order status
	                        idx = 1;
	                        ps.setString(idx++, orderId);
	                        ps.setTimestamp(idx++, new Timestamp(salesInteractionStartTimeInMills));
	                        ps.setString(idx++, orderId);
	                        ps.setString(idx++, statusHistory.getOrderStatus().substring(0, Math.min(30, statusHistory.getOrderStatus().length()))); //status_cd max length
	                        ps.setTimestamp(idx++, new Timestamp(statusHistory.getOrderStatusTimeInMills()));
	                        ps.setString(idx++, this.dbUser);
	                        ps.setString(idx++, this.dbUser);
	                        exeResult = ps.executeUpdate();
	                        lastSalesOrderStatusCd = statusHistory.getOrderStatus();
	                        isStatusChanged = true;
	                        logDebugInfo(salesContextId, "--ESDG-- INSERT_SALES_ORDER_LIFECYCLE_STMT isStatusChanged = true");
            			}
            		}
            	}
            	if( ps != null ) {ps.close(); ps = null;}
            	insertOrderStatusTime = Calendar.getInstance().getTimeInMillis() - startTs;
            }

            boolean isOrderBinaryChanged = false;

            logDebugInfo(salesContextId, "--ESDG-- esdgOrder.getJsonOrder() [" + esdgOrder.getJsonOrder() +
					"] lastSalesOrderJsonString [" + lastSalesOrderJsonString + "]");
            if( !StringUtils.isEmpty(esdgOrder.getJsonOrder()) && !esdgOrder.getJsonOrder().equals(lastSalesOrderJsonString) ) {
            	long startTs = Calendar.getInstance().getTimeInMillis();
	            //insert order data
	            ps = conn.prepareStatement(INSERT_SALES_ORDER_RAW_DATA_STMT);
	            idx = 1;
	            ps.setString(idx++, orderId);
	            ps.setTimestamp(idx++, new Timestamp(salesInteractionStartTimeInMills));
	            ps.setTimestamp(idx++, new Timestamp(esdgOrder.getDataGenerationTimeInMills()));
	            ps.setString(idx++, esdgOrder.getExternalKey() == null ? "NA" : esdgOrder.getExternalKey().substring(0, Math.min(100, esdgOrder.getExternalKey().length()))); //external ref id max length
	            ps.setBytes(idx++, SerializationUtils.serialize(esdgOrder.getJsonOrder()));
	            ps.setString(idx++, this.dbUser);
	            ps.setString(idx++, this.dbUser);
	            exeResult = ps.executeUpdate();
	            if( ps != null ) {ps.close(); ps = null;}
	            isOrderBinaryChanged = true;
	            insertOrderBinaryTime = Calendar.getInstance().getTimeInMillis() - startTs;
	            logDebugInfo(salesContextId, "--ESDG-- INSERT_SALES_ORDER_RAW_DATA_STMT");
            }

            boolean isFulfillmentStatusChanged = false;
            boolean isFulfillmentDataChanged = false;
            String fulfillmentStatus = esdgOrder.getFulfillmentStatus();
            long fulfillmentStatusTimeInMills = esdgOrder.getFulfillmentStatusTimeInMills() == 0 ? Calendar.getInstance().getTimeInMillis() : esdgOrder.getFulfillmentStatusTimeInMills();
            ArrayList<EsdgOrderFulfillmentStatusHistoryDO> fulfillmentChangeList = esdgOrder.getOrderFulfillmentStatusChangeList();

            logDebugInfo(salesContextId, "--ESDG-- fulfillmentStatus [" + fulfillmentStatus +
					"] esdgOrder.getFulfillmentStatusTimeInMills() [" + esdgOrder.getFulfillmentStatusTimeInMills() + "] lastSalesFulfillmentStatusTimeInMills [" +
					lastSalesFulfillmentStatusTimeInMills + "] fulfillmentChangeList != null [" + (fulfillmentChangeList != null) + "]");

            if( (!StringUtils.isEmpty(fulfillmentStatus) && esdgOrder.getFulfillmentStatusTimeInMills() > lastSalesFulfillmentStatusTimeInMills) || fulfillmentChangeList != null ) {
            	long startTs = Calendar.getInstance().getTimeInMillis();
            	if( StringUtils.isEmpty(salesFulfillmentId) ) {
            		//get next sequence for fulfillment
                	ps = conn.prepareStatement(SELECT_SALES_FULFILLMENT_SEQ_NEXTVAL_STMT);
                	rs = ps.executeQuery();
                    if ( !rs.next() ) {
                    	logger.error( "--ESDG-- Failed to get next sequence for order fulfillment in database. salesInteractionId = " + esdgOrder.getSalesInteractionId() + " : salesCtxId = " + esdgOrder.getSalesContextId());
                    } else {
                    	salesFulfillmentId = rs.getString(1);
                    }
                    if( ps != null ) {ps.close(); ps = null;}
	    			if( rs != null ) {rs.close(); rs = null;}

	    			if( !StringUtils.isEmpty(salesFulfillmentId) ) {
	            		//insert new sales fulfillment
	        			ps = conn.prepareStatement(INSERT_SALES_FULFILLMENT_STMT);
	                    idx = 1;
	                    ps.setString(idx++, salesFulfillmentId);
	                    ps.setString(idx++, salesContextIdWithoutPrefix);
	                    ps.setTimestamp(idx++, new Timestamp(salesInteractionStartTimeInMills));
	                    ps.setString(idx++, StringUtils.isEmpty(fulfillmentStatus) ? EsdgConstants.ESDG_FULFILLMENT_STATUS_UNKNOWN : fulfillmentStatus.substring(0, Math.min(10, fulfillmentStatus.length())));  //status_cd max length
	                    ps.setTimestamp(idx++, new Timestamp(fulfillmentStatusTimeInMills));
	                    ps.setString(idx++, this.dbUser);
	                    ps.setString(idx++, this.dbUser);
	                    exeResult = ps.executeUpdate();
	                    if( ps != null ) {ps.close(); ps = null;}
	                    isFulfillmentStatusChanged = true;
	                    logDebugInfo(salesContextId, "--ESDG--INSERT_SALES_FULFILLMENT_STMT");
	    			}
                } else if( !StringUtils.isEmpty(fulfillmentStatus) && esdgOrder.getFulfillmentStatusTimeInMills() > lastSalesFulfillmentStatusTimeInMills ) {
            		//update sales fulfillment
                	ps = conn.prepareStatement(UPDATE_SALES_FULFILLMENT_STMT);
                    idx = 1;
                    ps.setString(idx++, fulfillmentStatus.substring(0, Math.min(10, fulfillmentStatus.length())));  //status_cd max length
                    ps.setTimestamp(idx++, new Timestamp(fulfillmentStatusTimeInMills));
                    ps.setString(idx++, this.dbUser);
                    ps.setString(idx++, salesFulfillmentId);
                    exeResult = ps.executeUpdate();
                    if( ps != null ) {ps.close(); ps = null;}
                    isFulfillmentStatusChanged = true;
                    logDebugInfo(salesContextId, "--ESDG--UPDATE_SALES_FULFILLMENT_STMT");
            	}

	        	if( fulfillmentChangeList != null ) {
	        		//find existing fulfillment
	        		HashMap<String, FulfillmentTxnDataSummary> existingFulfillmentTxnHistoryMap = new HashMap<String, FulfillmentTxnDataSummary>();
	        		ps = conn.prepareStatement(SELECT_SALES_FULFILLMENT_TXN_DATA_STMT);
		            ps.setString(1, salesFulfillmentId);
		            rs = ps.executeQuery();
		            while( rs.next() ) {
		            	idx = 1;
		            	FulfillmentTxnDataSummary existingHistory = new FulfillmentTxnDataSummary();
		            	existingHistory.fulfillmentTxnDataId = rs.getString(idx++);
		            	String fulfillmentType = rs.getString(idx++);
		            	existingHistory.fulfillmentTxnTimeInMills = rs.getTimestamp(idx++).getTime();
		            	existingHistory.retryCount = rs.getLong(idx++);
	                	existingFulfillmentTxnHistoryMap.put(fulfillmentType, existingHistory);
		            }
		            if( ps != null ) {ps.close(); ps = null;}
	    			if( rs != null ) {rs.close(); rs = null;}

		            //sort into add/update list
		            ArrayList<EsdgOrderFulfillmentStatusHistoryDO> addList = new ArrayList<EsdgOrderFulfillmentStatusHistoryDO>();
		            ArrayList<FulfillmentTxnDataSummary> updateList = new ArrayList<FulfillmentTxnDataSummary>();
		            for( EsdgOrderFulfillmentStatusHistoryDO fulfillmentHistory : fulfillmentChangeList ) {
		            	FulfillmentTxnDataSummary existingHistory = existingFulfillmentTxnHistoryMap.get(fulfillmentHistory.getFulfillmentTypeCd());
		            	if( existingHistory == null ) {
		            		addList.add(fulfillmentHistory);
		            	} else if( fulfillmentHistory.getFulfillmentStatusTimeInMills() > existingHistory.fulfillmentTxnTimeInMills ) {
		            		existingHistory.updatedFulfillment = fulfillmentHistory;
		            		updateList.add(existingHistory);
		            	}
		            }

		            if( !addList.isEmpty() ) {
		        		ps = conn.prepareStatement(INSERT_SALES_FULFILLMENT_TXN_DATA_STMT);
		            	for( EsdgOrderFulfillmentStatusHistoryDO fulfillmentHistory : addList ) {
	                        idx = 1;
	                        ps.setString(idx++, salesFulfillmentId);
	                        ps.setTimestamp(idx++, new Timestamp(salesInteractionStartTimeInMills));
	                        ps.setString(idx++, fulfillmentHistory.getFulfillmentTypeCd().substring(0, Math.min(30, fulfillmentHistory.getFulfillmentTypeCd().length())));  //status_cd max length
	                        ps.setTimestamp(idx++, new Timestamp(fulfillmentHistory.getFulfillmentStatusTimeInMills()));
	                        if( !StringUtils.isEmpty(fulfillmentHistory.getFulfillmentTransactionJsonData()) ) {
	                        	clob = conn.createClob();
	                        	clob.setString(1, fulfillmentHistory.getFulfillmentTransactionJsonData());
	                        	ps.setClob(idx++, clob);
	                        } else {
	                        	ps.setString(idx++, "NA");
	                        }
	                        ps.setString(idx++, fulfillmentHistory.isSuccessful() ? "N" : "Y");
	                        ps.setString(idx++, this.dbUser);
	                        ps.setString(idx++, this.dbUser);
	                        exeResult = ps.executeUpdate();
	                        isFulfillmentDataChanged = true;
		            	}
		            	logDebugInfo(salesContextId, "--ESDG-- addList size [" + addList.size() + "] INSERT_SALES_FULFILLMENT_TXN_DATA_STMT");
		            	if( ps != null ) {ps.close(); ps = null;}
		            	if( clob != null ) {clob.free(); clob = null;}
		            }

		            if( !updateList.isEmpty() ) {
		            	ps = conn.prepareStatement(UPDATE_SALES_FULFILLMENT_TXN_DATA_STMT);
		            	for( FulfillmentTxnDataSummary fulfillmentHistory : updateList ) {
	                        idx = 1;
	                        ps.setTimestamp(idx++, new Timestamp(fulfillmentHistory.updatedFulfillment.getFulfillmentStatusTimeInMills()));
	                        if( !StringUtils.isEmpty(fulfillmentHistory.updatedFulfillment.getFulfillmentTransactionJsonData()) ) {
	                        	clob = conn.createClob();
	                        	clob.setString(1, fulfillmentHistory.updatedFulfillment.getFulfillmentTransactionJsonData());
	                        	ps.setClob(idx++, clob);
	                        } else {
	                        	ps.setString(idx++, "NA");
	                        }
	                        ps.setString(idx++, fulfillmentHistory.updatedFulfillment.isSuccessful() ? "N" : "Y");
	                        ps.setLong(idx++, fulfillmentHistory.retryCount + 1);
	                        ps.setString(idx++, this.dbUser);
	                        ps.setString(idx++, fulfillmentHistory.fulfillmentTxnDataId);
	                        exeResult = ps.executeUpdate();
	                        isFulfillmentDataChanged = true;
		            	}
		            	logDebugInfo(salesContextId, "--ESDG-- updateList size [" + updateList.size() + "] UPDATE_SALES_FULFILLMENT_TXN_DATA_STMT");
		            	if( ps != null ) {ps.close(); ps = null;}
		            	if( clob != null ) {clob.free(); clob = null;}
		            }
	        	}
	        	insertOrderFulfillmentTime = Calendar.getInstance().getTimeInMillis() - startTs;
            }

            //classifer
            HashMap<String, String> salesClassifierMap = esdgOrder.getOrderClassifierMap();
            if( salesClassifierMap != null ) {
            	long startTs = Calendar.getInstance().getTimeInMillis();
    			ps = conn.prepareStatement(SELECT_SALES_CLASSIFIERS_STMT);
    			idx = 1;
    			ps.setString(idx++, salesContextIdWithoutPrefix);
    			rs = ps.executeQuery();
    			HashMap<String, String> existingClassifierMap = new HashMap<String, String>();
    			while( rs.next() ) {
    				idx = 1;
    				existingClassifierMap.put(rs.getString(idx++), rs.getString(idx++));
    			}
    			if( ps != null ) {ps.close(); ps = null;}
    			if( rs != null ) {rs.close(); rs = null;}

            	ps = conn.prepareStatement(INSERT_SALES_CLASSIFIER_STMT);
            	for( Map.Entry<String, String> entry : salesClassifierMap.entrySet() ) {
            		if( !existingClassifierMap.containsKey(entry.getKey()) && !StringUtils.isEmpty(entry.getValue()) ) {
                        idx = 1;
                        ps.setString(idx++, salesContextIdWithoutPrefix);
                        ps.setTimestamp(idx++, new Timestamp(salesInteractionStartTimeInMills));
                        ps.setString(idx++, entry.getKey());
                        ps.setString(idx++, entry.getValue());
                        ps.setString(idx++, this.dbUser);
                        ps.setString(idx++, this.dbUser);
                        exeResult = ps.executeUpdate();
            		}
            	}
            	logDebugInfo(salesContextId, "--ESDG-- salesClassifierMap size [" + salesClassifierMap.size() + "] INSERT_SALES_CLASSIFIER_STMT");
            	if( ps != null ) {ps.close(); ps = null;}

	            insertOrderClassifierTime = Calendar.getInstance().getTimeInMillis() - startTs;
            }

            //update context lifecycle to reflect any change to this sales. This timestamp will be used to scan new change to any sales
            if( isFulfillmentStatusChanged || isOrderBinaryChanged || isStatusChanged ) {
            	ps = conn.prepareStatement(UPDATE_SALES_CONTEXT_LIFECYCL_STMT);
                idx = 1;
                ps.setTimestamp(idx++, new Timestamp(Calendar.getInstance().getTimeInMillis()));
                ps.setString(idx++, this.dbUser);
                ps.setString(idx++, salesContextIdWithoutPrefix);
                ps.executeUpdate();
                if( ps != null ) {ps.close(); ps = null;}
                logDebugInfo(salesContextId, "--ESDG-- UPDATE_SALES_CONTEXT_LIFECYCL_STMT");
            }

            //Spruce: Insert or Update Sales Order into temp table MTS_SALES_ORDER
            if(esdgOrder.isMTSTransitionOrderInd()) {
            	// salesInteractionId does not populate from OrderDO to EsdgOrderDO_1.
            	// It's retrieved from SALES_CONTEXT table.
            	insertUpdateMTSOrder(conn, esdgOrder, salesInteractionId);
            }

            //SAP - Waybill
            if (!StringUtils.isEmpty(esdgOrder.getShipmentStatus())) {
            	ps = conn.prepareStatement(MERGE_SALES_SHIPMENT_STMT);
                idx = 1;
  //              ? INTRACTN_START_TS, ? SALES_CONTEXT_ID FROM DUAL) S "

                ps.setTimestamp(idx++, new Timestamp(Calendar.getInstance().getTimeInMillis()));
                ps.setString(idx++, this.dbUser);
                ps.setTimestamp(idx++, new Timestamp(Calendar.getInstance().getTimeInMillis()));
                ps.setString(idx++, this.dbUser);
                ps.setString(idx++, esdgOrder.getShipmentTrackingNum());
                if (StringUtils.isEmpty(esdgOrder.getSapOrderNum()))
                	ps.setString(idx++, " ");
                else
                	ps.setString(idx++, esdgOrder.getSapOrderNum());
                ps.setTimestamp(idx++, new Timestamp(Calendar.getInstance().getTimeInMillis()));
                ps.setString(idx++, esdgOrder.getShipmentStatus());
                ps.setTimestamp(idx++, new Timestamp(Calendar.getInstance().getTimeInMillis()));
                ps.setLong(idx++, removeAlpha(esdgOrder.getSalesContextId()));
                ps.executeUpdate();
                if( ps != null ) {ps.close(); ps = null;}
                logDebugInfo(salesContextId, "--ESDG-- MERGE_UPDATE_SALES_SHIPMENT_STMT");
            }


            conn.commit();

            esdgOrder.setOrderId(orderId);
            if ( logger.isDebugEnabled() ) {
            	logger.debug(new StringBuffer().append("--ESDG-- Saved order to database (").append(exeResult).append("). order id = ").append(orderId).append(" : salesCtxId = ").append(esdgOrder.getSalesContextId())
            		.append(" : status changed = ").append(isStatusChanged).append(" : binary changed = ").append(isOrderBinaryChanged)
            		.append(" : fulfillment status changed = ").append(isFulfillmentStatusChanged).append(" : fulfillment data changed = ").append(isFulfillmentDataChanged).toString());
            }
            logger.info(new StringBuffer().append("--ESDG-- Saved order to database (").append(exeResult).append("). order id = ").append(orderId).append(" : salesCtxId = ").append(esdgOrder.getSalesContextId())
            		.append(" : total time = ").append(Calendar.getInstance().getTimeInMillis() - methodStartTs)
            		.append(" : order = ").append(insertOrderTime)
            		.append(" : order status = ").append(insertOrderStatusTime)
            		.append(" : binary = ").append(insertOrderBinaryTime)
            		.append(" : classifier = ").append(insertOrderClassifierTime)
            		.append(" : fulfillment = ").append(insertOrderFulfillmentTime).toString());
        } catch ( Exception ex ) {
        	try {
        		if (null != conn) {
        			conn.rollback();
        		}
        	} catch (Exception exx) {}

        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	StringBuffer sb = new StringBuffer()
        			.append("--ESDG-- Exception caught when saving order to database. salesCtxId = ").append(esdgOrder.getSalesContextId()).append("\n")
        			.append(esdgDatabaseConnectionFactory.getStatistics()).append("\n")
        			.append(esdgOrder.getJournal());
        	logger.error(sb.toString(), ex);
            result = false;
        } finally {
			try {
             	if( clob != null ) {clob.free(); clob = null;}
            } catch( Exception ex) {}
            closeConnection(conn, ps, rs);
        }

        return result;
	}

	/**
	 * Insert order.
	 *
	 * @param EsdgOrderContractDO_1 the esdg order contract
	 * @return true, if successful
	 */
	public boolean insertOrderContract(EsdgOrderContractDO_1 esdgOrder) {
		boolean result = true;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Clob clob = null;
        String orderId = "";
        String orderContractId = null;
        String salesContextId = esdgOrder.getSalesContextId();
        String salesContextIdWithoutPrefix = removeContextPrefix(salesContextId);
        String bundleId = esdgOrder.getBundleId();
        long salesInteractionStartTimeInMills = 0;
        String salesContextExtRefId = null;
        long insertOrderBinaryTime = 0;
        long methodStartTs = Calendar.getInstance().getTimeInMillis();
        String salesInteractionId = null;
        String externalTxnRefID = "";


        try {
        	if( !StringUtils.isEmpty(esdgOrder.getJsonOrderContract())) {
        		long startTs = Calendar.getInstance().getTimeInMillis();
        		int exeResult = 0;
        		int idx = 1;

        		conn = esdgDatabaseConnectionFactory.getDBConnection();

        		//1. get orderContractId
        		//add it when sequence is available.
            	ps = conn.prepareStatement(SELECT_SALES_ORDER_CONTRACT_SEQ_NEXTVAL_STMT);
            	rs = ps.executeQuery();
            	if ( !rs.next() ) {
            		String errorMsg = "Failed to get next sequence for order contract in database. bundleId = " + bundleId + " : salesCtxId = " + esdgOrder.getSalesContextId();
            		logger.error( "--ESDG-- " + errorMsg);
            		if( ps != null ) {ps.close(); ps = null;}
    				if( rs != null ) {rs.close(); rs = null;}
            		throw new Exception(errorMsg);
            	}else{
            		orderContractId = rs.getString(1);
            		if( ps != null ) {ps.close(); ps = null;}
            		if( rs != null ) {rs.close(); rs = null;}
            	}

        		//Temporary codes, remove it if SALES_ORDER_CONTRACT_SEQ is created.
            	/**
        		if(orderContractId==null){
        			if(esdgOrder.getSalesContextExternalRefId()!=null){
        				orderContractId = esdgOrder.getSalesContextExternalRefId();
        			}else if(salesContextId!=null && salesContextId.trim().length()>0){
        				orderContractId = salesContextIdWithoutPrefix;
            		}else if(bundleId!=null && bundleId.trim().length()>0){
            			//For BC
            			orderContractId = bundleId;
            		}
            	}
            	**/

        		//2. get orderId
        		if(salesContextId!=null && salesContextId.trim().length()>0){
        			orderId = salesContextIdWithoutPrefix;
        		}else if(bundleId!=null && bundleId.trim().length()>0){
        			//For BC
        			orderId = bundleId;
        		}

        		//3. get INTRACTN_START_TS
        		if(salesContextId!=null && salesContextId.trim().length()>0){
        			ps = conn.prepareStatement(SELECT_SALES_INTRACTN_START_TS_BY_CONTEXT_ID_STMT);
        			ps.setString(1, salesContextIdWithoutPrefix);

        			rs = ps.executeQuery();
        			if (rs.next() ) {
        				idx = 1;
        				salesInteractionId = rs.getString(idx++);
        				salesInteractionStartTimeInMills = rs.getTimestamp(idx++).getTime();
        				salesContextExtRefId = rs.getString(idx++);
        				//if not exist, fallback to data creation timestamp for partition key
        				if( salesInteractionStartTimeInMills < 0 ) {
        					salesInteractionStartTimeInMills = esdgOrder.getDataGenerationTimeInMills();
        				}
        			} else {
        				if( ps != null ) {ps.close(); ps = null;}
        				if( rs != null ) {rs.close(); rs = null;}
        				salesInteractionStartTimeInMills = esdgOrder.getDataGenerationTimeInMills();
        			}
        			if( ps != null ) {ps.close(); ps = null;}
        			if( rs != null ) {rs.close(); rs = null;}
        		}else if(bundleId!=null && bundleId.trim().length()>0){
        			//For BC
        			salesInteractionStartTimeInMills = esdgOrder.getDataGenerationTimeInMills();
        		}

        		//5. get EXTERNAL_TXN_REF_ID
        		if(salesContextId!=null && salesContextId.trim().length()>0){
        			externalTxnRefID = esdgOrder.getExternalKey() == null ? "NA" : esdgOrder.getExternalKey().substring(0, Math.min(100, esdgOrder.getExternalKey().length())); //external ref id max length
        		}else if(bundleId!=null && bundleId.trim().length()>0){
        			//FOr BC?
        			externalTxnRefID = esdgOrder.getExternalKey() == null ? "NA" : esdgOrder.getExternalKey().substring(0, Math.min(100, esdgOrder.getExternalKey().length())); //external ref id max length
        		}

        		//6. Clob SALES_CONTRACT_DATA
        		//Clob salesContractData = conn.createClob();
        		//salesContractData.setString(1, esdgOrder.getJsonOrderContract());

        		//insert order data
        		ps = conn.prepareStatement(INSERT_SALES_ORDER_CONTRACT_DATA);
        		idx = 1;
        		ps.setString(idx++, orderContractId);
        		ps.setString(idx++, orderId);
        		ps.setTimestamp(idx++, new Timestamp(salesInteractionStartTimeInMills));
        		ps.setTimestamp(idx++, new Timestamp(esdgOrder.getDataGenerationTimeInMills()));
        		ps.setString(idx++, externalTxnRefID);
        		ps.setBytes(idx++, SerializationUtils.serialize(esdgOrder.getJsonOrderContract()));
        		//ps.setClob(idx++, salesContractData);
        		ps.setString(idx++, this.dbUser);
        		ps.setString(idx++, this.dbUser);
        		exeResult = ps.executeUpdate();
        		if( ps != null ) {ps.close(); ps = null;}
        		insertOrderBinaryTime = Calendar.getInstance().getTimeInMillis() - startTs;
        		logDebugInfo(salesContextId, "--ESDG-- INSERT_SALES_ORDER_CONTRACT_DATA");

        		conn.commit();

                esdgOrder.setOrderContractId(orderContractId);

                logger.info(new StringBuffer().append("--ESDG-- Saved order contract to database (").append(exeResult).append("). order contract id = ").append(orderContractId).append(" : salesCtxId = ").append(esdgOrder.getSalesContextId())
                		.append(" : total time = ").append(Calendar.getInstance().getTimeInMillis() - methodStartTs)
                		.append(" : binary = ").append(insertOrderBinaryTime));
        	}else{
        		logDebugInfo(salesContextId, "--ESDG-- NOT INSERT_SALES_ORDER_CONTRACT_DATA AS JsonOrderContract is not in esdgOrder");
        	}
        }catch ( Exception ex ) {
        	try {
        		if (null != conn) {
        			conn.rollback();
        		}
        	} catch (Exception exx) {}

        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	StringBuffer sb = new StringBuffer()
        			.append("--ESDG-- Exception caught when saving order contract to database. salesCtxId = ").append(esdgOrder.getSalesContextId()).append("\n")
        			.append(esdgDatabaseConnectionFactory.getStatistics()).append("\n")
        			.append(esdgOrder.getJournal());
        	logger.error(sb.toString(), ex);
            result = false;
        } finally {
			try {
             	if( clob != null ) {clob.free(); clob = null;}
            } catch( Exception ex) {}
            closeConnection(conn, ps, rs);
        }

        return result;
	}

	private long removeAlpha(String salesContextId) {

		if (salesContextId == null)
			return 0;

		StringBuffer numValue = new StringBuffer();

		for (char tempChar : salesContextId.toCharArray()) {
			if (Character.isDigit(tempChar))
				numValue.append(tempChar);
		};

		if (numValue.length() == 0)
			return 0;

		return Long.parseLong(numValue.toString());
	}

	private void logDebugInfo(String salesContextId, String message) {
		if ( logger.isDebugEnabled() ) {
			logger.debug(" salesContextId [" + salesContextId + "]" + message);
		}
	}

	/**
	 * Select sales order.
	 *
	 * @param salesContextId the sales context id
	 * @return the esdg order d o_1
	 */
	public EsdgOrderDO_1 selectSalesOrder(String salesContextId) {
		EsdgOrderDO_1 esdgOrder = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String salesContextIdWithoutPrefix = removeContextPrefix(salesContextId);
        if( StringUtils.isEmpty(salesContextIdWithoutPrefix) || !StringUtils.isNumeric(salesContextIdWithoutPrefix) ) return null;

        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SELECT_LATEST_ORDER_STATUS_STMT);
            int idx = 1;
            ps.setString(idx++, salesContextIdWithoutPrefix);

            rs = ps.executeQuery();
            if ( !rs.next() ) {
            	if( ps != null ) {ps.close(); ps = null;}
    			if( rs != null ) {rs.close(); rs = null;}
                logger.warn( "--ESDG-- Order not found in database. sales context id = " + salesContextId);
            } else {
            	//latest status
            	esdgOrder = new EsdgOrderDO_1();
            	esdgOrder.setSalesContextId(salesContextId);
            	idx = 1;
            	esdgOrder.setOrderId(rs.getString(idx++));
            	esdgOrder.setOrderVersion(rs.getString(idx++));
            	esdgOrder.setOrderStatus(rs.getString(idx++));
            	esdgOrder.setSalesInteractionStartTimeInMills(rs.getTimestamp(idx++).getTime());
            	esdgOrder.setDataGenerationTimeInMills(rs.getTimestamp(idx++).getTime());
            	esdgOrder.setTypeCode(rs.getString(idx++));
            	
            	if( ps != null ) {ps.close(); ps = null;}
    			if( rs != null ) {rs.close(); rs = null;}

            	//latest object binary
            	ps = conn.prepareStatement(SELECT_LATEST_ORDER_STMT);
            	idx = 1;
            	ps.setString(idx++, esdgOrder.getOrderId());
            	ps.setString(idx++, esdgOrder.getOrderId());
            	rs = ps.executeQuery();
            	if ( rs.next() ) {
            		idx = 1;
            		esdgOrder.setExternalKey(rs.getString(idx++));

                	byte[] bytes = rs.getBytes(idx++);
                	if( bytes != null && bytes.length > 0 ) {
                		esdgOrder.setJsonOrder((String)SerializationUtils.deserialize(bytes));
                	}
            	}

            	if( StringUtils.isEmpty(esdgOrder.getJsonOrder()) ) {
            		logger.warn( "--ESDG-- Sales order contains emtpy data in database. sales context id = " + salesContextId);
            		return null;
            	}

            	long orderTs = rs.getTimestamp(idx++).getTime();
            	if( orderTs > esdgOrder.getDataGenerationTimeInMills() ) {
            		esdgOrder.setDataGenerationTimeInMills(orderTs);
            	}
            	if( ps != null ) {ps.close(); ps = null;}
    			if( rs != null ) {rs.close(); rs = null;}

    			//latest classifer
    			ps = conn.prepareStatement(SELECT_SALES_CLASSIFIERS_STMT);
    			idx = 1;
    			ps.setString(idx++, salesContextIdWithoutPrefix);
    			rs = ps.executeQuery();
    			HashMap<String, String> orderClassifierMap = new HashMap<String, String>();
    			while( rs.next() ) {
    				idx = 1;
    				orderClassifierMap.put(rs.getString(idx++), rs.getString(idx++));
    			}
    			if( ps != null ) {ps.close(); ps = null;}
    			if( rs != null ) {rs.close(); rs = null;}

    			if( !orderClassifierMap.isEmpty() ) {
    				esdgOrder.setOrderClassifierMap(orderClassifierMap);
    				esdgOrder.setBrandCd(orderClassifierMap.get(EsdgConstants.ESDG_SALES_CLASSIFIER_BRAND));  //TODO: candidate for normalization
    			}

    			//latest order fulfillment status
    			ps = conn.prepareStatement(SELECT_SALES_FULFILLMENT_STMT);
                ps.setString(1, salesContextIdWithoutPrefix);
                rs = ps.executeQuery();
                if ( rs.next() ) {
                	idx = 1;
                	String salesFulfillmentId = rs.getString(idx++);
                	esdgOrder.setFulfillmentStatus(rs.getString(idx++));
                	esdgOrder.setFulfillmentStatusTimeInMills(rs.getTimestamp(idx++).getTime());
                }
                if( ps != null ) {ps.close(); ps = null;}
    			if( rs != null ) {rs.close(); rs = null;}

            	if ( logger.isDebugEnabled() ) logger.debug("--ESDG-- Retrieved sales order from database. sales context id = " + salesContextId + " : " + esdgOrder.getJsonOrder());
            }
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	StringBuffer sb = new StringBuffer()
        		.append("--ESDG-- Exception caught when reading sales order from database. sales context id = ").append(salesContextId).append("\n")
               	.append(esdgDatabaseConnectionFactory.getStatistics());
            logger.error(sb.toString(), ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return esdgOrder;
	}

	/**
	 * Select sales order contracts.
	 *
	 * @param salesOrderId: the sales context id or bundle id
	 * @return the esdgOrderDO_1
	 */
	public EsdgOrderContractDO_1 selectSalesOrderContracts(String salesOrderId) {
		logger.debug("enter EsdgDatabaseAdapter_1.selectSalesOrderContracts() ..");
		EsdgOrderContractDO_1 esdgOrder =  new EsdgOrderContractDO_1();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String salesOrderIdWithoutPrefix = removeContextPrefix(salesOrderId);
        if( StringUtils.isEmpty(salesOrderIdWithoutPrefix) || !StringUtils.isNumeric(salesOrderIdWithoutPrefix) ) return null;

        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SELECT_ORDER_CONTRACT_STMT);
            int idx = 1;
            ps.setString(idx++, salesOrderIdWithoutPrefix);
            ps.setString(idx++, salesOrderIdWithoutPrefix);

            rs = ps.executeQuery();
            while ( rs.next() ) {
            	EsdgOrderContractDO orderContract = new EsdgOrderContractDO();
            	idx = 1;
            	orderContract.setSalesOrderContractDataID(rs.getString(idx++)); //SALES_ORDER_CONTRACT_DATA_ID
            	orderContract.setSalesOrderID(rs.getString(idx++));  //SALES_ORDER_ID
            	orderContract.setIntractnStartTs(rs.getTimestamp(idx++).getTime());  //INTRACTN_START_TS
            	orderContract.setSalesTxnTs(rs.getTimestamp(idx++).getTime());  //SALES_TXN_TS
            	orderContract.setExternalTxnRefID(rs.getString(idx++));   //EXTERNAL_TXN_REF_ID
            	byte[] bytes = rs.getBytes(idx++);   //SALES_CONTRACT_DATA
            	if(bytes != null && bytes.length > 0 ) {
            		orderContract.setSalesContractData((String)SerializationUtils.deserialize(bytes));
            	}
            	orderContract.setCreateUserID(rs.getString(idx++));   //CREATE_USER_ID
            	orderContract.setCreateTs(rs.getTimestamp(idx++).getTime());   //CREATE_TS
            	orderContract.setLastUpdtUserID(rs.getString(idx++));    //LAST_UPDT_USER_ID
            	orderContract.setLastUpdtTs(rs.getTimestamp(idx++).getTime());   //LAST_UPDT_TS

            	esdgOrder.getOrderContractList().add(orderContract);

            	logger.debug(" SalesOrderContractDataID:"+orderContract.getSalesOrderContractDataID()
            	+", SalesOrderID:"+orderContract.getSalesOrderID()
            	+", IntractnStartTs:"+orderContract.getIntractnStartTs()
            	+", SalesTxnTs:"+orderContract.getSalesTxnTs()
            	+", ExternalTxnRefID:"+orderContract.getExternalTxnRefID()
            	+", SalesContractData:"+orderContract.getSalesContractData()
            	+", CreateUserID:"+ orderContract.getCreateUserID()
            	+", CreateTs:"+orderContract.getCreateTs()
            	+", LastUpdtUserID:"+orderContract.getLastUpdtUserID()
            	+", LastUpdtTs:"+orderContract.getLastUpdtTs());
            }

            if( ps != null ) {ps.close(); ps = null;}
			if( rs != null ) {rs.close(); rs = null;}
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	StringBuffer sb = new StringBuffer()
        		.append("--ESDG-- Exception caught when reading sales order contract from database. sales order id = ").append(salesOrderId).append("\n")
               	.append(esdgDatabaseConnectionFactory.getStatistics());
            logger.error(sb.toString(), ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return esdgOrder;
	}

	/**
	 * Select sales fulfillment error type list.
	 *
	 * @param salesContextId the sales context id
	 * @return the list
	 */
	public List<String> selectSalesFulfillmentErrorTypeList(String salesContextId) {
		ArrayList<String> errorTypeList = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String salesContextIdWithoutPrefix = removeContextPrefix(salesContextId);
        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

        	ps = conn.prepareStatement(SELECT_SALES_FULFILLMENT_TXN_TYPE_STMT);
            ps.setString(1, salesContextIdWithoutPrefix);
            rs = ps.executeQuery();
            while( rs.next() ) {
            	errorTypeList.add(rs.getString(1));
            }
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	StringBuffer sb = new StringBuffer()
        		.append("--ESDG-- Exception caught when reading sales order error types from database. sales context id = ").append(salesContextId).append("\n")
               	.append(esdgDatabaseConnectionFactory.getStatistics());
            logger.error(sb.toString(), ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return errorTypeList;
	}

	/**
	 * Select sales order json objects.
	 *
	 * @param salesOrderId the sales order id
	 * @return the list
	 */
	public List<String> selectSalesOrderJsonObjects(String salesOrderId) {
		ArrayList<String> orderJsonList = new ArrayList<String>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

        	//all object binary
        	ps = conn.prepareStatement(SELECT_ORDER_DATA_STMT);
        	int idx = 1;
        	ps.setString(idx++, salesOrderId);
        	rs = ps.executeQuery();
        	while ( rs.next() ) {
        		idx = 1;
            	byte[] bytes = rs.getBytes(idx++);
            	if( bytes != null && bytes.length > 0 ) {
            		orderJsonList.add((String)SerializationUtils.deserialize(bytes));
            	}
        	}
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when reading sales order from database. sales order id = " + salesOrderId, ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return orderJsonList;
	}

	/**
	 * Removes the context prefix.
	 *
	 * @param salesContextId the sales context id
	 * @return the string
	 */
	public static String removeContextPrefix(String salesContextId) {
		return salesContextId == null ? null : salesContextId.startsWith( CONTEXT_ID_PREFIX ) ? salesContextId.substring(2) : salesContextId;
	}

	public static String removeInteractionPrefix(String salesInteractionId) {
		return salesInteractionId == null ? null : salesInteractionId.startsWith( INTERACTION_ID_PREFIX ) ? salesInteractionId.substring(INTERACTION_ID_PREFIX.length()) : salesInteractionId;
	}
	public static String removeSalesItemPrefix(String salesItemId) {
		return salesItemId == null ? null : salesItemId.startsWith( SALES_ITEM_ID_PREFIX ) ? salesItemId.substring(INTERACTION_ID_PREFIX.length()) : salesItemId;
	}
	/**
	 * Checks if is valid sales id.
	 *
	 * @param salesContextId the sales context id
	 * @return true, if is valid sales id
	 */
	public boolean isValidSalesId(String salesContextId) {
		if( salesContextId == null ) return false;
		String salesContextIdWithoutPrefix = removeContextPrefix(salesContextId);
        return !StringUtils.isEmpty(salesContextIdWithoutPrefix) && StringUtils.isNumeric(salesContextIdWithoutPrefix);
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
				rs.close(); rs = null;
			}
			if (ps != null) {
				ps.close(); ps = null;
			}
			if (conn != null) {
				conn.close(); conn = null;
			}
		} catch (Exception ex) {
			logger.error("--ESDG-- Exception caught when closing connection to database.", ex);
		}
	}

	/**
	 * The Class FulfillmentTxnDataSummary.
	 */
	private class FulfillmentTxnDataSummary {

		/** The fulfillment txn data id. */
		private String fulfillmentTxnDataId;

		/** The fulfillment txn time in mills. */
		private long fulfillmentTxnTimeInMills;

		/** The retry count. */
		private long retryCount;

		/** The updated fulfillment. */
		private EsdgOrderFulfillmentStatusHistoryDO updatedFulfillment;
	}

	public Long searchBatchOrderByExternalRefNumAndOptionalBAN(String batchOrderExternalRefNum, String billingAccountNum){
		Long salesBatchOrderId = null; //sales interaction id
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean isBANempty = StringUtils.isEmpty(billingAccountNum);

        if( StringUtils.isEmpty(batchOrderExternalRefNum) ) {
    		logger.warn("--ESDG-- searchBatchOrderByExternalRefNumAndOptionalBAN aborted due to missing mandatory inputs.");
    		return null;
    	}

		try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();
        	if (isBANempty) {
        		ps = conn.prepareStatement(SELECT_SALES_INTRACTN_ID_BY_EXTERNAL_SALES_REF_ID);
        	} else {
        		ps = conn.prepareStatement(SELECT_SALES_INTRACTN_ID_BY_EXTERNAL_SALES_REF_ID_AND_BAN);
        	}

            int idx = 1;
            ps.setString(idx++, batchOrderExternalRefNum);
        	if (!isBANempty) {
        		ps.setString(idx++, billingAccountNum);
        	}
        	ps.setTimestamp(idx, getBackTime(BATCH_ORDER_LOOK_UP_PERIOD));

            rs = ps.executeQuery();
            while ( rs.next() ) {
            	salesBatchOrderId = new Long(rs.getLong(1));
            }

            if( logger.isDebugEnabled() ) logger.info("--ESDG-- Query searchBatchOrderByExternalRefNumAndOptionalBAN. Found (" + salesBatchOrderId + "). with input data: batchOrderExternalRefNum = " + batchOrderExternalRefNum + " billingAccountNum = " + billingAccountNum);
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when querying searchBatchOrderByExternalRefNumAndOptionalBAN with input data: batchOrderExternalRefNum = " + batchOrderExternalRefNum + " billingAccountNum = " + billingAccountNum, ex);
        } finally {
            closeConnection(conn, ps, rs);
        }
		return salesBatchOrderId;
	}

	public Long searchSalesInteractionIdBySalesContextId(String salesContextId){
		Long salesInteractionId = null;
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

		try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();
       		ps = conn.prepareStatement(SELECT_SALES_INTRACTN_ID_BY_SALES_CONTEXT_ID);

            ps.setString(1, salesContextId);

            rs = ps.executeQuery();
            while ( rs.next() ) {
            	salesInteractionId = new Long(rs.getLong(1));
            }

            if( logger.isDebugEnabled() ) logger.info("--ESDG-- Query searchSalesInteractionIdBySalesContextId. Found (" + salesInteractionId + "). with input data: salesContextId = " + salesContextId);
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when querying searchSalesInteractionIdBySalesContextId with input data: salesContextId = " + salesContextId, ex);
        } finally {
            closeConnection(conn, ps, rs);
        }
		return salesInteractionId;
	}

	/**

	/**
	 * Select sales order json objects.
	 *
	 * @param salesOrderId the sales order id
	 * @return the list
	 */
	public List<String> selectBatchSalesOrderJsonObjects(Long salesBatchOrderId) {
		ArrayList<String> orderJsonList = new ArrayList<String>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

        	//all object binary
        	ps = conn.prepareStatement(SELECT_SALES_INTRACTN_DATA_BY_SALES_INTRACTN_ID);
        	int idx = 1;
        	ps.setLong(idx++, salesBatchOrderId);
        	ps.setTimestamp(idx, getBackTime(BATCH_ORDER_LOOK_UP_PERIOD));
        	rs = ps.executeQuery();
        	while ( rs.next() ) {
        		idx = 1;
            	byte[] bytes = rs.getBytes(idx++);
            	if( bytes != null && bytes.length > 0 ) {
            		orderJsonList.add((String)SerializationUtils.deserialize(bytes));
            	}
        	}
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when reading batch sales order from database. batch sales order id = " + salesBatchOrderId, ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return orderJsonList;
	}

	static Timestamp getBackTime(int lookBackDays){
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -lookBackDays);
		Date lookDate = cal.getTime();
		return new Timestamp(lookDate.getTime());
	}

	public EsdgInteractionDO_1 saveSalesInteraction(EsdgInteractionDO_1 interactionDO) {

		Connection conn = null;

		String externalRefId = interactionDO.getExternalSalesRefId();

        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();

			InteractionDao dao = new InteractionDao(conn, dbUser);

			dao.saveInteraction(interactionDO);

			conn.commit();

			if (logger.isDebugEnabled()) logger.debug("--ESDG-- Saved interaction to database. salesInteractionId= "+ interactionDO.getSalesInteractionId()	+ " : externalRefId = " + externalRefId);

			return interactionDO;

		} catch (Exception ex) {

			try {
				if (null != conn) {
					conn.rollback();
				}
			} catch (Exception exx) {
			}

			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

			logger.error("--ESDG-- Exception caught when saving new interaction to database. externalRefId = "	+ externalRefId, ex);
			return null;

		} finally {
			closeConnection(conn, null, null);
		}
	}

	public EsdgInteractionDO_1 selectSalesInteraction( String salesInteractionId ) {

        Connection conn = null;

        try {

        	conn = esdgDatabaseConnectionFactory.getDBConnection();
        	return new InteractionDao( conn, dbUser ).selectInteraction(salesInteractionId);

        } catch ( Exception ex ) {
        	try {
        		if (null != conn) {
        			conn.rollback();
        		}
        	} catch (Exception exx) {}

        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when selecting interaction from database. salesInteractionId = " + salesInteractionId, ex);
            return null;

        } finally {
            closeConnection(conn, null, null);
        }
	}

	public Set<String> searchInteractionIdByKeyName( String keyName ) {

        Connection conn = null;

        try {

        	conn = esdgDatabaseConnectionFactory.getDBConnection();
        	return new InteractionDao( conn, dbUser ).searchInteractionIdByKeyName(keyName);

        } catch ( Exception ex ) {
        	try {
        		if (null != conn) {
        			conn.rollback();
        		}
        	} catch (Exception exx) {}

        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when selecting interaction from database. keyName = " + keyName, ex);
            return null;

        } finally {
            closeConnection(conn, null, null);
        }
	}
	
	/**
	 * Search order.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param classifierType sales_classifier_typ_cd
	 * @param classifierValue sales_classifier_value
	 * @return the collection
	 */
	public Set<String> searchSalesContextIdByClassifier(Date startDate, Date endDate, String inClassifierType, String inClassifierValue) {
		Set<String> salesContextIdSet = new HashSet<String>();

		String classifierType = inClassifierType.trim();
		String classifierValue = inClassifierValue.trim();

		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	if( startDate == null || endDate == null || StringUtils.isEmpty(classifierType) ||  StringUtils.isEmpty(classifierValue) ) {
        		logger.warn("--ESDG-- Abort to query order database due to missing mandatory inputs.");
        		return null;
        	}
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SELECT_SALES_CONTEXT_ID_BY_SALES_CLASSFIER);
            int idx = 1;
            ps.setString(idx++, classifierType);
            ps.setString(idx++, classifierValue);
            ps.setTimestamp(idx++, new Timestamp(startDate.getTime()));
            ps.setTimestamp(idx++, new Timestamp(endDate.getTime()));

            rs = ps.executeQuery();
            while ( rs.next() ) {
            	String ctxId = rs.getString(1);
            	salesContextIdSet.add(ctxId);
            }

            if( logger.isDebugEnabled() ) logger.debug("--ESDG-- Query order database. Found (" + salesContextIdSet.size() + "). Parameters: " + getLogStringForSearchSalesContextIdByClassifier(startDate, endDate,classifierType, classifierValue));
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.debug("--ESDG-- Exception caught when querying order database. " + getLogStringForSearchSalesContextIdByClassifier(startDate, endDate, classifierType, classifierValue), ex);
        } finally {
            closeConnection(conn, ps, rs);
        }
        return salesContextIdSet;
	}

	/**
	 * Gets the query parameter log string.
	 *
	 * @param phoneNumber the phone number
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param statusList the status list
	 * @return the query parameter log string
	 */
	private String getLogStringForSearchSalesContextIdByClassifier(Date startDate, Date endDate, String classifierType, String classifierValue) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		StringBuffer sb = new StringBuffer().append("classifierType = ").append(classifierType);
		sb.append(" : classifierValue = ").append(classifierValue);
		sb.append(" : startDate = ").append(startDate == null ? "null" : formatter.format(startDate));
		sb.append(" : endDate = ").append(endDate == null ? "null" : formatter.format(endDate));
		return sb.toString();
	}

	/**
	 * Cecil Oct Search order by sales order status.
	 *
	 * @param phoneNumber the phone number
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param statusList the sales order status list
	 * @return the sales context id list
	 */
	/*public Set<String> searchOrderByOrderStatus(Date startDate, Date endDate, List<String> statusList) {
		Set<String> salesContextIdSet = new HashSet<String>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String statusListStr = null;
        try {
        	if( startDate == null || endDate == null || statusList == null || statusList.isEmpty()) {
        		logger.warn("--ESDG-- Abort to query order database due to missing mandatory inputs.");
        		return null;
        	}
        	StringBuffer sbStatusList = new StringBuffer();
        	StringBuffer sbStatusSqlInClause = new StringBuffer();
        	if( statusList != null && statusList.size() > 0 ) {
    			for( Iterator<String> i = statusList.iterator(); i.hasNext(); ) {
    				sbStatusList.append(i.next()).append(", ");
    				sbStatusSqlInClause.append("?,");
    			}
    		}
        	statusListStr = sbStatusList.toString();
        	statusListStr = statusListStr.substring(0, statusListStr.length() - 2) + ")";

        	String statusSqlInClauseStr = sbStatusSqlInClause.toString();
        	statusSqlInClauseStr = statusSqlInClauseStr.substring(0, statusSqlInClauseStr.length() - 1);
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SELECT_ORDER_BY_SALES_ORDER_STATUS  + "(" + statusSqlInClauseStr + ") ");
            int idx = 1;
            ps.setTimestamp(idx++, new Timestamp(startDate.getTime()));
            ps.setTimestamp(idx++, new Timestamp(endDate.getTime()));
            for( Iterator<String> i = statusList.iterator(); i.hasNext(); ) {
            	ps.setString(idx++, (String)i.next());
            }

            rs = ps.executeQuery();
            while ( rs.next() ) {
              	String ctxId = rs.getString(1);
            	salesContextIdSet.add(ctxId);
            }

            if( logger.isDebugEnabled() ) logger.debug("--ESDG-- Query order database. Found (" + salesContextIdSet.size() + "). Parameters: " + getQueryParameterLogString("", startDate, endDate, statusListStr));
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.debug("--ESDG-- Exception caught when querying order database. " + getQueryParameterLogString("", startDate, endDate, statusListStr), ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return salesContextIdSet;
	}*/

	/**
	 *  Spruce project: Insert MTS_SALES_ORDER
	 */
	private void insertUpdateMTSOrder(Connection conn, EsdgOrderDO_1 esdgOrder, String salesInteractionId) throws Exception {
		PreparedStatement ps = null;
		try {
			logDebugInfo(esdgOrder.getSalesContextId(), "--ESDG-- searchMTSOrder account [" + esdgOrder.getBillingAccountNumber() + "] phoneNum [" +
					esdgOrder.getSubscriberPhoneNumber() + "]");
			String contextID = searchMTSOrder(esdgOrder.getBillingAccountNumber(), esdgOrder.getSubscriberPhoneNumber());
			if(contextID == null) {
				ps = conn.prepareStatement(INSERT_MTS_SALES_ORDER_STMT);
				int idx = 1;
				ps.setString(idx++, esdgOrder.getSubscriberPhoneNumber());
				ps.setString(idx++, esdgOrder.getBillingAccountNumber());
				ps.setString(idx++, removeContextPrefix(esdgOrder.getSalesContextId()));
				ps.setString(idx++, salesInteractionId);
				ps.setTimestamp(idx++, new Timestamp(esdgOrder.getSalesInteractionStartTimeInMills()));
				ps.setString(idx++, esdgOrder.getSalesContextExternalRefId());
				ps.setString(idx++,esdgOrder.getOrderStatus());
				ps.setString(idx++, this.dbUser);
				ps.setString(idx++, this.dbUser);
				ps.executeUpdate();
				if( ps != null ) {ps.close(); ps = null;}
				logDebugInfo(esdgOrder.getSalesContextId(), "--ESDG-- INSERT_MTS_SALES_ORDER_STMT");
			}
			else {
				ps = conn.prepareStatement(UPDATE_MTS_SALES_ORDER_STMT);
                int idx = 1;
				ps.setString(idx++, removeContextPrefix(esdgOrder.getSalesContextId()));
				ps.setString(idx++, salesInteractionId);
				ps.setTimestamp(idx++, new Timestamp(esdgOrder.getSalesInteractionStartTimeInMills()));
				ps.setString(idx++, esdgOrder.getSalesContextExternalRefId());
                ps.setString(idx++, esdgOrder.getOrderStatus());
				ps.setString(idx++, this.dbUser);
                ps.setString(idx++, esdgOrder.getSubscriberPhoneNumber());
                ps.setString(idx++, esdgOrder.getBillingAccountNumber());
				ps.executeUpdate();
				if( ps != null ) {ps.close(); ps = null;}
				logDebugInfo(esdgOrder.getSalesContextId(), "--ESDG-- UPDATE_MTS_SALES_ORDER_STMT");
			}
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			if(ps != null) {
				ps.close();
			}
		}
	}

	/**
	 *  Spruce project: Select MTS_SALES_ORDER by BAN and Phone number
	 */
	public String searchMTSOrder(String Ban, String phoneNumber) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String salesContextId = null;
        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

        	ps = conn.prepareStatement(SELECT_MTS_SALES_ORDER_BY_PHONE_AND_BAN);
            ps.setString(1, phoneNumber);
            ps.setString(2, Ban);
            rs = ps.executeQuery();
            while( rs.next() ) {
            	salesContextId = rs.getString(1);
            	String esdgStatus = rs.getString(2);
            }
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	StringBuffer sb = new StringBuffer()
        		.append("--ESDG-- Exception caught when reading MTS sales order error types from database. phone number = ").append(phoneNumber).append("\n")
               	.append(esdgDatabaseConnectionFactory.getStatistics());
            logger.error(sb.toString(), ex);
        } finally {
            closeConnection(conn, ps, rs);
        }
		return salesContextId;
	}

	public ArrayList<String> getMTSOrders(String Ban, String phoneNumber, List<String> statusList ) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

        	if(phoneNumber == null) {
        		ps = conn.prepareStatement(SELECT_MTS_SALES_ORDER_BY_BAN);
            	int idx = 1;
                ps.setString(idx++, Ban);
        	} else {
        		ps = conn.prepareStatement(SELECT_MTS_SALES_ORDER_BY_PHONE_AND_BAN);
        		ps.setString(1, phoneNumber);
        		ps.setString(2, Ban);
        	}
            rs = ps.executeQuery();
            while( rs.next() ) {
            	String salesContextID = rs.getString(1);
            	String esdgStatus = rs.getString(2);
            	if(statusList != null) {
            		for(String expectStatus: statusList) {
            			if(esdgStatus.equals(expectStatus)) {
            				list.add(CONTEXT_ID_PREFIX + salesContextID);
            				break;
            			}
            		}
            	}
            	else {
            		list.add(CONTEXT_ID_PREFIX + salesContextID);
            	}
            }
        } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	StringBuffer sb = new StringBuffer()
        		.append("--ESDG-- Exception caught when reading MTS sales order error types from database. BAN = ").append(Ban).append("\n")
               	.append(esdgDatabaseConnectionFactory.getStatistics());
            logger.error(sb.toString(), ex);
            throw(ex);
        } finally {
            closeConnection(conn, ps, rs);
        }
		return list;
	}

	//===================== methods for sales items begin =========================
	public String getNewSalesItemId(String salesOrderId ) {

		Connection conn = null;

        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			SalesOrderItemDao dao = new SalesOrderItemDao(conn, dbUser);

			String newSalesItemId = dao.getNewSalesItemId( salesOrderId );
			conn.commit();

			return newSalesItemId;

		} catch (Exception ex) {

			try { if (null != conn) {	conn.rollback(); } } catch (Exception exx) { }

			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

			logger.error("--ESDG-- Exception caught when generating new salesItemId for salesOrderId"	+ salesOrderId , ex);
			return null;

		} finally {
			closeConnection(conn, null, null);
		}
	}

	public List<String>  searchSalesItemIdsBySalesOrderId(String salesOrderId, String itemType ) {

		List<String> resultList =null;
		Connection conn = null;

        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			SalesOrderItemDao dao = new SalesOrderItemDao(conn, dbUser);

			salesOrderId = removeContextPrefix(salesOrderId);

			resultList = dao.searchSalesItemIdBySalesOrderId ( salesOrderId, itemType );

			if (logger.isInfoEnabled()) {
				logger.info("--ESDG-- search salesItemId for [salesOrderId="+ salesOrderId + ", type=" + itemType + "]; result: " + resultList );
			}

		} catch (Exception ex) {

			logger.error("--ESDG-- Exception caught when searching salesItem ids for salesOrderId="+ salesOrderId + ", type=" + itemType, ex);

			try { if (null != conn) {	conn.rollback(); } } catch (Exception exx) { }

			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

		} finally {
			closeConnection(conn, null, null);
		}
		return resultList;

	}

	public void saveSalesItem( EsdgSalesItemDO salesItem ) {

		Connection conn = null;

        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			SalesOrderItemDao dao = new SalesOrderItemDao(conn, dbUser);

			salesItem.setSalesContextId( removeContextPrefix( salesItem.getSalesContextId() ));

			dao.saveSalesItem( salesItem );

			conn.commit();

			if (logger.isInfoEnabled()) logger.info("--ESDG-- Saved salesItem to database. salesItemId="+ salesItem.getItemId() );

		} catch (Exception ex) {

			try { if (null != conn) { conn.rollback(); } } catch (Exception exx) { }

			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

			logger.error("--ESDG-- Exception caught when saving salesItem to database."	+ salesItem.getItemId(), ex);

		} finally {
			closeConnection(conn, null, null);
		}
	}

	public EsdgSalesItemDO selectSalesItem(String salesItemId) {
		Connection conn = null;
		EsdgSalesItemDO result = null;

        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			SalesOrderItemDao dao = new SalesOrderItemDao(conn, dbUser);

			result = dao.selectSalesItemByItemId(salesItemId);

			if ( result!=null ) {
				if( logger.isInfoEnabled()) { logger.info("--ESDG-- found salesItem from database. salesItemId="+ salesItemId ); }
			}


		} catch (Exception ex) {

			try { if (null != conn) { conn.rollback(); } } catch (Exception exx) { }

			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when selecting salesItem from database. salesItemId=" + salesItemId, ex);

		} finally {
			closeConnection(conn, null, null);
		}

		return result;
	}
	//===================== methods for sales items end =========================

	//===================== methods for subscriber context begin =========================
	@SuppressWarnings("unchecked")
	public List<String>  searchSubscriberContextKeys(String ban, String type, String status, int days ) {

		List<String> resultList =null;
		Connection conn = null;

        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			SubscriberContextDao dao = new SubscriberContextDao(conn, dbUser);

			resultList = dao.searchSubscriberContextKeys(ban, type, status, days) ;

			if (logger.isInfoEnabled()) {
				logger.info("--ESDG-- search subscriberContextId for " + Arrays.asList( ban,type, status, days ) + " result: " + resultList );
			}

		} catch (Exception ex) {

			resultList = new ArrayList<String>();

			logger.error("--ESDG-- Exception caught when searching subscriberContext ids for ban="+ ban, ex);

			try { if (null != conn) {	conn.rollback(); } } catch (Exception exx) { }

			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

		} finally {
			closeConnection(conn, null, null);
		}
		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<String>  searchSubscriberContextKeysBySalesId(String salesId, String type, String status) {

		List<String> resultList =null;
		Connection conn = null;

        try {
        	salesId= EsdgDatabaseAdapter_1.removeContextPrefix( salesId ) ;
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			SubscriberContextDao dao = new SubscriberContextDao(conn, dbUser);

			resultList = dao.searchSubscriberContextKeysBySalesId( salesId, type, status) ;

			if (logger.isInfoEnabled()) {
				logger.info("--ESDG-- search subscriberContextId for " + Arrays.asList( salesId,type, status ) + " result: " + resultList );
			}

		} catch (Exception ex) {

			resultList = new ArrayList<String>();

			logger.error("--ESDG-- Exception caught when searching subscriberContext ids for ban="+ salesId, ex);

			try { if (null != conn) {	conn.rollback(); } } catch (Exception exx) { }

			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

		} finally {
			closeConnection(conn, null, null);
		}
		return resultList;

	}
	public void saveSubscirberContext( EsdgSubscriberContextDO subscriberContext ) {

		Connection conn = null;

        try {

			if (logger.isInfoEnabled()) {
				logger.info("--ESDG-- Saving subscriberContext to database");
	        	logger.info( subscriberContext.getJournal() );
			}

			conn = esdgDatabaseConnectionFactory.getDBConnection();
			SubscriberContextDao dao = new SubscriberContextDao(conn, dbUser);

			subscriberContext.setSalesContextId( removeContextPrefix( subscriberContext.getSalesContextId() ));

			dao.saveSubscriberContext(subscriberContext);

			conn.commit();


		} catch (Exception ex) {

			try { if (null != conn) { conn.rollback(); } } catch (Exception exx) { }

			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

			logger.error("--ESDG-- Exception caught when saving subscriberContext to database."	+ subscriberContext.getSubscriberContextId(), ex);

		} finally {
			closeConnection(conn, null, null);
		}
	}

	public EsdgSubscriberContextDO selectSubscriberContext(String subscriberContextKey) {

		Connection conn = null;
		EsdgSubscriberContextDO result = null;

        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			SubscriberContextDao dao = new SubscriberContextDao(conn, dbUser);

			result = dao.selectSubscirberContextByKey(subscriberContextKey);

			if ( result!=null ) {
				if( logger.isInfoEnabled()) { logger.info("--ESDG-- found subscriberContext from database. subscriberContextKey="+ subscriberContextKey ); }
			}


		} catch (Exception ex) {

			try { if (null != conn) { conn.rollback(); } } catch (Exception exx) { }

			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when selecting subscriberContext from database. subscriberContextKey=" + subscriberContextKey, ex);

		} finally {
			closeConnection(conn, null, null);
		}

		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	public Map selectSubscriberContexts (Collection<String> keys ) {

		Connection conn = null;
		Map result = null;

        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			SubscriberContextDao dao = new SubscriberContextDao(conn, dbUser);

			result = dao.selectSubscirberContextsByKeys(keys);

			if ( result!=null ) {
				if( logger.isInfoEnabled()) { logger.info("--ESDG-- found subscriberContext from database. subscriberContextKeys "+ keys ); }
			}


		} catch (Exception ex) {

			try { if (null != conn) { conn.rollback(); } } catch (Exception exx) { }

			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.error("--ESDG-- Exception caught when selecting subscriberContext from database. subscriberContextKeys " + keys, ex);

		} finally {
			closeConnection(conn, null, null);
		}

		return result;
	}
	//===================== methods for sales items end =========================

	public List<String> searchEligibileResumeSalesIdByPhoneNumber(String phoneNumber, Date oldestInteractionStartDate) {

		Timestamp oldestInteractionTimestamp = new Timestamp(oldestInteractionStartDate.getTime());
		logger.debug("Enter phoneNumber=" + phoneNumber + " oldestInteractionStartDate=" + oldestInteractionStartDate + " oldestInteractionTimestamp=" + oldestInteractionTimestamp);

		List<String> result = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SELECT_RESUMABLE_SALES_ID_BY_PHONE);
            int idx = 1;
           	ps.setString(idx++, phoneNumber);
            
           	//SALES_CONTEXT:  SC.INTRACTN_START_TS
           	ps.setTimestamp(idx++, oldestInteractionTimestamp);
            
            rs = ps.executeQuery();
            while ( rs.next() ) {
            	String salesId = rs.getString(1);
            		result.add(CONTEXT_ID_PREFIX + salesId);
            }
         } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when executing SQL: " + SELECT_RESUMABLE_SALES_ID_BY_PHONE + "; phoneNumber=" + phoneNumber + "; oldestInteractionTimestamp=" + oldestInteractionTimestamp, ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return result;
	}

	public List<String> searchEligibileResumeSalesIdByAccount(String accountNumber, Date oldestInteractionStartDate) {

		Timestamp oldestInteractionTimestamp = new Timestamp(oldestInteractionStartDate.getTime());
		logger.debug("Enter accountNumber=" + accountNumber + " oldestInteractionStartDate=" + oldestInteractionStartDate + " oldestInteractionTimestamp=" + oldestInteractionTimestamp);

		List<String> result = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SELECT_RESUMABLE_SALES_ID_BY_ACCOUNT);
            int idx = 1;
            //SALES_CONTEXT:  SC.INTRACTN_START_TS
           	ps.setTimestamp(idx++, oldestInteractionTimestamp);
           
           	//SALES_INTRACTN: SI.CUST_BILLING_ACCOUNT_NUM
           	ps.setString(idx++, accountNumber);
            
            rs = ps.executeQuery();
            while ( rs.next() ) {
            	String salesId = rs.getString(1);
            		result.add(CONTEXT_ID_PREFIX + salesId);
            }
         } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when executing SQL: " + SELECT_RESUMABLE_SALES_ID_BY_ACCOUNT + "BAN=" + accountNumber + "; oldestInteractionTimestamp=" + oldestInteractionTimestamp, ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return result;
	}

	public List<String[]> searchSalesIdByInteractionId(String saleInteractionId) {

		logger.debug("Enter saleInteractionId=" + saleInteractionId );

		List<String[]> result = new ArrayList<String[]>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SEELECT_SALES_ID_STATUS_BY_INTERACTION_ID);
            int idx = 1;
           	ps.setString(idx++, removeInteractionPrefix(saleInteractionId) );

            rs = ps.executeQuery();
            while ( rs.next() ) {
            	String[] orderInfo = new String[2];
            	orderInfo[0] = CONTEXT_ID_PREFIX + rs.getString(1);
            	orderInfo[1] = rs.getString(2);
            	result.add( orderInfo );
            }
         } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when executing SQL: " + SEELECT_SALES_ID_STATUS_BY_INTERACTION_ID + "saleInteractionId=" + saleInteractionId , ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return result;
	}

	public List<String> searchSalesContextIdByInteractionId(String saleInteractionId) {

		logger.debug("Enter saleInteractionId=" + saleInteractionId );

		List<String> result = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SEELECT_SALES_CONTEXT_ID_BY_INTERACTION_ID);
            int idx = 1;
           	ps.setString(idx++, removeInteractionPrefix(saleInteractionId) );

            rs = ps.executeQuery();
            while ( rs.next() ) {
            	String salesContextId = rs.getString(1);
            	result.add( salesContextId );
            }
         } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when executing SQL: " + SEELECT_SALES_CONTEXT_ID_BY_INTERACTION_ID + "saleInteractionId=" + saleInteractionId , ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return result;
	}

	public List<String> searchSalesIdByAccount(String accountNumber, Date oldestInteractionStartDate) {

		Timestamp oldestInteractionTimestamp = new Timestamp(oldestInteractionStartDate.getTime());
		logger.info("Enter accountNumber=" + accountNumber + " oldestInteractionStartDate=" + oldestInteractionStartDate);

		List<String> result = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	conn = esdgDatabaseConnectionFactory.getDBConnection();

            ps = conn.prepareStatement(SELECT_SALES_ID_BY_ACCOUNT);
            int idx = 1;
            //SALES_CONTEXT:  SC.INTRACTN_START_TS
           	ps.setTimestamp(idx++, oldestInteractionTimestamp);
           
           	//SALES_INTRACTN: SI.INTRACTN_START_TS
           	ps.setTimestamp(idx++, oldestInteractionTimestamp);
           	
           	//SALES_INTRACTN: SI.CUST_BILLING_ACCOUNT_NUM
           	ps.setString(idx++, accountNumber);

            rs = ps.executeQuery();
            while ( rs.next() ) {
            	String salesId = rs.getString(1);
           		result.add(CONTEXT_ID_PREFIX + salesId);
            }
         } catch ( Exception ex ) {
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when executing SQL: " + SELECT_SALES_ID_BY_ACCOUNT + "BAN=" + accountNumber + "; oldestInteractionTimestamp=" + oldestInteractionTimestamp, ex);
        } finally {
            closeConnection(conn, ps, rs);
        }

        return result;
	}

	public String createNextPaymentId() {
        Connection conn = null;
        String salesPaymentId = null;
        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			PaymentContextDao dao = new PaymentContextDao(conn, dbUser);
			salesPaymentId = dao.createNextPaymentId();
        } catch ( Exception ex ) {
        	try {
        		if (null != conn) {
        			conn.rollback();
        		}
        	} catch (Exception exx) {}
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when getting next sequence for SALES_PAYMENT in database.", ex);
        } finally {
			closeConnection(conn, null, null);
        }
        return salesPaymentId;
	}

	public EsdgPaymentDO selectSalesPayment(String salesPaymentId) {
		Connection conn = null;
		EsdgPaymentDO result = null;
        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			PaymentContextDao dao = new PaymentContextDao(conn, dbUser);
			result = dao.selectPaymentById(salesPaymentId);
			if ( result!=null ) {
				if( logger.isInfoEnabled()) { logger.info("--ESDG-- found EsdgPaymentDO from database. salesPaymentId="+ salesPaymentId ); }
			}
		} catch (Exception ex) {
			try { if (null != conn) { conn.rollback(); } } catch (Exception exx) { }
			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when selecting EsdgPaymentDO from database. salesPaymentId=" + salesPaymentId, ex);
		} finally {
			closeConnection(conn, null, null);
		}
		return result;
	}

	public void saveSalesPayment(EsdgPaymentDO esdgPaymentDO) {
		Connection conn = null;
        try {
			if (logger.isInfoEnabled()) {
				logger.info("--ESDG-- Saving SalesPayment to database");
	        	logger.info( esdgPaymentDO.getJournal() );
			}
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			PaymentContextDao dao = new PaymentContextDao(conn, dbUser);
			esdgPaymentDO.setSalesContextId( removeContextPrefix( esdgPaymentDO.getSalesContextId() ));
			dao.savePaymentContext(esdgPaymentDO);
			conn.commit();
		} catch (Exception ex) {
			try { if (null != conn) { conn.rollback(); } } catch (Exception exx) { }
			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
			logger.error("--ESDG-- Exception caught when saving esdgPaymentDO to database."	+ esdgPaymentDO.getSalesPaymentId(), ex);
		} finally {
			closeConnection(conn, null, null);
		}
	}

	public List<String> findPaymentIdsByInteractionId(String interactionId) {
        Connection conn = null;
        List<String> salesPaymentIds = null;
        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			PaymentContextDao dao = new PaymentContextDao(conn, dbUser);
			salesPaymentIds = dao.findPaymentIdsByInteractionId(interactionId);
        } catch ( Exception ex ) {
        	try {
        		if (null != conn) {
        			conn.rollback();
        		}
        	} catch (Exception exx) {}
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught in findPaymentIdsByInteractionId.", ex);
        } finally {
			closeConnection(conn, null, null);
        }
        return salesPaymentIds;
	}

	public EsdgShipmentInteractionDO selectInteractionShipment(String salesInteractionShipmentId) {
		Connection conn = null;
		EsdgShipmentInteractionDO result = null;
        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			ShipmentContextDao dao = new ShipmentContextDao(conn, dbUser);
			result = dao.selectInteractionShipment(salesInteractionShipmentId);
			if ( result!=null ) {
				if( logger.isInfoEnabled()) { logger.info("--ESDG-- found EsdgShipmentInteractionDO from database. salesInteractionShipmentId="+ salesInteractionShipmentId); }
			}
		} catch (Exception ex) {
			try { if (null != conn) { conn.rollback(); } } catch (Exception exx) { }
			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when selecting EsdgShipmentInteractionDO from database. salesInteractionShipmentId=" + salesInteractionShipmentId, ex);
		} finally {
			closeConnection(conn, null, null);
		}
		return result;
	}

	public void saveSalesInteractionShipment(EsdgShipmentInteractionDO oValue) {
        Connection conn = null;
        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			ShipmentContextDao dao = new ShipmentContextDao(conn, dbUser);
			dao.saveSalesInteractionShipment(oValue);
			conn.commit();
        } catch ( Exception ex ) {
        	try {
        		if (null != conn) {
        			conn.rollback();
        		}
        	} catch (Exception exx) {}
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when saving to SALES_INTRACTN_SHIPMENT in database.", ex);
        } finally {
			closeConnection(conn, null, null);
        }
	}

	public String createNextSalesInteractionShipmentId() {
        Connection conn = null;
        String salesInteractionShipmentId = null;
        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			ShipmentContextDao dao = new ShipmentContextDao(conn, dbUser);
			salesInteractionShipmentId = dao.createNextSalesInteractionShipmentId();
        } catch ( Exception ex ) {
        	try {
        		if (null != conn) {
        			conn.rollback();
        		}
        	} catch (Exception exx) {}
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when getting next sequence for SALES_INTRACTN_SHIPMENT in database.", ex);
        } finally {
			closeConnection(conn, null, null);
        }
        return salesInteractionShipmentId;
	}

	public List<String> findInteractionShipmentIdByInteractionId(String interactionId) {
        Connection conn = null;
        List<String> interactionShipmentIds = null;
        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			ShipmentContextDao dao = new ShipmentContextDao(conn, dbUser);
			interactionShipmentIds = dao.findInteractionShipmentIdByInteractionId(interactionId);
        } catch ( Exception ex ) {
        	try {
        		if (null != conn) {
        			conn.rollback();
        		}
        	} catch (Exception exx) {}
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught in findInteractionShipmentIdByInteractionId.", ex);
        } finally {
			closeConnection(conn, null, null);
        }
        return interactionShipmentIds;
	}

	public void saveEsdgInteractionKeyValue(EsdgInteractionKeyValueDO keyValueDO) {
        Connection conn = null;
        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			InteractionDao dao = new InteractionDao(conn, dbUser);
			dao.upsertSalesInteractionKeyValue(keyValueDO);
			conn.commit();
        } catch ( Exception ex ) {
        	try {
        		if (null != conn) {
        			conn.rollback();
        		}
        	} catch (Exception exx) {}
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when saving to SALES_INTRACTN_KEYVALUE in database.", ex);
        } finally {
			closeConnection(conn, null, null);
        }
	}

	public EsdgInteractionKeyValueDO selectEsdgInteractionKeyValue(String salesInteractionId, String keyName) {
		Connection conn = null;
		EsdgInteractionKeyValueDO result = null;
        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			InteractionDao dao = new InteractionDao(conn, dbUser);
			result = dao.selectSalesInteractionKeyValue(salesInteractionId, keyName);
			if ( result!=null ) {
				if( logger.isInfoEnabled()) { logger.info("--ESDG-- found EsdgInteractionKeyValueDO from database. salesInteractionId="+ salesInteractionId + ", keyName: " + keyName); }
			}
		} catch (Exception ex) {
			try { if (null != conn) { conn.rollback(); } } catch (Exception exx) { }
			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when selecting EsdgInteractionKeyValueDO from database. salesInteractionId="+ salesInteractionId + ", keyName: " + keyName, ex);
		} finally {
			closeConnection(conn, null, null);
		}
		return result;
	}
	
	//CMSVCPO-1127 PO - Return order status history for order details
	public List<EsdgSalesOrderLifeCycleDO> selectSalesOrderLifeCycle(String salesInteractionId) {
		Connection conn = null;
		List<EsdgSalesOrderLifeCycleDO> result = new ArrayList<EsdgSalesOrderLifeCycleDO>();
        try {
			conn = esdgDatabaseConnectionFactory.getDBConnection();
			InteractionDao dao = new InteractionDao(conn, dbUser);
			result = dao.selectSalesOrderLifeCycle(salesInteractionId);
			if (!result.isEmpty()) {
				if( logger.isInfoEnabled()) { logger.info("--ESDG-- found EsdgInteractionKeyValueDO from database. salesInteractionId="+ salesInteractionId); }
			}
		} catch (Exception ex) {
			try { if (null != conn) { conn.rollback(); } } catch (Exception exx) { }
			esdgDatabaseConnectionFactory.invalidateDBConnection(conn);
        	logger.error("--ESDG-- Exception caught when selecting EsdgInteractionKeyValueDO from database. salesInteractionId="+ salesInteractionId, ex);
		} finally {
			closeConnection(conn, null, null);
		}
		return result;
	}

	/**
	 * Search order.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the collection of interaction_id , a.k.a PO id
	 */
	public List<EsdgOrderDO_1> searchOrderByClassifiers(Date startDate, Date endDate, Map<String, List<String>> classifiers, List<String> statuses) {
		
		List<EsdgOrderDO_1> resultSet = new ArrayList<EsdgOrderDO_1>();

		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	if( startDate == null || endDate == null || 
        			( (classifiers==null ||classifiers.isEmpty()) && ( statuses==null || statuses.isEmpty()	))	
        		) {
        		logger.warn("--ESDG-- Abort to query order database due to missing mandatory inputs.");
        		return null;
        	}
        	conn = esdgDatabaseConnectionFactory.getDBConnection();
        	
    		
//    		for( int i=0; i<3; i++) {
//    			EsdgOrderDO_1 order = new EsdgOrderDO_1();
//    			order.setSalesContextId("ES00"+ i);
//    			order.setOrderStatus("prepared");
//    			order.setSalesInteractionId("PO100"+ i);
//    			order.setTypeCode("productName-00"+i);
//    			resultSet.add(order);
//    		}

    		OrderQueryBuilder queryBuilder = new OrderQueryBuilder();
    		queryBuilder.setDateRange(startDate, endDate);
    		queryBuilder.setClassifier(classifiers);
    		queryBuilder.setStatuses(statuses);
    		
    		ps = queryBuilder.getPreparedStatement(conn);
            rs = ps.executeQuery();
            
            while ( rs.next() ) {
    			EsdgOrderDO_1 order = new EsdgOrderDO_1();
    			order.setSalesContextId( CONTEXT_ID_PREFIX + rs.getString("SALES_CONTEXT_ID" ) );
    			order.setOrderStatus( rs.getString("ORDER_STATUS" ));
    			order.setSalesInteractionId( rs.getString("SALES_INTRACTN_ID" ));
    			//hijack EsdgOrderDO_1, use typeCode as productName
    			order.setTypeCode(rs.getString("PRODUCT_NAME" ));

    			resultSet.add(order);
            }

            if( logger.isDebugEnabled() ) logger.debug("--ESDG-- Query order database. Found (" + resultSet.size() + ").");
        	
        } catch ( Exception ex ) {
        	
        	esdgDatabaseConnectionFactory.invalidateDBConnection(conn);

        	logger.debug("--ESDG-- Exception caught when querying order database. " , ex);
        	
        } finally {
            closeConnection(conn, ps, rs);
        }
        return resultSet;
	}
	
	static class OrderQueryBuilder {
		
		private static String mainSelect = "select c.sales_context_id, c.sales_intractn_id "
				+ ", S.SALES_ORDER_STATUS_CD as order_status "
				+ ", cf.SALES_CLASSIFIER_VALUE as PRODUCT_NAME "
				+ "from sales_context c "
				+ "left outer join sales_classifier cf on cf.sales_context_id = c.sales_context_id and cf.intractn_start_ts = c.intractn_start_ts and cf.SALES_CLASSIFIER_TYP_CD='PRODUCT_NAME' "
				+ "left outer join sales_order o on c.sales_context_id=o.sales_context_id and o.intractn_start_ts=c.intractn_start_ts "
				+ "left outer join sales_order_lifecycl s on s.sales_order_id = O.sales_order_id and s.sales_order_version_num = (SELECT MAX(sales_order_version_num) FROM sales_order_lifecycl d2 WHERE d2.sales_order_id = O.sales_order_id ) "
				+ "where (c.sales_context_id,c.intractn_start_ts) in ";
		
		private static String classifierClause = " ( select sales_context_id, intractn_start_ts from sales_classifier "
				+ "where SALES_CLASSIFIER_TYP_CD=? and SALES_CLASSIFIER_VALUE in ";
		
		private static String dateRangeClause =	" and intractn_start_ts between ? and ? ";

		private static String statusClause = " and S.SALES_ORDER_STATUS_CD in ( ";
		
		private Date startDate;
		private Date endDate;
		private List<String> statuses ;
		private Map<String,List<String>> classifiers;
		
		private List<Object> parameterValues = new ArrayList<Object> ();
		
		public void setDateRange( Date startDate, Date endDate ) {
			this.startDate = startDate;
			this.endDate = endDate;
		}
		public void setStatuses( List<String> statuses ) {
			this.statuses = statuses;
		}
		
		public void setClassifier( Map<String,List<String>> classifiers ) {
			this.classifiers = classifiers;
		}
		
		public String buildSQL( ) {
			
			StringBuilder sqlBuilder = new StringBuilder(mainSelect) ;
			
			int idx = 0;
			for( Map.Entry<String, List<String>> entry : classifiers.entrySet() ) {
				
				sqlBuilder.append( classifierClause ).append( " ( ");
				
				//add  SALES_CLASSIFIER_VALUE placeholder
				for( int i=0; i<entry.getValue().size()-1; i++ ) {
					sqlBuilder.append(" ?,");
				}
				sqlBuilder.append(" ? ) ");

				parameterValues.add( entry.getKey() );
				parameterValues.addAll( entry.getValue() );

				
				if (idx==classifiers.size()-1) {
					//for the last one, add date range clause 
					sqlBuilder.append( dateRangeClause );
					parameterValues.add( new Timestamp( startDate.getTime()));
					parameterValues.add( new Timestamp( endDate.getTime()));
					
					//sub-query close
					sqlBuilder.append( " ) " );
					for( int i=0; i<idx; i++ ) {sqlBuilder.append( " ) " );	}
					
				} else {
					sqlBuilder.append ( " and (sales_context_id,intractn_start_ts) in " ); 
				}
				
				
				idx++;
			}
			
			if ( statuses!=null && statuses.isEmpty()==false ) {
				sqlBuilder.append( statusClause );
				for( int i=0; i<statuses.size()-1; i++ ) {
					sqlBuilder.append(" ?,");
				}
				sqlBuilder.append("? )");
				
				parameterValues.addAll( statuses );
			}
			
			return sqlBuilder.toString();
		}
		
		public PreparedStatement getPreparedStatement( Connection conn ) throws Exception {
			
			String sql = buildSQL();
			logger.info( sql );
			logger.info( "parameters: " + parameterValues );

			PreparedStatement ps = conn.prepareStatement( sql );

			int idx=1;

			for( Object value : parameterValues ) {
				ps.setObject( idx++, value );
			}
			
			return ps;
		}
	}
}
