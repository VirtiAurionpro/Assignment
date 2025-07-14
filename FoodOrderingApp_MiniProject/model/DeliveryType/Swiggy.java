package com.aurionpro.model.DeliveryType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aurionpro.model.DeliveryAgentRepository;
import com.aurionpro.model.IDelivery;

public class Swiggy implements IDelivery {
	List<DeliveryAgentObject> swiggyAgents = new ArrayList<>();
	Map<String,String> deliveryAgentMap = DeliveryAgentRepository.deliveryAgentMap;
	static boolean added = false;

	@Override
	public List<DeliveryAgentObject> agentList() {
		if (!added)
			addSwiggyAgents();
		return swiggyAgents;
	}

	private void addSwiggyAgents() {
		swiggyAgents.add(new DeliveryAgentObject("S001", "Neha Joshi", 9876501234L, "Swiggy", "Available"));
		swiggyAgents.add(new DeliveryAgentObject("S002", "Vikram Rana", 9812345678L, "Swiggy", "Available"));
		swiggyAgents.add(new DeliveryAgentObject("S003", "Divya Iyer", 9900112233L, "Swiggy", "Available"));
		for (DeliveryAgentObject agent : swiggyAgents) {
			deliveryAgentMap.put(agent.getAgentID(), "");
		}
		added = true;
	}
}
