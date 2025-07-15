package com.aurionpro.model.DeliveryType;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.aurionpro.model.DeliveryAgentRepository;
import com.aurionpro.model.Customer.CustomerDisplay;
import com.aurionpro.model.Customer.InvoiceObject;

public class AgentDisplay {

	Map<String, String> deliveryInvoiceMap = DeliveryAgentRepository.deliveryInvoiceMap;
	Scanner scanner = new Scanner(System.in);
	
	public void start(String agentID, List<DeliveryAgentObject> agents) {
		boolean found = false;
		for (DeliveryAgentObject agent : agents) {
			if (agent.getAgentID().equals(agentID) && agent.getStatus().equals("Engaged")) {
				List<InvoiceObject> invoices = CustomerDisplay.invoices;
				String invoiceID = deliveryInvoiceMap.get(agentID);
				InvoiceObject invoice = null;
				for (InvoiceObject invoiceObject : invoices) {
					if (invoiceObject.getInvoiceID().equals(invoiceID)) {
						invoice = invoiceObject;
						break;
					}
				}
				System.out.println("\nDelivery Details");
				System.out.println("---------------------------------------------------");
				System.out.println("Agent Name       : " + agent.getAgentName());
				System.out.println("Recipient Name   : " + invoice.getCutomer().getName());
				System.out.println("Delivery Address : " + invoice.getCutomer().getAddress());
				System.out.println("Total Bill       : ₹" + (invoice.getOrder().getOrderTotal()-invoice.getOrder().getDiscount()));
				System.out.println("Payment Mode     :"+invoice.getPaymentObject().getPaymentMode());
				System.out.println("Payment Status   : " + invoice.getPaymentObject().getPaymentStatus());
				System.out.println("---------------------------------------------------");
				found=true;
				break;
			}
		}
		if(!found) {
			System.out.println("You do not have any deliveries right now.");
		}
		System.out.println("--------------------------------------------------");
		System.out.println("Do you wish to stay logged in? (yes/no)");
		System.out.print("Enter your choice: ");
		String ans = scanner.next();
		if (!ans.equalsIgnoreCase("yes")) {
			System.out.println("Logging out. See you again!");
			return;
		}
		System.out.println("Continuing session...");
		start(agentID,agents);
	}

}
