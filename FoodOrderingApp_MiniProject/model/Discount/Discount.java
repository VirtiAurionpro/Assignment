package com.aurionpro.model.Discount;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Discount {
	public static TreeMap<Double, Double> treeMap = new TreeMap<>(Comparator.reverseOrder());

	public void loadDiscounts() {
		Discountabove2000 D1 = new Discountabove2000();
		treeMap.put(D1.getAmount(), D1.getRate());
		Discountabove1500 D2 = new Discountabove1500();
		treeMap.put(D2.getAmount(), D2.getRate());
		NoDiscount D3 = new NoDiscount();
		treeMap.put(D3.getAmount(), D3.getRate());
	}

	public double calculateDiscountedValue(double amount) {
		for (Map.Entry<Double, Double> entry : treeMap.entrySet()) {
			if (entry.getKey() < amount)
				return ((amount*entry.getValue())/100);
		}
		return 0;
	}
}
