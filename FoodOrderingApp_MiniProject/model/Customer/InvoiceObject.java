package com.aurionpro.model.Customer;

public class InvoiceObject {
	private String invoiceID;
	private OrderList order;
	private CustomerObject cutomer;
	private PaymentObject paymentObject;
	private static int counter = 0;

	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public OrderList getOrder() {
		return order;
	}

	public void setOrder(OrderList order) {
		this.order = order;
	}

	public CustomerObject getCutomer() {
		return cutomer;
	}

	public void setCutomer(CustomerObject cutomer) {
		this.cutomer = cutomer;
	}

	public PaymentObject getPaymentObject() {
		return paymentObject;
	}

	public void setPaymentObject(PaymentObject paymentObject) {
		this.paymentObject = paymentObject;
	}

	public InvoiceObject() {
		super();
	}

	public InvoiceObject(String invoiceID, OrderList order, CustomerObject cutomer, PaymentObject paymentObject) {
		super();
		this.invoiceID = invoiceID;
		this.order = order;
		this.cutomer = cutomer;
		this.paymentObject = paymentObject;
	}

	public String generateId() {
	    counter += 1;
	    return "IN" + counter;
	}
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("===================================================\n");
	    sb.append("                  INVOICE DETAILS\n");
	    sb.append("===================================================\n");
	    sb.append("Invoice ID     : ").append(invoiceID).append("\n");
	    sb.append("Customer Name  : ").append(cutomer.getName()).append("\n");
	    sb.append("Address        : ").append(cutomer.getAddress()).append("\n"); 
	    sb.append("Payment Mode   : ").append(paymentObject.getPaymentMode()).append("\n");
	    sb.append("Payment Status : ").append(paymentObject.getPaymentStatus()).append("\n");
	    sb.append("Bill Amount     : ₹").append(paymentObject.getPaymentAmount()).append("\n");
//	    if (paymentObject.getPaymentID() != null) {
//	        sb.append("Payment ID     : ").append(paymentObject.getPaymentID()).append("\n");
//	    }
	    sb.append(order); 
	    sb.append("\n===================================================\n");
	    return sb.toString();
	}


}
