package com.telus.csm.ewlnsc.rules.base;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.TraceVO;

public  class AbstractShoppingCartSpecification <T,S> extends AbstractSpecification<T,S> {
	
	protected List<S> result = new ArrayList<S>();
	
	boolean isConcurent = false;
	
	

	public AbstractShoppingCartSpecification() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSatisfiedBy(T o, List<S> resultList) {
		if(!isConcurent) {
			result = execute(o);
		}
		
		resultList.addAll(result);
		
		boolean isValidationPassed = true;
		
		for(S t : result) {
			if(((TraceVO) t).isValidationPassedInd() == false) {
				isValidationPassed = false;
				break;
			}
		}
		return isValidationPassed;
	}

	@Override
	public  List<S> execute(T o){
		return new ArrayList<S>();
	}

	public List<S> getResult() {
		return result;
	}

	public boolean isConcurent() {
		return isConcurent;
	}

	public void setConcurent(boolean isConcurent) {
		this.isConcurent = isConcurent;
	}
}
