package com.telus.csm.ess.core.posttask.node;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.ucss.wirelinesales.domain.Order;

public class AddNewVestaToSfdcStgExcecNode extends AbstractNode<Boolean> {
	
	private ShoppingCartContextVO shoppingCartContextVO;
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	@Autowired
	private IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;
	
	public AddNewVestaToSfdcStgExcecNode(ShoppingCartContextVO shoppingCartContextVO) {
		super("AddNewVestaToSfdcStgExcecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
	}

	@Override
	public void run() {
		try {
			String orderId = shoppingCartContextVO.getOrderId();
			if(StringUtils.isEmpty(orderId)) {
				throw new Exception("Vesta orderID is missing.");
			}
			Order order = wirelineSalesEJBAdapter.getOrderByTransactionId(orderId);
			if (order != null) {
				boolean result = wirelineSalesEJBAdapter.addNewVestaToSfdcStg(order.getOrderId());
				String msg = "Succesful call to addNewVestaToSfdcStg for order :"  + shoppingCartContextVO.getOrderId()+ " result "+ result;
				logger.error("", "AddNewVestaToSfdcStgExcecNode.run", msg);
			}
			setResult(Boolean.TRUE);
		} catch (Exception e) {
			String msg = "Failed to addNewVestaToSfdcStg for order :"  + shoppingCartContextVO.getOrderId();
			logger.error("", "run", msg, e);
			handleException(e, msg);
			setResult(Boolean.FALSE);
		}
	}

}
