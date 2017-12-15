package com.acme.jboss.ejb.client;

public class RoundRobinDeploymentNodeSelectorTest extends NodeSelectorTest<RoundRobinDeploymentNodeSelector> {

	@Override
	RoundRobinDeploymentNodeSelector createCut() {
		return new RoundRobinDeploymentNodeSelector();
	}

	@Override
	String executeNodeCallOneNode(RoundRobinDeploymentNodeSelector cut, String[] onlyOneNode) {
		return cut.selectNode(onlyOneNode, null, null, null);
	}

	@Override
	String executeNodeCallMultipleNodes(RoundRobinDeploymentNodeSelector cut, String[] multipleNodes) {
		return cut.selectNode(multipleNodes, null, null, null);
	}
}
