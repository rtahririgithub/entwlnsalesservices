package com.telus.csm.ess.order.rest.operation;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ess.rest.domain.SubmitSalesToPOSRequest;
import com.telus.csm.ess.rest.domain.SubmitSalesToPOSResponse;
import com.telus.csm.ewlnsc.delegate.SubmitSalesToPOSDelegate;

@Component
@Scope(SCOPE_PROTOTYPE)
public class SubmitSalesToPOSRestOperation {

	@Autowired
	SubmitSalesToPOSDelegate submitSalesToPOSDelegate;
	
	public SubmitSalesToPOSResponse execute(final SubmitSalesToPOSRequest param) {
		
		final SubmitSalesToPOSResponse submitOrderResponseVO = submitSalesToPOSDelegate.execute(param);
		
		return submitOrderResponseVO;
	}
	


}
