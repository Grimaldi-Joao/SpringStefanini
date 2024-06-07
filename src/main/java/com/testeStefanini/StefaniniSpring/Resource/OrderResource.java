package com.testeStefanini.StefaniniSpring.Resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testeStefanini.StefaniniSpring.Entities.Order;
import com.testeStefanini.StefaniniSpring.DTO.OrderDTO;
import com.testeStefanini.StefaniniSpring.Service.OrderService;
import com.testeStefanini.StefaniniSpring.Service.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {// recursos da classe Order
    
    @Autowired
    private OrderService service;

    @GetMapping //Para o spring entender que é um metodo get do HTTP tem que usar essa anotation
    public List<OrderDTO> fiandAll(){//responseEntity é um tipo de retorno do spring que retorna respostas de requisições web
        
        List<Order> list = service.findAll();
        
        
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
        //ok() é para retornar a resposta com sucesso no http
        //body() retornar o corpo da resposta nesse caso retorna o corpo de u
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Order> findById(@PathVariable Long id){//para reconhecer que o id do getmappin é o muesmo da entrada nos usamos o Pathvariable

        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
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



