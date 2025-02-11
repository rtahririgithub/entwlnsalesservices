package com.telus.csm.ewlnsc.rules.base;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.TraceVO;


public class AbstractSpecification <T,S> implements Specification<T,S> {
	

	boolean isConcurent = false;
	protected List<S> result = new ArrayList<S>();
	protected T parameter;
	
	public T getParameter() {
		return parameter;
	}

	public void setParameter(T parameter) {
		this.parameter = parameter;
	}

	public Specification<T,S> and(final Specification<T,S> specification) {
		return new AndSpecification<T,S>(this, specification);
	}

	public Specification<T,S> bitwiseAnd(final Specification<T,S> specification) {
		return new BitwiseAndSpecification<T,S>(this, specification);
	}
	
	public Specification<T,S> or(final Specification<T,S> specification) {
		return new OrSpecification<T,S>(this, specification);
	}

	public Specification<T,S> not(final Specification<T,S> specification) {
		return new NotSpecification<T,S>(specification);
	}

	
	@Override
	public boolean isSatisfiedBy(T o, List<S> traces) {
		result=traces;
		return true;
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

	public void addTrace(List<S> resultList){
		this.result.addAll(resultList);
	}
	

}
