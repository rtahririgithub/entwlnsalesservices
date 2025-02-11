package com.telus.csm.ewlnsc.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;
public class GetPendingOrdersTask extends TaskBase{
              
              private long customerId;
              private long sessionId;
              private Map<String, ArrayList<SubscribedProductSummaryVO>> response = new HashMap<String, ArrayList<SubscribedProductSummaryVO>>();

              private Map<String, ArrayList<SubscribedProductSummaryVO>> summaryResponse = new HashMap<String, ArrayList<SubscribedProductSummaryVO>>();
              private IWirelineSalesEJBAdapter vestaEjbAdapter;
              
              List<Throwable> errors = new ArrayList<Throwable>();
              
              public List<Throwable> getErrors() {
				return errors;
			}

			public Map<String, ArrayList<SubscribedProductSummaryVO>> getSummaryResponse() {
                             return summaryResponse;
              }

              public void setSummaryResponse(
                                           Map<String, ArrayList<SubscribedProductSummaryVO>> summaryResponse) {
                             this.summaryResponse = summaryResponse;
              }

              public Map<String, ArrayList<SubscribedProductSummaryVO>> getResponse() {
                             return response;
              }

              public void setResponse(Map<String, ArrayList<SubscribedProductSummaryVO>> response) {
                             this.response = response;
              }

              public GetPendingOrdersTask(long customerId, long sessionId, IWirelineSalesEJBAdapter vestaEjbAdapter) {
                             super();
                             this.customerId = customerId;
                             this.sessionId = sessionId;
                             this.vestaEjbAdapter = vestaEjbAdapter;
              }
                             
              
              @Override
              protected void execute() {
                             
                             String methodName = "GetPendingOrdersTask.run()";
                             LoggerUtil.getLogger(this).info(methodName, "Start");
                             
                             try {
                                           
                                           //response.setServiceCallResponse( wirelineSalesOrderManagerAdapter.getPendingOrdersByCustomerId( customerId, sessionId ) );
                                           //this is to get the all the vesta pending order by this customer
                                           Map<String, ArrayList<SubscribedProductSummaryVO>> subscribedProductSummaryListMap = vestaEjbAdapter.getPendingOrdersByCustomerId( customerId, sessionId );
                                           //RTCA 1.5
                                           Map<String, ArrayList<SubscribedProductSummaryVO>> summarySubcribedProductArrayMap = new HashMap<String, ArrayList<SubscribedProductSummaryVO>>();
                                           
                                           if( subscribedProductSummaryListMap != null ) {
                                                          LoggerUtil.getLogger(this).info(methodName, "subscribedProductSummaryListMap is not null");
                                                          for( Iterator<ArrayList<SubscribedProductSummaryVO>> i = subscribedProductSummaryListMap.values().iterator(); i.hasNext(); ) {
                                                                        ArrayList<SubscribedProductSummaryVO> spsList = i.next();
                                                                        if(spsList.size()>0){
                                                                                      LoggerUtil.getLogger(this).debug(methodName, "returned subscribedProductSummary List is not empty.");
                                                                                      for(SubscribedProductSummaryVO sps : spsList){
                                                                                                    if(sps.getProductInstance()!=null){
                                                                                                     LoggerUtil.getLogger(this).debug(methodName,"subscribedProductSummary.productInstance id is "+ sps.getProductInstance().getProductInstanceId());
                                                                                                                   sps.setProductInPendingOrderStatusInVestaInd(true); 
                                                                                                                   
                                                                                                                   //in pending order we set product in pending status.
                                                                                                                   if(sps.getProductInstance().getDepositAmmount()!=null && sps.getProductInstance().getDepositAmmount()>=0){
                                                                                                                                 sps.getProductInstance().setIncludedForDepositCalculationInd(true);
                                                                                                                   }else{
                                                                                                                                 sps.getProductInstance().setIncludedForDepositCalculationInd(false);;
                                                                                                                   }
                                                                                                                   //borrowed sps attribute for account number storage
                                                                                                                   String accountNumber  = sps.getOneTimePayChannelNumber();
                                                                                                                   if(summarySubcribedProductArrayMap.get(accountNumber)!=null && !summarySubcribedProductArrayMap.get(accountNumber).isEmpty()){
                                                                                                                                 LoggerUtil.getLogger(this).info(methodName, "account number is "+accountNumber);
                                                                                                                                 summarySubcribedProductArrayMap.get(accountNumber).add(sps);
                                                                                                                                 sps.setOneTimePayChannelNumber(null);
                                                                                                                   }else{
                                                                                                                                 ArrayList<SubscribedProductSummaryVO> spsumaryList = new ArrayList<SubscribedProductSummaryVO>();
                                                                                                                                 spsumaryList.add(sps);
                                                                                                                                 sps.setOneTimePayChannelNumber(null);
                                                                                                                                 summarySubcribedProductArrayMap.put(accountNumber, spsumaryList);
                                                                                                                   }
                                                                                                     }
                                                                                      }
                                                                        }
                                                          }
                                           }
                                           
                                       //response.setServiceCallResponse(subscribedProductSummaryListMap);
                                           //response.setServiceCallSuccessFlag( true );
                                           response = subscribedProductSummaryListMap;
                                           
                                           summaryResponse = summarySubcribedProductArrayMap;
                                           
                                           //RTCA 1.5
                             //summaryResponse.setServiceCallResponse(summarySubcribedProductArrayMap);
                                           //summaryResponse.setServiceCallSuccessFlag(true);
                             } catch (Exception e) {
                                           LoggerUtil.getLogger(this).info(methodName, e.getMessage());
                                           errors.add(e);
                             } finally {
                                           LoggerUtil.getLogger(this).info(methodName, "finished");
                             }

                             
              }

}

