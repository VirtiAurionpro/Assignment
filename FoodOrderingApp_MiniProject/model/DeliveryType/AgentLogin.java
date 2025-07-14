package com.aurionpro.model.DeliveryType;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.aurionpro.model.DeliveryAgentRepository;
import com.aurionpro.model.Exceptions.AccountNotFound;

public class AgentLogin {
	Map<String, String> deliveryAgentMap = DeliveryAgentRepository.deliveryAgentMap;
	Scanner scanner = new Scanner(System.in);

	public void start(List<DeliveryAgentObject> agents, String agentID) {
		boolean found = false;
		for (DeliveryAgentObject agent : agents) {
			if (agent.getAgentID().equals(agentID)) {
				found = true;
				String entry = deliveryAgentMap.get(agentID);
				if (entry.isEmpty()) {
					entry = new AgentFirstLogin().start(agentID);
					deliveryAgentMap.put(agentID, entry);
					new AgentDisplay().start(agentID,agents);
					break;
				}
				if (agentLogin(agents, agentID)) {
					new AgentDisplay().start(agentID,agents);
					break;
				}
			}
		}
		if (!found)
			throw new AccountNotFound(agentID);
	}

	private boolean agentLogin(List<DeliveryAgentObject> agents, String agentID) {
		for (DeliveryAgentObject agent : agents) {
			if (agent.getAgentID().equals(agentID)) {
				String entry = deliveryAgentMap.get(agentID);
				System.out.println("Enter your password");
				String password = scanner.next();
				if (password.equals(entry)) {
					System.out.println("Login successful. Redirecting to Agent Dashboard..");
					return true;
				}
				System.out.println("Invalid Credentails");
			}
		}
		return false;
	}

}
