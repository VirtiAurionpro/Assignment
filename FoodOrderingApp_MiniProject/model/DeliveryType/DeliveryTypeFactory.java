package com.aurionpro.model.DeliveryType;

import java.util.HashMap;
import java.util.Map;

import com.aurionpro.model.IDelivery;

public class DeliveryTypeFactory {

	private static final Map<String, IDelivery> deliveryServices = new HashMap<>();

	static {
	    deliveryServices.put("Zomato", new Zomato());
	    deliveryServices.put("Swiggy", new Swiggy());
	    deliveryServices.put("Zepto", new Zepto());
	    deliveryServices.put("Blinkit", new Blinkit());
	}
	
	public static IDelivery getDeliveryType(String deliveryType) {
	    return deliveryServices.get(deliveryType);
	}

}
