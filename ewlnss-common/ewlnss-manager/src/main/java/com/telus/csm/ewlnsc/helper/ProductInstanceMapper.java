package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.scis.domain.FeatureInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.delegate.ReferencePDSDataSvcBusDelegator;
import com.telus.csm.ewlnsc.domain.GetReferencePDSResponseDO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.SalesServiceConstants;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductInstanceList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductServiceInstance;
import com.telus.tmi.xmlschema.xsd.product.productinstance.customer_product_instance_sub_domain_v2.ProductParameter;

/**
 * @author Jose.Mena
 *
 */
public class ProductInstanceMapper {
	
	// private static Map<String, String> salesProducttoOMSProductMap = new HashMap<String, String>()
	
	private ProductInstanceMapper() {
		
	}
	
	private static Map<String, String> getSalesProducttoOMSProductMap() {
		Map<String, String> salesProducttoOMSProductMap = new HashMap<String, String>();
		Map<String, String> wssToOMSMap = getWSSProductToOMSProductMap();
		for (Map.Entry<String, String> entry : wssToOMSMap.entrySet()) {
			salesProducttoOMSProductMap.put(entry.getKey(), entry.getValue());
		}
		return salesProducttoOMSProductMap;
	}
	
	public static ProductInstanceList mapToProductInstanceForAvailableProducts(String productRequestId, List<SubscribedProductInfoRestVO> subscribedProductSummaryList) {
		ProductInstanceList productInstanceList = new ProductInstanceList();
		List<ProductServiceInstance> prodSvcList = new ArrayList<ProductServiceInstance>();
		for (int i = 0; subscribedProductSummaryList != null && i < subscribedProductSummaryList.size(); i++) {
			SubscribedProductInfoRestVO subscribedProductSummary = subscribedProductSummaryList.get(i);
			
			// don't pass product instance for SING
//			String productType = mapWSSProductToProductType(subscribedProductSummary.getProductType())
//			if (SalesServiceConstants.SALES_PRODUCT_TYPE_SINGLE_LINE.equalsIgnoreCase(productType)) {
//				continue
//			}
			
			ProductServiceInstance prodSvc = new ProductServiceInstance();
			mapProductServiceInstanceForAvailableProducts(productRequestId, subscribedProductSummary, prodSvc);
			//prodSvc.setAddress(subscribedProductSummary.getServiceAddress())
			//prodSvc.setProductTypeCode(getSalesProducttoOMSProductMap().get(subscribedProductSummary.getProductTypeCd()))
			prodSvc.setProductTypeCode(subscribedProductSummary.getProductTypeCd());
			prodSvcList.add(prodSvc);
		}
		
		productInstanceList.getProductInstance().addAll(prodSvcList);
		
		return productInstanceList;
	}

	/**
	 * @param productRequestId
	 * @param subscribedProductSummary
	 * @param prodSvc
	 */
	private static void mapProductServiceInstanceForAvailableProducts(String productRequestId, SubscribedProductInfoRestVO subscribedProductInfoRestVO, ProductServiceInstance prodServInstance) {
		String productType = subscribedProductInfoRestVO.getProductTypeCd();
		
		// SubscribedProductSummaryVO.ProductInstance existingProduct = subscribedProductInfoRestVO.getProductInstance()
		List<ProductInstanceInfoRestVO> existingProduct = subscribedProductInfoRestVO.getProductInstance();
		if (existingProduct != null && !existingProduct.isEmpty()) {
			ProductInstanceInfoRestVO existingProd = existingProduct.get(0);
			
			prodServInstance.setKeyId(existingProd.getProductInstanceId()); // APID
			if (prodServInstance.getKeyId() == null || prodServInstance.getKeyId().length() == 0) {
				prodServInstance.setKeyId(" "); // empty space;
			}
			
			prodServInstance.setServiceId(existingProd.getServiceId());
			if (prodServInstance.getServiceId() == null || StringUtils.isEmpty(prodServInstance.getServiceId())) {
				prodServInstance.setServiceId(" "); // empty space
			}
			
			if (existingProd.getProductCatalogId() != null) {
				prodServInstance.setProductCatalogId(Long.valueOf(existingProd.getProductCatalogId()));
			}
			
			prodServInstance.setProductTypeCode(productType);
			
			List<ProductParameter> prodServParamList = new ArrayList<ProductParameter>(); // T
			
			//+++fixed productRequestId passed "null" value should be numeric Type
			String productRequestIdAppedix = productRequestId;
			productRequestIdAppedix += productType;
			
			ProductParameter prodServParam1 = createProductParameter("productRequestId", productRequestIdAppedix);	
			
			prodServParamList.add(prodServParam1);
			
			if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(subscribedProductInfoRestVO.getProductTypeCd())){
				if(existingProd.getSingleLineComponent()!=null && existingProd.getSingleLineComponent().getWholesaleAdslInd()!=null){
					ProductParameter prodServParamWholeSalesInd = createProductParameter("wholesaleAdslIndicator", existingProd.getSingleLineComponent().getWholesaleAdslInd().toString());
					prodServParamList.add(prodServParamWholeSalesInd);
				}else{
					ProductParameter prodServParamWholeSalesInd = createProductParameter("wholesaleAdslIndicator", "false");
					prodServParamList.add(prodServParamWholeSalesInd);
				}
				
				//send the calling features if these are availables from consolidatedAcc response
				
				if(existingProd.getSingleLineComponent()!=null && existingProd.getSingleLineComponent().getCallingFeatureList()!=null && !existingProd.getSingleLineComponent().getCallingFeatureList().isEmpty()){
					for(FeatureInfoRestVO feature : existingProd.getSingleLineComponent().getCallingFeatureList()){
						ProductParameter prodServParamCallingFeature = new ProductParameter();
						prodServParamCallingFeature.setParameterName("callingFeatures");
						prodServParamCallingFeature.setParameterValue(feature.getName()); 
						prodServParamList.add(prodServParamCallingFeature);
					}
				}
			}
			
			String resourceId = subscribedProductInfoRestVO.getProductInstance().get(0).getResourceId();
			if (resourceId == null || resourceId.length() == 0) resourceId = "";
			ProductParameter prodServParam2 = createProductParameter("resourceId", resourceId);
			prodServParamList.add(prodServParam2);
			
			if(SalesServiceConstants.OMS_PRODUCT_HSIC.equalsIgnoreCase(subscribedProductInfoRestVO.getProductTypeCd())){
				String localServiceProvider = subscribedProductInfoRestVO.getProductInstance().get(0).getInternetComponent().getInternetTypeCd();
				ProductParameter prodServParam3=null;
				if(!StringUtils.isBlank(localServiceProvider)){
					prodServParam3 = createProductParameter("localServiceProvider", localServiceProvider);
				}else{
					prodServParam3 = createProductParameter("localServiceProvider", "");
				}
				
				prodServParamList.add(prodServParam3);
				
			}
			
			if(SalesServiceConstants.OMS_PRODUCT_TTV.equalsIgnoreCase(subscribedProductInfoRestVO.getProductTypeCd())){
				if(subscribedProductInfoRestVO.getProductInstance().get(0).getEquipmentList()!=null){	
					int equipSize = subscribedProductInfoRestVO.getProductInstance().get(0).getEquipmentList().size();
					ProductParameter prodServParam4 = createProductParameter("totalNumberOfSTBs", ""+equipSize);
					prodServParamList.add(prodServParam4);
				} else {
					ProductParameter prodServParam4 = createProductParameter("totalNumberOfSTBs", "0");
					prodServParamList.add(prodServParam4);
				}
				
				if (subscribedProductInfoRestVO.getProductInstance().get(0) != null && subscribedProductInfoRestVO.getProductInstance().get(0).getTtvComponent().getHdChannelInd() != null) {
					ProductParameter prodServParam5 = createProductParameter("HDChannelInd", subscribedProductInfoRestVO.getProductInstance().get(0).getTtvComponent().getHdChannelInd().toString());
					prodServParamList.add(prodServParam5);
				} else {
					ProductParameter prodServParam5 = createProductParameter("HDChannelInd", "false");
					prodServParamList.add(prodServParam5);
				}
				
			}
			
			//"SL, is the instance of the clearancePathId,	VESTA cannot get the servicePathID from OMS"
			
			prodServInstance.getProductParameters().addAll(prodServParamList);
		}
	}

	/**
	 * @param string
	 * @param productRequestIdAppedix
	 * @return
	 */
	private static ProductParameter createProductParameter(String name, String value) {
		ProductParameter prodServParam = new ProductParameter();
		prodServParam.setParameterName(name);
		prodServParam.setParameterValue(value);
		return prodServParam;
	}

	/**
	 * @param wssProduct
	 * @return
	 */
	public static String mapWSSProductToProductType(String wssProduct) {
		Map <String, String> wssOMSProductMap;
		String code = "";
		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
		wssOMSProductMap = refPdsBusDelegate.getReferencePDSTableByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PROD_OMS_PROD);
		if (wssOMSProductMap.isEmpty()){
			return code;
		}
		if (wssOMSProductMap.containsKey(wssProduct)){
			return wssOMSProductMap.get(wssProduct);
		}
		return code;
	}
	
	private static Map<String, String> getWSSProductToOMSProductMap(){
		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
		return refPdsBusDelegate.getReferencePDSTableByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PROD_OMS_PROD);
	}

}
