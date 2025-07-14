package com.aurionpro.model.Customer;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.aurionpro.model.DeliveryAgentRepository;
import com.aurionpro.model.IDelivery;
import com.aurionpro.model.DeliveryType.DeliveryAgentObject;
import com.aurionpro.model.DeliveryType.DeliveryTypeFactory;

public class AssignDeliveryAgent {

	public void start(InvoiceObject invoice) {
		Map<String, String> deliveryInvoiceMap = DeliveryAgentRepository.deliveryInvoiceMap;
		System.out.println("===================================================");
		System.out.println("         Assigning Delivery Agent");
		System.out.println("===================================================");

		Scanner scanner = new Scanner(System.in);
		System.out.println("Which delivery type do you want?");
		String deliveryType = getDeliveryServiceType(scanner);
		IDelivery deliveryService = DeliveryTypeFactory.getDeliveryType(deliveryType);
		List<DeliveryAgentObject> agents = deliveryService.agentList();
		for (DeliveryAgentObject agent : agents)
			if (agent.getStatus().equals("Available")) {
				System.out.println("\nDelivery agent assigned successfully!");
				System.out.println("---------------------------------------");
				System.out.println("Name          : " + agent.getAgentName());
				System.out.println("Phone Number  : " + agent.getAgentPhoneNumber());
				System.out.println("---------------------------------------");
				agent.setStatus("Engaged");
				System.out.println("Thank you for using our services.");
				System.out.println("Your order will be delivered shortly");
				deliveryInvoiceMap.put(agent.getAgentName(), invoice.getInvoiceID());
				break;
			}
	}

	public String getDeliveryServiceType(Scanner scanner) {
		System.out.println("\nChoose a delivery platform:");
		System.out.println("1. Zomato");
		System.out.println("2. Swiggy");
		System.out.println("3. Zepto");
		System.out.println("4. Blinkit");
		System.out.println("5. Exit");
		System.out.print("Enter your choice (1-5): ");

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
		}
		return null;
	}
}
