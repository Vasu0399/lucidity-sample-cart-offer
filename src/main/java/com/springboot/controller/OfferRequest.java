package com.springboot.controller;

import lombok.Data;
import java.util.List;

@Data
public class OfferRequest {
    private int restaurant_id;
    private String offer_type;
    private int offer_value;
    private List<String> customer_segment;
    
    public OfferRequest(int restaurant_id, String offer_type, int offer_value, List<String> customer_segment) {
        this.restaurant_id = restaurant_id;
        this.offer_type = offer_type;
        this.offer_value = offer_value;
        this.customer_segment = customer_segment;
    }
}
