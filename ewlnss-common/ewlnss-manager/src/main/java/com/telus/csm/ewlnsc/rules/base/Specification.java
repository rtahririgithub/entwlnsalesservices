package com.telus.csm.ewlnsc.rules.base;

import java.util.List;


public interface Specification <T, S> {

	boolean isSatisfiedBy(T o, List<S> traces);

	Specification<T,S> and(Specification<T,S> specification);

	Specification<T,S> bitwiseAnd(Specification<T,S> specification);

	Specification<T,S> or(Specification<T,S> specification);

	Specification<T,S> not(Specification<T,S> specification);
	
	public List<S> execute(T o);
}