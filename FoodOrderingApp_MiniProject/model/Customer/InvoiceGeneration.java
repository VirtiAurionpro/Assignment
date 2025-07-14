package com.aurionpro.model.Customer;

import java.util.List;

public class InvoiceGeneration {

	public void start(List<CustomerObject> customerList, String customerID, PaymentObject paymentObject,
		OrderList order, List<InvoiceObject> invoices) {
		System.out.println("===================================================");
		System.out.println("              Generating Invoice...");
		System.out.println("===================================================");
		CustomerObject customerObject = null;
		for (CustomerObject customer : customerList) {
			if (customer.getCustomerID().equalsIgnoreCase(customerID)) {
				customerObject = customer;
				break;
			}
		}
		InvoiceObject invoice = new InvoiceObject();
		String id = invoice.generateId();
		invoice = new InvoiceObject(id, order, customerObject, paymentObject); 
		invoices.add(invoice);
		System.out.println(invoice);
		System.out.println("\nInvoice has been successfully generated.");
		System.out.println("A copy has been sent to your registered mobile number.");

		System.out.println("\nAssigning delivery agent...");
		new AssignDeliveryAgent().start(invoice);

		System.out.println("===================================================");
	}

}
