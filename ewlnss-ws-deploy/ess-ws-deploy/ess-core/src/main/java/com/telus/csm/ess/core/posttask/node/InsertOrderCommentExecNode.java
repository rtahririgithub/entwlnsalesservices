package com.telus.csm.ess.core.posttask.node;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class InsertOrderCommentExecNode extends AbstractNode<Boolean> {
	
	private ShoppingCartContextVO shoppingCartContextVO;
	private String comment;
	private String commentType;
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	@Autowired
	private IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;

	public InsertOrderCommentExecNode(ShoppingCartContextVO shoppingCartContextVO, String comment, String commentType) {
		super("InsertOrderCommentExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.comment = comment;
		this.commentType = commentType;
	}

	@Override
	public void run() {
		try {
			String orderId = shoppingCartContextVO.getOrderId();
			if(StringUtils.isEmpty(orderId)) {
				throw new Exception("Vesta orderID is missing.");
			}
			wirelineSalesEJBAdapter.insertComment(orderId, comment, commentType);
			setResult(Boolean.TRUE);
		} catch (Exception e) {
			String msg = "Failed to insert comment :" + comment + " : " + "of type : " + commentType + "for order : [" + shoppingCartContextVO.getOrderId();
			logger.error("", "run", msg, e);
			handleException(e, msg);
			setResult(Boolean.FALSE);
		}
		
	}

}
