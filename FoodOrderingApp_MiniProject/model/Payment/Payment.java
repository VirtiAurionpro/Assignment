package com.aurionpro.model.Payment;

import java.util.Scanner;

import com.aurionpro.model.IPayment;
import com.aurionpro.model.Customer.OrderList;
import com.aurionpro.model.Customer.PaymentObject;
import com.aurionpro.model.Exceptions.InvalidChoice;

public class Payment {

	public PaymentObject start(OrderList orderList) {
		while (true) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			double billAmount = orderList.getOrderTotal()-orderList.getDiscount();
			System.out.println("Amount due:" + billAmount);
			String customerName = orderList.getCustomerName();
			System.out.println("\nPlease select your mode of payment:");
			System.out.println("1.Debit Card\n2.Credit Card\n3.UPI\n4.Cash on Delivery\n5.Exit");
			try {
				int choice = scanner.nextInt();
				IPayment paymentMethod = null;
				switch (choice) {
				case 1: {
					paymentMethod = new DebitCardPayment();
					paymentMethod.getDetails();
					if (paymentMethod.donePayment(billAmount)) {
						return (new PaymentObject(customerName, billAmount, "Debit Card", "Completed"));
					}
					break;
				}
				case 2: {
					paymentMethod = new CreditCardPayment();
					paymentMethod.getDetails();
					if (paymentMethod.donePayment(billAmount))
						return (new PaymentObject(customerName, billAmount, "Credit Card", "Completed"));
					break;
				}
				case 3: {
					paymentMethod = new UPIPayment();
					paymentMethod.getDetails();
					if (paymentMethod.donePayment(billAmount))
						return (new PaymentObject(customerName, billAmount, "UPI", "Completed"));
					break;
				}
				case 4: {
					return (new PaymentObject(customerName, billAmount, "Cash on Delivery", "Pending"));
				}
				case 5:
					return null;
				default:
					throw new InvalidChoice();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
