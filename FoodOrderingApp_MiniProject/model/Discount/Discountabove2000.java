package com.aurionpro.model.Discount;

import com.aurionpro.model.IDiscount;

public class Discountabove2000 implements IDiscount{
	@Override
	public double getAmount() {
		return 2000;
	}

	@Override
	public double getRate() {
		return 7.5;
	}
}
