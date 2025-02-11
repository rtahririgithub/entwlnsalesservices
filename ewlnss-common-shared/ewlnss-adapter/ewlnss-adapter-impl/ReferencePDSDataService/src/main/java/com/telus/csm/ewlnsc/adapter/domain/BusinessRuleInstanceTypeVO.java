package com.telus.csm.ewlnsc.adapter.domain;

import java.util.List;

public class BusinessRuleInstanceTypeVO {

	protected List<CodeValueTypeVO> input;
	protected List<CodeValueTypeVO> output;

	public List<CodeValueTypeVO> getInput() {
		return input;
	}

	public void setInput(List<CodeValueTypeVO> input) {
		this.input = input;
	}

	public List<CodeValueTypeVO> getOutput() {
		return output;
	}

	public void setOutput(List<CodeValueTypeVO> output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "BusinessRuleInstanceTypeVO [input=" + input + ", output=" + output + "]";
	}
	
	

}
