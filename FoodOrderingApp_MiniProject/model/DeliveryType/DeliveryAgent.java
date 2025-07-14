package com.aurionpro.model.DeliveryType;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.IDelivery;
import com.aurionpro.model.Exceptions.InvalidChoice;

public class DeliveryAgent {

	public void start() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("==================================");
			System.out.println("         Agent Portal");
			System.out.println("==================================");
			System.out.println("Please select an option below:");
			System.out.println("1. Agent Login");
			System.out.println("2. Exit");
			System.out.print("Enter your choice (1-2): ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				String deliveryType = null;
				try {
					deliveryType = getDeliveryServiceType(scanner);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				IDelivery deliveryService = DeliveryTypeFactory.getDeliveryType(deliveryType);
				List<DeliveryAgentObject> agents = deliveryService.agentList();
				System.out.println("Enter your Agent ID: ");
				String agentID = scanner.next();
				try {
				new AgentLogin().start(agents,agentID);
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
				break;
			}
			case 2: {
				return;
			}
			}
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
		System.out.print("Enter your choice (1-4): ");

		int choice = scanner.nextInt();
//		if (choice == 5)
//			return null;
		switch (choice) {
		case 1:
			return "Zomato";
		case 2:
			return "Swiggy";
		case 3:
			return "Zepto";
		case 4:
			return "Blinkit";
		default:
			throw new InvalidChoice();
		}
//		return null;
	}
}
