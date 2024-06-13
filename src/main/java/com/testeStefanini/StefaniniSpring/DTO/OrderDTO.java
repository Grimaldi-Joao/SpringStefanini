package com.testeStefanini.StefaniniSpring.DTO;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testeStefanini.StefaniniSpring.Entities.Order;
import com.testeStefanini.StefaniniSpring.Entities.OrderItem;
import com.testeStefanini.StefaniniSpring.Entities.Enum.OrderStatus;

public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    private Set<OrderItem> items = new HashSet<>();

    public OrderDTO() {
    }

    public OrderDTO(Long id, Instant moment, OrderStatus orderStatus, Set<OrderItem> items) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus != null ? orderStatus.getCode() : null;
        this.items = items;
    }

    // Construtor para criar OrderDTO a partir de Order
    public OrderDTO(Order order) {
        this.id = order.getId();
        this.moment = order.getMoment();
        this.orderStatus = order.getOrderStatus().getCode();
        this.items = order.getItems();
    }

    // Getters and Setters
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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    // hashCode e equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderDTO other = (OrderDTO) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId())) {
            return false;
        }
        return true;
    }
}
