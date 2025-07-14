package com.aurionpro.model.Customer;

public class LineItem {

	private String lineItemID;
	private String itemID;
	private String itemName;
	private double unitPrice;
	private double lineTotal;
	private double quantity;

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getLineItemID() {
		return lineItemID;
	}

	public void setLineItemID(String lineItemID) {
		this.lineItemID = lineItemID;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getlineTotal() {
		return lineTotal;
	}

	public void setlineTotal(double lineTotal) {
		this.lineTotal = lineTotal;
	}

	public LineItem(String lineItemID, String itemID, String itemName, double quantity, double unitPrice,
			double lineTotal) {
		super();
		this.lineItemID = lineItemID;
		this.itemID = itemID;
		this.itemName = itemName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.lineTotal = lineTotal;
	}

	public LineItem() {
		super();
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("-------------------------------------------------------------------------------------------------\n");
	    sb.append(String.format("%-12s %-12s %-20s %-12.2f %-15.2f %-10.2f%n",
	        lineItemID, itemID, itemName, quantity, unitPrice, lineTotal));
	    sb.append("--------------------------------------------------------------------------------------------------");
	    return sb.toString();
	}

}
