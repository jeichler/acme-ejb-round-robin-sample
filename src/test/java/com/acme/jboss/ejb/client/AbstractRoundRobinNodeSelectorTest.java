package com.acme.jboss.ejb.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public abstract class AbstractRoundRobinNodeSelectorTest<T> {

	private static final String NODE1 = "node1";
	private static final String NODE2 = "node2";
	private static final String NODE3 = "node3";
	static final String[] ONE_NODE = { NODE1 };
	static final String[] MULTIPLE_NODES = { NODE1, NODE2, NODE3 };

	private T cut;

	@BeforeEach
	private void setup() {
		cut = createCut();
	}

	abstract T createCut();

	/**
	 *
	 * @param cut
	 *            - make sure to use this instance only instead of creating your one
	 *            one.
	 * @return the node name the nodeselector returned
	 */
	abstract String executeNodeCallOneNode(final T cut, final String[] onlyOneNode);

	/**
	 * @see AbstractRoundRobinNodeSelectorTest#executeNodeCallMultipleNodes(Object, String[])
	 */
	abstract String executeNodeCallMultipleNodes(final T cut, final String[] multipleNodes);

	@DisplayName("Nodes must be called using round robin")
	@Test
	public void nodesAreCalledInARoundRobinFashion() {
		// first call --> first node, second call --> second node, third call --> third
		// node
		for (int i = 1; i <= MULTIPLE_NODES.length; i++) {
			String nodeResult = executeNodeCallMultipleNodes(cut, MULTIPLE_NODES);
			Assertions.assertEquals("node" + i, nodeResult);
		}
		// make a fourth call to check that now the first node is returned again
		String nodeResult = executeNodeCallMultipleNodes(cut, MULTIPLE_NODES);
		Assertions.assertEquals(NODE1, nodeResult);
	}

	@DisplayName("Only one node available, so this one must be always returned")
	@Test
	public void returnTheOnlyAvailableNode() {
		// call a couple of times, always node1 must be returned. connected nodes must
		// not be taken into consideration
		for (int i = 0; i < 1000; i++) {
			String nodeResult = executeNodeCallOneNode(cut, ONE_NODE);
			Assertions.assertEquals(NODE1, nodeResult);
		}
	}
}
