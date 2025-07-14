package com.aurionpro.model.Exceptions;

public class AgentNotFound extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String agentID;

	public AgentNotFound() {
		super();
	}

	public AgentNotFound(String agentID) {
		super();
		this.agentID = agentID;
	}

	public String getagentID() {
		return agentID;
	}

	public void setagentID(String agentID) {
		this.agentID = agentID;
	}
	public String getMessage() {
		return "\nNo one found with agent ID '" + agentID + "'. Please check and try again.";
	}
}
