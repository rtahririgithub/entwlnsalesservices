package com.telus.csm.ewlnsc.helper;

import java.util.HashMap;

import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.grid.domain.EquipmentCatalogueItemDO;

public class CommonWLNGridHelperV2 {
	
	HashMap<String, CatalogueItemDO> FIFA_OFFERING_CATEGORIES = new HashMap<String, CatalogueItemDO>();
	
	public static CommonWLNGridHelperV2 getInstance() {
		return new CommonWLNGridHelperV2();
	}
	
	public CommonWLNGridHelperV2() {
		initMappings();
	}

	private CatalogueItemDO newItem ( String name, String serviceType ) {
		CatalogueItemDO catItemDO = new CatalogueItemDO();
		catItemDO.setName(name);
		catItemDO.setDescription("");
		catItemDO.setComponentServiceType("");
		catItemDO.setProductCode("");
		catItemDO.setComponentServiceType( serviceType );
		return catItemDO;
	}

	private void initMappings () {
		
		FIFA_OFFERING_CATEGORIES.put("1", newItem(  "OTHER", null) );
		FIFA_OFFERING_CATEGORIES.put("9144579890813692873", newItem(  "", "TVEQ") );

		//below mapping are based on RefPDS table [NGC_FIFA_CATGY_ID_MAP]
		FIFA_OFFERING_CATEGORIES.put("9149604200013116042", newItem(  "Commitment Promotions", null) );
		FIFA_OFFERING_CATEGORIES.put("9999999999999999999", newItem(  "Mobility postpaid", null) );
		
		//SING
		FIFA_OFFERING_CATEGORIES.put("9150253846313241927", newItem(  "Home Phone", null) ); //summary
		FIFA_OFFERING_CATEGORIES.put("9136923654113578813", newItem(  "Home Phone Calling Features", null) );
		FIFA_OFFERING_CATEGORIES.put("9154316370813715720", newItem(  "Call Control", null) );
		FIFA_OFFERING_CATEGORIES.put("9136923654113578815", newItem(  "Services", null) );
		FIFA_OFFERING_CATEGORIES.put("9144277361313204496", newItem(  "Special Selection Feature", null) );
		FIFA_OFFERING_CATEGORIES.put("9147592534213951743", newItem(  "Add-On Features", null) );
		FIFA_OFFERING_CATEGORIES.put("9155372810813112508", newItem(  "Unlimited US and Canada LD", null) );
		FIFA_OFFERING_CATEGORIES.put("9136923654113578814", newItem(  "Home Phone Long Distance Plans", null) );
		
		//TTV
		FIFA_OFFERING_CATEGORIES.put("9146775320213795833", newItem(  "Pik TV", null) );
		FIFA_OFFERING_CATEGORIES.put("9150253640113241856", newItem(  "Optik TV", null) );
		FIFA_OFFERING_CATEGORIES.put("9152370800613416356", newItem(  "Pik TV Equipment (Media Box)", null) );
		FIFA_OFFERING_CATEGORIES.put("9144579698313692797", newItem(  "Optik TV Equipment (Set Top Box)", null) );
		
		FIFA_OFFERING_CATEGORIES.put("9153364436313016766", newItem(  "Premium", null) );
		FIFA_OFFERING_CATEGORIES.put("9136080250413413278", newItem(  "Theme Packs", null) );
		FIFA_OFFERING_CATEGORIES.put("9139557680013312078", newItem(  "TV Add Ons (Channels)", null) );
		FIFA_OFFERING_CATEGORIES.put("9142401888413295007", newItem(  "Essentials", null) );
		FIFA_OFFERING_CATEGORIES.put("9146928965313767546", newItem(  "Basics", null) );
		FIFA_OFFERING_CATEGORIES.put("9136201148713458176", newItem(  "Premium Packs", null) );
		
		//HSIA
		FIFA_OFFERING_CATEGORIES.put("9137773148713852470", newItem(  "Internet", null) ); //summary
		FIFA_OFFERING_CATEGORIES.put("9144605016013767974", newItem(  "Internet Add Ons", null) );
		FIFA_OFFERING_CATEGORIES.put("9148302515313935481", newItem(  "Internet Equipment (Modem)", null) );
	}

	
	public EquipmentCatalogueItemDO getEquipmentByProductCode(String productCode) {
		
		return null;
	}

	public CatalogueItemDO getCatalogueItemById(String catalogueItemId) {
		
		return FIFA_OFFERING_CATEGORIES.get(catalogueItemId);
		/*
		if("1".equalsIgnoreCase(catalogueItemId)) {
			CatalogueItemDO catItemDO = new CatalogueItemDO();
			catItemDO.setName("OTHER");
			catItemDO.setDescription("");
			catItemDO.setComponentServiceType("");
			catItemDO.setProductCode("");
			return catItemDO;
		}
		
		if("9144579890813692873".equalsIgnoreCase(catalogueItemId)) {
			CatalogueItemDO catItemDO = new CatalogueItemDO();
			catItemDO.setName("");
			catItemDO.setDescription("");
			catItemDO.setComponentServiceType("");
			catItemDO.setProductCode("");
			catItemDO.setComponentServiceType("TVEQ");
			return catItemDO;
		}
		
		return null;
		*/
	}

}
