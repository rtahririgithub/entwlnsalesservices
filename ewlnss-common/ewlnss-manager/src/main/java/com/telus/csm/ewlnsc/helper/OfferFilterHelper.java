package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.rules.base.Specification;
import com.telus.csm.ewlnsc.rules.business.AddOnRule;
import com.telus.csm.ewlnsc.rules.business.CallingFeatureRule;
import com.telus.csm.ewlnsc.rules.business.ContractTermRule;
import com.telus.csm.ewlnsc.rules.business.FilterAccessoryOfferByIncompatiblePricePlansRule;
import com.telus.csm.ewlnsc.rules.business.FilterAccessoryOfferByTemplateProduct;
import com.telus.csm.ewlnsc.rules.business.FilterHsZeroProductRule;
import com.telus.csm.ewlnsc.rules.business.FilterOfferByCustomerStatus;
import com.telus.csm.ewlnsc.rules.business.FilterOfferByEquipmentRule;
import com.telus.csm.ewlnsc.rules.business.FilterOfferByOfferTemplateId;
import com.telus.csm.ewlnsc.rules.business.FilterOfferByPendingProductRule;
import com.telus.csm.ewlnsc.rules.business.FilterOfferByProductEligibilityProductComponentRule;
import com.telus.csm.ewlnsc.rules.business.FilterOfferByProductEligibilityRule;
import com.telus.csm.ewlnsc.rules.business.FilterOfferByRequestProductListRule;
import com.telus.csm.ewlnsc.rules.business.FilterOfferBySubscribedProductRule;
import com.telus.csm.ewlnsc.rules.business.FilterOfferBySubscribedProductsAndCIdRule;
import com.telus.csm.ewlnsc.rules.business.FilterOfferEquipmentByCreditEligibilityRule;
import com.telus.csm.ewlnsc.rules.business.FilterOfferForPikTVCustomerRule;
import com.telus.csm.ewlnsc.rules.business.FilterProductDiscountByTermRule;
import com.telus.csm.ewlnsc.rules.business.FilterProductDiscountByWinbackIndRule;
import com.telus.csm.ewlnsc.rules.business.FilterSweetenerOfferByAccessoryOfferListIncompatiblePricePlansRule;
import com.telus.csm.ewlnsc.rules.business.HsicProductPlanNotQualifiedRule;
import com.telus.csm.ewlnsc.rules.business.ProductByCodeQualificationRule;
import com.telus.csm.ewlnsc.rules.business.RequestTTVProductListRule;
import com.telus.csm.ewlnsc.rules.business.SubscribedHsicPPRankingWorseThenOfferRule;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.AbstractTask;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductEligiblity;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Sweetener;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

import commonj.work.Work;

public class OfferFilterHelper {

	private SalesOfferCommonVO salesOfferCommonVO;
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(OfferFilterHelper.class);
	
	public OfferFilterHelper(SalesOfferCommonVO salesOfferCommonVO){
		this.salesOfferCommonVO = salesOfferCommonVO;
	}
	
	public List<Offer> filterOfferSummary(List<Offer> offerList, List<String> requestedProductList, List<TraceVO> traces) {

		Specification<Offer,TraceVO> filterOfferSummarySpec = new AbstractSpecification<Offer,TraceVO>();
		Specification<Offer,TraceVO> splitOffersSummarySpec = new AbstractSpecification<Offer,TraceVO>();


		filterOfferSummarySpec = filterOfferSummarySpec.and(new ContractTermRule(salesOfferCommonVO));
		filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterOfferByRequestProductListRule(salesOfferCommonVO));
		
		// 2018 June Exception release for TTV recontracting
		// adding new filter rule to filter out TTV products other than the one already assigned/pending
		filterOfferSummarySpec = filterOfferSummarySpec.and(new RequestTTVProductListRule(salesOfferCommonVO));
		
		filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterOfferForPikTVCustomerRule(salesOfferCommonVO));
		
		// 2018 Aug release for Pik TV
		// no longer need the rules to rule out all Pik TV offer
		// filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterOfferWithPikTVRule(salesOfferCommonVO));
		
		filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterOfferByPendingProductRule(salesOfferCommonVO));
		
		// BundleBuilder Redesign - Gary - 2019-07-11 - remove FeasibilityRule, add rule FilterHsZeroProductRule
		//filterOfferSummarySpec = filterOfferSummarySpec.and(new FeasibilityRule(salesOfferCommonVO));
		filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterHsZeroProductRule(salesOfferCommonVO));
		
		
		filterOfferSummarySpec = filterOfferSummarySpec.and(new ProductByCodeQualificationRule(salesOfferCommonVO));
		filterOfferSummarySpec = filterOfferSummarySpec.and(new HsicProductPlanNotQualifiedRule(salesOfferCommonVO));
		filterOfferSummarySpec = filterOfferSummarySpec.and(new CallingFeatureRule(salesOfferCommonVO));
		
		// BundleBuilder Redesign - Gary - 2019-07-11  - remove FilterOfferEquipmentByFeasibilityRule
		//filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterOfferEquipmentByFeasibilityRule(salesOfferCommonVO));
		filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterOfferEquipmentByCreditEligibilityRule(salesOfferCommonVO));
		filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterOfferByEquipmentRule(salesOfferCommonVO));
		filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterOfferByCustomerStatus(salesOfferCommonVO));
		filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterOfferBySubscribedProductsAndCIdRule(salesOfferCommonVO));
		filterOfferSummarySpec = filterOfferSummarySpec.and(new SubscribedHsicPPRankingWorseThenOfferRule(salesOfferCommonVO));
		
		Map<String,List<ProductEligiblity>> eligibilityProductsMap = new HashMap<String,List<ProductEligiblity>>();
		Map<String,List<WirelineOfferProduct>> offerProductList = new HashMap<String,List<WirelineOfferProduct>>();
		
		List<Offer> filteredOfferList = filterOffers(offerList, filterOfferSummarySpec, traces);
		
		for (Offer offer : filteredOfferList) {
			if(offer.getProductEligiblityList()!=null && !offer.getProductEligiblityList().isEmpty()) {
				eligibilityProductsMap.put(String.valueOf(offer.getOfferId()), offer.getProductEligiblityList());
			}
			offerProductList.put(String.valueOf(offer.getOfferId()), offer.getOfferProduct().getWirelineOfferProductList());
		}
		
		logger.debug("filterOfferSummary", "List of remaining offers prior Split offer filtering: " +offerProductList.keySet().toString());
		
		splitOffersSummarySpec = splitOffersSummarySpec.and(new FilterOfferByProductEligibilityRule(salesOfferCommonVO,eligibilityProductsMap,offerProductList, requestedProductList));
		
		// NWLN-11627 add new rule to filter out Accessory using productTemplateProductTypeCode
		splitOffersSummarySpec = splitOffersSummarySpec.and(new FilterAccessoryOfferByTemplateProduct(salesOfferCommonVO,eligibilityProductsMap,offerProductList, requestedProductList));
		
		// NWLN-7598, NWLN-7605 - add new rule to filter out Regular HS offer when new customer or existing customer without TTV adding new Mediaroom TTV / Pik TV
		splitOffersSummarySpec = splitOffersSummarySpec.and(new FilterOfferByOfferTemplateId(salesOfferCommonVO,offerProductList));
		
		// NWLN-7743 - Filter out the market offer if the required HS component from the offer's productEligibiltyList is not the matching HS from the offer request or from the existing product list
		splitOffersSummarySpec = splitOffersSummarySpec.and(new FilterOfferByProductEligibilityProductComponentRule(salesOfferCommonVO,offerProductList));
		
		//from this point we should filter out from the remaining offers (filteredOfferList) the offers that don't comply the split offers criteria
		List<Offer> resultingOfferList = filterOffers(filteredOfferList, splitOffersSummarySpec, traces);
		
		return resultingOfferList;
	}

	public List<Offer> filterOfferDetail(List<Offer> offerList, List<TraceVO> traces) {

		Specification<Offer,TraceVO> filterOfferDetailSpec = new AbstractSpecification<Offer,TraceVO>();
		
		//rules shared with getAvailableOfferSummary

		filterOfferDetailSpec = filterOfferDetailSpec.and(new ContractTermRule(salesOfferCommonVO));
		filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterOfferByRequestProductListRule(salesOfferCommonVO));
		
		// 2018 June Exception release for TTV recontracting
		// adding new filter rule to filter out TTV products other than the one already assigned/pending
		filterOfferDetailSpec = filterOfferDetailSpec.and(new RequestTTVProductListRule(salesOfferCommonVO));
		
		filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterOfferForPikTVCustomerRule(salesOfferCommonVO));
		
		// 2018 Aug release for Pik TV
		// no longer need the rules to rule out all Pik TV offer
		// filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterOfferWithPikTVRule(salesOfferCommonVO));
		
		filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterOfferByPendingProductRule(salesOfferCommonVO));
		
		// BundleBuilder Redesign - Gary - 2019-07-11 - remove FeasibilityRule, add rule FilterHsZeroProductRule
		//filterOfferDetailSpec = filterOfferDetailSpec.and(new FeasibilityRule(salesOfferCommonVO));
		filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterHsZeroProductRule(salesOfferCommonVO));
		
		
		filterOfferDetailSpec = filterOfferDetailSpec.and(new ProductByCodeQualificationRule(salesOfferCommonVO));
		filterOfferDetailSpec = filterOfferDetailSpec.and(new HsicProductPlanNotQualifiedRule(salesOfferCommonVO));
		filterOfferDetailSpec = filterOfferDetailSpec.and(new CallingFeatureRule(salesOfferCommonVO));
		
		// BundleBuilder Redesign - Gary - 2019-07-11 - remove FilterOfferEquipmentByFeasibilityRule
		//filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterOfferEquipmentByFeasibilityRule(salesOfferCommonVO));
		filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterOfferEquipmentByCreditEligibilityRule(salesOfferCommonVO));
		filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterOfferByEquipmentRule(salesOfferCommonVO));
		filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterOfferByCustomerStatus(salesOfferCommonVO));
		filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterOfferBySubscribedProductsAndCIdRule(salesOfferCommonVO));	
		filterOfferDetailSpec = filterOfferDetailSpec.and(new SubscribedHsicPPRankingWorseThenOfferRule(salesOfferCommonVO));
		//rules specific to getSalesOfferDetail
		filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterOfferBySubscribedProductRule(salesOfferCommonVO));
		filterOfferDetailSpec = filterOfferDetailSpec.and(new AddOnRule(salesOfferCommonVO));
		filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterProductDiscountByTermRule(salesOfferCommonVO));
//		filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterProductDiscountByCreditEligibilityRule(salesOfferCommonVO)); //offer limited to good credit result should not be returned to bad credit check
		filterOfferDetailSpec = filterOfferDetailSpec.and(new FilterProductDiscountByWinbackIndRule(salesOfferCommonVO));
		
		List<Offer> filteredOfferList = filterOffers(offerList, filterOfferDetailSpec, traces);
		
		return filteredOfferList;
	}

	public List<Offer> filterAccessoryOfferSummary(List<Offer> inputOfferList, List<TraceVO> traces) {

		Specification<Offer,TraceVO> filterOfferSummarySpec = new AbstractSpecification<Offer,TraceVO>();

		filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterAccessoryOfferByIncompatiblePricePlansRule(salesOfferCommonVO));
		
		List<Offer> filteredOfferList = filterOffers(inputOfferList, filterOfferSummarySpec, traces);
		
		return filteredOfferList;
	}
	
	public List<Sweetener> filterSweetenerOfferByAccessoryOfferList(List<Sweetener> inputSweetenerOfferList, List<TraceVO> traces) {

		Specification<Offer,TraceVO> filterOfferSummarySpec = new AbstractSpecification<Offer,TraceVO>();

		filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterSweetenerOfferByAccessoryOfferListIncompatiblePricePlansRule(salesOfferCommonVO));
		filterOfferSummarySpec = filterOfferSummarySpec.and(new FilterProductDiscountByWinbackIndRule(salesOfferCommonVO));		
		
		List<Sweetener> filteredSweetenerOfferList = filterOffers(inputSweetenerOfferList, filterOfferSummarySpec, traces);

		return filteredSweetenerOfferList;
	}
	
	private <T extends Offer> List<T> filterOffers(List<T> inputOffers, final Specification<Offer,TraceVO> spec, final List<TraceVO> traces) {

		String functionName = "filterOffers";
		
		final List<T> result = Collections.synchronizedList(new ArrayList<T>());
		
		if (inputOffers == null || inputOffers.isEmpty()) {
			logger.debug(functionName, "inputOffers=empty");
			return result;
		}
		
		ArrayList<Work> taskList = new ArrayList<Work>();
		for (final T offer : inputOffers) {
			
			AbstractTask task = new AbstractTask() {
				@Override
				public void run() {
					if (spec.isSatisfiedBy(offer, traces)) {
						result.add(offer);
					}
				}
			};
			taskList.add(task);
		}
		
		try {
			ICommonJWorkManager workManager = WorkManagerFactory.getCommonJWorkManager();
			workManager.processTasks(taskList);
		} catch (Exception e) {
			logger.error("error", functionName, "Exception", e);
			throw new RuntimeException(e);
		}

		logger.debug(functionName, "inputOffers=" + inputOffers.size() + ", outputOffers=" + result.size());
		
		return result;
	}

}
