package com.edureka.order.exception;


public class OrderNotPlacedException extends RuntimeException {

    public OrderNotPlacedException(String message) {
        super(message);
    }
}

