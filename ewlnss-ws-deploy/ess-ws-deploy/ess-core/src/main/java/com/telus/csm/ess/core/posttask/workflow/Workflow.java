package com.telus.csm.ess.core.posttask.workflow;

import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;

import com.telus.csm.ess.core.posttask.node.AbstractNode;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;

import commonj.work.Work;
import commonj.work.WorkException;

public class Workflow implements Work {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ArrayList<NodeWrapper> nodes = new ArrayList<NodeWrapper>();
	private ArrayList<AbstractNode<?>> processedNodes = new ArrayList<AbstractNode<?>>();

	@Override
	public void run() {
		try {
			logger.info("", "Started a workflow");
			Collection<Work> parallelTasks = new ArrayList<Work>();
			for(NodeWrapper node: nodes) {
				if(node.isAsync()) {
					logger.info("", "Parallel processing. Adding the node: " + node.getNode().getName());
					parallelTasks.add(node.getNode());
					continue;
				}
				
				//synch
				if(!parallelTasks.isEmpty()) {
					processParallelTasks(parallelTasks);
					parallelTasks.clear();
				}
				logger.info("", "Processing the node: " + node.getNode().getName());
				node.getNode().run();
				processedNodes.add(node.getNode());
			}
			//any parallel tasks left
			if(!parallelTasks.isEmpty()) {
				processParallelTasks(parallelTasks);
				parallelTasks.clear();
			}
		} catch(Exception e) {
			throw new RuntimeException("Failed to process a workflow.", e);
		}
	}

	private void processParallelTasks(Collection<Work> parallelTasks) throws WorkException, InterruptedException, NamingException {
		Collection<Work> processedTasks = WorkManagerFactory.getCommonJWorkManager().processTasks(parallelTasks);	
		if(processedTasks != null) {
			for(Work task: processedTasks) {
				processedNodes.add((AbstractNode<?>) task);
			}
		}
	}

	@Override
	public boolean isDaemon() {
		return false;
	}

	@Override
	public void release() {
	}

	public void addNode(AbstractNode<?> node) {
		nodes.add(new NodeWrapper(node, NodeWrapper.TYPE.SYNC));
	}

	public void addParallelNode(AbstractNode<?> node) {
		nodes.add(new NodeWrapper(node, NodeWrapper.TYPE.ASYNC));
	}

	public ArrayList<AbstractNode<?>> getProcessedNodes() {
		return processedNodes;
	}

	public ArrayList<NodeWrapper> getNodes() {
		return nodes;
	}
}
