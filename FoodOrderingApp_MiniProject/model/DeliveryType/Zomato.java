package com.aurionpro.model.DeliveryType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aurionpro.model.DeliveryAgentRepository;
import com.aurionpro.model.IDelivery;

public class Zomato implements IDelivery {
	private List<DeliveryAgentObject> zomatoAgents = new ArrayList<>();
	Map<String,String> deliveryAgentMap = DeliveryAgentRepository.deliveryAgentMap;
	static boolean added=false;
	@Override
	public List<DeliveryAgentObject> agentList() {
		if(!added)
			addZomatoAgents();
		return zomatoAgents;
	}

	private void addZomatoAgents() {
		zomatoAgents.add(new DeliveryAgentObject("Z001", "Amit Sharma", 9876543210L, "Zomato", "Available"));
		zomatoAgents.add(new DeliveryAgentObject("Z002", "Pooja Mehta", 9123456780L, "Zomato", "Available"));
		zomatoAgents.add(new DeliveryAgentObject("Z003", "Rahul Deshmukh", 9988776655L, "Zomato", "Available"));
		for (DeliveryAgentObject agent : zomatoAgents) {
			deliveryAgentMap.put(agent.getAgentID(), "");
		}
		added=true;
	}
}
