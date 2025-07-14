package com.aurionpro.model.DeliveryType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aurionpro.model.DeliveryAgentRepository;
import com.aurionpro.model.IDelivery;

public class Blinkit implements IDelivery {
	List<DeliveryAgentObject> blinkitAgents = new ArrayList<>();
	Map<String,String> deliveryAgentMap = DeliveryAgentRepository.deliveryAgentMap;
	static boolean added=false;
	public List<DeliveryAgentObject> agentList() {
		if(!added)
			addBlinkitAgents();
		return blinkitAgents;
	}

	private void addBlinkitAgents() {
		blinkitAgents.add(new DeliveryAgentObject("B001", "Ritika Shah", 9811223344L, "Blinkit", "Available"));
		blinkitAgents.add(new DeliveryAgentObject("B002", "Harish Patel", 9900223344L, "Blinkit", "Available"));
		blinkitAgents.add(new DeliveryAgentObject("B003", "Meena Kaul", 9899887766L, "Blinkit", "Available"));
		for (DeliveryAgentObject agent : blinkitAgents) {
			deliveryAgentMap.put(agent.getAgentID(), "");
		}
		added=true;
	}
}
