package com.springboot;

import com.springboot.controller.ApiResponse;
import com.springboot.controller.ApplyOfferRequest;
import com.springboot.controller.ApplyOfferResponse;
import com.springboot.controller.OfferRequest;
import com.springboot.controller.SegmentResponse;
import com.springboot.controller.AutowiredController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class CartOfferApplicationTests {

    @Test
    public void checkFlatPercentageDiscountGreaterThan100() throws Exception {
        List<String> segments = new ArrayList<>();
        segments.add("p1");

        OfferRequest offerRequest = new OfferRequest(1, "FLATX%", 200, segments);

        AutowiredController autowiredController = new AutowiredController() {
            @Override
            public SegmentResponse getSegmentResponse(int userid) {
                SegmentResponse mockSegmentResponse = new SegmentResponse();
                mockSegmentResponse.setSegment("p1");
                return mockSegmentResponse;
            }
        };

        ApiResponse postResponse = autowiredController.postOperation(offerRequest);
        Assert.assertEquals("success", postResponse.getResponse_msg());

        ApplyOfferRequest applyOfferRequest = new ApplyOfferRequest();
        applyOfferRequest.setCart_value(200);
        applyOfferRequest.setUser_id(1);
        applyOfferRequest.setRestaurant_id(1);

        ApplyOfferResponse response = autowiredController.applyOffer(applyOfferRequest);
        
        Assert.assertEquals(0, response.getCart_value());

        int responseCode = addOffer(offerRequest);
        Assert.assertEquals(200, responseCode);
    }

    @Test
    public void checkFlatXDiscountGreaterThanCartValue() throws Exception {
        List<String> segments = new ArrayList<>();
        segments.add("p1");

        OfferRequest offerRequest = new OfferRequest(1, "FLATX", 300, segments);

        AutowiredController autowiredController = new AutowiredController() {
            @Override
            public SegmentResponse getSegmentResponse(int userid) {
                SegmentResponse mockSegmentResponse = new SegmentResponse();
                mockSegmentResponse.setSegment("p1");
                return mockSegmentResponse;
            }
        };

        ApiResponse postResponse = autowiredController.postOperation(offerRequest);
        Assert.assertEquals("success", postResponse.getResponse_msg());

        ApplyOfferRequest applyOfferRequest = new ApplyOfferRequest();
        applyOfferRequest.setCart_value(200);
        applyOfferRequest.setUser_id(1);
        applyOfferRequest.setRestaurant_id(1);

        ApplyOfferResponse response = autowiredController.applyOffer(applyOfferRequest);
        
        Assert.assertEquals(0, response.getCart_value());

        int responseCode = addOffer(offerRequest);
        Assert.assertEquals(200, responseCode);
    }

    @Test
    public void checkFlatXForOneSegment() throws Exception {
        List<String> segments = new ArrayList<>();
        segments.add("p1");

        OfferRequest offerRequest = new OfferRequest(1, "FLATX", 10, segments);

        AutowiredController autowiredController = new AutowiredController() {
            @Override
            public SegmentResponse getSegmentResponse(int userid) {
                SegmentResponse mockSegmentResponse = new SegmentResponse();
                mockSegmentResponse.setSegment("p1");
                return mockSegmentResponse;
            }
        };

        autowiredController.postOperation(offerRequest);

        ApplyOfferRequest applyOfferRequest = new ApplyOfferRequest();
        applyOfferRequest.setCart_value(200);
        applyOfferRequest.setUser_id(1);
        applyOfferRequest.setRestaurant_id(1);

        ApplyOfferResponse response = autowiredController.applyOffer(applyOfferRequest);
        Assert.assertEquals(190, response.getCart_value());

        int responseCode = addOffer(offerRequest);
        Assert.assertEquals(200, responseCode);
    }

    @Test
    public void checkFlatPercentageForOneSegment() throws Exception {
        List<String> segments = new ArrayList<>();
        segments.add("p1");

        OfferRequest offerRequest = new OfferRequest(1, "FLATX%", 10, segments);

        AutowiredController autowiredController = new AutowiredController() {
            @Override
            public SegmentResponse getSegmentResponse(int userid) {
                SegmentResponse mockSegmentResponse = new SegmentResponse();
                if (userid == 1) {
                    mockSegmentResponse.setSegment("p1");
                } else {
                    mockSegmentResponse.setSegment("p2");
                }
                return mockSegmentResponse;
            }
        };

        ApiResponse postResponse = autowiredController.postOperation(offerRequest);
        Assert.assertEquals("success", postResponse.getResponse_msg());

        ApplyOfferRequest applyOfferRequest = new ApplyOfferRequest();
        applyOfferRequest.setCart_value(200);
        applyOfferRequest.setUser_id(1);
        applyOfferRequest.setRestaurant_id(1);

        ApplyOfferResponse response = autowiredController.applyOffer(applyOfferRequest);
        Assert.assertEquals(180, response.getCart_value());

        int responseCode = addOffer(offerRequest);
        Assert.assertEquals(200, responseCode);
    }

    @Test
    public void checkFlatXForSegmentP2() throws Exception {
        List<String> segments = new ArrayList<>();
        segments.add("p2");

        OfferRequest offerRequest = new OfferRequest(2, "FLATX", 20, segments);

        AutowiredController autowiredController = new AutowiredController() {
            @Override
            public SegmentResponse getSegmentResponse(int userid) {
                SegmentResponse mockSegmentResponse = new SegmentResponse();
                mockSegmentResponse.setSegment("p2");
                return mockSegmentResponse;
            }
        };

        autowiredController.postOperation(offerRequest);

        ApplyOfferRequest applyOfferRequest = new ApplyOfferRequest();
        applyOfferRequest.setCart_value(200);
        applyOfferRequest.setUser_id(2);
        applyOfferRequest.setRestaurant_id(2);

        ApplyOfferResponse response = autowiredController.applyOffer(applyOfferRequest);
        Assert.assertEquals(180, response.getCart_value());

        int responseCode = addOffer(offerRequest);
        Assert.assertEquals(200, responseCode);
    }

    @Test
    public void checkFlatXForSegmentP3() throws Exception {
        List<String> segments = new ArrayList<>();
        segments.add("p3");

        OfferRequest offerRequest = new OfferRequest(3, "FLATX", 30, segments);

        AutowiredController autowiredController = new AutowiredController() {
            @Override
            public SegmentResponse getSegmentResponse(int userid) {
                SegmentResponse mockSegmentResponse = new SegmentResponse();
                mockSegmentResponse.setSegment("p3");
                return mockSegmentResponse;
            }
        };

        autowiredController.postOperation(offerRequest);

        ApplyOfferRequest applyOfferRequest = new ApplyOfferRequest();
        applyOfferRequest.setCart_value(200);
        applyOfferRequest.setUser_id(3);
        applyOfferRequest.setRestaurant_id(3);

        ApplyOfferResponse response = autowiredController.applyOffer(applyOfferRequest);
        Assert.assertEquals(170, response.getCart_value());

        int responseCode = addOffer(offerRequest);
        Assert.assertEquals(200, responseCode);
    }

    @Test
    public void checkNoOfferFound() throws Exception {
        List<String> segments = new ArrayList<>();
        segments.add("p1");

        OfferRequest offerRequest = new OfferRequest(1, "FLATX", 10, segments);

        AutowiredController autowiredController = new AutowiredController() {
            @Override
            public SegmentResponse getSegmentResponse(int userid) {
                SegmentResponse mockSegmentResponse = new SegmentResponse();
                mockSegmentResponse.setSegment("p2");
                return mockSegmentResponse;
            }
        };

        autowiredController.postOperation(offerRequest);

        ApplyOfferRequest applyOfferRequest = new ApplyOfferRequest();
        applyOfferRequest.setCart_value(200);
        applyOfferRequest.setUser_id(1);
        applyOfferRequest.setRestaurant_id(1);

        ApplyOfferResponse response = autowiredController.applyOffer(applyOfferRequest);
        Assert.assertEquals(200, response.getCart_value());

        int responseCode = addOffer(offerRequest);
        Assert.assertEquals(200, responseCode);
    }

    @Test
    public void checkCartValueZero() throws Exception {
        List<String> segments = new ArrayList<>();
        segments.add("p1");

        OfferRequest offerRequest = new OfferRequest(1, "FLATX", 10, segments);

        AutowiredController autowiredController = new AutowiredController() {
            @Override
            public SegmentResponse getSegmentResponse(int userid) {
                SegmentResponse mockSegmentResponse = new SegmentResponse();
                mockSegmentResponse.setSegment("p1");
                return mockSegmentResponse;
            }
        };

        autowiredController.postOperation(offerRequest);

        ApplyOfferRequest applyOfferRequest = new ApplyOfferRequest();
        applyOfferRequest.setCart_value(0);
        applyOfferRequest.setUser_id(1);
        applyOfferRequest.setRestaurant_id(1);

        ApplyOfferResponse response = autowiredController.applyOffer(applyOfferRequest);

        Assert.assertTrue(response.getCart_value() >= 0);

        int responseCode = addOffer(offerRequest);
        Assert.assertEquals(200, responseCode);
    }

    public int addOffer(OfferRequest offerRequest) throws Exception {
        String urlString = "http://localhost:9001/api/v1/offer";
        java.net.URL url = new java.net.URL(urlString);

        java.net.HttpURLConnection con = (java.net.HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");

        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        String POST_PARAMS = mapper.writeValueAsString(offerRequest);

        try (java.io.OutputStream os = con.getOutputStream()) {
            os.write(POST_PARAMS.getBytes());
            os.flush();
        }

        int responseCode = con.getResponseCode();

        StringBuffer response = new StringBuffer();
        try (java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        return responseCode;
    }
}
