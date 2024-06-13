package com.testeStefanini.StefaniniSpring.DTOservices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeStefanini.StefaniniSpring.DTO.OrderDTO;
import com.testeStefanini.StefaniniSpring.Entities.Order;
import com.testeStefanini.StefaniniSpring.Repository.OrderRepository;
import com.testeStefanini.StefaniniSpring.Service.exceptions.ResourceNotFoundException;

@Service
public class orderServices {

    @Autowired
    private OrderRepository repository;

    public List<OrderDTO> findAll() {
        List<Order> list = repository.findAll();
        return list.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    public OrderDTO findById(Long id) {
        Order obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return new OrderDTO(obj);
    }
}
