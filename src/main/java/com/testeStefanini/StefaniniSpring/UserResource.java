package com.testeStefanini.StefaniniSpring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {// recursos da classe User
    
    @GetMapping //Para o spring entender que é um metodo get do HTTP tem que usar essa anotation
    public ResponseEntity<User> fiandAll(){//responseEntity é um tipo de retorno do spring que retorna respostas de requisições web
        User u = new User(1L, "joao", "email@email.com", "123456789", "123");
        return ResponseEntity.ok().body(u);
        //ok() é para retornar a resposta com sucesso no http
        //body() retornar o corpo da resposta nesse caso retorna o corpo de u
    }
}



