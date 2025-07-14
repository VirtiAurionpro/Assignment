package com.aurionpro.model.Discount;

import com.aurionpro.model.IDiscount;

public class Discountabove1500 implements IDiscount{

	@Override
	public double getAmount() {
		return 1500;
	}

	@Override
	public double getRate() {
		return 5;
	}

}
