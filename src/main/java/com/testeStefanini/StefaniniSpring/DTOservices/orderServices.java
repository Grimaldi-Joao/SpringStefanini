package com.testeStefanini.StefaniniSpring.DTOservices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeStefanini.StefaniniSpring.Entities.Order;
import com.testeStefanini.StefaniniSpring.DTO.OrderDTO;
import com.testeStefanini.StefaniniSpring.Repository.OrderRepository;
import com.testeStefanini.StefaniniSpring.Service.exceptions.ResourceNotFoundException;

@Service
public class orderServices {

    @Autowired
    private OrderRepository repository;

    public List<OrderDTO> findAll() {
        List<Order> orders = repository.findAll();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderDTO findById(Long id) {
        Optional<Order> order = repository.findById(id);
        return order.map(this::convertToDTO).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private OrderDTO convertToDTO(Order order) {
        return new OrderDTO(
            order.getId(),
            order.getMoment(),
            order.getOrderStatus().toString(),
            order.getClient().getName(),
            order.getTotal()
        );
    }
}
