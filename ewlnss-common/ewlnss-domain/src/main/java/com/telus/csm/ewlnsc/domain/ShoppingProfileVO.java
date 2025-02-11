package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class ShoppingProfileVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private ApplicationProfileVO applicationProfile = null;
	private UserProfileVO userProfile = null;
	private AgentProfileVO agentProfile = null;

	public ApplicationProfileVO getApplicationProfile() {
		return applicationProfile;
	}

	public void setApplicationProfile(ApplicationProfileVO applicationProfile) {
		this.applicationProfile = applicationProfile;
	}

	public UserProfileVO getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfileVO userProfile) {
		this.userProfile = userProfile;
	}

	public AgentProfileVO getAgentProfile() {
		return agentProfile;
	}

	public void setAgentProfile(AgentProfileVO agentProfile) {
		this.agentProfile = agentProfile;
	}

}
