package com.telus.csm.ewlnsc.test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.junit.Test;

import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetWirelineProductComponentListResponseType;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.marketofferservicerequestresponse_v3.GetMarketOfferDetailResponse;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.marketofferingcommon_v2.Component;
import com.telus.tmi.xmlschema.xsd.product.productoffering.marketofferingcommon_v2.MarketOfferDetail;
import com.telus.tmi.xmlschema.xsd.product.productoffering.marketofferingcommon_v2.OfferDefinition;
import com.telus.tmi.xmlschema.xsd.product.productoffering.marketofferingcommon_v2.ProductDefinition;

public class CompareProductComponentListMOMtoESIS {

	String message = "Robert";

	@Test
	public void testCompareProductComponentListMOMtoESIS()
			throws JAXBException, FileNotFoundException, XMLStreamException {

		JAXBElement<GetMarketOfferDetailResponse> getMarketOfferDetailResponseElement = unmarshallXmlFile(
				"c:\\work\\getMarketOfferDetailResponse.xml", GetMarketOfferDetailResponse.class);
		// System.out.println(getMarketOfferDetailResponseElement.getName());
		// System.out.println(getMarketOfferDetailResponseElement.getValue());

		JAXBElement<GetWirelineProductComponentListResponseType> getWirelineProductComponentListResponseElement = unmarshallXmlFile(
				"c:\\work\\getWirelineProductComponentListResponse.xml",
				GetWirelineProductComponentListResponseType.class);
		// System.out.println(getWirelineProductComponentListResponseElement.getName());
		// System.out.println(getWirelineProductComponentListResponseElement.getValue());

		GetMarketOfferDetailResponse getMarketOfferDetailResponse = getMarketOfferDetailResponseElement.getValue();

		GetWirelineProductComponentListResponseType getWirelineProductComponentListResponseType = getWirelineProductComponentListResponseElement
				.getValue();

		for (WirelineProductCatalogueItem wirelineProductCatalogueItem : getWirelineProductComponentListResponseType
				.getSalesProductComponentList()) {
			String esisProductCatalogueItemId = wirelineProductCatalogueItem.getProductCatalogueItemIdentifier()
					.getProductCatalogueItemId();

			boolean matchedOffer = false;

			for (MarketOfferDetail marketOfferDetail : getMarketOfferDetailResponse.getMarketOfferDetailList()) {
				for (OfferDefinition offerDefinition : marketOfferDetail.getOfferDefinitionList()) {
					long momProductCatalogueItemId = offerDefinition.getProductCatalogueItemId();

					if (esisProductCatalogueItemId.equals(momProductCatalogueItemId + "")) {
						matchedOffer = true;
						System.out.println("\n\n");
						System.out.println(
								"Matched MOM and ESS Offer ProductCatalogueItemId " + momProductCatalogueItemId);

						System.out.println("\n");
						System.out.println(String.format("%-20s%-30s%-30s", "","ESIS","MOM"));
						System.out.println("\n");
						System.out.println(String.format("%-20s%-30s%-30s",
								"offerid", esisProductCatalogueItemId ,momProductCatalogueItemId));

						System.out.println("offerDefinition\t"
								+ wirelineProductCatalogueItem.getProductCatalogueNameTxt().getDescriptionText() + "\t"
								+ offerDefinition.getName().getName().get(0).getName());

						for (WirelineProductCatalogueItem component : wirelineProductCatalogueItem.getComponentList()) {
							component.getProductCatalogueItemIdentifier().getProductCatalogueItemId();

							String esisPCId = component.getProductCatalogueItemIdentifier().getProductCatalogueItemId();

							boolean matchedL1 = false;
							Component mainComponent = null;
							for (ProductDefinition productDefinition : offerDefinition.getProductDefinitionList()) {
								String momPCId = productDefinition.getProductCatalogueItemId() + "";

								if (esisPCId.equals(momPCId)) {
									matchedL1 = true;
									System.out.println(
											"\nMatched esisPCId componentList and momPCId productDefinitionList: "
													+ momPCId);

									System.out.println(String.format("%-20s%-30s%-30s", "- Description:",
											component.getProductCatalogueNameTxt().getDescriptionText(),
											productDefinition.getName().getName().get(0).getName()));

									mainComponent = productDefinition.getMainComponent();
								}
							}

							if (!matchedL1) {
								System.out.println("Couldn't match ESS level 1 ProductCatalogueItemId " + esisPCId);
							}

							boolean matchedL2 = false;
							for (WirelineProductCatalogueItem component2 : component.getComponentList()) {
								String esisComponent2Id = component2.getProductCatalogueItemIdentifier()
										.getProductCatalogueItemId();
								String momMainComponentId = mainComponent.getProductCatalogueItemId() + "";

								if (esisComponent2Id.equals(momMainComponentId)) {
									matchedL2 = true;
									System.out.println(
											"\nMatched esisComponent2Id and momMainComponentId productDefinitionList: "
													+ momMainComponentId);
									System.out.println(String.format("%-20s%-30s%-30s", "-- Description",
											component2.getProductCatalogueNameTxt().getDescriptionText(),
											mainComponent.getName().getName().get(0).getName()));

									for (WirelineProductCatalogueItem component3 : component2.getComponentList()) {
										String esisComponent3Id = component3.getProductCatalogueItemIdentifier()
												.getProductCatalogueItemId();

										Component matchingMomCompnent3 = new Component();
										boolean matchedL3 = false;

										for (Component momCompnent3 : mainComponent.getComponentList()) {
											String momComponent3Id = momCompnent3.getProductCatalogueItemId() + "";

											if (esisComponent3Id.equals(momComponent3Id)) {
												matchedL3 = true;
												System.out.println(
														"\nMatched momComponent3Id and momComponent3Id productDefinitionList: "
																+ momComponent3Id);
												System.out.println(String.format("%-20s%-30s%-30s", "--- Description",
														component3.getProductCatalogueNameTxt().getDescriptionText(),
														momCompnent3.getName().getName().get(0).getName()));

												matchingMomCompnent3 = momCompnent3;
											}
										}

										if (!matchedL3) {
											System.out
													.println("\n!!! Couldn't match ESS level 3 ProductCatalogueItemId "
															+ esisComponent3Id);
										}

										for (WirelineProductCatalogueItem component4 : component3.getComponentList()) {
											String esisComponent4Id = component4.getProductCatalogueItemIdentifier()
													.getProductCatalogueItemId();

											boolean matchedL4 = false;

											
											for (Component momCompnent4 : matchingMomCompnent3.getComponentList()) {
												String momComponent4Id = momCompnent4.getProductCatalogueItemId() + "";

												if (esisComponent4Id.equals(momComponent4Id)) {
													matchedL4 = true;
													System.out.println(
															"\nMatched momComponent4Id and momComponent4Id productDefinitionList: "
																	+ momComponent4Id);
													System.out.println(String.format("%-20s%-30s%-30s", "---- Description",
															component4.getProductCatalogueNameTxt()
																	.getDescriptionText(),
															momCompnent4.getName().getName().get(0).getName()));
												}
											}

											if (!matchedL4) {
												System.out.println(
														"\n!!! Couldn't match ESS level 4 ProductCatalogueItemId "
																+ esisComponent4Id);
											}

										}

									}
								}

							}
							if (!matchedL2) {
								System.out.println("\n!!! Couldn't match ESS level 2 ProductCatalogueItemId "
										+ mainComponent.getProductCatalogueItemId());
							}

						}
					}
				}
			}

			if (!matchedOffer) {
				System.out
						.println("\n!!! Couldn't match ESS Offer ProductCatalogueItemId " + esisProductCatalogueItemId);
			}
		}

		assertEquals(message, "Robert");
	}

	private <T> JAXBElement<T> unmarshallXmlFile(String fileName, Class<T> xmlClass)
			throws FactoryConfigurationError, XMLStreamException, FileNotFoundException, JAXBException {
		Unmarshaller unmarshaller = null;
		JAXBElement<T> je = null;
		try {
			XMLInputFactory xif = XMLInputFactory.newFactory();
			XMLStreamReader xsr = xif.createXMLStreamReader(new FileReader(fileName));// "src/forum11465653/input.xml"));
			xsr.nextTag(); // Advance to Envelope tag
			xsr.nextTag(); // Advance to Body tag
			xsr.nextTag(); // Advance to responce tag

			JAXBContext jc = JAXBContext.newInstance(xmlClass);
			unmarshaller = jc.createUnmarshaller();
			je = unmarshaller.unmarshal(xsr, xmlClass);
		} finally {
			unmarshaller = null;
		}
		return je;
	}
}
