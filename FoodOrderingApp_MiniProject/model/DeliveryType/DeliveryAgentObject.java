package com.aurionpro.model.DeliveryType;

public class DeliveryAgentObject {
	private String agentID;
	private String agentName;
	private long agentPhoneNumber;
	private String agentDeliveryType;
	private String status;
	private int counter = 0;

	public String getAgentID() {
		return agentID;
	}

	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public long getAgentPhoneNumber() {
		return agentPhoneNumber;
	}

	public void setAgentPhoneNumber(long agentPhoneNumber) {
		this.agentPhoneNumber = agentPhoneNumber;
	}

	public String getAgentDeliveryType() {
		return agentDeliveryType;
	}

	public void setAgentDeliveryType(String agentDeliveryType) {
		this.agentDeliveryType = agentDeliveryType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DeliveryAgentObject(String agentID, String agentName, long agentPhoneNumber, String agentDeliveryType,
			String status) {
		super();
		this.agentID = agentID;
		this.agentName = agentName;
		this.agentPhoneNumber = agentPhoneNumber;
		this.agentDeliveryType = agentDeliveryType;
		this.status = status;
	}

	public DeliveryAgentObject() {
		super();
	}

	public String generateID() {
		counter += 1;
		return "AG" + counter;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(String.format("| %-10s | %-20s | %-15s | %-18s | %-10s |\n",
	        agentID, agentName, agentPhoneNumber, agentDeliveryType, status));
	    sb.append("---------------------------------------------------------------------------------");
	    return sb.toString();
	}


}
