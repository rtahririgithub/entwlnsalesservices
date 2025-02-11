package com.telus.csm.ewlnsc.rules.base;


import java.util.List;


public class NotSpecification<T,S> extends AbstractSpecification<T,S> {

	private Specification<T,S> specification;

	public NotSpecification(final Specification<T,S> specification) {
		this.specification = specification;
	}

	public boolean isSatisfiedBy(final T t, List<S> traces) {
		return !specification.isSatisfiedBy(t, traces);
	}
}
