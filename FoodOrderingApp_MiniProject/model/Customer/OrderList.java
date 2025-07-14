package com.aurionpro.model.Customer;

import java.time.LocalDate;
import java.util.List;

public class OrderList {
	private String orderID;
	private LocalDate date;
	private List<LineItem> lineItems;
	private double orderTotal;
	private static int counter = 0;
	private String customerName;
	private double discount;

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getorderID() {
		return orderID;
	}

	public void setorderID(String orderID) {
		this.orderID = orderID;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public OrderList() {
		super();
	}

	public OrderList(String orderID, LocalDate date, List<LineItem> lineItems, double orderTotal, String customerName) {
		super();
		this.orderID = orderID;
		this.date = date;
		this.lineItems = lineItems;
		this.orderTotal = orderTotal;
		this.customerName = customerName;
	}

	public String generateId() {
		counter += 1;
		return "OR" + counter;
	}

	@Override
	public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("---------------------------- ORDER -------------------------\n");
        builder.append("Order ID     : ").append(orderID).append("\n");
        builder.append("Date         : ").append(date).append("\n");
        builder.append("Deliver To   : ").append(customerName).append("\n");
        builder.append(String.format("%-20s %-12s %-15s %-10s\n", "Item Name", "Item Units", "Item Price", "Total"));
        builder.append("-------------------------------------------------------------\n");

        for (LineItem item : lineItems) {
            builder.append(String.format("%-20s %-10.2f ₹%-10.2f ₹%-10.2f\n",
                item.getItemName(), item.getQuantity(), item.getUnitPrice(), item.getlineTotal()));
        }

        builder.append("-------------------------------------------------------------\n");
        builder.append(String.format("%-55s ₹%-10.2f\n", "Subtotal:", orderTotal));
        builder.append(String.format("%-55s ₹%-10.2f\n", "Discount:", discount));
        builder.append(String.format("%-55s ₹%-10.2f\n", "Total Payable:", orderTotal - discount));
        return builder.toString();
    }

	public double calculateTotal(List<LineItem> lineItems2) {
		double total = 0;
        for (LineItem item : lineItems2) {
            total += item.getlineTotal();
        }
        return total;
	}

}
