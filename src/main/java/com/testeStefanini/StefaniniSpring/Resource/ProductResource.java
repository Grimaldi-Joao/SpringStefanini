package com.testeStefanini.StefaniniSpring.Resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testeStefanini.StefaniniSpring.Entities.Product;
import com.testeStefanini.StefaniniSpring.Service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {// recursos da classe Product
    
    @Autowired
    private ProductService service;

    @GetMapping //Para o spring entender que é um metodo get do HTTP tem que usar essa anotation
    public ResponseEntity<List<Product>> fiandAll(){//responseEntity é um tipo de retorno do spring que retorna respostas de requisições web
        
        List<Product> list = service.findAll();
        
        
        return ResponseEntity.ok().body(list);
        //ok() é para retornar a resposta com sucesso no http
        //body() retornar o corpo da resposta nesse caso retorna o corpo de u
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Product> findById(@PathVariable Long id){//para reconhecer que o id do getmappin é o muesmo da entrada nos usamos o Pathvariable

        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
}



