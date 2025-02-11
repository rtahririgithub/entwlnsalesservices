package com.telus.csm.ess.core.operation;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ess.core.delegate.SubmitWirelineSalesDelegate;
import com.telus.csm.ess.core.domain.UpdateSaleCoreResponse;
import com.telus.csm.ewlnsc.domain.SubmitWirelineOrderRequestVO;
import com.telus.csm.ewlnsc.domain.SubmitWirelineOrderResponseVO;
import com.telus.csm.ewlnsc.domain.UpdateSaleResponseVO;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.util.LoggerUtil;

@Component
@Scope(SCOPE_PROTOTYPE)
public class UpdateSaleCoreOperation {

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(UpdateSaleCoreOperation.class);

	@Autowired
	SubmitWirelineSalesDelegate submitWirelineSalesDelegate;

	public UpdateSaleCoreResponse execute(String saleId, Boolean completeSaleInd) {
		
		UpdateSaleCoreResponse result = new UpdateSaleCoreResponse();
		
		if (Boolean.TRUE.equals(completeSaleInd)) {
			final UpdateSaleResponseVO updateSaleResponseVO = submitWirelineSalesDelegate.executeUpate(saleId);
			result.setShoppingCartId(updateSaleResponseVO.getShoppingCartId());
			
			if (!updateSaleResponseVO.isSuccess()) {
				result.setHasError(true);
				result.setMessageList(EnterpriseWLNCommonTransformer.transformToMessageListVO(updateSaleResponseVO.getErrorCode(), updateSaleResponseVO.getErrorMessage()));
			}
		}
		
		return result;
	}

}
