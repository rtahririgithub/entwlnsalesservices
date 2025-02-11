package com.telus.csm.ewlnsc.delegate;

import static com.telus.csm.ewlnsc.transformer.CommerceShoppingCartTransformer.mapToShoppingCartVO;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.ICommerceShoppingCartMgmtRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetCommerceShoppingCartAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCommerceShoppingCartAdapterResponse;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;

/**
 * This class is responsible for retrieve CommerceShoppingCart and convert it into ShoppingCartVO 
 */
public class ShoppingCartDelegateV2 {

	private static final Logger LOGGER = Logger.getLogger(ShoppingCartDelegateV2.class);
	
	
	private static ShoppingCartDelegateV2 singleton = null;
	

	private ShoppingCartDelegateV2() {}
	
	public static synchronized ShoppingCartDelegateV2 getInstance() {

		if (singleton == null) {
			singleton = new ShoppingCartDelegateV2();
		}
		return singleton;
	}

	
	public ShoppingCartVO getShoppingCart( String txnId,  String shoppingCartId, String cartItemId ) {
		
		long startTime = System.currentTimeMillis();
		
		try {
			
			LOGGER.info( "Retrieving by cacheKey=" + shoppingCartId);
			
			GetCommerceShoppingCartAdapterRequest adapterRequest = new GetCommerceShoppingCartAdapterRequest();
			adapterRequest.setShoppingCartId(shoppingCartId);
			adapterRequest.setSalesTransactionId(txnId);
			
			ICommerceShoppingCartMgmtRestSvcAdapter adapter = AdapterFactory.getAdapter( ICommerceShoppingCartMgmtRestSvcAdapter.class );
			GetCommerceShoppingCartAdapterResponse adapterResponse = adapter.getCommerceShoppingCart( adapterRequest );
			
			ShoppingCartVO shoppingCartVO = mapToShoppingCartVO( adapterResponse.getShoppingCart(), cartItemId );
					
			return shoppingCartVO;

		} finally {
			
			LOGGER.debug( "getShoppingCart timeElapsed(ms):" + (System.currentTimeMillis()- startTime )  );
		}
	}

	
}
