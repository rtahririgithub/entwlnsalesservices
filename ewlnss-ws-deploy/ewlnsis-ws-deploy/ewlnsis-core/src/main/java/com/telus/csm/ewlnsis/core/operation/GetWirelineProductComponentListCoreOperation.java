package com.telus.csm.ewlnsis.core.operation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.GetCatalogueHierarchyResponseVO;
import com.telus.csm.ewlnsc.domain.GetProductCatalogueHierarchyRequestVO;
import com.telus.csm.ewlnsc.domain.ProductCatalogueItemVO;
import com.telus.csm.ewlnsc.grid.domain.ProductCharacteristicDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.ValidationUtil;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.*;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.*;

import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsis.core.domain.GetWirelineProductComponentListCoreRequest;
import com.telus.csm.ewlnsis.core.domain.GetWirelineProductComponentListCoreResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetWirelineProductComponentListType.SalesProductComponentList;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductCatalogueIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.QuantityAllowed;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductCatalogueItem.ProductCharacteristicList;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetWirelineProductComponentListCoreOperation {

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetWirelineProductComponentListCoreOperation.class);
	
	public GetWirelineProductComponentListCoreResponse execute( GetWirelineProductComponentListCoreRequest request) {
		String functionName = "execute()";
		logger.enter(functionName);
		
		GetWirelineProductComponentListCoreResponse response = new GetWirelineProductComponentListCoreResponse();
		// Validate Request
		List<String> missingElementList = new ArrayList<String>();
		List<String> invalidInputList = new ArrayList<String>();
		List<String> warningList = new ArrayList<String>();
		
		validateInput(request, missingElementList, invalidInputList, warningList);
		if (!missingElementList.isEmpty() || !invalidInputList.isEmpty() || !warningList.isEmpty()) {
			response.setMessageList(ValidationUtil.generateMessageList(request.getOperationHeader(), missingElementList, invalidInputList, warningList, GWPCL_MISSING_MANDATORY_ELEMENTS, GWPCL_INVALID_INPUT));
			logger.info(functionName, EnterpriseWLNSalesServicesConstants.INPUT_VALIDATION_FAILED_OR_WARNING_MSG);
		}
		
		if (!missingElementList.isEmpty() || !invalidInputList.isEmpty()) {
			return response;
		}
		
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		List <GetCatalogueHierarchyResponseVO> productCatalogueItemVOList = new ArrayList<GetCatalogueHierarchyResponseVO>();
		for (SalesProductComponentList salesProdComp : request.getSalesProductComponentList()) {
			if (isProductTypeCodeValidforESS(salesProdComp.getProductTypeCode())) {
				GetProductCatalogueHierarchyRequestVO requestVO = new GetProductCatalogueHierarchyRequestVO();
				requestVO.setTemplateId(salesProdComp.getProductTemplateId());
				requestVO.setMainComponentId(salesProdComp.getMainComponentId());
				for (ProductComponentIdentifier prod : salesProdComp.getProductComponentList()) {
					requestVO.addComponentIdentifier(prod.getParentProductCatalogueIdentifier(), prod.getProductCatalogueIdentifier());
				}
				GetCatalogueHierarchyResponseVO productCatalogueHierarchy = gridHelper.getProductCatalogueHierarchy(requestVO);
				if (productCatalogueHierarchy != null) {
					productCatalogueItemVOList.add(productCatalogueHierarchy);
				}
			}
		}
		
		populateResponse(productCatalogueItemVOList, response);
		
		logger.exit(functionName);
		return response;
	}

	/**
	 * @param productCatalogueItemVOList
	 * @param response
	 */
	private void populateResponse(List<GetCatalogueHierarchyResponseVO> productCatalogueItemVOList,
			GetWirelineProductComponentListCoreResponse response) {
		List<WirelineProductCatalogueItem> salesProductComponentList;
		if (!productCatalogueItemVOList.isEmpty()) {
			
			salesProductComponentList = new ArrayList<WirelineProductCatalogueItem>();
			
			for ( GetCatalogueHierarchyResponseVO responseVO : productCatalogueItemVOList) {
				
				salesProductComponentList.add( transform( responseVO.getCatalogueItem() ) );
				
				response.getMessageList().addAll( responseVO.getMessageList() );
			}
			response.setSalesProductComponentList(salesProductComponentList);
		}
		
	}

	/**
	 * @param components
	 * @return
	 */
	private List<WirelineProductCatalogueItem> transform(List<ProductCatalogueItemVO> productCatalogueItemVOList) {
		List<WirelineProductCatalogueItem> prodCompList = new ArrayList<WirelineProductCatalogueItem>();
		if (productCatalogueItemVOList != null && !productCatalogueItemVOList.isEmpty()) {
			for (ProductCatalogueItemVO prod : productCatalogueItemVOList){
				WirelineProductCatalogueItem prodComponent = transform(prod);
				
				prodCompList.add(prodComponent);
			}
		}
		return prodCompList;
	}

	protected WirelineProductCatalogueItem transform(ProductCatalogueItemVO prod) {
		WirelineProductCatalogueItem prodComponent = new WirelineProductCatalogueItem();
		
		ProductCatalogueIdentifier prodCatIdentif = new ProductCatalogueIdentifier();
		prodCatIdentif.setProductCatalogueItemId(prod.getCatalogueItemId());
		prodCatIdentif.setExternalProductCatalogueItemId(prod.getProductCode());
		
		prodComponent.setProductCatalogueItemIdentifier(prodCatIdentif);
		
		Description productCatalogueNameTxt = new Description();
		productCatalogueNameTxt.setLocale(LANG_EN);
		productCatalogueNameTxt.setDescriptionText(prod.getName());
		
		prodComponent.setProductCatalogueNameTxt(productCatalogueNameTxt);
		
		Description productCatalogueDescription = new Description();
		productCatalogueDescription.setLocale(LANG_EN);
		productCatalogueDescription.setDescriptionText(prod.getDescription());
		
		prodComponent.setProductCatalogueDescription(productCatalogueDescription);
		
		prodComponent.setCatalogueItemTypeCode(prod.getCatalogueItemType());
		prodComponent.setServiceTypeCode(prod.getComponentServiceType());
		
		QuantityAllowed quantity = new QuantityAllowed();
		quantity.setMinNum(BigInteger.valueOf(prod.getMinimumQuatity()));
		quantity.setMaxNum(BigInteger.valueOf(prod.getMaximumQuatity()));
		
		prodComponent.setQuantity(quantity);
		
		prodComponent.setProductCharacteristicList(transformCharacteristicList(prod.getCharacteristics()));
		
		prodComponent.setComponentList(transform(prod.getComponents()));
		return prodComponent;
	}

	/**
	 * @param characteristics
	 * @return
	 */
	private List<ProductCharacteristicList> transformCharacteristicList(List<ProductCharacteristicDO> param) {
		List<ProductCharacteristicList> characteristicList = new ArrayList<ProductCharacteristicList>();
		for (ProductCharacteristicDO characteristicParam : param) {
			ProductCharacteristicList charact = new ProductCharacteristicList();
			charact.setProductCatalogueItemId(characteristicParam.getCatalogueItemId());
			charact.setProductCharacteristicName(characteristicParam.getName());
			charact.setProductCharacteristicCode(characteristicParam.getCode());
			charact.setDefaultValueTxt(characteristicParam.getValue());
			characteristicList.add(charact);
		}
		return characteristicList;
	}

	private void validateInput(GetWirelineProductComponentListCoreRequest rq, List<String> missingList,
			List<String> invalidInputList, List<String> warningList) {
		String functionName = "validateInput()";
		logger.enter(functionName);
		// validate Header
		ValidationUtil.validateHeader(rq.getOperationHeader(), missingList, invalidInputList);
		
		// all elements outside operationHeader are mandatory
		if (rq.getSalesProductComponentList().isEmpty()) {
			missingList.add(GWPCL_MISSING_SALES_PROD_COMP_LIST);
		} else {
			for (SalesProductComponentList prodComp : rq.getSalesProductComponentList()) {
				if (StringUtils.isEmpty(prodComp.getProductTypeCode())) {
					missingList.add(GWPCL_MISSING_PRODUCT_TYPE);
				} else if (!isProductTypeCodeValid(prodComp.getProductTypeCode())){
					invalidInputList.add(GWPCL_INVALID_PRODUCT_TYPE);
				}
				
				if (StringUtils.isEmpty(prodComp.getProductTemplateId())) {
					missingList.add(GWPCL_MISSING_PRODUCT_TEMPLATE_IDENTIF);
				}
				if (StringUtils.isEmpty(prodComp.getMainComponentId())) {
					missingList.add(GWPCL_MISSING_MAIN_COMPONENT_IDENTIF);
				}
				
				if (prodComp.getProductComponentList().isEmpty()) {
					missingList.add(GWPCL_MISSING_PRODUCT_COMPONENT_LIST);
				} else {
					//validate the size of productComponentList
					int maxProductComponentListSize = 100;
					try {
						maxProductComponentListSize = Integer.parseInt(ApplicationProperties.getConfigString("${common/getWirelineProductComponentListParams/maxProductComponentList}"));
					} catch (NumberFormatException e) {
						logger.error(e);
					}
					
					if (prodComp.getProductComponentList().size() > maxProductComponentListSize) {
						invalidInputList.add(GWPCL_INVALID_PRODUCT_COMPONENT_LIST);
					}
					for (ProductComponentIdentifier prodIdentif : prodComp.getProductComponentList()) {
						if (StringUtils.isEmpty(prodIdentif.getProductCatalogueIdentifier())) {
							missingList.add(GWPCL_MISSING_PRODUCT_CATALOG_IDENTIF);
						} else if (!StringUtils.isNumeric(prodIdentif.getProductCatalogueIdentifier()) || Integer.valueOf(prodIdentif.getProductCatalogueIdentifier()) == 0){
							invalidInputList.add(GWPCL_INVALID_PRODUCT_CATALOG_IDENTIF);
						}
						if (StringUtils.isEmpty(prodIdentif.getParentProductCatalogueIdentifier())) {
							missingList.add(GWPCL_MISSING_PARENT_PRODUCT_CATALOG_IDENTIF);
						} else if (!StringUtils.isNumeric(prodIdentif.getParentProductCatalogueIdentifier()) || Integer.valueOf(prodIdentif.getParentProductCatalogueIdentifier()) == 0){
							invalidInputList.add(GWPCL_INVALID_PARENT_PRODUCT_CATALOG_IDENTIF);
						}
						
					}
				}
			}
		}
		logger.exit(functionName);
	}

	/**
	 * @param productTypeCode
	 * @return
	 */
	private boolean isProductTypeCodeValid(String productTypeCode) {
		// valid values are: HSIC, TTV, SING, STV
		// [CL] during code review: include STV as possible valid value
		boolean condition1 = isProductTypeCodeValidforESS(productTypeCode);
		return condition1 || STV.equalsIgnoreCase(productTypeCode);
	}
	
	private boolean isProductTypeCodeValidforESS(String productTypeCode) {
		return HSIC.equalsIgnoreCase(productTypeCode) || SING.equalsIgnoreCase(productTypeCode) || TTV.equalsIgnoreCase(productTypeCode);
	}
}
