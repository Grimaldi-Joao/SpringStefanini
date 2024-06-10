package com.testeStefanini.StefaniniSpring.DTOresourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testeStefanini.StefaniniSpring.DTO.OrderDTO;
import com.testeStefanini.StefaniniSpring.Service.OrderService;

@RestController
@RequestMapping(value = "/ordersDTO")
public class orderResourse {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAllDTO() {
        List<OrderDTO> list = service.findAllDTO();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findByIdDTO(@PathVariable Long id) {
        OrderDTO dto = service.findByIdDTO(id);
        return ResponseEntity.ok().body(dto);
    }
}
