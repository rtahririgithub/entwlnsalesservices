package com.telus.csm.ewlnsc.domain.schemasclone;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.scis.domain.AddressRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;



public class GetAssignedAndPendingProductResponseVO extends CoreResponseBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SubscribedProductInfoRestVO> subscribedProductList;
	private List<SubscribedProductInfoRestVO> pendingProductList;

	public List<SubscribedProductInfoRestVO> getPendingProductList() {
		return pendingProductList;
	}

	public void setPendingProductList(List<SubscribedProductInfoRestVO> pendingProductList) {
		this.pendingProductList = pendingProductList;
	}

	public List<SubscribedProductInfoRestVO> getSubscribedProductList() {
		return subscribedProductList;
	}

	public void setSubscribedProductList(List<SubscribedProductInfoRestVO> subscribedProductList) {
		this.subscribedProductList = subscribedProductList;
	}
	
	public List<SubscribedProductInfoRestVO> getPendingProductListByServiceAddress(
			ServiceAddressVO requestServiceAddress,List<SubscribedServiceIdentifierVO> subscribedServiceList,ServiceAddressResponseVO serviceAddress) {
		List<SubscribedProductInfoRestVO> resultPendingProductList = new ArrayList<SubscribedProductInfoRestVO>();
		if(requestServiceAddress!=null){
			String reqServiceAddressId = requestServiceAddress.getServiceAddressId();
			if(pendingProductList!=null && !pendingProductList.isEmpty()){
				for(SubscribedProductInfoRestVO pendingProduct : pendingProductList){
					if(!StringUtils.isEmpty(pendingProduct.getServiceAddress().getServiceAddressId())){ //Checking that the serviceAddressId returned from consolidated account is not null
						if(reqServiceAddressId.equalsIgnoreCase(pendingProduct.getServiceAddress().getServiceAddressId())){
							resultPendingProductList.add(pendingProduct);
						}
					}else{ //If serviceAddressId from consolidated account response is null, then evaluate Address from pending product with the address from AddressManagementSvc
						if(compareAddresses(serviceAddress, pendingProduct.getServiceAddress())){
							resultPendingProductList.add(pendingProduct);
						}
					}
					
				}				
			}
		}
		return resultPendingProductList;
	}

	public List<SubscribedProductInfoRestVO> getPendingProductListByServiceAddress(
			ServiceAddressBase requestServiceAddress,List<ServiceIdentifier> subscribedServiceList,ServiceAddressResponseVO serviceAddress) {
		List<SubscribedProductInfoRestVO> resultPendingProductList = new ArrayList<SubscribedProductInfoRestVO>();
		if(requestServiceAddress!=null){
			String reqServiceAddressId = requestServiceAddress.getServiceAddressId();
			if(pendingProductList!=null && !pendingProductList.isEmpty()){
				for(SubscribedProductInfoRestVO pendingProduct : pendingProductList){
					if(!StringUtils.isEmpty(pendingProduct.getServiceAddress().getServiceAddressId())){ //Checking that the serviceAddressId returned from consolidated account is not null
						if(reqServiceAddressId.equalsIgnoreCase(pendingProduct.getServiceAddress().getServiceAddressId())){
							resultPendingProductList.add(pendingProduct);
						}
					}else{ //If serviceAddressId from consolidated account response is null, then evaluate Address from pending product with the address from AddressManagementSvc
						if(compareAddresses(serviceAddress, pendingProduct.getServiceAddress())){
							resultPendingProductList.add(pendingProduct);
						}
					}
					
				}				
			}
		}
		return resultPendingProductList;
	}
	
	public boolean compareAddresses(ServiceAddressResponseVO serviceAddress, AddressRestVO productAddress){
		boolean result=false;
		if (serviceAddress != null && serviceAddress.getServiceAddress() != null && productAddress != null
				&& serviceAddress.getServiceAddress().getStreetNumber().equals(productAddress.getStreetNum())
				&& serviceAddress.getServiceAddress().getStreetName().equalsIgnoreCase(productAddress.getStreetName())
				&& serviceAddress.getServiceAddress().getMunicipalityName().equalsIgnoreCase(productAddress.getCity())
				&& serviceAddress.getServiceAddress().getProvinceStateCode().equalsIgnoreCase(productAddress.getProvinceCd())
				/*&& serviceAddress.getServiceAddress().getPostalZipCode().equalsIgnoreCase(productAddress.getPostalCd())*/) {
			//If all the conditions above have been fulfilled, then consider the product as pending
			result = true;
		}
		return result;
	}
	
	public List<SubscribedProductInfoRestVO> getAssignedProductListByServiceAddressAndServiceId(
			ServiceAddressVO requestServiceAddress,List<SubscribedServiceIdentifierVO> subscribedServiceList) {
		List<SubscribedProductInfoRestVO> resultAssignedProductList=new ArrayList<SubscribedProductInfoRestVO>();
		if(requestServiceAddress!=null){
			String reqServiceAddressId = requestServiceAddress.getServiceAddressId();
			if(subscribedProductList!=null && !subscribedProductList.isEmpty()){
				for(SubscribedProductInfoRestVO subscribedProduct : subscribedProductList){
					if(reqServiceAddressId.equalsIgnoreCase(subscribedProduct.getServiceAddress().getServiceAddressId()) && checkExistingServiceId(subscribedProduct.getProductInstance(), subscribedServiceList)){
						resultAssignedProductList.add(subscribedProduct);
					}
				}
			}
		}
		return resultAssignedProductList;
	}

	public List<SubscribedProductInfoRestVO> getAssignedProductListByServiceAddressAndServiceId(
			ServiceAddressBase requestServiceAddress, List<ServiceIdentifier> subscribedServiceList) {
		List<SubscribedProductInfoRestVO> resultAssignedProductList=new ArrayList<SubscribedProductInfoRestVO>();
		if(requestServiceAddress!=null){
			String reqServiceAddressId = requestServiceAddress.getServiceAddressId();
			if(subscribedProductList!=null && !subscribedProductList.isEmpty()){
				for(SubscribedProductInfoRestVO subscribedProduct : subscribedProductList){
					if(reqServiceAddressId.equalsIgnoreCase(subscribedProduct.getServiceAddress().getServiceAddressId()) && checkExistingSubscribedServiceId(subscribedProduct.getProductInstance(), subscribedServiceList)){
						resultAssignedProductList.add(subscribedProduct);
					}
				}
			}
		}
		return resultAssignedProductList;
	}

	public boolean checkExistingServiceId(List<ProductInstanceInfoRestVO> subscribedProductInstanceList, List<SubscribedServiceIdentifierVO> requestSubscribedServiceIdList){
		boolean result=false;
		if(subscribedProductInstanceList!=null && !subscribedProductInstanceList.isEmpty() && requestSubscribedServiceIdList!=null && !requestSubscribedServiceIdList.isEmpty()){
			for(ProductInstanceInfoRestVO subscribedProduct : subscribedProductInstanceList){
				String subscribedServiceId = subscribedProduct.getServiceId();
				for(SubscribedServiceIdentifierVO requestProduct : requestSubscribedServiceIdList){
					if(subscribedServiceId.equalsIgnoreCase(requestProduct.getServiceId())){
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}

	public boolean checkExistingSubscribedServiceId(List<ProductInstanceInfoRestVO> subscribedProductInstanceList, List<ServiceIdentifier> requestSubscribedServiceIdList){
		boolean result=false;
		if(subscribedProductInstanceList!=null && !subscribedProductInstanceList.isEmpty() && requestSubscribedServiceIdList!=null && !requestSubscribedServiceIdList.isEmpty()){
			for(ProductInstanceInfoRestVO subscribedProduct : subscribedProductInstanceList){
				String subscribedServiceId = subscribedProduct.getServiceId();
				for(ServiceIdentifier requestProduct : requestSubscribedServiceIdList){
					if(subscribedServiceId.equalsIgnoreCase(requestProduct.getServiceId())){
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}
}
