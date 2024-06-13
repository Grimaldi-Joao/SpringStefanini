package com.testeStefanini.StefaniniSpring.DTOresourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testeStefanini.StefaniniSpring.DTO.OrderDTO;
import com.testeStefanini.StefaniniSpring.DTOservices.orderServices;

@RestController
@RequestMapping(value = "/ordersDTO")
public class orderResourse {

    @Autowired
    private orderServices service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        OrderDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
