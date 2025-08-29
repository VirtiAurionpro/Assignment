package com.aurionpro.model;

public class Nominee {
	public String account_no;
	public String nominee_name;
	public String relation;
	public int nominee_age;
	public String nominee_identification_no;
	public String nominee_mobile_no;

	public Nominee() {
		super();
	}

	public Nominee(String account_no, String nominee_name, String relation, int nominee_age,
			String nominee_identification_no, String nominee_mobile_no) {
		super();
		this.account_no = account_no;
		this.nominee_name = nominee_name;
		this.relation = relation;
		this.nominee_age = nominee_age;
		this.nominee_identification_no = nominee_identification_no;
		this.nominee_mobile_no = nominee_mobile_no;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getNominee_name() {
		return nominee_name;
	}

	public void setNominee_name(String nominee_name) {
		this.nominee_name = nominee_name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public int getNominee_age() {
		return nominee_age;
	}

	public void setNominee_age(int nominee_age) {
		this.nominee_age = nominee_age;
	}

	public String getNominee_identification_no() {
		return nominee_identification_no;
	}

	public void setNominee_identification_no(String nominee_identification_no) {
		this.nominee_identification_no = nominee_identification_no;
	}

	public String getNominee_mobile_no() {
		return nominee_mobile_no;
	}

	public void setNominee_mobile_no(String nominee_mobile_no) {
		this.nominee_mobile_no = nominee_mobile_no;
	}

}
