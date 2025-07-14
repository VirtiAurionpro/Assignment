package com.aurionpro.model.Discount;

import com.aurionpro.model.IDiscount;

public class NoDiscount implements IDiscount{

	@Override
	public double getAmount() {
		return 500;
	}

	@Override
	public double getRate() {
		return 0;
	}

}
