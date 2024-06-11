package com.testeStefanini.StefaniniSpring.DTO;

import java.time.Instant;

public class OrderDTO {
    private Long id;
    private Instant moment;
    private String orderStatus;
    private String clientName;
    private Double total;

    public OrderDTO() {}

    public OrderDTO(Long id, Instant moment, String orderStatus, String clientName, Double total) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.clientName = clientName;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
