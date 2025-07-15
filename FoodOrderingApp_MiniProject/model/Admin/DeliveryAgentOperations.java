package com.aurionpro.model.Admin;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.aurionpro.model.DeliveryAgentRepository;
import com.aurionpro.model.IDelivery;
import com.aurionpro.model.DeliveryType.DeliveryAgentObject;
import com.aurionpro.model.DeliveryType.DeliveryTypeFactory;
import com.aurionpro.model.Exceptions.AgentNotFound;
import com.aurionpro.model.Exceptions.InvalidChoice;

public class DeliveryAgentOperations {

	public void addAgent(Scanner scanner) {
		while (true) {
			Map<String, String> deliveryAgentMap = DeliveryAgentRepository.deliveryAgentMap;
			System.out.println("=======================================");
			System.out.println("        Add New Delivery Agent");
			System.out.println("=======================================");
			String deliveryType = getDeliveryServiceType(scanner);
			if (deliveryType == null) {
				System.out.println("Returning to previous menu...");
				break;
			}
			IDelivery deliveryService = DeliveryTypeFactory.getDeliveryType(deliveryType);
			List<DeliveryAgentObject> agents = deliveryService.agentList();
			System.out.println("Enter Agent Name");
			String name = scanner.next();
			String id = new DeliveryAgentObject().generateID();
			System.out.println("Enter Agent Phone Number ");
			long phoneNo = scanner.nextLong();
			DeliveryAgentObject agent = new DeliveryAgentObject(id, name, phoneNo, deliveryType, "Available");
			agents.add(agent);
			deliveryAgentMap.put(agent.getAgentID(), "");
			System.out.println("Agent Details Added Successfully");
			System.out.println("--------------------------------------------------");
			System.out.println("            Delivery Agent Details");
			System.out.println("--------------------------------------------------");
			System.out.println("=================================================================================");
			System.out.printf("| %-10s | %-20s | %-15s | %-18s | %-10s |\n", "Agent ID", "Name", "Phone Number",
					"Delivery Service", "Status");
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println(agent);
		}
	}

	public String getDeliveryServiceType(Scanner scanner) {
		System.out.println("=======================================");
		System.out.println("       Choose Delivery Type");
		System.out.println("=======================================");
		System.out.println("1. Zomato");
		System.out.println("2. Swiggy");
		System.out.println("3. Zepto");
		System.out.println("4. Blinkit");
		System.out.println("5. Exit");
		System.out.print("Enter your choice (1-5): ");
		try {
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				return "Zomato";
			case 2:
				return "Swiggy";
			case 3:
				return "Zepto";
			case 4:
				return "Blinkit";
			case 5:
				return null;
			default:
				throw new InvalidChoice();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void displayAgents(Scanner scanner) {
		while (true) {
			String deliveryType = getDeliveryServiceType(scanner);
			System.out.println(deliveryType);
			if (deliveryType == null)
				break;
			IDelivery deliveryService = DeliveryTypeFactory.getDeliveryType(deliveryType);
			List<DeliveryAgentObject> agents = deliveryService.agentList();
			System.out.println("--------------------------------------------------");
			System.out.println("            Delivery Agent Details");
			System.out.println("--------------------------------------------------");
			System.out.println("=================================================================================");
			System.out.printf("| %-10s | %-20s | %-15s | %-18s | %-10s |\n", "Agent ID", "Name", "Phone Number",
					"Delivery Service", "Status");
			System.out.println("---------------------------------------------------------------------------------");
			for (DeliveryAgentObject agent : agents)
				System.out.println(agent);
		}
	}

	public void removeAgent(Scanner scanner) {
		System.out.println("=======================================");
		System.out.println("        Remove Delivery Agent");
		System.out.println("=======================================");

		String deliveryType = getDeliveryServiceType(scanner);
		if (deliveryType == null) {
			System.out.println("Returning to previous menu...");
			return;
		}

		IDelivery deliveryService = DeliveryTypeFactory.getDeliveryType(deliveryType);
		List<DeliveryAgentObject> agents = deliveryService.agentList();

		System.out.print("Enter Agent ID to remove: ");
		String agentID = scanner.next();

		boolean removed = agents.removeIf(agent -> agent.getAgentID().equalsIgnoreCase(agentID));
		if (removed) {
			System.out.println("\nAgent with ID '" + agentID + "' has been removed successfully.");
		} else {
			throw new AgentNotFound(agentID);
		}
	}

	public void editAgent(Scanner scanner) {
		boolean found = false;
		System.out.println("=======================================");
		System.out.println("        Update Delivery Agent");
		System.out.println("=======================================");
		String deliveryType = getDeliveryServiceType(scanner);
		if (deliveryType == null) {
			System.out.println("Returning to previous menu...");
			return;
		}
		IDelivery deliveryService = DeliveryTypeFactory.getDeliveryType(deliveryType);
		List<DeliveryAgentObject> agents = deliveryService.agentList();
		System.out.println("Enter Agent ID");
		String id = scanner.next();
		for (DeliveryAgentObject agent : agents) {
			if (agent.getAgentID().equalsIgnoreCase(id)) {
				System.out.println("Enter Agent Name");
				scanner.nextLine();
				String name = scanner.nextLine();
				agent.setAgentName(name);
				System.out.println("Enter Agent Phone Number ");
				long phoneNo = scanner.nextLong();
				agent.setAgentPhoneNumber(phoneNo);
				found = true;
				break;
			}
		}
		if (!found)
			throw new AgentNotFound(id);
		System.out.println("Agent Details Edited Successfully");
	}
}
