package com.telus.csm.ewlnsc.domain;

import java.util.ArrayList;
import java.util.List;

public class GetProductCatalogueHierarchyRequestVO extends CoreRequestBase {

	private static final long serialVersionUID = 1L;

	private String templateId;
	private String mainComponentId;
	private List<ComponentIdentifier> componentIdentifiers;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getMainComponentId() {
		return mainComponentId;
	}

	public void setMainComponentId(String mainComponentId) {
		this.mainComponentId = mainComponentId;
	}

	public List<ComponentIdentifier> getComponentIdentifiers() {
		return componentIdentifiers;
	}

	public void addComponentIdentifier( String parentId, String catalogueId ) {
		if (componentIdentifiers==null) {
			componentIdentifiers = new ArrayList<ComponentIdentifier>();
		}
		componentIdentifiers.add( new ComponentIdentifier(parentId, catalogueId) );
	}
	public static class ComponentIdentifier {

		private String parentId;
		private String catalogueId;
		
		private ComponentIdentifier(  String parentId, String catalogueId ) {
			this.parentId = parentId;
			this.catalogueId = catalogueId;
		}
		public String getCatalogueId() {
			return catalogueId;
		}
		public String getParentId() {
			return parentId;
		}
	
	}
}
