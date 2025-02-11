package com.telus.csm.ess.core.posttask.node;

import org.springframework.beans.factory.annotation.Autowired;

import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.ucss.wirelinesales.exception.WLNSalesApplicationException;

public class InsertVestaCommentExecNode extends AbstractNode<Boolean> {
	
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	@Autowired
	private IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;
	private String vestaOrderId;
	private String newComment;
	private String commentType;

	public InsertVestaCommentExecNode(String vestaOrderId,String newComment,String commentType) {
		super("InsertVestaCommentExecNode");
		this.vestaOrderId = vestaOrderId;
		this.newComment = newComment;
		this.commentType = commentType;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Inserting comment with details: vestaOrderId=" + vestaOrderId + ", newComment="+newComment + ", commentType=" + commentType);
			wirelineSalesEJBAdapter.insertComment(vestaOrderId, newComment, commentType);
		} catch (WLNSalesApplicationException e) {
			logger.error("Exeception", "execute", "Exception from wirelineSalesEJBAdapter.insertComment", e);
			String msg = "Failed to Insert Comment to Vesta for order [" + vestaOrderId + "]";
			logger.error("", "run", msg, e);
			handleException(e, msg);
			setResult(Boolean.FALSE);
		}

	}

}
