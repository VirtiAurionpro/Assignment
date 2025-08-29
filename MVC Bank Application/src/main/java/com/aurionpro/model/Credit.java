package com.aurionpro.model;

import java.util.Date;

public class Credit {

	private Registration customer;
	private String application_id;
	private byte[] kycDocument;
	private byte[] addressProof;
	private byte[] incomeProof;
	private byte[] panOrForm60;
	private byte[] photograph;
	private Date application_date;
	private boolean is_Approved;
	private String creditCardNo;
	private String cardExpiry;

	public Credit() {
		super();
	}

	public Credit(Registration customer, String application_id, byte[] kycDocument, byte[] addressProof,
			byte[] incomeProof, byte[] panOrForm60, byte[] photograph, Date application_date, boolean is_Approved,
			String creditCardNo, String cardExpiry) {
		super();
		this.customer = customer;
		this.application_id = application_id;
		this.kycDocument = kycDocument;
		this.addressProof = addressProof;
		this.incomeProof = incomeProof;
		this.panOrForm60 = panOrForm60;
		this.photograph = photograph;
		this.application_date = application_date;
		this.is_Approved = is_Approved;
		this.creditCardNo = creditCardNo;
		this.cardExpiry = cardExpiry;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public Registration getCustomer() {
		return customer;
	}

	public void setCustomer(Registration customer) {
		this.customer = customer;
	}

	public byte[] getKycDocument() {
		return kycDocument;
	}

	public void setKycDocument(byte[] kycDocument) {
		this.kycDocument = kycDocument;
	}

	public byte[] getAddressProof() {
		return addressProof;
	}

	public void setAddressProof(byte[] addressProof) {
		this.addressProof = addressProof;
	}

	public byte[] getIncomeProof() {
		return incomeProof;
	}

	public void setIncomeProof(byte[] incomeProof) {
		this.incomeProof = incomeProof;
	}

	public byte[] getPanOrForm60() {
		return panOrForm60;
	}

	public void setPanOrForm60(byte[] panOrForm60) {
		this.panOrForm60 = panOrForm60;
	}

	public byte[] getPhotograph() {
		return photograph;
	}

	public void setPhotograph(byte[] photograph) {
		this.photograph = photograph;
	}

	public Date getApplication_date() {
		return application_date;
	}

	public void setApplication_date(Date application_date) {
		this.application_date = application_date;
	}

	public boolean isIs_Approved() {
		return is_Approved;
	}

	public void setIs_Approved(boolean is_Approved) {
		this.is_Approved = is_Approved;
	}

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

}
