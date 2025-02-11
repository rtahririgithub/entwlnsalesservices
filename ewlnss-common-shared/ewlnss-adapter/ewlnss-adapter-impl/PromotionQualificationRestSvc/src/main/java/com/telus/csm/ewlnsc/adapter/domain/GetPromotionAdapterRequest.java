package com.telus.csm.ewlnsc.adapter.domain;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.telus.csm.cpq.rest.domain.CommerceChannel;
import com.telus.csm.cpq.rest.domain.ChannelRef;
import com.telus.csm.cpq.rest.domain.Characteristic;
import com.telus.csm.cpq.rest.domain.ContractTerm;
import com.telus.csm.cpq.rest.domain.ImmediatePromotion;
import com.telus.csm.cpq.rest.domain.PlaceRef;
import com.telus.csm.cpq.rest.domain.Product;
import com.telus.csm.cpq.rest.domain.ProductCharacteristic;
import com.telus.csm.cpq.rest.domain.ProductOffering;
import com.telus.csm.cpq.rest.domain.ProductSpecificationRef;
import com.telus.csm.cpq.rest.domain.PromoCodeRef;
import com.telus.csm.cpq.rest.domain.PromotionQualification;
import com.telus.csm.cpq.rest.domain.PromotionQualificationItem;
import com.telus.csm.cpq.rest.domain.RelatedPartyRef;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;


public class GetPromotionAdapterRequest extends AdapterRequestBase {

	
	private static final long serialVersionUID = 1L;
	
	private boolean refreshCacheInd;
	private String originatorApplicationId;
	private boolean consolidatedCustomerProfileInd = true;
	StringBuilder sb = new StringBuilder();
	
	public boolean isRefreshCacheInd() {
		return refreshCacheInd;
	}
	public void setRefreshCacheInd(boolean refreshCacheInd) {
		this.refreshCacheInd = refreshCacheInd;
	}
	public String getOriginatorApplicationId() {
		return originatorApplicationId;
	}
	public void setOriginatorApplicationId(String originatorApplicationId) {
		this.originatorApplicationId = originatorApplicationId;
	}
	public boolean isConsolidatedCustomerProfileInd() {
		return consolidatedCustomerProfileInd;
	}
	public void setConsolidatedCustomerProfileInd(boolean consolidatedCustomerProfileInd) {
		this.consolidatedCustomerProfileInd = consolidatedCustomerProfileInd;
	}
	public boolean isAllowProductTierUpgradeOnlyInd() {
		return allowProductTierUpgradeOnlyInd;
	}
	public void setAllowProductTierUpgradeOnlyInd(boolean allowProductTierUpgradeOnlyInd) {
		this.allowProductTierUpgradeOnlyInd = allowProductTierUpgradeOnlyInd;
	}
	private boolean allowProductTierUpgradeOnlyInd;
	
	private PromotionQualification promotionQualification;
	public PromotionQualification getPromotionQualification() {
		return promotionQualification;
	}
	public void setPromotionQualification(PromotionQualification promotionQualification) {
		this.promotionQualification = promotionQualification;
	}

	public String getCacheKey() {
		
		if(promotionQualification == null) return "";
		
		List<RelatedPartyRef> RelatedPartyRefList =  promotionQualification.getRelatedParty();
		List<ChannelRef>  channelList = promotionQualification.getChannel();
		List<PlaceRef> placeList = promotionQualification.getPlace();
		List<PromotionQualificationItem> promotionQualificationItemList = promotionQualification.getPromotionQualificationItem();
		Comparator<RelatedPartyRef> relatedPartyRefCompareByRole = new Comparator<RelatedPartyRef>() {
		    @Override
		    public int compare(RelatedPartyRef o1, RelatedPartyRef o2) {
		        return o1.getRole().compareTo(o2.getRole());
		    }
		};
		Comparator<Characteristic> characteristicCompareByName = new Comparator<Characteristic>() {
		    @Override
		    public int compare(Characteristic o1, Characteristic o2) {
		        return o1.getName().compareTo(o2.getName());
		    }
		};
		
		Comparator<ProductCharacteristic> ProductCharacteristicCompareByName = new Comparator<ProductCharacteristic>() {
		    @Override
		    public int compare(ProductCharacteristic o1, ProductCharacteristic o2) {
		        return o1.getName().compareTo(o2.getName());
		    }
		};
		Comparator<ChannelRef> channelCompareById = new Comparator<ChannelRef>() {
		    @Override
		    public int compare(ChannelRef o1, ChannelRef o2) {
		        return ((CommerceChannel)o1).getRole().compareTo(((CommerceChannel)o2).getRole());
		    }
		};
		Comparator<PlaceRef> placeCompareByRoleIdName = new Comparator<PlaceRef>() {
		    @Override
		    public int compare(PlaceRef o1, PlaceRef o2) {
		        return (o1.getRole()+o1.getId()+o1.getName()) .compareTo(o2.getRole()+o2.getId()+o2.getName());
		    }
		};
		Comparator<PromotionQualificationItem> promotionQualificationItemCompareByUpsellInd = new Comparator<PromotionQualificationItem>() {
		    @Override
		    public int compare(PromotionQualificationItem o1, PromotionQualificationItem o2) {
		        return (o1.getUpsell()) .compareTo(o2.getUpsell());
		    }
		};
		
		Comparator<ProductSpecificationRef> ProductSpecificationRefCompareByIdName = new Comparator<ProductSpecificationRef>() {
		    @Override
		    public int compare(ProductSpecificationRef o1, ProductSpecificationRef o2) {
		        return (o1.getId()+o1.getName()) .compareTo(o2.getId()+o2.getName());
		    }
		};
		Comparator<ProductOffering> ProductOfferingCompareById = new Comparator<ProductOffering>() {
		    @Override
		    public int compare(ProductOffering o1, ProductOffering o2) {
		        return (o1.getId()) .compareTo(o2.getId());
		    }
		};
		
		
		addToCacheKey("refreshCacheInd", isRefreshCacheInd()+"");
		addToCacheKey("consolidatedCustomerProfileInd",isConsolidatedCustomerProfileInd()+"");
		addToCacheKey("allowProductTierUpgradeOnlyInd", isAllowProductTierUpgradeOnlyInd()+"");
		addToCacheKey("qualificationTypeCd",getPromotionQualification().getQualificationType());
				
		//RelatedPartyRefList
		if(RelatedPartyRefList != null) {
		Collections.sort(RelatedPartyRefList, relatedPartyRefCompareByRole);

			for(RelatedPartyRef relatedPartyRef:RelatedPartyRefList) {
				addToCacheKey("RelatedPartyRef.id",relatedPartyRef.getId());
				addToCacheKey("RelatedPartyRef.role",relatedPartyRef.getRole());
				addToCacheKey("RelatedPartyRef.atReferredType",relatedPartyRef.getAtReferredType());
				
				if(relatedPartyRef.getCharacteristic() != null) {
					Collections.sort(relatedPartyRef.getCharacteristic(), characteristicCompareByName);
					for(Characteristic characteristic:relatedPartyRef.getCharacteristic()) {
						addToCacheKey("RelatedPartyRef.Characteristic.name",characteristic.getName());
						addToCacheKey("RelatedPartyRef.Characteristic.value",characteristic.getValue());
					}
				}
				sb.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			}
		}
		//Channel
		sb.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		if(channelList != null) {
			Collections.sort(channelList, channelCompareById);
			for(ChannelRef channel:channelList) {
				CommerceChannel CommerceChannel = (CommerceChannel)channel;
				addToCacheKey("Channel.ReferredType",channel.getAtReferredType());
				addToCacheKey("Channel.id",channel.getId());
				addToCacheKey("Channel.role",CommerceChannel.getRole());
				addToCacheKey("Channel.ChannelOrganizationTypeCd",CommerceChannel.getChannelOrganizationTypeCd());
				addToCacheKey("Channel.ChannelOrganizationInternalId",CommerceChannel.getChannelOrganizationInternalId());
				addToCacheKey("Channel.ChannelOrganizationNumber",CommerceChannel.getChannelOrganizationNumber());
				addToCacheKey("Channel.SalesPersonRoleCd",CommerceChannel.getSalesPersonRoleCd());
				addToCacheKey("Channel.SalesRepId",CommerceChannel.getSalesRepId());
				addToCacheKey("Channel.SalesRepInternalId",CommerceChannel.getSalesRepInternalId());
				addToCacheKey("Channel.ChannelOutletProvinceCd",CommerceChannel.getChannelOutletProvinceCd());
				addToCacheKey("Channel.ChannelOutletInternalId",CommerceChannel.getChannelOutletInternalId());
				addToCacheKey("Channel.ChannelOutletId",CommerceChannel.getChannelOutletId());
				addToCacheKey("Channel.LoginId",CommerceChannel.getLoginId());
				
			}
			sb.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		}
		//comparators
		//place
		sb.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		if(placeList != null) {
			Collections.sort(placeList, placeCompareByRoleIdName);
			for(PlaceRef place: placeList) {
				addToCacheKey("Place.Role",place.getRole());
				addToCacheKey("Place.Id",place.getId());
				addToCacheKey("Place.Name",place.getName());
				if(place.getCharacteristics() != null) {
					Collections.sort(place.getCharacteristics(), characteristicCompareByName);
					for(Characteristic characteristic:place.getCharacteristics()) {
						addToCacheKey("Place.Characteristic.name",characteristic.getName());
						addToCacheKey("Place.Characteristic.value",characteristic.getValue());
					}
				}
			}
		}
		
		if(promotionQualificationItemList != null) {
			Collections.sort(promotionQualificationItemList, promotionQualificationItemCompareByUpsellInd);
			for(PromotionQualificationItem pqItem :promotionQualificationItemList) {
				sb.append(pqItem.getUpsell());
				Product product = pqItem.getProduct();
				ContractTerm contractTerm = pqItem.getContractTerm();
				PromoCodeRef promoCodeRef = pqItem.getPromoCode();
				List<ProductOffering> productOfferingList = pqItem.getProductOffering();
				ImmediatePromotion immediatePromotion =pqItem.getImmediatePromotion();
				//product
				addToCacheKey("product.id",product.getId());
				addToCacheKey("product.name",product.getName());
				List<ProductSpecificationRef> podSpecsList = product.getProductSpecification();
				if(podSpecsList != null) {
					Collections.sort(podSpecsList, ProductSpecificationRefCompareByIdName);
					for(ProductSpecificationRef prodSpec:podSpecsList) {
						addToCacheKey("product.ProductSpecificationRef.id",prodSpec.getId());
						addToCacheKey("product.ProductSpecificationRef.name",prodSpec.getName());
					}
				}
				if(product.getPlace() != null) {
					Collections.sort(product.getPlace(), placeCompareByRoleIdName);
					for(PlaceRef place: product.getPlace()) {
						addToCacheKey("Product.Place.Role",place.getRole());
						addToCacheKey("Product.Place.Id",place.getId());
						addToCacheKey("Product.Place.Name",place.getName());
						Collections.sort(place.getCharacteristics(), characteristicCompareByName);
						for(Characteristic characteristic:place.getCharacteristics()) {
							addToCacheKey("Product.Place.Characteristic.name",characteristic.getName());
							addToCacheKey("Product.Place.Characteristic.value",characteristic.getValue());
						}
					}
				}
				if( productOfferingList != null) {
					Collections.sort(productOfferingList, ProductOfferingCompareById);
					for(ProductOffering productOffering:productOfferingList) {
						addToCacheKey("ProductOffering.Id",productOffering.getId());
						addToCacheKey("ProductOffering.PerspectiveDate",productOffering.getPerspectiveDate());
						addToCacheKey("ProductOffering.SourceSystemId",productOffering.getSourceSystemId());
						if( productOffering.getCharacteristics() != null) {
							Collections.sort(productOffering.getCharacteristics(), ProductCharacteristicCompareByName);
							for(ProductCharacteristic characteristic:productOffering.getCharacteristics()) {
								addToCacheKey("Product.Place.Characteristic.name",characteristic.getName());
								addToCacheKey("Product.Place.Characteristic.value",characteristic.getValue());
							}
						}
						
					}
				}
				if(promoCodeRef != null ) {
					addToCacheKey("promoCodeRef.promoCode", promoCodeRef.getPromoCode());
				}
				if(contractTerm != null) {
					if(contractTerm.getDuration() != null) {
						addToCacheKey("Product.contractTerm",contractTerm.getDuration().getAmount() + contractTerm.getDuration().getUnits());
					}
				}
				
				
			}
		}
		return sb.toString();
	}
	
	private void addToCacheKey(String key, String value) {
		if(value != null) {
			sb.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER)
			.append(key)
			.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER)
			.append(value);
		}
	}
	
}