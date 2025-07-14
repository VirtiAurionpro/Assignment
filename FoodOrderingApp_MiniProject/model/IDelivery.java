package com.aurionpro.model;

import java.util.List;

import com.aurionpro.model.DeliveryType.DeliveryAgentObject;

public interface IDelivery {
	List<DeliveryAgentObject> agentList();
}
