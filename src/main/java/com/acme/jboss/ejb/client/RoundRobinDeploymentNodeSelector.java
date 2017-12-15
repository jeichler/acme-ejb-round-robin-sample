package com.acme.jboss.ejb.client;

import org.jboss.ejb.client.DeploymentNodeSelector;

/**
 * This round robin implementation for a non-clustered environment Taken from
 * https://access.redhat.com/solutions/162293
 */
public class RoundRobinDeploymentNodeSelector implements DeploymentNodeSelector {

	private volatile int lastServer = 0;

	public String selectNode(final String[] eligibleNodes, final String appName, final String moduleName, final String distinctName) {
		// Just a single node available, so just return it
		if (eligibleNodes.length == 1) {
			return eligibleNodes[0];
		}
		if (lastServer >= eligibleNodes.length) {
			lastServer = 0;
		}
		return eligibleNodes[lastServer++];
	}
}
