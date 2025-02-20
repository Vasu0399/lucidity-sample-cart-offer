package com.springboot.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@RestController
public class AutowiredController {

    // A list to store all offers
    List<OfferRequest> allOffers = new ArrayList<>();

    @PostMapping(path = "/api/v1/offer")
    public ApiResponse postOperation(@RequestBody OfferRequest offerRequest) {
        System.out.println("Offer added: " + offerRequest);
        allOffers.add(offerRequest);
        return new ApiResponse("success");
    }

    public ApplyOfferResponse applyOffer(@RequestBody ApplyOfferRequest applyOfferRequest) throws Exception {
        System.out.println("Applying offer for request: " + applyOfferRequest);
    
        int cartVal = applyOfferRequest.getCart_value();
        SegmentResponse segmentResponse = getSegmentResponse(applyOfferRequest.getUser_id());
        System.out.println("Retrieved segment: " + segmentResponse.getSegment());
    
        Optional<OfferRequest> matchRequest = allOffers.stream()
                .filter(x -> x.getRestaurant_id() == applyOfferRequest.getRestaurant_id())
                .filter(x -> x.getCustomer_segment().contains(segmentResponse.getSegment()))
                .findFirst();
    
        if (matchRequest.isPresent()) {
            OfferRequest gotOffer = matchRequest.get();
    
            if (gotOffer.getOffer_type().equals("FLATX")) {
                System.out.println("Applying flat offer: " + gotOffer.getOffer_value());
                cartVal = cartVal - gotOffer.getOffer_value();
            } else if (gotOffer.getOffer_type().equals("FLATX%")) {
                int discountPercentage = gotOffer.getOffer_value();
                cartVal = (int) (cartVal - cartVal * discountPercentage * 0.01);
            }
        } else {
            System.out.println("No matching offer found.");
        }
    
        // Ensure cart value doesn't go below 0
        if (cartVal < 0) {
            cartVal = 0;
        }
    
        // Return the updated cart value in the response
        return new ApplyOfferResponse(cartVal);
    }      

    // Method to get the segment based on the user ID
    public SegmentResponse getSegmentResponse(int userid) {
        SegmentResponse segmentResponse = new SegmentResponse();
        if (userid == 1) {
            segmentResponse.setSegment("p1");
        } else if (userid == 2) {
            segmentResponse.setSegment("p2");
        } else if (userid == 3) {
            segmentResponse.setSegment("p3");
        } else {
            segmentResponse.setSegment("unknown");
        }
        return segmentResponse;
    }
}