package com.acme.jboss.ejb.client;

import org.jboss.ejb.client.ClusterNodeSelector;

/**
 * This round robin implementation is for a clustered environment Taken from
 * https://access.redhat.com/solutions/151853
 */
public class RoundRobinClusterNodeSelector implements ClusterNodeSelector {

	private volatile int lastServer = 0;

	public String selectNode(final String clusterName, final String[] connectedNodes, final String[] availableNodes) {
		// Just a single node available, so just return it
		if (availableNodes.length == 1) {
			return availableNodes[0];
		}
		if (lastServer >= availableNodes.length) {
			lastServer = 0;
		}
		return availableNodes[lastServer++];
	}
}
