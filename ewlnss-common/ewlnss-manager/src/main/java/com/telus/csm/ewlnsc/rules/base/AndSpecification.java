package com.telus.csm.ewlnsc.rules.base;


import java.util.List;


public class AndSpecification<T,S> extends AbstractSpecification<T,S> {

	private Specification<T,S> specification1;
	private Specification<T,S> specification2;

	public AndSpecification(final Specification<T,S> specification1, final Specification<T,S> specification2) {
		this.specification1 = specification1;
		this.specification2 = specification2;
	}

	public boolean isSatisfiedBy(final T t, List<S> traces) {
		return specification1.isSatisfiedBy(t, traces) && specification2.isSatisfiedBy(t, traces);
	}
}
