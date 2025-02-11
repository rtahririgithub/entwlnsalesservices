package com.telus.csm.ess.core.posttask.workflow;

import com.telus.csm.ess.core.posttask.node.AbstractNode;

public class NodeWrapper {
	public static enum TYPE {SYNC, ASYNC};
	
	private AbstractNode<?> node;
	private TYPE type;
	
	public NodeWrapper(AbstractNode<?> node, TYPE type) {
		this.node = node;
		this.type = type;
	}

	public boolean isAsync() {
		return TYPE.ASYNC.equals(type);
	}

	public AbstractNode<?> getNode() {
		return node;
	}
	
}
