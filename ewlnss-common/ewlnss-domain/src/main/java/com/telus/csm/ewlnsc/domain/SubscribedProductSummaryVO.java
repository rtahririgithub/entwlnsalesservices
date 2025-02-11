
package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

public class SubscribedProductSummaryVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected String productLine;
    protected String productType;
    protected String serviceType;
    protected String recurringPayChannelNumber;
    protected String oneTimePayChannelNumber;
    protected SubscribedProductSummaryVO.ProductInstance productInstance;
    protected String subscriptionStatus;
    protected Boolean productInPendingOrderStatusInVestaInd;
    protected Boolean productInPendingOrderStatusInOMSInd;
    protected AddressVO serviceAddress;
    protected SubscribedProductSummaryVO.ServicePhoneNumberList servicePhoneNumberList;
    protected Long productRanking;

    private String billingAccountNumber;
    private List<String> pricePlanIdList = new ArrayList<String>();

    public Long getProductRanking() {
		return productRanking;
	}

	public void setProductRanking(Long productRanking) {
		this.productRanking = productRanking;
	}

	/**
     * Gets the value of the productLine property.
     * 
     * @return
     *     possible object is
     *     {@link SalesProductLineVO }
     *     
     */
    public String getProductLine() {
        return productLine;
    }

    /**
     * Sets the value of the productLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesProductLineVO }
     *     
     */
    public void setProductLine(String value) {
        this.productLine = value;
    }

    /**
     * Gets the value of the productType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets the value of the productType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductType(String value) {
        this.productType = value;
    }

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceType(String value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the recurringPayChannelNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecurringPayChannelNumber() {
        return recurringPayChannelNumber;
    }

    /**
     * Sets the value of the recurringPayChannelNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecurringPayChannelNumber(String value) {
        this.recurringPayChannelNumber = value;
    }

    /**
     * Gets the value of the oneTimePayChannelNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOneTimePayChannelNumber() {
        return oneTimePayChannelNumber;
    }

    /**
     * Sets the value of the oneTimePayChannelNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOneTimePayChannelNumber(String value) {
        this.oneTimePayChannelNumber = value;
    }

    /**
     * Gets the value of the productInstance property.
     * 
     * @return
     *     possible object is
     *     {@link SubscribedProductSummaryVO.ProductInstance }
     *     
     */
    public SubscribedProductSummaryVO.ProductInstance getProductInstance() {
        return productInstance;
    }

    /**
     * Sets the value of the productInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscribedProductSummaryVO.ProductInstance }
     *     
     */
    public void setProductInstance(SubscribedProductSummaryVO.ProductInstance value) {
        this.productInstance = value;
    }

    /**
     * Gets the value of the subscriptionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    /**
     * Sets the value of the subscriptionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionStatus(String value) {
        this.subscriptionStatus = value;
    }

    /**
     * Gets the value of the productInPendingOrderStatusInVestaInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProductInPendingOrderStatusInVestaInd() {
    	if (productInPendingOrderStatusInVestaInd == null) return false;
        return productInPendingOrderStatusInVestaInd;
    }
            public Boolean getProductInPendingOrderStatusInVestaInd() {
        return productInPendingOrderStatusInVestaInd;
    }

    /**
     * Sets the value of the productInPendingOrderStatusInVestaInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProductInPendingOrderStatusInVestaInd(Boolean value) {
        this.productInPendingOrderStatusInVestaInd = value;
    }

    /**
     * Gets the value of the productInPendingOrderStatusInOMSInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProductInPendingOrderStatusInOMSInd() {
        if (productInPendingOrderStatusInOMSInd == null) return false;
    	return productInPendingOrderStatusInOMSInd;
    }
    public Boolean getProductInPendingOrderStatusInOMSInd() {
        return productInPendingOrderStatusInOMSInd;
    }

    /**
     * Sets the value of the productInPendingOrderStatusInOMSInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProductInPendingOrderStatusInOMSInd(Boolean value) {
        this.productInPendingOrderStatusInOMSInd = value;
    }

    /**
     * Gets the value of the serviceAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressVO }
     *     
     */
    public AddressVO getServiceAddress() {
        return serviceAddress;
    }

    /**
     * Sets the value of the serviceAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressVO }
     *     
     */
    public void setServiceAddress(AddressVO value) {
        this.serviceAddress = value;
    }

    /**
     * Gets the value of the servicePhoneNumberList property.
     * 
     * @return
     *     possible object is
     *     {@link SubscribedProductSummaryVO.ServicePhoneNumberList }
     *     
     */
    public SubscribedProductSummaryVO.ServicePhoneNumberList getServicePhoneNumberList() {
        return servicePhoneNumberList;
    }

    /**
     * Sets the value of the servicePhoneNumberList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscribedProductSummaryVO.ServicePhoneNumberList }
     *     
     */
    public void setServicePhoneNumberList(SubscribedProductSummaryVO.ServicePhoneNumberList value) {
        this.servicePhoneNumberList = value;
    }


    public static class ProductInstance implements Serializable{

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		protected String productInstanceId;
        protected String serviceId;
        protected String serviceInstanceId;
        protected String productName;
        protected Long depositAmmount;
        protected String resourceId;
        protected String productCatalogId;
        protected Boolean productSuppressionInd;
        protected Boolean includedForDepositCalculationInd;
        protected String termCd;
        protected String offerCatalogId;
        protected SubscribedProductSummaryVO.ProductInstance.SingleLineComponent singleLineComponent;
        protected SubscribedProductSummaryVO.ProductInstance.InternetComponent internetComponent;
        protected SubscribedProductSummaryVO.ProductInstance.TtvComponent ttvComponent;
        protected SubscribedProductSummaryVO.ProductInstance.EquipmentList equipmentList;
        protected XMLGregorianCalendar commitmentPeriodStartDate;
        //2018 April Exception
        protected String refServiceId;
        
		public String getRefServiceId() {
			return refServiceId;
		}

		public void setRefServiceId(String refServiceId) {
			this.refServiceId = refServiceId;
		}

		public XMLGregorianCalendar getCommitmentPeriodStartDate() {
			return commitmentPeriodStartDate;
		}

		public void setCommitmentPeriodStartDate(
				XMLGregorianCalendar commitmentPeriodStartDate) {
			this.commitmentPeriodStartDate = commitmentPeriodStartDate;
		}

		/**
         * Gets the value of the productInstanceId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductInstanceId() {
            return productInstanceId;
        }

        /**
         * Sets the value of the productInstanceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductInstanceId(String value) {
            this.productInstanceId = value;
        }

        /**
         * Gets the value of the serviceId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getServiceId() {
            return serviceId;
        }

        /**
         * Sets the value of the serviceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setServiceId(String value) {
            this.serviceId = value;
        }

        /**
         * Gets the value of the serviceInstanceId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getServiceInstanceId() {
            return serviceInstanceId;
        }

        /**
         * Sets the value of the serviceInstanceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setServiceInstanceId(String value) {
            this.serviceInstanceId = value;
        }

        /**
         * Gets the value of the productName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductName() {
            return productName;
        }

        /**
         * Sets the value of the productName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductName(String value) {
            this.productName = value;
        }

        /**
         * Gets the value of the depositAmmount property.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getDepositAmmount() {
            return depositAmmount;
        }

        /**
         * Sets the value of the depositAmmount property.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setDepositAmmount(Long value) {
            this.depositAmmount = value;
        }

        /**
         * Gets the value of the resourceId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResourceId() {
            return resourceId;
        }

        /**
         * Sets the value of the resourceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResourceId(String value) {
            this.resourceId = value;
        }

        /**
         * Gets the value of the productCatalogId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductCatalogId() {
            return productCatalogId;
        }

        /**
         * Sets the value of the productCatalogId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductCatalogId(String value) {
            this.productCatalogId = value;
        }

        /**
         * Gets the value of the productSuppressionInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isProductSuppressionInd() {
            return productSuppressionInd;
        }
            public Boolean getProductSuppressionInd() {
            return productSuppressionInd;
        }

        /**
         * Sets the value of the productSuppressionInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setProductSuppressionInd(Boolean value) {
            this.productSuppressionInd = value;
        }

        /**
         * Gets the value of the includedForDepositCalculationInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isIncludedForDepositCalculationInd() {
            return includedForDepositCalculationInd;
        }
            public Boolean getIncludedForDepositCalculationInd() {
            return includedForDepositCalculationInd;
        }

        /**
         * Sets the value of the includedForDepositCalculationInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIncludedForDepositCalculationInd(Boolean value) {
            this.includedForDepositCalculationInd = value;
        }

        /**
         * Gets the value of the termCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTermCd() {
            return termCd;
        }

        /**
         * Sets the value of the termCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTermCd(String value) {
            this.termCd = value;
        }

        /**
         * Gets the value of the offerCatalogId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOfferCatalogId() {
            return offerCatalogId;
        }

        /**
         * Sets the value of the offerCatalogId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOfferCatalogId(String value) {
            this.offerCatalogId = value;
        }

        /**
         * Gets the value of the singleLineComponent property.
         * 
         * @return
         *     possible object is
         *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent }
         *     
         */
        public SubscribedProductSummaryVO.ProductInstance.SingleLineComponent getSingleLineComponent() {
            return singleLineComponent;
        }

        /**
         * Sets the value of the singleLineComponent property.
         * 
         * @param value
         *     allowed object is
         *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent }
         *     
         */
        public void setSingleLineComponent(SubscribedProductSummaryVO.ProductInstance.SingleLineComponent value) {
            this.singleLineComponent = value;
        }

        /**
         * Gets the value of the internetComponent property.
         * 
         * @return
         *     possible object is
         *     {@link SubscribedProductSummaryVO.ProductInstance.InternetComponent }
         *     
         */
        public SubscribedProductSummaryVO.ProductInstance.InternetComponent getInternetComponent() {
            return internetComponent;
        }

        /**
         * Sets the value of the internetComponent property.
         * 
         * @param value
         *     allowed object is
         *     {@link SubscribedProductSummaryVO.ProductInstance.InternetComponent }
         *     
         */
        public void setInternetComponent(SubscribedProductSummaryVO.ProductInstance.InternetComponent value) {
            this.internetComponent = value;
        }

        /**
         * Gets the value of the ttvComponent property.
         * 
         * @return
         *     possible object is
         *     {@link SubscribedProductSummaryVO.ProductInstance.TtvComponent }
         *     
         */
        public SubscribedProductSummaryVO.ProductInstance.TtvComponent getTtvComponent() {
            return ttvComponent;
        }

        /**
         * Sets the value of the ttvComponent property.
         * 
         * @param value
         *     allowed object is
         *     {@link SubscribedProductSummaryVO.ProductInstance.TtvComponent }
         *     
         */
        public void setTtvComponent(SubscribedProductSummaryVO.ProductInstance.TtvComponent value) {
            this.ttvComponent = value;
        }

        /**
         * Gets the value of the equipmentList property.
         * 
         * @return
         *     possible object is
         *     {@link SubscribedProductSummaryVO.ProductInstance.EquipmentList }
         *     
         */
        public SubscribedProductSummaryVO.ProductInstance.EquipmentList getEquipmentList() {
            return equipmentList;
        }

        /**
         * Sets the value of the equipmentList property.
         * 
         * @param value
         *     allowed object is
         *     {@link SubscribedProductSummaryVO.ProductInstance.EquipmentList }
         *     
         */
        public void setEquipmentList(SubscribedProductSummaryVO.ProductInstance.EquipmentList value) {
            this.equipmentList = value;
        }


        public static class EquipmentList implements Serializable{

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			protected List<SubscribedProductSummaryVO.ProductInstance.EquipmentList.Equipment> equipment;

            /**
             * Gets the value of the equipment property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the equipment property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getEquipment().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link SubscribedProductSummaryVO.ProductInstance.EquipmentList.Equipment }
             * 
             * 
             */
            public List<SubscribedProductSummaryVO.ProductInstance.EquipmentList.Equipment> getEquipment() {
                if (equipment == null) {
                    equipment = new ArrayList<SubscribedProductSummaryVO.ProductInstance.EquipmentList.Equipment>();
                }
                return this.equipment;
            }

            /**
             * Sets the value of the equipment property.
             * 
             * @param equipment
             *     allowed object is
             *     {@link SubscribedProductSummaryVO.ProductInstance.EquipmentList.Equipment }
             *     
             */
            public void setEquipment(List<SubscribedProductSummaryVO.ProductInstance.EquipmentList.Equipment> equipment) {
                this.equipment = equipment;
            }


            public static class Equipment implements Serializable{

                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected String equipmentName;
                protected String equipmentAcquisitionType;
                protected String equipmentMaterialItemCode;

                public String getEquipmentMaterialItemCode() {
					return equipmentMaterialItemCode;
				}

				public void setEquipmentMaterialItemCode(String equipmentMaterialItemCode) {
					this.equipmentMaterialItemCode = equipmentMaterialItemCode;
				}

				/**
                 * Gets the value of the equipmentName property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getEquipmentName() {
                    return equipmentName;
                }

                /**
                 * Sets the value of the equipmentName property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setEquipmentName(String value) {
                    this.equipmentName = value;
                }

                /**
                 * Gets the value of the equipmentAcquisitionType property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getEquipmentAcquisitionType() {
                    return equipmentAcquisitionType;
                }

                /**
                 * Sets the value of the equipmentAcquisitionType property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setEquipmentAcquisitionType(String value) {
                    this.equipmentAcquisitionType = value;
                }

            }

        }


        public static class InternetComponent implements Serializable{

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			protected String internetTypeCd;
            protected SubscribedProductSummaryVO.ProductInstance.InternetComponent.PromotionList promotionList;


			public SubscribedProductSummaryVO.ProductInstance.InternetComponent.PromotionList getPromotionList() {
				return promotionList;
			}

			public void setPromotionList(
					SubscribedProductSummaryVO.ProductInstance.InternetComponent.PromotionList promotionList) {
				this.promotionList = promotionList;
			}

			/**
             * Gets the value of the internetTypeCd property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInternetTypeCd() {
                return internetTypeCd;
            }

            /**
             * Sets the value of the internetTypeCd property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInternetTypeCd(String value) {
                this.internetTypeCd = value;
            }
            
            public static class PromotionList implements Serializable
            {

            	/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected List<PromotionVO> promotion;

            	public List<PromotionVO> getPromotion() {
            		if (promotion == null) {
            			promotion = new ArrayList<PromotionVO>();
            		}
            		return this.promotion;
            	}

            	public void setPromotion(List<PromotionVO> promotion) {
            		this.promotion = promotion;
            	}
            }
        }


        public static class SingleLineComponent implements Serializable{

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			protected SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList subscriptionNumberList;
            protected Boolean singleLineNumberListedInd;
            protected SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList callingFeatureList;
            protected SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList tollPlanList;
            protected Boolean wholesaleAdslInd;
            protected SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.FeaturesPackList featuresPackList;
            protected SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.HomePhonePackList homePhonePackList;
            protected SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.PromotionList promotionList;

			public SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.HomePhonePackList getHomePhonePackList() {
				return homePhonePackList;
			}

			public void setHomePhonePackList(
					SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.HomePhonePackList homePhonePackList) {
				this.homePhonePackList = homePhonePackList;
			}

			public SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.PromotionList getPromotionList() {
				return promotionList;
			}

			public void setPromotionList(
					SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.PromotionList promotionList) {
				this.promotionList = promotionList;
			}

			/**
             * Gets the value of the subscriptionNumberList property.
             * 
             * @return
             *     possible object is
             *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList }
             *     
             */
            public SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList getSubscriptionNumberList() {
                return subscriptionNumberList;
            }

            /**
             * Sets the value of the subscriptionNumberList property.
             * 
             * @param value
             *     allowed object is
             *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList }
             *     
             */
            public void setSubscriptionNumberList(SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList value) {
                this.subscriptionNumberList = value;
            }

            /**
             * Gets the value of the singleLineNumberListedInd property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isSingleLineNumberListedInd() {
                return singleLineNumberListedInd;
            }
            public Boolean getSingleLineNumberListedInd() {
                return singleLineNumberListedInd;
            }

            /**
             * Sets the value of the singleLineNumberListedInd property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setSingleLineNumberListedInd(Boolean value) {
                this.singleLineNumberListedInd = value;
            }

            /**
             * Gets the value of the callingFeatureList property.
             * 
             * @return
             *     possible object is
             *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList }
             *     
             */
            public SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList getCallingFeatureList() {
                return callingFeatureList;
            }

            /**
             * Sets the value of the callingFeatureList property.
             * 
             * @param value
             *     allowed object is
             *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList }
             *     
             */
            public void setCallingFeatureList(SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList value) {
                this.callingFeatureList = value;
            }

            /**
             * Gets the value of the tollPlanList property.
             * 
             * @return
             *     possible object is
             *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList }
             *     
             */
            public SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList getTollPlanList() {
                return tollPlanList;
            }

            /**
             * Sets the value of the tollPlanList property.
             * 
             * @param value
             *     allowed object is
             *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList }
             *     
             */
            public void setTollPlanList(SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList value) {
                this.tollPlanList = value;
            }

            /**
             * Gets the value of the wholesaleAdslInd property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isWholesaleAdslInd() {
                return wholesaleAdslInd;
            }
            public Boolean getWholesaleAdslInd() {
                return wholesaleAdslInd;
            }

            /**
             * Sets the value of the wholesaleAdslInd property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setWholesaleAdslInd(Boolean value) {
                this.wholesaleAdslInd = value;
            }

            /**
             * Gets the value of the featuresPackList property.
             * 
             * @return
             *     possible object is
             *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.FeaturesPackList }
             *     
             */
            public SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.FeaturesPackList getFeaturesPackList() {
                return featuresPackList;
            }

            /**
             * Sets the value of the featuresPackList property.
             * 
             * @param value
             *     allowed object is
             *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.FeaturesPackList }
             *     
             */
            public void setFeaturesPackList(SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.FeaturesPackList value) {
                this.featuresPackList = value;
            }

            public static class HomePhonePackList implements Serializable
            {
            	/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected List<String> homePhonePack;

            	public List<String> getHomePhonePack() {
            		if (homePhonePack == null) {
            			homePhonePack = new ArrayList<String>();
            		}
            		return this.homePhonePack;
            	}

            	public void setHomePhonePack(List<String> homePhonePack) {
            		this.homePhonePack = homePhonePack;
            	}
            }
            
            public static class CallingFeatureList implements Serializable{

                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected List<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature> callingFeature;

                /**
                 * Gets the value of the callingFeature property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the callingFeature property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getCallingFeature().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature }
                 * 
                 * 
                 */
                public List<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature> getCallingFeature() {
                    if (callingFeature == null) {
                        callingFeature = new ArrayList<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature>();
                    }
                    return this.callingFeature;
                }

                /**
                 * Sets the value of the callingFeature property.
                 * 
                 * @param callingFeature
                 *     allowed object is
                 *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature }
                 *     
                 */
                public void setCallingFeature(List<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature> callingFeature) {
                    this.callingFeature = callingFeature;
                }


                public static class CallingFeature implements Serializable{

                    /**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					
					protected String callingFeatureName;
                    protected String callingFeatureCode;

                    /**
                     * Gets the value of the callingFeatureName property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCallingFeatureName() {
                        return callingFeatureName;
                    }

                    /**
                     * Sets the value of the callingFeatureName property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCallingFeatureName(String value) {
                        this.callingFeatureName = value;
                    }

                    /**
                     * Gets the value of the callingFeatureCode property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCallingFeatureCode() {
                        return callingFeatureCode;
                    }

                    /**
                     * Sets the value of the callingFeatureCode property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCallingFeatureCode(String value) {
                        this.callingFeatureCode = value;
                    }

                }
                
            }
            
            public static class PromotionList implements Serializable
            {
            	
            	/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected List<PromotionVO> promotion;
            	
            	public List<PromotionVO> getPromotion() {
            		if (promotion == null) {
            			promotion = new ArrayList<PromotionVO>();
            		}
            		return this.promotion;
            	}
            	
            	public void setPromotion(List<PromotionVO> promotion) {
            		this.promotion = promotion;
            	}
            }


            public static class FeaturesPackList implements Serializable{

                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected List<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack> featuresPack;

                /**
                 * Gets the value of the featuresPack property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the featuresPack property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getFeaturesPack().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack }
                 * 
                 * 
                 */
                public List<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack> getFeaturesPack() {
                    if (featuresPack == null) {
                        featuresPack = new ArrayList<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack>();
                    }
                    return this.featuresPack;
                }

                /**
                 * Sets the value of the featuresPack property.
                 * 
                 * @param featuresPack
                 *     allowed object is
                 *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack }
                 *     
                 */
                public void setFeaturesPack(List<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack> featuresPack) {
                    this.featuresPack = featuresPack;
                }


                public static class FeaturesPack implements Serializable{

                    /**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					
					protected String featuresPackName;
                    protected String featuresPackCode;

                    /**
                     * Gets the value of the featuresPackName property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getFeaturesPackName() {
                        return featuresPackName;
                    }

                    /**
                     * Sets the value of the featuresPackName property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setFeaturesPackName(String value) {
                        this.featuresPackName = value;
                    }

                    /**
                     * Gets the value of the featuresPackCode property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getFeaturesPackCode() {
                        return featuresPackCode;
                    }

                    /**
                     * Sets the value of the featuresPackCode property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setFeaturesPackCode(String value) {
                        this.featuresPackCode = value;
                    }

                }

            }


            public static class SubscriptionNumberList implements Serializable{

                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected List<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber> subscriptionNumber;

                /**
                 * Gets the value of the subscriptionNumber property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the subscriptionNumber property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getSubscriptionNumber().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber }
                 * 
                 * 
                 */
                public List<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber> getSubscriptionNumber() {
                    if (subscriptionNumber == null) {
                        subscriptionNumber = new ArrayList<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber>();
                    }
                    return this.subscriptionNumber;
                }

                /**
                 * Sets the value of the subscriptionNumber property.
                 * 
                 * @param subscriptionNumber
                 *     allowed object is
                 *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber }
                 *     
                 */
                public void setSubscriptionNumber(List<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber> subscriptionNumber) {
                    this.subscriptionNumber = subscriptionNumber;
                }


                public static class SubscriptionNumber implements Serializable{

                    /**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					
					protected String code;
                    protected String phoneNumber;

                    /**
                     * Gets the value of the code property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCode() {
                        return code;
                    }

                    /**
                     * Sets the value of the code property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCode(String value) {
                        this.code = value;
                    }

                    /**
                     * Gets the value of the phoneNumber property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPhoneNumber() {
                        return phoneNumber;
                    }

                    /**
                     * Sets the value of the phoneNumber property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPhoneNumber(String value) {
                        this.phoneNumber = value;
                    }

                }

            }


            public static class TollPlanList implements Serializable{

                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected List<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList.TollPlan> tollPlan;

                /**
                 * Gets the value of the tollPlan property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the tollPlan property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getTollPlan().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList.TollPlan }
                 * 
                 * 
                 */
                public List<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList.TollPlan> getTollPlan() {
                    if (tollPlan == null) {
                        tollPlan = new ArrayList<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList.TollPlan>();
                    }
                    return this.tollPlan;
                }

                /**
                 * Sets the value of the tollPlan property.
                 * 
                 * @param tollPlan
                 *     allowed object is
                 *     {@link SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList.TollPlan }
                 *     
                 */
                public void setTollPlan(List<SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList.TollPlan> tollPlan) {
                    this.tollPlan = tollPlan;
                }


                public static class TollPlan implements Serializable{

                    /**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					
					protected String tollCategoryName;
                    protected String tollPlanName;

                    /**
                     * Gets the value of the tollCategoryName property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getTollCategoryName() {
                        return tollCategoryName;
                    }

                    /**
                     * Sets the value of the tollCategoryName property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setTollCategoryName(String value) {
                        this.tollCategoryName = value;
                    }

                    /**
                     * Gets the value of the tollPlanName property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getTollPlanName() {
                        return tollPlanName;
                    }

                    /**
                     * Sets the value of the tollPlanName property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setTollPlanName(String value) {
                        this.tollPlanName = value;
                    }

                }

            }

        }


        public static class TtvComponent implements Serializable{

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			protected Boolean hdChannelInd;
            protected SubscribedProductSummaryVO.ProductInstance.TtvComponent.TvPackList tvPackList;
            protected SubscribedProductSummaryVO.ProductInstance.TtvComponent.TvComboList tvComboList;
            protected SubscribedProductSummaryVO.ProductInstance.TtvComponent.PromotionList promotionList;

			public SubscribedProductSummaryVO.ProductInstance.TtvComponent.TvPackList getTvPackList() {
				return tvPackList;
			}
			public void setTvPackList(
					SubscribedProductSummaryVO.ProductInstance.TtvComponent.TvPackList tvPackList) {
				this.tvPackList = tvPackList;
			}
			public SubscribedProductSummaryVO.ProductInstance.TtvComponent.TvComboList getTvComboList() {
				return tvComboList;
			}
			public void setTvComboList(
					SubscribedProductSummaryVO.ProductInstance.TtvComponent.TvComboList tvComboList) {
				this.tvComboList = tvComboList;
			}
			public SubscribedProductSummaryVO.ProductInstance.TtvComponent.PromotionList getPromotionList() {
				return promotionList;
			}
			public void setPromotionList(
					SubscribedProductSummaryVO.ProductInstance.TtvComponent.PromotionList promotionList) {
				this.promotionList = promotionList;
			}
			/**
             * Gets the value of the hdChannelInd property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isHdChannelInd() {
                return hdChannelInd;
            }
            public Boolean getHdChannelInd() {
                return hdChannelInd;
            }

            /**
             * Sets the value of the hdChannelInd property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setHdChannelInd(Boolean value) {
                this.hdChannelInd = value;
            }

            public static class TvPackList implements Serializable
            {
            	/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected List<WirelineComponentVO> tvPack;
            	
            	public List<WirelineComponentVO> getTvPack() {
            		if (tvPack == null) {
            			tvPack = new ArrayList<WirelineComponentVO>();
            		}
            		return this.tvPack;
            	}
            	
            	public void setTvPack(List<WirelineComponentVO> tvPack) {
            		this.tvPack = tvPack;
            	}
            }
            
            public static class TvComboList implements Serializable
            {
            	/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected List<WirelineComponentVO> tvCombo;
            	
            	public List<WirelineComponentVO> getTvCombo() {
            		if (tvCombo == null) {
            			tvCombo = new ArrayList<WirelineComponentVO>();
            		}
            		return this.tvCombo;
            	}
            	
            	public void setTvCombo(List<WirelineComponentVO> tvCombo) {
            		this.tvCombo = tvCombo;
            	}
            }
            
            public static class PromotionList implements Serializable
            {

            	/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected List<PromotionVO> promotion;

            	public List<PromotionVO> getPromotion() {
            		if (promotion == null) {
            			promotion = new ArrayList<PromotionVO>();
            		}
            		return this.promotion;
            	}

            	public void setPromotion(List<PromotionVO> promotion) {
            		this.promotion = promotion;
            	}
            }
            
        }

    }


    public static class ServicePhoneNumberList implements Serializable{

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		protected List<String> servicePhoneNumber;

        /**
         * Gets the value of the servicePhoneNumber property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the servicePhoneNumber property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServicePhoneNumber().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getServicePhoneNumber() {
            if (servicePhoneNumber == null) {
                servicePhoneNumber = new ArrayList<String>();
            }
            return this.servicePhoneNumber;
        }

        /**
         * Sets the value of the servicePhoneNumber property.
         * 
         * @param servicePhoneNumber
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setServicePhoneNumber(List<String> servicePhoneNumber) {
            this.servicePhoneNumber = servicePhoneNumber;
        }

    }

	public String getBillingAccountNumber() {
		return billingAccountNumber;
	}

	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}

	public List<String> getPricePlanIdList() {
		return pricePlanIdList;
	}

	public void setPricePlanIdList(List<String> pricePlanIdList2) {
		// TODO Auto-generated method stub
		
	}

}
