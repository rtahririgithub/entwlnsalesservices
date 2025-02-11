package com.telus.csm.ess.core.posttask.node;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class UpdateDepositForInvoiceNumberExecNode extends AbstractNode<Boolean> {
	
	private ShoppingCartContextVO shoppingCartContextVO;
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	@Autowired
	private IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;
	
	public UpdateDepositForInvoiceNumberExecNode(ShoppingCartContextVO shoppingCartContextVO) {
		super("UpdateDepositForInvoiceNumberExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
	}

	@Override
	public void run() {
		try {
			String orderId = shoppingCartContextVO.getOrderId();
			String invoiceNumber = null;
			Double depositAmount = (double) 0;
			
			if(StringUtils.isEmpty(orderId)) {
				throw new Exception("Vesta orderID is missing.");
			}
			if (shoppingCartContextVO.getCreateInvoiceAdapterResponse() != null) {
				invoiceNumber = shoppingCartContextVO.getCreateInvoiceAdapterResponse().getInvoiceNo();
			}
			if (shoppingCartContextVO.getGetDepositInfoResponse() != null) {
				depositAmount = shoppingCartContextVO.getGetDepositInfoResponse().getTotalRequiredDeposit();
			}			
			
			if (!StringUtils.isEmpty(invoiceNumber)) {
				wirelineSalesEJBAdapter.updateOrderDepositInvoiceNumber(orderId, depositAmount, invoiceNumber);
				String msg = "update invoice number :" + invoiceNumber + "succesful for order :"  + shoppingCartContextVO.getOrderId();
				logger.info("run", msg);
				setResult(Boolean.TRUE);
			} else {
				String msg = "no invoice number..update invoice number for deposit skipped for order :"  + shoppingCartContextVO.getOrderId();
				logger.info("run", msg);
			}
		} catch (Exception e) {
			String msg = "Failed to update invoice number for deposit for order :"  + shoppingCartContextVO.getOrderId();
			logger.error("", "run", msg, e);
			handleException(e, msg);
			setResult(Boolean.FALSE);
		}
	}

}
