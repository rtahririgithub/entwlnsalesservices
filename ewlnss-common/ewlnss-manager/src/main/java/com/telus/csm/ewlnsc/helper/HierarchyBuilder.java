package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.domain.EssAppException;
import com.telus.csm.ewlnsc.domain.GetCatalogueHierarchyResponseVO;
import com.telus.csm.ewlnsc.domain.GetProductCatalogueHierarchyRequestVO;
import com.telus.csm.ewlnsc.domain.GetProductCatalogueHierarchyRequestVO.ComponentIdentifier;
import com.telus.csm.ewlnsc.domain.ProductCatalogueItemVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemHierarchyDO;
import com.telus.csm.ewlnsc.grid.domain.ProductCharacteristicDO;
import com.telus.csm.ewlnsc.grid.repository.CatalogueItemHierarchyRepository;
import com.telus.csm.ewlnsc.grid.repository.CatalogueItemRepository;
import com.telus.csm.ewlnsc.grid.repository.ProductCharacteristicRepository;
import com.telus.csm.ewlnsc.grid.repository.RepositoryFactory;
import com.telus.csm.ewlnsc.grid.repository.WirelineCacheControlRepository;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;

public class HierarchyBuilder {
	
	private static Logger LOG = Logger.getLogger(HierarchyBuilder.class);
	
	private Set<String> nodePathSet = new LinkedHashSet<String> ();
	private Set<String> catalogueIdSet = new LinkedHashSet<String>();
	private ProductCatalogueItemVO rootNode;
	private ProductCatalogueItemVO mainComponentNode;
	
	private CatalogueItemHierarchyRepository hierarchyRepo;
	private CatalogueItemRepository itemRepo;
	private ProductCharacteristicRepository characteristicRepo;
	
	
	public GetCatalogueHierarchyResponseVO getProductCatalogueHierarchy (GetProductCatalogueHierarchyRequestVO request) {
		   
		String suffix = WirelineCacheControlRepository.getInstance().getCacheSuffix();
		RepositoryFactory repoFactory = RepositoryFactory.getInstance();
		hierarchyRepo = repoFactory.getHierarchyRepository(suffix);
		itemRepo = repoFactory.getCatalogueItemRepository(suffix);
		characteristicRepo = repoFactory.getProductCharacteristicRepository(suffix);
		
		CatalogueItemDO rootCatItem = itemRepo.get(request.getTemplateId());
		if ( rootCatItem!=null ) {
			
			GetCatalogueHierarchyResponseVO resonseVO = new GetCatalogueHierarchyResponseVO();
			
			setRoot(rootCatItem);
			
			//find the main component node
			Set<Map.Entry<String, CatalogueItemHierarchyDO>> componentSet = hierarchyRepo.queryMainComponentNode( request.getTemplateId(), request.getMainComponentId() );
			
			if ( componentSet.size()!=1) {
				String msg = null; 
				if (componentSet.size()==0 ) {
					msg = "Unable to find main component node for templateId/mainComponentId = " + request.getTemplateId() + "/" + request.getMainComponentId();
				} else {
					ArrayList<String> parentList = new ArrayList<String>();
					for( Map.Entry<String, CatalogueItemHierarchyDO> entry : componentSet ) {
						parentList.add( entry.getValue().getNodePath() + ": " + entry.getValue().getParentProdInternalNm() );
					}
					msg = "Found multiple main component node for templateId/mainComponentId = " + request.getTemplateId() + "/" + request.getMainComponentId() + "; parent list: " + parentList;
				}
				
				LOG.error( msg );
				throw new EssAppException ("ERROR_FIND_MAIN_COMPONENT",  msg); //TODO error message
			}
			
			CatalogueItemHierarchyDO mainComponentHierarchy = componentSet.iterator().next().getValue();
			
			setMainComponent( mainComponentHierarchy );
			
			String componentPath = mainComponentHierarchy.getNodePath();
			
			for( ComponentIdentifier compId: request.getComponentIdentifiers() ) {
				
				if ( compId.getParentId().equals(mainComponentHierarchy.getChildProdCatItemId() ) ) {
					setMainComponentChild( compId.getCatalogueId() );
					
				} else {
					
					Set<Map.Entry<String, CatalogueItemHierarchyDO>> leafNodes = hierarchyRepo.queryHierarchyLeafNode( componentPath, compId.getParentId(), compId.getCatalogueId() );
					
					if ( leafNodes.size()==1) {
						
						//we found the unique leaf node
						CatalogueItemHierarchyDO leafHierarchy = leafNodes.iterator().next().getValue();
						setLeafNode ( leafHierarchy );
						
					} else {
						
						String msgTxt = null;
						if ( leafNodes.size()==0 ) {
							msgTxt = "Unable find catalogue item in hierarchy for componentPath=" + componentPath + ", parent/child=" + compId.getParentId() +"/" + compId.getCatalogueId() ;
						} else {
							msgTxt = "Found multiple catalogue items in hierarchy for componentPath=" + componentPath + ", parent/child=" + compId.getParentId() +"/" + compId.getCatalogueId() +",  query result size=" + leafNodes.size();
						}
						
						LOG.warn(msgTxt );
						
						SalesResponseMessage.MessageList resMsg = new SalesResponseMessage.MessageList();
						resMsg.setContextData( msgTxt );
						resMsg.setMessageType( EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING );
						resMsg.setErrorCode( EnterpriseWLNSalesServicesErrorCodes.GWPCL_UNIDENTIFIABLE_HIERARCHY_NODE);
						resonseVO.addMsg(resMsg);
					}
				}

			}
			
			resonseVO.setCatalogueItem( buildHierarchy() );
			return resonseVO;
		}
		
		throw new EssAppException ("ERROR_FIND_MAIN_ROOT_CATALOGUE_ITEM", "Unable find root catalogue item."); //TODO error message			
	}

	public CatalogueItemHierarchyDO getProductCatalogueHierarchy (String externalRefrenceId) {
		String suffix = WirelineCacheControlRepository.getInstance().getCacheSuffix();
		RepositoryFactory repoFactory = RepositoryFactory.getInstance();
		hierarchyRepo = repoFactory.getHierarchyRepository(suffix);
		return hierarchyRepo.get(externalRefrenceId);
	}	
	
	private void setRoot( CatalogueItemDO rootCatItem ) {
		rootNode = new ProductCatalogueItemVO();
		map( rootCatItem, rootNode );
		rootNode.setNodePath("/"+ rootNode.getCatalogueItemId());
	}
	
	private void setMainComponent(  CatalogueItemHierarchyDO mainComponentHierarchy ) {
		
		ProductCatalogueItemVO productNode = addChild( rootNode, mainComponentHierarchy.getParentProdCatItemId() );
		mainComponentNode  = addChild ( productNode,  mainComponentHierarchy.getChildProdCatItemId());
	}
	
	private void setMainComponentChild(String catalogueId) {
		addChild (mainComponentNode, catalogueId);
	}

	private void setLeafNode(CatalogueItemHierarchyDO leafHierarchy) {
		
		String mainComponentId = mainComponentNode.getCatalogueItemId();
		ProductCatalogueItemVO currentNode = mainComponentNode;
		
		String[] path = leafHierarchy.getNodePath().split("/");
		boolean foundMainCompoment = false;
		for ( String catalogueItemId : path ) {
			
			if ( foundMainCompoment ) {
				currentNode = addChild( currentNode, catalogueItemId );
			} else {
				if (catalogueItemId.equals(mainComponentId) ) {
					foundMainCompoment=true;
				}
			}
		}
	}	
	
	private ProductCatalogueItemVO buildHierarchy() {
		
		if (LOG.isDebugEnabled() ) LOG.debug( "retrieve hierarchyNode,  " + nodePathSet );
		Map<String,CatalogueItemHierarchyDO> hierarchyMap = hierarchyRepo.getAllEntries(nodePathSet );

		if (LOG.isDebugEnabled() ) LOG.debug( "retrieve catalogueItem,  " + catalogueIdSet  );
		Map<String,CatalogueItemDO> itemMap = itemRepo.getAllEntries( catalogueIdSet );
		
		if (LOG.isDebugEnabled() ) LOG.debug( "retrieve productCharacteristic,  " + catalogueIdSet  );
		Map<String, List<ProductCharacteristicDO> > characteristicMap = characteristicRepo.getCatalogueItemCharacteristic( catalogueIdSet );
		
		mapChildren (rootNode, hierarchyMap,  itemMap, characteristicMap);
		
		return rootNode;
	}

	private void mapChildren(ProductCatalogueItemVO parentNode, Map<String, CatalogueItemHierarchyDO> hierarchyMap, Map<String,CatalogueItemDO> itemMap, Map<String, List<ProductCharacteristicDO>> characteristicMap)  {

		parentNode.setCharacteristics( characteristicMap.get(parentNode.getCatalogueItemId() ));
		
		for( ProductCatalogueItemVO child: parentNode.getComponents() ) {
			
			CatalogueItemHierarchyDO hierarchyDO = hierarchyMap.get( child.getNodePath() );
			if ( hierarchyDO !=null) {
				map ( hierarchyDO, child );
			} else {
				LOG.warn( "missing hierarchy for: " + child.getNodePath() );
			}
			
			CatalogueItemDO itemDO = itemMap.get(child.getCatalogueItemId());
			if (itemDO!=null) {
				map( itemDO, child );
			} else {
				LOG.warn( "missing catalogueItem for: " + child.getCatalogueItemId() );
			}
			
			//recursively map children
			mapChildren( child, hierarchyMap,  itemMap, characteristicMap);
		}
	}
	
	private ProductCatalogueItemVO addChild(ProductCatalogueItemVO node, String catalogueId) {
		ProductCatalogueItemVO child = node.addChild( catalogueId ) ;
		registerPendingIds ( child );
		return child;
	}
	
	private void registerPendingIds(ProductCatalogueItemVO node ) {
		nodePathSet.add( node.getNodePath() );
		catalogueIdSet.add( node.getCatalogueItemId() );
	}
	
	private void map( CatalogueItemDO source , ProductCatalogueItemVO target ) {
		target.setCatalogueItemId( source.getCatalogueItemId() );
		target.setCatalogueItemType( source.getItemType() );
		target.setProductCode( source.getProductCode() );
		target.setComponentServiceType( source.getComponentServiceType() );
		target.setEffEndDt( source.getEffEndDt() );
		target.setEffStartDt( source.getEffStartDt() );
		target.setDescription( source.getDescription() );
		target.setName( source.getName() );
	}
	
	private void map( CatalogueItemHierarchyDO source , ProductCatalogueItemVO target ) {
		target.setMaximumQuatity( source.getRelnMaxAllowedQty() );
		target.setMinimumQuatity( source.getRelnMinAllowedQty() );
		
		target.setCatalogueItemType( source.getChildProdCatItemTypeCd() );
		target.setProductCode( source.getChildProdCd() );
		target.setComponentServiceType( source.getChildCompSvcTypeCd() );
		target.setName( source.getChildProdInternalNm() );
		
	}	
}

