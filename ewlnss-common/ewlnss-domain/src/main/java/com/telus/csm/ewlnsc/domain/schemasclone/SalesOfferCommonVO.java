package com.telus.csm.ewlnsc.domain.schemasclone;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;
import com.telus.csm.ewlnsc.domain.schemasclone.ServiceIdentifier;


/**
 * Class to retain the necessary objects to call OfferInformationService to get the OfferListForCustomer
 * 
 * @author Alejandro.Hernandez
 *
 */
public class SalesOfferCommonVO {
	
	private GetOffersResponseVO offersResponseVO;
	private GetAssignedAndPendingProductResponseVO assignedAndPendingProductsResponseVO;
	private GetProductQualificationAdapterResponse productQualificationAdapterResponseVO;
	private GetCreditProfileByCustomerIdAdapterResponse creditProfileByCustIdResponseVO;
	private GetCreditEligibilityAdapterResponse creditEligibilityAdapterResponseVO;
	private ServiceAddressResponseVO serviceAddressResponseVO;
	private CheckProductFeasibilityAdapterResponse checkFeasibilityResponseVO;
	private GetOffersRequestVO offersRequestVO;
	private GetOfferListAdapterResponse getOfferListAdapterResponse;
	private GetAvailableServiceInstanceListAdapterResponse availableServiceInstanceListAdapterResponse;
	private GetOfferListAdapterResponse accessoryOfferListByOfferIdentifierListAdapterResponse;
	private boolean callCheckFeasibilityInd;
	private boolean isCustomerEligibleForRecontract;
	private String productCatalogIdForRecontracting;
	private String tierCdForRecontracting;
	private String hsicTierCodeForRequestedProduct = "";
	private GetSalesOfferDetailRequestVO offerDetailRequestVO;
	private boolean isNoPortsAvailableInd;
	// 2018 June Exception release for TTV recontracting
	// Fix for QC 65443
	private String hsTierCdForTTVRecontracting;
	
	public boolean isNoPortsAvailableInd() {
		return isNoPortsAvailableInd;
	}
	public void setNoPortsAvailableInd(boolean isNoPortsAvailableInd) {
		this.isNoPortsAvailableInd = isNoPortsAvailableInd;
	}
	public String getHsicTierCodeForRequestedProduct() {
		return hsicTierCodeForRequestedProduct;
	}
	public void setHsicTierCodeForRequestedProduct(String hsicTierCodeForRequestedProduct) {
		this.hsicTierCodeForRequestedProduct = hsicTierCodeForRequestedProduct;
	}
	public GetOfferListAdapterResponse getOfferListAdapterResponse() {
		return getOfferListAdapterResponse;
	}
	public void setOfferListAdapterResponse(GetOfferListAdapterResponse getOfferListAdapterResponse) {
		this.getOfferListAdapterResponse = getOfferListAdapterResponse;
	}
	public GetOffersRequestVO getOffersRequestVO() {
		return offersRequestVO;
	}
	public void setOffersRequestVO(GetOffersRequestVO offersRequestVO) {
		this.offersRequestVO = offersRequestVO;
	}
	public GetOffersResponseVO getOffersResponseVO() {
		return offersResponseVO;
	}
	public void setOffersResponseVO(GetOffersResponseVO offersResponseVO) {
		this.offersResponseVO = offersResponseVO;
	}
	public GetAssignedAndPendingProductResponseVO getAssignedAndPendingProductsResponseVO() {
		return assignedAndPendingProductsResponseVO;
	}
	public void setAssignedAndPendingProductsResponseVO(
			GetAssignedAndPendingProductResponseVO assignedAndPendingProductsResponseVO) {
		this.assignedAndPendingProductsResponseVO = assignedAndPendingProductsResponseVO;
	}
	public GetProductQualificationAdapterResponse getProductQualificationAdapterResponseVO() {
		return productQualificationAdapterResponseVO;
	}
	public void setProductQualificationAdapterResponseVO(
			GetProductQualificationAdapterResponse productQualificationAdapterResponseVO) {
		this.productQualificationAdapterResponseVO = productQualificationAdapterResponseVO;
	}
	public GetCreditProfileByCustomerIdAdapterResponse getCreditProfileByCustIdResponseVO() {
		return creditProfileByCustIdResponseVO;
	}
	public void setCreditProfileByCustIdResponseVO(
			GetCreditProfileByCustomerIdAdapterResponse creditProfileByCustIdResponseVO) {
		this.creditProfileByCustIdResponseVO = creditProfileByCustIdResponseVO;
	}
	public GetCreditEligibilityAdapterResponse getCreditEligibilityAdapterResponseVO() {
		return creditEligibilityAdapterResponseVO;
	}
	public void setCreditEligibilityAdapterResponseVO(
			GetCreditEligibilityAdapterResponse creditEligibilityAdapterResponseVO) {
		this.creditEligibilityAdapterResponseVO = creditEligibilityAdapterResponseVO;
	}
	public CheckProductFeasibilityAdapterResponse getCheckFeasibilityResponseVO() {
		return checkFeasibilityResponseVO;
	}
	public void setCheckFeasibilityResponseVO(CheckProductFeasibilityAdapterResponse checkFeasibilityResponseVO) {
		this.checkFeasibilityResponseVO = checkFeasibilityResponseVO;
	}
	public ServiceAddressResponseVO getServiceAddressResponseVO() {
		return serviceAddressResponseVO;
	}
	public void setServiceAddressResponseVO(ServiceAddressResponseVO serviceAddressResponseVO) {
		this.serviceAddressResponseVO = serviceAddressResponseVO;
	}
	public GetAvailableServiceInstanceListAdapterResponse getAvailableServiceInstanceListAdapterResponse() {
		return availableServiceInstanceListAdapterResponse;
	}
	public void setAvailableServiceInstanceListAdapterResponse(
			GetAvailableServiceInstanceListAdapterResponse availableServiceInstanceListAdapterResponse) {
		this.availableServiceInstanceListAdapterResponse = availableServiceInstanceListAdapterResponse;
	}
	public boolean isCallCheckFeasibilityInd() {
		return callCheckFeasibilityInd;
	}
	public void setCallCheckFeasibilityInd(boolean callCheckFeasibilityInd) {
		this.callCheckFeasibilityInd = callCheckFeasibilityInd;
	}
	public boolean isCustomerEligibleForRecontract() {
		return isCustomerEligibleForRecontract;
	}
	public void setCustomerEligibleForRecontract(boolean isCustomerEligibleForRecontract) {
		this.isCustomerEligibleForRecontract = isCustomerEligibleForRecontract;
	}
	public String getProductCatalogIdForRecontracting() {
		return productCatalogIdForRecontracting;
	}
	public void setProductCatalogIdForRecontracting(String productCatalogIdForRecontracting) {
		this.productCatalogIdForRecontracting = productCatalogIdForRecontracting;
	}
	public GetSalesOfferDetailRequestVO getOfferDetailRequestVO() {
		return offerDetailRequestVO;
	}
	public void setOfferDetailRequestVO(GetSalesOfferDetailRequestVO offerDetailRequestVO) {
		this.offerDetailRequestVO = offerDetailRequestVO;
	}
	public String getTierCdForRecontracting() {
		return tierCdForRecontracting;
	}
	public void setTierCdForRecontracting(String tierCdForRecontracting) {
		this.tierCdForRecontracting = tierCdForRecontracting;
	}
	public String getHsTierCdForTTVRecontracting() {
		return hsTierCdForTTVRecontracting;
	}
	public void setHsTierCdForTTVRecontracting(String hsTierCdForTTVRecontracting) {
		this.hsTierCdForTTVRecontracting = hsTierCdForTTVRecontracting;
	}
	public GetOfferListAdapterResponse getAccessoryOfferListByOfferIdentifierListAdapterResponse() {
		return accessoryOfferListByOfferIdentifierListAdapterResponse;
	}
	public void setAccessoryOfferListByOfferIdentifierListAdapterResponse(
			GetOfferListAdapterResponse accessoryOfferListByOfferIdentifierListAdapterResponse) {
		this.accessoryOfferListByOfferIdentifierListAdapterResponse = accessoryOfferListByOfferIdentifierListAdapterResponse;
	}
	
    // 2018 June Exception release for TTV recontracting
	/**
	 * 
	 * @return
	 */
	public List<SubscribedProductInfoRestVO> getAssignedProductListByServiceAddressAndServiceId() {
		List<SubscribedProductInfoRestVO> resultAssignedProductList=new ArrayList<SubscribedProductInfoRestVO>();
		if (assignedAndPendingProductsResponseVO != null) {
			ServiceAddressVO requestServiceAddress = null;

			if ( (getOffersRequestVO().getSalesOfferCriteria() != null) && (getOffersRequestVO().getSalesOfferCriteria().getServiceAddress() != null) ) {
				requestServiceAddress = getOffersRequestVO().getSalesOfferCriteria().getServiceAddress();
			}
			else if ( (getOffersRequestVO().getSweetenerOfferFilterCriteria() != null) && (getOffersRequestVO().getSweetenerOfferFilterCriteria().getServiceAddress() != null) ) {
				requestServiceAddress = new ServiceAddressVO();
				requestServiceAddress.setServiceAddressId(getOffersRequestVO().getSweetenerOfferFilterCriteria().getServiceAddress().getServiceAddressId());
			}
			else if ( (getOffersRequestVO().getAccessoryOfferCriteria() != null) && (getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress() != null) ) {
				requestServiceAddress = new ServiceAddressVO();
				requestServiceAddress.setServiceAddressId(getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress().getServiceAddressId());
			}

			List<SubscribedServiceIdentifierVO> subscribedServiceList = null;

			if ( (getOffersRequestVO().getSalesOfferCriteria() != null) && (getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier() != null) &&
				 (!getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier().isEmpty()) ) {
				subscribedServiceList = getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier();
			}
			else if ( (getOffersRequestVO().getSweetenerOfferFilterCriteria() != null) && (getOffersRequestVO().getSweetenerOfferFilterCriteria().getSubscribedServiceIdentifierList() != null) &&
					 (!getOffersRequestVO().getSweetenerOfferFilterCriteria().getSubscribedServiceIdentifierList().isEmpty()) ) {
				subscribedServiceList = new ArrayList<SubscribedServiceIdentifierVO>();

				for (ServiceIdentifier serviceIdentifier : getOffersRequestVO().getSweetenerOfferFilterCriteria().getSubscribedServiceIdentifierList()) {
					SubscribedServiceIdentifierVO subscribedServiceIdentifierVO = new SubscribedServiceIdentifierVO();
					subscribedServiceIdentifierVO.setServiceId(serviceIdentifier.getServiceId());
					subscribedServiceIdentifierVO.setServiceReferenceId(serviceIdentifier.getServiceReferenceId());
					subscribedServiceList.add(subscribedServiceIdentifierVO);
				}
			}
			else if ( (getOffersRequestVO().getAccessoryOfferCriteria() != null) && (getOffersRequestVO().getAccessoryOfferCriteria().getSubscribedServiceIdentifierList() != null) &&
					 (!getOffersRequestVO().getAccessoryOfferCriteria().getSubscribedServiceIdentifierList().isEmpty()) ) {
				subscribedServiceList = new ArrayList<SubscribedServiceIdentifierVO>();

				for (ServiceIdentifier serviceIdentifier : getOffersRequestVO().getAccessoryOfferCriteria().getSubscribedServiceIdentifierList()) {
					SubscribedServiceIdentifierVO subscribedServiceIdentifierVO = new SubscribedServiceIdentifierVO();
					subscribedServiceIdentifierVO.setServiceId(serviceIdentifier.getServiceId());
					subscribedServiceIdentifierVO.setServiceReferenceId(serviceIdentifier.getServiceReferenceId());
					subscribedServiceList.add(subscribedServiceIdentifierVO);
				}
			}

			if (requestServiceAddress != null) {
				String reqServiceAddressId = requestServiceAddress.getServiceAddressId();

				List<SubscribedProductInfoRestVO> subscribedProductList = assignedAndPendingProductsResponseVO.getSubscribedProductList();

				if (subscribedProductList!=null && !subscribedProductList.isEmpty()) {
					for (SubscribedProductInfoRestVO subscribedProduct : subscribedProductList) {
						if (reqServiceAddressId.equalsIgnoreCase(subscribedProduct.getServiceAddress().getServiceAddressId()) &&
							assignedAndPendingProductsResponseVO.checkExistingServiceId(subscribedProduct.getProductInstance(), subscribedServiceList)) {
							resultAssignedProductList.add(subscribedProduct);
						}
					}
				}
			}
		}

		return resultAssignedProductList;
	}
	
    // 2018 June Exception release for TTV recontracting
	/**
	 * 
	 * @return
	 */
	public List<SubscribedProductInfoRestVO> getPendingProductListByServiceAddress() {
		List<SubscribedProductInfoRestVO> resultPendingProductList = new ArrayList<SubscribedProductInfoRestVO>();
		if (assignedAndPendingProductsResponseVO != null) {	

			ServiceAddressVO requestServiceAddress = null;

			if ( (getOffersRequestVO().getSalesOfferCriteria() != null) && (getOffersRequestVO().getSalesOfferCriteria().getServiceAddress() != null) ) {
				requestServiceAddress = getOffersRequestVO().getSalesOfferCriteria().getServiceAddress();
			}
			else if ( (getOffersRequestVO().getSweetenerOfferFilterCriteria() != null) && (getOffersRequestVO().getSweetenerOfferFilterCriteria().getServiceAddress() != null) ) {
				requestServiceAddress = new ServiceAddressVO();
				requestServiceAddress.setServiceAddressId(getOffersRequestVO().getSweetenerOfferFilterCriteria().getServiceAddress().getServiceAddressId());
			}
			else if ( (getOffersRequestVO().getAccessoryOfferCriteria() != null) && (getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress() != null) ) {
				requestServiceAddress = new ServiceAddressVO();
				requestServiceAddress.setServiceAddressId(getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress().getServiceAddressId());
			}
			if(requestServiceAddress!=null){
				String reqServiceAddressId = requestServiceAddress.getServiceAddressId();
				if(assignedAndPendingProductsResponseVO.getPendingProductList()!=null && !assignedAndPendingProductsResponseVO.getPendingProductList().isEmpty()){
					for(SubscribedProductInfoRestVO pendingProduct : assignedAndPendingProductsResponseVO.getPendingProductList()){
						if(!StringUtils.isEmpty(pendingProduct.getServiceAddress().getServiceAddressId())){ //Checking that the serviceAddressId returned from consolidated account is not null
							if(reqServiceAddressId.equalsIgnoreCase(pendingProduct.getServiceAddress().getServiceAddressId())){
								resultPendingProductList.add(pendingProduct);
							}
						}else{ //If serviceAddressId from consolidated account response is null, then evaluate Address from pending product with the address from AddressManagementSvc
							if(assignedAndPendingProductsResponseVO.compareAddresses(getServiceAddressResponseVO(), pendingProduct.getServiceAddress())) {
								resultPendingProductList.add(pendingProduct);
							}
						}
						
					}				
				}
			}
		}
		return resultPendingProductList;
			
	}
}
