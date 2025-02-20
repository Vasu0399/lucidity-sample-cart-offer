package com.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyOfferResponse {
    private int status_code;  
    private int cart_value;   

    public ApplyOfferResponse(int cart_value) {
        this.status_code = 200;  
        this.cart_value = cart_value;
    }

    public ApplyOfferResponse(int status_code, String message) {
        this.status_code = status_code;
        this.cart_value = -1; 
    }
}