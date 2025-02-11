package com.telus.csm.ewlnsc.delegate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.BooleanHolder;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.CreateInvoiceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetBookingRequirementResponse;
import com.telus.csm.ewlnsc.domain.AppointmentTypeVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.NoteVO;
import com.telus.csm.ewlnsc.domain.OPShoppingCartDelegateResponseVO;
import com.telus.csm.ewlnsc.domain.OrderCommentVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.domain.Message;
import com.telus.csm.ewlnss.adapter.domain.ValidationMessage;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfo;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;


public class OrderCommentDelegate {

	private static final LoggerUtil logger = LoggerUtil.getLogger(OrderCommentDelegate.class);


	public List<OrderCommentVO> execute(ShoppingCartContextVO shoppingCartContextVO, BooleanHolder forceBackOffice) {
		String functionName="execute";
		logger.enter(functionName);

		List<OrderCommentVO> comments = new ArrayList<OrderCommentVO>();

		if ( (shoppingCartContextVO != null) &&
			 (shoppingCartContextVO.getShoppingCartVO() != null) ) {
			if ( (shoppingCartContextVO.getShoppingCartVO().getCartItemList() != null) &&
				 (!shoppingCartContextVO.getShoppingCartVO().getCartItemList().isEmpty()) ) {
				String originatorId = null; 
			
				// 3.	Order with D2C Order Comment
				if (shoppingCartContextVO.getShoppingCartVO().getShoppingProfile() != null) {
					if ( (shoppingCartContextVO.getShoppingCartVO().getShoppingProfile().getUserProfile() != null) &&
						 (shoppingCartContextVO.getShoppingCartVO().getShoppingProfile().getUserProfile().getSalesRepId() != null) &&
						 (!shoppingCartContextVO.getShoppingCartVO().getShoppingProfile().getUserProfile().getSalesRepId().isEmpty()) ) {
						originatorId = shoppingCartContextVO.getShoppingCartVO().getShoppingProfile().getUserProfile().getSalesRepId();
					}

					OrderCommentVO orderCommentVO = createCommentForD2COrder(shoppingCartContextVO.getShoppingCartVO(), originatorId);

					if (orderCommentVO != null) {
						comments.add(orderCommentVO);	
						forceBackOffice.value |= true;
						logger.info(functionName, "Will force to backoffice because of createCommentForD2COrder");
					}
				}


				for (CartItemVO cartItem : shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
					if ( (cartItem != null) &&
						 (cartItem.isSalesOrderItem()) &&
						 (cartItem.getProducts() != null) ) {

						// 1.	Order with manual sweetener
						List<OrderCommentVO> orderCommentVOs = createCommentsForManualSweetener(cartItem.getProducts(), originatorId, shoppingCartContextVO, cartItem.getCartItemRelationId());					

						if ( (orderCommentVOs != null) && (!orderCommentVOs.isEmpty()) ) {
							comments.addAll(orderCommentVOs);
							forceBackOffice.value |= true;
							logger.info(functionName, "Will force to backoffice because of createCommentsForManualSweetener");
						}

						// 2.	Order with SL Winback (Shaw TN port-in) - require temporary phone number
						if (cartItem.getProducts().getHomePhoneProduct() != null) {
							OrderCommentVO orderCommentVO = createCommentForSlWinback(cartItem.getProducts().getHomePhoneProduct(), originatorId);

							if (orderCommentVO != null) {
								comments.add(orderCommentVO);
								forceBackOffice.value |= true;
								logger.info(functionName, "Will force to backoffice because of createCommentForSlWinback");
							}
						}

						// 9.	TTV with HSZero Order
						if (cartItem.getProducts().getInternetProduct() != null) {
							OrderCommentVO orderCommentVO = createCommentForTtvWithHSzero(cartItem.getProducts().getInternetProduct(), originatorId);

							if (orderCommentVO != null) {
								comments.add(orderCommentVO);
							}
						}

						if (shoppingCartContextVO.getFeasibilityResponseVO() != null) {
							// 4.	Order does not have install date and time book for FW or RW
							List<OrderCommentVO> orderCommentVOs_2 = createCommentForFwRwOrderWithoutInstallAppointment(shoppingCartContextVO.getFeasibilityResponseVO(), cartItem.getProducts(), originatorId);

							if ( (orderCommentVOs_2 != null) && (!orderCommentVOs_2.isEmpty()) ) {
								comments.addAll(orderCommentVOs_2);
							}

							// 7.	Selected delivery method for Boost Wifi is "Provide by installer".. If HS install type is not field work and TV install type is Field Work, add the installer comment"
							// &	
							// 8.	Selected delivery method for HS Modem is "Provide by installer".. If HS install type is not field work and TV install type is Field Work, add the installer comment"
							if (cartItem.getProducts().getInternetProduct() != null) {
								List<OrderCommentVO> orderCommentVOs_3 = createCommentsForInstallerToProvideHsModemOrBoostWifiModems(shoppingCartContextVO.getFeasibilityResponseVO(), cartItem.getProducts().getInternetProduct(), originatorId);

								if ( (orderCommentVOs_3 != null) && (!orderCommentVOs_3.isEmpty()) ) {
									comments.addAll(orderCommentVOs_3);
								}
							}
						}
					}
				}

//				// 14.	Order with SL Winback (TN port-in )
//				OrderCommentVO comment_case14 = createCommentForSLWinbackTNPortIn(shoppingCartContextVO.getShoppingCartVO().getCartItemList(), originatorId);
//				if(comment_case14 != null) {
//					comments.add(comment_case14);
//					forceBackOffice.value |= true;
//					logger.info(functionName, "Will force to backoffice because of Order with SL Winback (TN port-in )");
//				}
				// 15.	Order requires CLEC disconnect
				// 2018-12-04  Disconnect comments, backoffice for disconnect to be removed 
				// OrderCommentVO comment_case15 = createCommentsForCLECDisconnect(shoppingCartContextVO.getShoppingCartVO().getCartItemList(), originatorId);
				// if (comment_case15 != null) {
				// 	comments.add(comment_case15);
				// 	forceBackOffice.value |= true;
				//	logger.info(functionName, "Will force to backoffice because of disconnect orders");
				//}
			}
		}

		if (comments.isEmpty()) {
			comments = null;
		}

		logger.exit(functionName);

		return comments;
	}

	private List<OrderCommentVO> createCommentsForManualSweetener(ProductTypeVO products, String originatorId, ShoppingCartContextVO shoppingCartContextVO, String cartItemRelationId) {
		String functionName="createCommentsForManualSweetener";
		logger.enter(functionName);

		List<OrderCommentVO> comments = new ArrayList<OrderCommentVO>();

		// Internet
		if ( (products.getInternetProduct() != null) &&
			 (products.getInternetProduct().getSweeteners() != null) &&
			 (!products.getInternetProduct().getSweeteners().isEmpty()) ) {
			for (FFHSweetenerTypeVO sweetenerType : products.getInternetProduct().getSweeteners()) {
				OrderCommentVO comment = createCommentForManualSweetener(sweetenerType, originatorId, Constants.HSIC, shoppingCartContextVO, cartItemRelationId);

				if (comment != null) {
					comments.add(comment);
				}
			}
		}

		// TV
		if ( (products.getTelevisionProduct() != null) &&
			 (products.getTelevisionProduct().getSweeteners() != null) &&
			 (!products.getTelevisionProduct().getSweeteners().isEmpty()) ) {
			for (FFHSweetenerTypeVO sweetenerType : products.getTelevisionProduct().getSweeteners()) {
				OrderCommentVO comment = createCommentForManualSweetener(sweetenerType, originatorId, Constants.TTV, shoppingCartContextVO, cartItemRelationId);

				if (comment != null) {
					comments.add(comment);
				}
			}
		}

		// Phone
		if ( (products.getHomePhoneProduct() != null) &&
			 (products.getHomePhoneProduct().getSweeteners() != null) &&
			 (!products.getHomePhoneProduct().getSweeteners().isEmpty()) ) {
			for (FFHSweetenerTypeVO sweetenerType : products.getHomePhoneProduct().getSweeteners()) {
				OrderCommentVO comment = createCommentForManualSweetener(sweetenerType, originatorId, EnterpriseWLNSalesServicesConstants.SING, shoppingCartContextVO, cartItemRelationId);

				if (comment != null) {
					comments.add(comment);
				}
			}
		}

		if (comments.isEmpty()) {
			comments = null;
		}

		logger.exit(functionName);

		return comments;
	}

	private OrderCommentVO createCommentForManualSweetener(FFHSweetenerTypeVO sweetenerType, String originatorId, String productType, ShoppingCartContextVO shoppingCartContextVO, String cartItemRelationId) {
		String functionName="createCommentForManualSweetener";
		logger.enter(functionName);

		OrderCommentVO comment = null;

		if(sweetenerType != null && sweetenerType.findPromotionId() != null) {
			List<SweetenerOfferSummary> sweetenerOfferSummaryList = shoppingCartContextVO.getSweetnersByProduct(productType, cartItemRelationId);
			if(sweetenerOfferSummaryList != null && !sweetenerOfferSummaryList.isEmpty()) {
				for(SweetenerOfferSummary sweetenerOfferSummary : sweetenerOfferSummaryList) {
					if(sweetenerOfferSummary != null 
							&& sweetenerType.findPromotionId().equals(String.valueOf(sweetenerOfferSummary.getPromotionId()))
							&& WLNOfferUtil.isManualSweetener(sweetenerOfferSummary)) {
						String description = null;
						if(sweetenerOfferSummary.getOfferDescriptionList() != null) {
							for(Description descr: sweetenerOfferSummary.getOfferDescriptionList()) {
								if(descr != null && Constants.LANG_EN.equalsIgnoreCase(descr.getLocale()) && !StringUtils.isEmpty(descr.getDescriptionText())) {
									description = descr.getDescriptionText();
									break;
								}
							}
						}
						comment = createComment("Manual sweetener(s) to add: " + description, Constants.NOTE_TYPE_CODE_FREE_FORMAT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, productType);
					}
				}
			}
		}

		logger.exit(functionName);

		return comment;
	}

	private OrderCommentVO createComment(String noteText, String noteTypeCode, String orderEntityTypeCode, String originatorId) {
		String functionName="createComment";
		logger.enter(functionName);
		logger.info(functionName, "noteText: " + noteText);
		logger.info(functionName, "noteTypeCode: " + noteTypeCode);
		logger.info(functionName, "orderEntityTypeCode: " + orderEntityTypeCode);
		logger.info(functionName, "originatorId: " + originatorId);

		OrderCommentVO comment = new OrderCommentVO();
		comment.setNoteText(noteText);
		comment.setNoteTypeCode(noteTypeCode);
		comment.setOrderEntityTypeCode(orderEntityTypeCode);
		comment.setOriginatorId(originatorId);

		logger.exit(functionName);

		return comment;
	}

	private OrderCommentVO createComment(String noteText, String noteTypeCode, String orderEntityTypeCode, String originatorId, String productType) {
		String functionName="createComment";
		logger.enter(functionName);

		OrderCommentVO comment = createComment(noteText, noteTypeCode, orderEntityTypeCode, originatorId);
		comment.setProductType(productType);

		logger.exit(functionName);

		return comment;
	}

	private OrderCommentVO createCommentForSlWinback(HomePhoneProductTypeVO homePhoneProduct, String originatorId) {
		String functionName="createCommentForSlWinback";
		logger.enter(functionName);

		OrderCommentVO comment = null;

		if ( (homePhoneProduct.getPhoneNumber() != null) &&
			 (homePhoneProduct.getPhoneNumber().getPortRequestType() != null) &&
			 (homePhoneProduct.getPhoneNumber().getPortRequestType().isTemporaryPhoneNumberRequiredInd() != null) &&
			 (homePhoneProduct.getPhoneNumber().getPortRequestType().isTemporaryPhoneNumberRequiredInd()) ) {
			comment = createComment("Project Pineapple", Constants.NOTE_TYPE_CODE_CLEC_COMMENT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, EnterpriseWLNSalesServicesConstants.SING);
		}

		logger.exit(functionName);

		return comment;
	}

	private OrderCommentVO createCommentForD2COrder(ShoppingCartVO shoppingCartVO, String originatorId) {
		
		String functionName="createCommentForD2COrder";
		logger.enter(functionName);

		OrderCommentVO comment = null;

		//Open this up solely based on not exsisting in shopping cart. 
		if ( (shoppingCartVO.getNote() != null) &&
			 (!shoppingCartVO.getNote().isEmpty()) ) {
			for (NoteVO orderComment : shoppingCartVO.getNote()) {
				if ( (orderComment != null) &&
					 (orderComment.getType() != null) &&
					 (Constants.NOTE_TYPE_ORDER_COMMENTS.equalsIgnoreCase(orderComment.getType())) &&
					 (orderComment.getText() != null) &&
					 (!orderComment.getText().isEmpty()) ) {
					if (comment == null) {
						comment = createComment("Comments from Sales Rep: " + orderComment.getText() + ".", Constants.NOTE_TYPE_CODE_FREE_FORMAT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId);
					}
					else {
						comment.setNoteText(comment.getNoteText() + orderComment.getText() + ".");
					}
				}
			}
		}

		logger.exit(functionName);

		return comment;
	}

	private OrderCommentVO createCommentForTtvWithHSzero(InternetProductTypeVO internetProduct, String originatorId) {
		String functionName="createCommentForTtvWithHSzero";
		logger.enter(functionName);

		OrderCommentVO comment = null;

		for (ProductComponentVO productComponent : internetProduct.getProductComponents()) {
			if ( (productComponent != null) &&
				 (productComponent.getProductCatalogueId() != null) &&
				 (!productComponent.getProductCatalogueId().isEmpty()) ) {
				CatalogueItemDO catalogueItemDO = CommonWLNGridHelper.getInstance().getCatalogueItemById(productComponent.getProductCatalogueId());
				
				if ( (catalogueItemDO != null) &&
					 (catalogueItemDO.getProductCode() != null) &&
					 (Constants.HS0_EXTERNAL_CATALOGUE_ID.equals(catalogueItemDO.getProductCode())) ) {
					logger.exit(functionName);

					return createComment("TTV no High Speed order and client does not know their username or password", Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, Constants.TTV);
				}
			}
		}

		logger.exit(functionName);

		return comment;
	}
	
	private List<OrderCommentVO> createCommentForFwRwOrderWithoutInstallAppointment(CheckProductFeasibilityAdapterResponse feasibilityResponse, ProductTypeVO products, String originatorId) {
		String functionName="createCommentForFwRwOrderWithoutInstallAppointment";
		logger.enter(functionName);

		List<OrderCommentVO> comments = new ArrayList<OrderCommentVO>();

		boolean internetHasAppointment = true;
		boolean tvHasAppointment = true;
		boolean phoneHasAppointment = true;

		if ( (feasibilityResponse.getInstallTypeRWFW() != null) &&
			 (!feasibilityResponse.getInstallTypeRWFW().isEmpty()) ) {

			// Internet
			if ( (products.getInternetProduct() != null) &&
				 ( (products.getInternetProduct().getAppointmentDetail() == null) ||
				   (products.getInternetProduct().getAppointmentDetail().getBookedAppointmentDate() == null) ||
				   (products.getInternetProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId() == null) ||
				   (products.getInternetProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId().isEmpty()) ) ) {
				internetHasAppointment = false;

				StringBuilder commentText = new StringBuilder();

				if ( (products.getInternetProduct().getAppointmentDetail() != null) &&
					 (products.getInternetProduct().getAppointmentDetail().getPreferredAppointmentDates() != null) &&
					 (!products.getInternetProduct().getAppointmentDetail().getPreferredAppointmentDates().isEmpty()) ) {
					commentText.append("Assistance required to book an install date and time for HSIC product. Customer preferred date & time:");

					int i = 0;

					for (AppointmentTypeVO preferredAppointmentDates : products.getInternetProduct().getAppointmentDetail().getPreferredAppointmentDates()) {
						if ( (preferredAppointmentDates != null) &&
							 (preferredAppointmentDates.getAppointmentDate() != null) ) {
							if (i==0) {
								commentText.append(" " + preferredAppointmentDates.getAppointmentDate() );

								if (preferredAppointmentDates.getAppointmentTimeSlot() != null) {
									commentText.append(" " + preferredAppointmentDates.getAppointmentTimeSlot().getStartTime() + " : " + 
											preferredAppointmentDates.getAppointmentTimeSlot().getEndTime());
								}
							}
							else {
								commentText.append("," + " " + preferredAppointmentDates.getAppointmentDate()) ;

								if (preferredAppointmentDates.getAppointmentTimeSlot() != null) {
									commentText.append(" " + preferredAppointmentDates.getAppointmentTimeSlot().getStartTime() + " : " + 
											preferredAppointmentDates.getAppointmentTimeSlot().getEndTime());
								}
							}

							i++;
						}
					}						
				}
				else {
					commentText.append("Assistance required to book an install date and time for HSIC product.");
				}

				comments.add(createComment(commentText.toString(), Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, Constants.HSIC));	
			}

			// TV
			if ( (products.getTelevisionProduct() != null) &&
				 ( (products.getTelevisionProduct().getAppointmentDetail() == null) ||
				   (products.getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate() == null) ||
				   (products.getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId() == null) ||
				   (products.getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId().isEmpty()) ) ) {
				tvHasAppointment = false;

				StringBuilder commentText = new StringBuilder();

				if ( (products.getTelevisionProduct().getAppointmentDetail() != null) &&
					 (products.getTelevisionProduct().getAppointmentDetail().getPreferredAppointmentDates() != null) &&
					 (!products.getTelevisionProduct().getAppointmentDetail().getPreferredAppointmentDates().isEmpty()) ) {
					commentText.append("Assistance required to book an install date and time for TV product. Customer preferred date & time:");

					int i = 0;

					for (AppointmentTypeVO preferredAppointmentDates : products.getTelevisionProduct().getAppointmentDetail().getPreferredAppointmentDates()) {
						if ( (preferredAppointmentDates != null) &&
							 (preferredAppointmentDates.getAppointmentDate() != null)) {
							if (i==0) {
								commentText.append(" " + preferredAppointmentDates.getAppointmentDate() );
								if (preferredAppointmentDates.getAppointmentTimeSlot() != null) {
									commentText.append(" " + preferredAppointmentDates.getAppointmentTimeSlot().getStartTime() + " : " + 
											preferredAppointmentDates.getAppointmentTimeSlot().getEndTime());
								}
							}
							else {
								commentText.append("," + " " + preferredAppointmentDates.getAppointmentDate()) ;

								if (preferredAppointmentDates.getAppointmentTimeSlot() != null) {
									commentText.append(" " + preferredAppointmentDates.getAppointmentTimeSlot().getStartTime() + " : " + 
											preferredAppointmentDates.getAppointmentTimeSlot().getEndTime());
								}
							}

							i++;
						}
					}						
				}
				else {
					commentText.append("Assistance required to book an install date and time for TV product.");
				}

				comments.add(createComment(commentText.toString(), Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, Constants.TTV));	
			}

			// Phone
			if ( (products.getHomePhoneProduct() != null) &&
				 ( (products.getHomePhoneProduct().getAppointmentDetail() == null) ||
				   (products.getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate() == null) ||
				   (products.getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId() == null) ||
				   (products.getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId().isEmpty()) ) ) {
				phoneHasAppointment = false;

				StringBuilder commentText = new StringBuilder();

				if ( (products.getHomePhoneProduct().getAppointmentDetail() != null) &&
					 (products.getHomePhoneProduct().getAppointmentDetail().getPreferredAppointmentDates() != null) &&
					 (!products.getHomePhoneProduct().getAppointmentDetail().getPreferredAppointmentDates().isEmpty()) ) {
					commentText.append("Assistance required to book an install date and time for SING product. Customer preferred date & time:");

					int i=0;

					for (AppointmentTypeVO preferredAppointmentDates : products.getHomePhoneProduct().getAppointmentDetail().getPreferredAppointmentDates()) {
						if ( (preferredAppointmentDates != null) &&
							 (preferredAppointmentDates.getAppointmentDate() != null)) {
							if (i==0) {
								commentText.append(" " + preferredAppointmentDates.getAppointmentDate() );

								if (preferredAppointmentDates.getAppointmentTimeSlot() != null) {
									commentText.append(" " + preferredAppointmentDates.getAppointmentTimeSlot().getStartTime() + " : " + 
											preferredAppointmentDates.getAppointmentTimeSlot().getEndTime());
								}
							}
							else {
								commentText.append("," + " " + preferredAppointmentDates.getAppointmentDate()) ;

								if (preferredAppointmentDates.getAppointmentTimeSlot() != null) {
									commentText.append(" " + preferredAppointmentDates.getAppointmentTimeSlot().getStartTime() + " : " + 
											preferredAppointmentDates.getAppointmentTimeSlot().getEndTime());
								}
							}

							i++;
						}
					}						
				}
				else {
					commentText.append("Assistance required to book an install date and time for SING product.");
				}

				comments.add(createComment(commentText.toString(), Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, EnterpriseWLNSalesServicesConstants.SING));	
			}

			// If none of the products has an appointment only then we do create a comment.
			// Therefore, if at least one of the products has an appointment, don't create comments at all.
			if (internetHasAppointment || tvHasAppointment || phoneHasAppointment) {
				comments = null;
			}
		}

		if (comments != null && comments.isEmpty()) {
			comments = null;
		}

		logger.exit(functionName);

		return comments;
	}

	public List<OrderCommentVO> execute(ShoppingCartContextVO shoppingCartContextVO, OPShoppingCartDelegateResponseVO opShoppingCartDelegateResponseVO, GetDepositInfoResponse depositInfoResponse,
												CreateInvoiceAdapterResponse createInvoiceAdapterResponse, GetBookingRequirementResponse bookingRequirementResponse,
												ConfirmBookingAdapterResponse confirmBookingAdapterResponse, String confirmBookingId) {
		String functionName="execute";
		logger.enter(functionName);

		List<OrderCommentVO> comments = new ArrayList<OrderCommentVO>();

		String originatorId = null; 		
		if (shoppingCartContextVO.getShoppingCartVO().getShoppingProfile() != null) {
			if ( (shoppingCartContextVO.getShoppingCartVO().getShoppingProfile().getUserProfile() != null) &&
				 (shoppingCartContextVO.getShoppingCartVO().getShoppingProfile().getUserProfile().getSalesRepId() != null) &&
				 (!shoppingCartContextVO.getShoppingCartVO().getShoppingProfile().getUserProfile().getSalesRepId().isEmpty()) ) {
				originatorId = shoppingCartContextVO.getShoppingCartVO().getShoppingProfile().getUserProfile().getSalesRepId();
			}
		}
		
		// 6.	Orders submitted to back office  -  OP validation errors
		List<OrderCommentVO> comments_2 = createCommentsForOPValidationErrors(shoppingCartContextVO, opShoppingCartDelegateResponseVO, originatorId);

		if ( (comments_2 != null) && (!comments_2.isEmpty()) ) {
			comments.addAll(comments_2);
		}

		// 5.	Order with deposit invoice # pending for deposit payment
		// &
		// 10.	Final deposit amount calculated is different from the estimated deposit amount
		OrderCommentVO comment_3 = createCommentForDepositPaymentRequired(shoppingCartContextVO, depositInfoResponse, createInvoiceAdapterResponse, originatorId);

		if (comment_3 != null) {
			comments.add(comment_3);
		}

		// 11.	Order has an install date & time booked for field work but no booking is not required after OP Shopping Cart creation.
		List<OrderCommentVO> comments_4 = createCommentsForCustomerBookingNotRequired(shoppingCartContextVO, bookingRequirementResponse, originatorId);

		if ( (comments_4 != null) && (!comments_4.isEmpty()) ) {
			comments.addAll(comments_4);
		}

		// 13.	Order has an install date & time booked for field work but booking requirement fails (due to longer SWT required)  after OP Shopping Cart creation.
		List<OrderCommentVO> comments_5 = createCommentsForBookingRequirementFailed(shoppingCartContextVO, confirmBookingAdapterResponse, confirmBookingId, originatorId);

		if ( (comments_5 != null) && (!comments_5.isEmpty()) ) {
			comments.addAll(comments_5);
		}
		
		// 12.	Order does not require appointment booking for software order but Field Work install type returned after OP Shopping Cart creation.
		List<OrderCommentVO> comments_6 = cerateCommentForBookingRequiredButMissing(shoppingCartContextVO, bookingRequirementResponse, originatorId);

		if ( (comments_6 != null) && (!comments_6.isEmpty()) ) {
			comments.addAll(comments_6);
		}

		// 16. Order has GWP but the customer is not eligible for GWP based on the credit assessment results.
		List<OrderCommentVO> comments_7 = createCommentsForGWPOrderWhichIsNOtEligible(shoppingCartContextVO, originatorId);

		if ( (comments_7 != null) && (!comments_7.isEmpty()) ) {
			comments.addAll(comments_7);
		}

		if (comments.isEmpty()) {
			comments = null;
		}

		logger.exit(functionName);

		return comments;
	}

	private List<OrderCommentVO> createCommentsForOPValidationErrors(ShoppingCartContextVO shoppingCartContextVO, OPShoppingCartDelegateResponseVO opShoppingCartResponse, String originatorId) {
		String functionName="createCommentsForOPValidationErrors";	
		logger.enter(functionName);

		List<OrderCommentVO> comments = null;

		if ( (opShoppingCartResponse != null) &&
			 (hasError(opShoppingCartResponse)) &&
			 (shoppingCartContextVO.getProductOrder() != null) &&
			 (shoppingCartContextVO.getProductOrder().getProductOrderItems() != null) &&
			 (!shoppingCartContextVO.getProductOrder().getProductOrderItems().isEmpty()) &&
			 (opShoppingCartResponse.getMessageList() != null) &&
			 (!opShoppingCartResponse.getMessageList().isEmpty())) {
			comments = new ArrayList<OrderCommentVO>();

			for (Message message : opShoppingCartResponse.getMessageList()) {
				if ( (message.getValidationItem() != null) &&
					 (!message.getValidationItem().isEmpty()) &&
					 (message.getStatus().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.FAILURE)) &&
					 (message.getOrderItemId() != null) ) {
					if ( (message.getValidationItem().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.FEASIBILITY)) ||
						 (message.getValidationItem().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.O_OA_CONFIG)) ||
						 (message.getValidationItem().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.PRICING)) ) {
						for (ValidationMessage validationMessage : message.getValidationMessageList()) {
							if (validationMessage.getMessageType().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR)) {
								for (ProductOrderItem productOrderItem : shoppingCartContextVO.getProductOrder().getProductOrderItems()) {
									if ( (productOrderItem != null) &&
										 (message.getOrderItemId().equals(String.valueOf(productOrderItem.getId()))) ) {
										OrderCommentVO comment = createComment("Assistance required to resolve the following issue(s): " + validationMessage.getMessage(),
												Constants.NOTE_TYPE_CODE_FREE_FORMAT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, productOrderItem.getProduct().getServiceType());
										comments.add(comment);
									}
								}
							}
						}
					}
				}
			}

			if (comments.isEmpty()) {
				comments = null;
			}
		}

		logger.exit(functionName);

		return comments;
	}

	private boolean hasError(OPShoppingCartDelegateResponseVO opShoppingCartResponse) {
		if ( (opShoppingCartResponse.getMessageList() != null) && (!opShoppingCartResponse.getMessageList().isEmpty()) ) {
			for (Message message : opShoppingCartResponse.getMessageList()) {
				if ( (message.getValidationItem() != null) && (!message.getValidationItem().isEmpty()) ) {
					if ( (message.getValidationItem().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.O_OA_CONFIG)) &&
						 (message.getStatus().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.FAILURE)) ) {
						return true;
					}

					if ( (message.getValidationItem().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.PRICING)) &&
							  (message.getStatus().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.FAILURE)) ) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private OrderCommentVO createCommentForDepositPaymentRequired(ShoppingCartContextVO shoppingCartContextVO, GetDepositInfoResponse depositInfoResponse, CreateInvoiceAdapterResponse createInvoiceAdapterResponse, String originatorId) {
		String functionName="createCommentForDepositPaymentRequired";	
		logger.enter(functionName);

		OrderCommentVO comment = null;

		if ( (depositInfoResponse != null) &&
			 (depositInfoResponse.getTotalRequiredDeposit() != null) &&
			 (depositInfoResponse.getTotalRequiredDeposit() > 0) ) {
			if ( (shoppingCartContextVO.getShoppingCartVO().getDepositAssessed() == null) ||
				 (shoppingCartContextVO.getShoppingCartVO().getDepositAssessed().getDepositAmt() == null) ) {
				comment = createComment("Deposit payment required.", Constants.NOTE_TYPE_CODE_FREE_FORMAT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId);
			}
			else if (shoppingCartContextVO.getShoppingCartVO().getDepositAssessed().getDepositAmt() == 0) {
				comment = createComment("Deposit payment required.", Constants.NOTE_TYPE_CODE_FREE_FORMAT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId);
			}
			else if ( (shoppingCartContextVO.getShoppingCartVO().getDepositAssessed().getDepositAmt() > 0) &&
					  (shoppingCartContextVO.getShoppingCartVO().getDepositAssessed().getDepositAmt() != depositInfoResponse.getTotalRequiredDeposit()) ) {
				comment = createComment("Inform customer to accept the new deposit amount. Deposit payment required.", 
												Constants.NOTE_TYPE_CODE_FREE_FORMAT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId);
			}
		}

		if ( (comment != null) && (comment.getNoteText() != null) ) {
			// invoice is pending
			addInvoiceNumber(createInvoiceAdapterResponse, comment);
		}

		logger.exit(functionName);

		return comment;
	}

	private void addInvoiceNumber(CreateInvoiceAdapterResponse createInvoiceAdapterResponse, OrderCommentVO comment) {
		String functionName="addInvoiceNumber";	
		logger.enter(functionName);
		
		if (createInvoiceAdapterResponse != null) {
			if ( (createInvoiceAdapterResponse.getInvoiceNo() == null) || (createInvoiceAdapterResponse.getInvoiceNo().isEmpty()) ) {
				logger.debug(functionName, "Invoice number in CreateInvoiceAdapterResponse is NULL or empty.");
			}

			comment.setNoteText(comment.getNoteText() + ", invoice # is " + createInvoiceAdapterResponse.getInvoiceNo());	
		}

		logger.exit(functionName);
	}

	private List<OrderCommentVO> createCommentsForCustomerBookingNotRequired(ShoppingCartContextVO shoppingCartContextVO, GetBookingRequirementResponse bookingRequirementResponse, String originatorId) {
		String functionName="createCommentsForCustomerBookingNotRequired";	
		logger.enter(functionName);

		List<OrderCommentVO> comments = null;

		if ( (bookingRequirementResponse != null) &&
			 (bookingRequirementResponse.getOrderBookingRequirement() != null) &&
			 (bookingRequirementResponse.getOrderBookingRequirement().getBookingActionStatus() != null) &&
			 (!"AppointmentRequired".equalsIgnoreCase(bookingRequirementResponse.getOrderBookingRequirement().getBookingActionStatus())) &&
			 (shoppingCartContextVO.getShoppingCartVO().getCartItemList() != null) &&
			 (!shoppingCartContextVO.getShoppingCartVO().getCartItemList().isEmpty()) ) {
			comments = new ArrayList<OrderCommentVO>();

			for (CartItemVO cartItemVO : shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
				if ( (cartItemVO != null) && (cartItemVO.getProducts() != null) ) {
					if ( (cartItemVO.getProducts().getInternetProduct() != null) &&
						 (cartItemVO.getProducts().getInternetProduct().getAppointmentDetail() != null) &&
						 (cartItemVO.getProducts().getInternetProduct().getAppointmentDetail().getBookedAppointmentDate() != null) &&
						 (cartItemVO.getProducts().getInternetProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId() != null) &&
						 (!cartItemVO.getProducts().getInternetProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId().isEmpty()) ) {
						OrderCommentVO comment = createComment("Inform customer for installation requirement change. Appointment booked for field work visit is no longer required.", Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, Constants.HSIC);
						comments.add(comment);
					}

					if ( (cartItemVO.getProducts().getTelevisionProduct() != null) &&
						 (cartItemVO.getProducts().getTelevisionProduct().getAppointmentDetail() != null) &&
						 (cartItemVO.getProducts().getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate() != null) &&
						 (cartItemVO.getProducts().getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId() != null) &&
						 (!cartItemVO.getProducts().getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId().isEmpty()) ) {
						OrderCommentVO comment = createComment("Inform customer for installation requirement change. Appointment booked for field work visit is no longer required.", Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, Constants.TTV);
						comments.add(comment);
					}

					if ( (cartItemVO.getProducts().getHomePhoneProduct() != null) &&
						 (cartItemVO.getProducts().getHomePhoneProduct().getAppointmentDetail() != null) &&
						 (cartItemVO.getProducts().getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate() != null) &&
						 (cartItemVO.getProducts().getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId() != null) &&
						 (!cartItemVO.getProducts().getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId().isEmpty()) ) {
						OrderCommentVO comment = createComment("Inform customer for installation requirement change. Appointment booked for field work visit is no longer required.", Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, EnterpriseWLNSalesServicesConstants.SING);
						comments.add(comment);
					}
				}
			}

			if (comments.isEmpty()) {
				comments = null;
			}
		}

		logger.exit(functionName);

		return comments;
	}

	private List<OrderCommentVO> createCommentsForBookingRequirementFailed(ShoppingCartContextVO shoppingCartContextVO, ConfirmBookingAdapterResponse confirmBookingAdapterResponse,
																					String confirmBookingId, String originatorId) {
		String functionName="createCommentsForBookingRequirementFailed";	
		logger.enter(functionName);

		List<OrderCommentVO> comments = null;

		if ( (confirmBookingAdapterResponse != null) &&
			 (confirmBookingAdapterResponse.getMessageList() != null) &&
			 (!confirmBookingAdapterResponse.getMessageList().isEmpty()) &&
			 (confirmBookingId != null) ) {
			comments = new ArrayList<OrderCommentVO>();

			for (com.telus.csm.ewlnss.adapter.domain.Message message: confirmBookingAdapterResponse.getMessageList()) {
				if ( (message != null) &&
					 (message.getMessageType() != null) &&
					 ("error".equalsIgnoreCase(message.getMessageType())) &&
					 (shoppingCartContextVO.getShoppingCartVO().getCartItemList() != null) &&
					 (!shoppingCartContextVO.getShoppingCartVO().getCartItemList().isEmpty()) ) {
					for (CartItemVO cartItem : shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
						if ( (cartItem != null) &&
							 (cartItem.getProducts() != null) ) {
							if ( (cartItem.getProducts().getHomePhoneProduct() != null) &&
								 (cartItem.getProducts().getHomePhoneProduct().getAppointmentDetail() != null) &&
								 (cartItem.getProducts().getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate() != null) &&
								 (cartItem.getProducts().getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId() != null) &&
								 (confirmBookingId.equals(cartItem.getProducts().getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId())) ) {
								OrderCommentVO comment = createComment("Inform customer for installation requirement change. New appointment booking for field work visit is required.", 
																				Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, EnterpriseWLNSalesServicesConstants.SING);
								comments.add(comment);
							}

							if ( (cartItem.getProducts().getInternetProduct() != null) &&
								 (cartItem.getProducts().getInternetProduct().getAppointmentDetail() != null) &&
								 (cartItem.getProducts().getInternetProduct().getAppointmentDetail().getBookedAppointmentDate() != null) &&
								 (cartItem.getProducts().getInternetProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId() != null) &&
								 (confirmBookingId.equals(cartItem.getProducts().getInternetProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId())) ) {
								OrderCommentVO comment = createComment("Inform customer for installation requirement change. New appointment booking for field work visit is required.", 
																				Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, Constants.HSIC);
								comments.add(comment);
							}
						
							if ( (cartItem.getProducts().getTelevisionProduct() != null) &&
								 (cartItem.getProducts().getTelevisionProduct().getAppointmentDetail() != null) &&
								 (cartItem.getProducts().getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate() != null) &&
								 (cartItem.getProducts().getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId() != null) &&
								 (confirmBookingId.equals(cartItem.getProducts().getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId())) ) {
								OrderCommentVO comment = createComment("Inform customer for installation requirement change. New appointment booking for field work visit is required.", 
																				Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, Constants.TTV);
								comments.add(comment);
							}
						}
					}
				}
			}
		}

		if (comments != null && comments.isEmpty()) {
			comments = null;
		}

		logger.exit(functionName);

		return comments;
	}

	private List<OrderCommentVO> createCommentsForInstallerToProvideHsModemOrBoostWifiModems(CheckProductFeasibilityAdapterResponse feasibilityResponseVO,
																									InternetProductTypeVO internetProduct, String originatorId) {
		String functionName="createCommentsForInstallerToProvideHsModemOrBoostWifiModems";	
		logger.enter(functionName);

		List<OrderCommentVO> comments = new ArrayList<OrderCommentVO>();

		boolean hsicConditionIsSatisfied = false;
		boolean ttvConditionIsSatisfied = false;

		if ( (feasibilityResponseVO.getProductFeasibilityInfoList() != null) &&
			 (feasibilityResponseVO.getProductFeasibilityInfoList().getProductFeasibilityInfo() != null) &&
			 (!feasibilityResponseVO.getProductFeasibilityInfoList().getProductFeasibilityInfo().isEmpty()) ) {
			for (ProductFeasibilityInfo productFeasibilityInfo : feasibilityResponseVO.getProductFeasibilityInfoList().getProductFeasibilityInfo()) {
				if ( (productFeasibilityInfo != null) &&
					 (productFeasibilityInfo.getProductSpecification() != null) &&
					 (productFeasibilityInfo.getProductSpecification().getName() != null) &&
					 (productFeasibilityInfo.getProvisioningInformation() != null) &&
					 (productFeasibilityInfo.getProvisioningInformation().getRecommendedInstallType() != null) ) {
					if ( Constants.HSIC.equals(productFeasibilityInfo.getProductSpecification().getName()) &&
						 (!Constants.FIELD_WORK.equals(productFeasibilityInfo.getProvisioningInformation().getRecommendedInstallType())) ) {
						hsicConditionIsSatisfied = true;
					}

					if ( Constants.TTV.equals(productFeasibilityInfo.getProductSpecification().getName()) &&
						 (Constants.FIELD_WORK.equals(productFeasibilityInfo.getProvisioningInformation().getRecommendedInstallType())) ) {
						ttvConditionIsSatisfied = true;
					}
				}

				if (hsicConditionIsSatisfied && ttvConditionIsSatisfied) {
					break;
				}
			}

			if ( (hsicConditionIsSatisfied) &&
				 (ttvConditionIsSatisfied) &&
				 (internetProduct.getEquipments() != null) &&
				 (!internetProduct.getEquipments().isEmpty()) ) {
				for (FFHEquipmentTypeVO equipment : internetProduct.getEquipments()) {
					if ( (equipment != null) &&
						 (equipment.getDeliveryMethodType() != null) &&
						 (Constants.DELIVERY_METHOD_INSTALLER.equals(equipment.getDeliveryMethodType())) &&
						 (equipment.getAcquisitionType() != null) &&
						 (equipment.getAcquisitionType().isRentalEquipmentIndicator() != null) &&
						 (equipment.getAcquisitionType().isRentalEquipmentIndicator()) ) {
						String acquisitionType = null;
						if (equipment.getAcquisitionType().isBuyIndicator()) {
							acquisitionType = "buy";
						}
						else if (equipment.getAcquisitionType().isRentalEquipmentIndicator()) {
							acquisitionType = "rent";
						}

						OrderCommentVO comment = createComment("Installer please provide HS Modem to the customer. " + equipment.getMaterialItemCode() + " - " + acquisitionType,
								Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId);

						comments.add(comment);
					}
				}
			}
		}

		if (comments.isEmpty()) {
			comments = null;
		}

		logger.exit(functionName);

		return comments;
	}

	private List<OrderCommentVO> cerateCommentForBookingRequiredButMissing(ShoppingCartContextVO shoppingCartContextVO, GetBookingRequirementResponse bookingRequirementResponse, String originatorId) {
		String functionName="cerateCommentForBookingRequiredButMissing";	
		logger.enter(functionName);

		List<OrderCommentVO> comments = null;

		if ( (bookingRequirementResponse != null) &&
			 (bookingRequirementResponse.getOrderBookingRequirement() != null) &&
			 (bookingRequirementResponse.getOrderBookingRequirement().getBookingActionStatus() != null) &&
			 (!"AppointmentRequired".equalsIgnoreCase(bookingRequirementResponse.getOrderBookingRequirement().getBookingActionStatus())) &&
			 (shoppingCartContextVO.getShoppingCartVO().getCartItemList() != null) &&
			 (!shoppingCartContextVO.getShoppingCartVO().getCartItemList().isEmpty()) ) {
			comments = new ArrayList<OrderCommentVO>();

			for (CartItemVO cartItemVO : shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
				if ( (cartItemVO != null) && (cartItemVO.getProducts() != null) ) {
					if ( (cartItemVO.getProducts().getInternetProduct() != null) &&
						 ( (cartItemVO.getProducts().getInternetProduct().getAppointmentDetail() == null) || 
						   (cartItemVO.getProducts().getInternetProduct().getAppointmentDetail().getBookedAppointmentDate() == null) ) ) {
						OrderCommentVO comment = createComment("Inform customer for installation requirement change. Appointment booking for field work visit is required.",
															   Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, Constants.HSIC);
						comments.add(comment);
					}

					if ( (cartItemVO.getProducts().getTelevisionProduct() != null) &&
						 ( (cartItemVO.getProducts().getTelevisionProduct().getAppointmentDetail() == null) ||
						   (cartItemVO.getProducts().getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate() == null) ) ) {
						OrderCommentVO comment = createComment("Inform customer for installation requirement change. Appointment booking for field work visit is required.",
								   							   Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, Constants.TTV);
						comments.add(comment);
					}

					if ( (cartItemVO.getProducts().getHomePhoneProduct() != null) &&
						 ( (cartItemVO.getProducts().getHomePhoneProduct().getAppointmentDetail() == null) ||
						   (cartItemVO.getProducts().getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate() == null) ) ) {
						OrderCommentVO comment = createComment("Inform customer for installation requirement change. Appointment booking for field work visit is required.",
	   							   										Constants.NOTE_TYPE_CODE_INSTALLER, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, EnterpriseWLNSalesServicesConstants.SING);
						comments.add(comment);
					}
				}
			}
		}

		logger.exit(functionName);

		return comments;
	}

//	private OrderCommentVO createCommentForSLWinbackTNPortIn(List<CartItemVO> cartItemList, String originatorId) {
//		String functionName="createCommentForSLWinbackTNPortIn";	
//		logger.enter(functionName);
//
//		OrderCommentVO comment = null;
//
//		Boolean temporaryPhoneNumberRequiredInd = null;
//		boolean disconnectRequestForSING = false;
//		boolean nonShawDisconnectRequest = false;
//
//		for (CartItemVO cartItem : cartItemList) {
//			if (cartItem != null) {
//				if (cartItem.isSalesOrderItem()) {
//					if ( (cartItem.getProducts() != null) &&
//						 (cartItem.getProducts().getHomePhoneProduct() != null) &&
//						 (cartItem.getProducts().getHomePhoneProduct().getPhoneNumber() != null) &&
//						 (cartItem.getProducts().getHomePhoneProduct().getPhoneNumber().getPortRequestType() != null) &&
//						 (cartItem.getProducts().getHomePhoneProduct().getPhoneNumber().getPortRequestType().isTemporaryPhoneNumberRequiredInd() != null) ) {
//						temporaryPhoneNumberRequiredInd = cartItem.getProducts().getHomePhoneProduct().getPhoneNumber().getPortRequestType().isTemporaryPhoneNumberRequiredInd();
//					}
//				}
//				else if (cartItem.isDisconnectOrderItem()) {
//					if ( (cartItem.getDisconnectRequestList() != null) &&
//						 (!cartItem.getDisconnectRequestList().isEmpty()) ) {
//						for (DisconnectRequestTypeVO disconnectRequest : cartItem.getDisconnectRequestList()) {
//							if (disconnectRequest != null) {
//								if ( (disconnectRequest.getProductServiceType() != null) &&
//									 (disconnectRequest.getProductServiceType().contains(EnterpriseWLNSalesServicesConstants.SING)) ) {
//									disconnectRequestForSING = true;
//								}
//
//								if ( (disconnectRequest.getOriginalCarrierInfo() != null) &&
//									 (disconnectRequest.getOriginalCarrierInfo().getProviderId() != null) &&
//									 (!Constants.SHAW_PROVIDER_ID.equals(disconnectRequest.getOriginalCarrierInfo().getProviderId())) ) {
//									nonShawDisconnectRequest = true;
//								}
//							}
//						}
//					}
//				}
//
//				if ( (temporaryPhoneNumberRequiredInd != null) &&
//					 (disconnectRequestForSING) ) {
//					if (!temporaryPhoneNumberRequiredInd) {
//						comment = createComment("Ready for submission. Complete the check out.", Constants.NOTE_TYPE_CODE_CLEC_COMMENT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, EnterpriseWLNSalesServicesConstants.SING);
//
//						break;
//					}
//					else {
//						 if (nonShawDisconnectRequest) {
//							 comment = createComment("Ready for submission. Complete the check out.", Constants.NOTE_TYPE_CODE_CLEC_COMMENT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, EnterpriseWLNSalesServicesConstants.SING);
//
//							 break;
//						 }
//					}
//				}
//			}
//		}
//
//		logger.exit(functionName);
//
//		return comment;
//	}

//	private OrderCommentVO createCommentsForCLECDisconnect(List<CartItemVO> cartItemList, String originatorId) {
//		String functionName="createCommentsForCLECDisconnect";	
//		logger.enter(functionName);
//		OrderCommentVO comment = null;
//		String disconnectProductList = "";
//		for (CartItemVO cartItem : cartItemList) {
//			if (cartItem.isDisconnectOrderItem()) {
//				if(cartItem.getDisconnectRequestList() != null) {
//					for(DisconnectRequestTypeVO disconnectRequest: cartItem.getDisconnectRequestList()) {
//						if(disconnectRequest.getProductServiceType() != null) {
//							for(String prodServType: disconnectRequest.getProductServiceType()) {
//								if(!disconnectProductList.contains(prodServType)) {
//									if(!StringUtils.isEmpty(disconnectProductList)) {
//										disconnectProductList += ",";
//									}
//									disconnectProductList += prodServType;
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		if(!StringUtils.isEmpty(disconnectProductList)) {
//			comment = createComment("Assistance required to send CLEC disconnect order for " + disconnectProductList + ".", Constants.NOTE_TYPE_CODE_CLEC_COMMENT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId);
//		}
//		logger.exit(functionName);
//		return comment;
//	}

	private List<OrderCommentVO> createCommentsForGWPOrderWhichIsNOtEligible(ShoppingCartContextVO shoppingCartContextVO, String originatorId) {
		String functionName="createCommentsForGWPOrderWhichIsNOtEligible";	
		logger.enter(functionName);

		List<OrderCommentVO> comments = new ArrayList<OrderCommentVO>();

		if ( (shoppingCartContextVO.getGetCreditProfileByCustomerIdAdapterResponse() != null) &&
			 (shoppingCartContextVO.getGetCreditProfileByCustomerIdAdapterResponse().getCreditProfile() != null) &&
			 (shoppingCartContextVO.getGetCreditProfileByCustomerIdAdapterResponse().getCreditProfile().getCreditWorthiness() != null) &&
			 (shoppingCartContextVO.getGetCreditProfileByCustomerIdAdapterResponse().getCreditProfile().getCreditWorthiness().getProductCategoryQualification() != null) &&
			 (!shoppingCartContextVO.getGetCreditProfileByCustomerIdAdapterResponse().getCreditProfile().getCreditWorthiness().getProductCategoryQualification().isBoltOnInd()) &&
			 (shoppingCartContextVO.getShoppingCartVO() != null) &&
			 (shoppingCartContextVO.getShoppingCartVO().getCartItemList() != null) &&
			 (!shoppingCartContextVO.getShoppingCartVO().getCartItemList().isEmpty()) ) {
			for (CartItemVO cartItem : shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
				if ( (cartItem != null) &&
					 (cartItem.isGwpOrderItem()) &&
					 (cartItem.getProducts() != null) ) {
					if (cartItem.getProducts().getInternetProduct() != null) {
						comments.add(createComment("Please try to fulfill request. Customer received order confirmation with GWP. Contact with any issues.", Constants.NOTE_TYPE_CODE_FREE_FORMAT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, Constants.HSIC));
					}

					if (cartItem.getProducts().getTelevisionProduct() != null) {
						comments.add(createComment("Please try to fulfill request. Customer received order confirmation with GWP. Contact with any issues.", Constants.NOTE_TYPE_CODE_FREE_FORMAT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, Constants.TTV));
					}

					if (cartItem.getProducts().getHomePhoneProduct() != null) {
						comments.add(createComment("Please try to fulfill request. Customer received order confirmation with GWP. Contact with any issues.", Constants.NOTE_TYPE_CODE_FREE_FORMAT, Constants.ORDER_ENTITY_TYPE_CODE_OA, originatorId, EnterpriseWLNSalesServicesConstants.SING));
					}

					break;
				}
			}
		}

		if (comments.isEmpty()) {
			comments = null;
		}

		logger.exit(functionName);

		return comments;
	}
}