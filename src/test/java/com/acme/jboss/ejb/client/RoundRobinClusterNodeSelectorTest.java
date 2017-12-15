package com.acme.jboss.ejb.client;

public class RoundRobinClusterNodeSelectorTest extends NodeSelectorTest<RoundRobinClusterNodeSelector> {

	@Override
	RoundRobinClusterNodeSelector createCut() {
		return new RoundRobinClusterNodeSelector();
	}

	@Override
	String executeNodeCallOneNode(RoundRobinClusterNodeSelector cut, String[] onlyOneNode) {
		// pass multiple nodes as connected nodes. they must not be taken into
		// consideration.
		return cut.selectNode(null, MULTIPLE_NODES, onlyOneNode);
	}

	@Override
	String executeNodeCallMultipleNodes(RoundRobinClusterNodeSelector cut, String[] multipleNodes) {
		return cut.selectNode(null, null, multipleNodes);
	}

}