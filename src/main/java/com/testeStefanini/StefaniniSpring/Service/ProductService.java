package com.testeStefanini.StefaniniSpring.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeStefanini.StefaniniSpring.Entities.Product;
import com.testeStefanini.StefaniniSpring.Repository.ProductRepository;

@Service// não se usa um @component pois como isso é um serviço é para deixar semanticamente correto
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long Id){
        Optional<Product> objProuct = repository.findById(Id);
        return objProuct.get();
    }

}
