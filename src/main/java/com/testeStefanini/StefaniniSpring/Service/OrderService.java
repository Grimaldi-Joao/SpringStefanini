package com.testeStefanini.StefaniniSpring.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeStefanini.StefaniniSpring.Entities.Order;
import com.testeStefanini.StefaniniSpring.Repository.OrderRepository;

@Service// não se usa um @component pois como isso é um serviço é para deixar semanticamente correto
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long Id){
        Optional<Order> Obj = repository.findById(Id);
        return Obj.get();
    }

}
