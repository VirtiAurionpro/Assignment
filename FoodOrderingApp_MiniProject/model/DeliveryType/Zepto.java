package com.aurionpro.model.DeliveryType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aurionpro.model.DeliveryAgentRepository;
import com.aurionpro.model.IDelivery;

public class Zepto implements IDelivery {
	List<DeliveryAgentObject> zeptoAgents = new ArrayList<>();
	Map<String,String> deliveryAgentMap = DeliveryAgentRepository.deliveryAgentMap;
	static boolean added=false;
	@Override
	public List<DeliveryAgentObject> agentList() {
		if(!added)
			addZeptoAgents();
		return zeptoAgents;
	}

	private void addZeptoAgents() {
		zeptoAgents.add(new DeliveryAgentObject("ZP001", "Suresh Kumar", 9876001122L, "Zepto", "Available"));
		zeptoAgents.add(new DeliveryAgentObject("ZP002", "Priya Nair", 9822334455L, "Zepto", "Available"));
		zeptoAgents.add(new DeliveryAgentObject("ZP003", "Ankit Verma", 9944556677L, "Zepto", "Available"));
		for (DeliveryAgentObject agent : zeptoAgents) {
			deliveryAgentMap.put(agent.getAgentID(), "");
		}
		added=true;
	}
}
