package com.example.ProjectBinarFood.payloads.requests;

import com.example.ProjectBinarFood.models.OrderDetail;

import java.util.List;

public class CreateOrderRequest {

    private String userId;
    private String destinationAddress;
    private List<OrderDetail> orderDetails;

    public CreateOrderRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
