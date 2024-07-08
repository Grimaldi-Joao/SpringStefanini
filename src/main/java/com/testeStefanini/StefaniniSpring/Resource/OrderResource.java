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
import com.testeStefanini.StefaniniSpring.Service.OrderService;
import com.testeStefanini.StefaniniSpring.DTO.OrderDTO;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {// recursos da classe Order
    
    @Autowired
    private OrderService service;

    @GetMapping //Para o spring entender que é um metodo get do HTTP tem que usar essa anotation
    public ResponseEntity<List<OrderDTO>> fiandAll(){//responseEntity é um tipo de retorno do spring que retorna respostas de requisições web
        
        List<Order> list = service.findAll();

        List<OrderDTO> dtoListGet = list.stream().map(OrderDTO::new).collect(Collectors.toList());
        //Stream pe uma função faz o processamento em sequencia dos dados da minha lista
        //map aplica uma função especifica para cada elemento da stream,(OrderDTO::new) faz referencia a o contrutor OrderDTO(Order order)
        //collect seleciona todos os elementos da stream,(Collectors.toList()) pega todos os coletaveis e converte ele para uma lista
        
        return ResponseEntity.ok().body(dtoListGet);
        //ok() é para retornar a resposta com sucesso no http
        //body() retornar o corpo da resposta nesse caso retorna o corpo de u
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){//para reconhecer que o id do getmappin é o muesmo da entrada nos usamos o Pathvariable
        Order objOrder = service.findById(id);
        OrderDTO retornDto = new OrderDTO(objOrder);
        return ResponseEntity.ok().body(retornDto);
    }

}



