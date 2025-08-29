package com.aurionpro.model;

public class Beneficiary {
	private String beneficiaryId;
	private String beneficiaryAccount_no;
	private String beneficiaryName;
	private String beneficiaryBank_name;
	private String beneficiaryIfsc_code;
	private String beneficiaryMobile_no;
	private String beneficiaryNickname;
	private String beneficiaryEmail;
	private String sourceAccountNo;
	public String getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public String getBeneficiaryAccount_no() {
		return beneficiaryAccount_no;
	}

	public void setBeneficiaryAccount_no(String beneficiaryAccount_no) {
		this.beneficiaryAccount_no = beneficiaryAccount_no;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBeneficiaryBank_name() {
		return beneficiaryBank_name;
	}

	public void setBeneficiaryBank_name(String beneficiaryBank_name) {
		this.beneficiaryBank_name = beneficiaryBank_name;
	}

	public String getBeneficiaryIfsc_code() {
		return beneficiaryIfsc_code;
	}

	public void setBeneficiaryIfsc_code(String beneficiaryIfsc_code) {
		this.beneficiaryIfsc_code = beneficiaryIfsc_code;
	}

	public String getBeneficiaryMobile_no() {
		return beneficiaryMobile_no;
	}

	public void setBeneficiaryMobile_no(String beneficiaryMobile_no) {
		this.beneficiaryMobile_no = beneficiaryMobile_no;
	}

	public String getBeneficiaryNickname() {
		return beneficiaryNickname;
	}

	public void setBeneficiaryNickname(String beneficiaryNickname) {
		this.beneficiaryNickname = beneficiaryNickname;
	}

	public String getBeneficiaryEmail() {
		return beneficiaryEmail;
	}

	public void setBeneficiaryEmail(String beneficiaryEmail) {
		this.beneficiaryEmail = beneficiaryEmail;
	}

	public Beneficiary(String beneficiaryId, String beneficiaryAccount_no, String beneficiaryName,
			String beneficiaryBank_name, String beneficiaryIfsc_code, String beneficiaryMobile_no,
			String beneficiaryNickname, String beneficiaryEmail,String sourceAccountNo) {
		super();
		this.beneficiaryId = beneficiaryId;
		this.beneficiaryAccount_no = beneficiaryAccount_no;
		this.beneficiaryName = beneficiaryName;
		this.beneficiaryBank_name = beneficiaryBank_name;
		this.beneficiaryIfsc_code = beneficiaryIfsc_code;
		this.beneficiaryMobile_no = beneficiaryMobile_no;
		this.beneficiaryNickname = beneficiaryNickname;
		this.beneficiaryEmail = beneficiaryEmail;
		this.sourceAccountNo=sourceAccountNo;
	}

	public Beneficiary() {
		super();
	}

	public String getSourceAccountNo() {
		return sourceAccountNo;
	}

	public void setSourceAccountNo(String sourceAccountNo) {
		this.sourceAccountNo = sourceAccountNo;
	}

	@Override
	public String toString() {
		return "Beneficiary [beneficiaryId=" + beneficiaryId + ", beneficiaryAccount_no=" + beneficiaryAccount_no
				+ ", beneficiaryName=" + beneficiaryName + ", beneficiaryBank_name=" + beneficiaryBank_name
				+ ", beneficiaryIfsc_code=" + beneficiaryIfsc_code + ", beneficiaryMobile_no=" + beneficiaryMobile_no
				+ ", beneficiaryNickname=" + beneficiaryNickname + ", beneficiaryEmail=" + beneficiaryEmail
				+ ", sourceAccountNo=" + sourceAccountNo + "]";
	}

}
