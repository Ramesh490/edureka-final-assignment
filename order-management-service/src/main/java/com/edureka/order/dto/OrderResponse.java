package com.edureka.order.dto;

public class OrderResponse {

	private String message;

	public OrderResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
