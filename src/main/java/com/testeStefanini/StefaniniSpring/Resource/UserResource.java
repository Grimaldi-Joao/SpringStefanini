package com.testeStefanini.StefaniniSpring.Resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testeStefanini.StefaniniSpring.Entities.User;
import com.testeStefanini.StefaniniSpring.Service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {// recursos da classe User
    
    @Autowired
    private UserService service;

    @GetMapping //Para o spring entender que é um metodo get do HTTP tem que usar essa anotation
    public ResponseEntity<List<User>> fiandAll(){//responseEntity é um tipo de retorno do spring que retorna respostas de requisições web
        //aqui ele está retornando todos os usuarios
        List<User> list = service.findAll();
        
        
        return ResponseEntity.ok().body(list);
        //ok() é para retornar a resposta com sucesso no http
        //body() retornar o corpo da resposta nesse caso retorna o corpo de u
    }

    @GetMapping(value = {"/{id}"})//retornando os usuarios pelo id
    public ResponseEntity<User> findById(@PathVariable Long id){//para reconhecer que o id do getmappin é o muesmo da entrada nos usamos o Pathvariable

        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {//RequestBody serve para desserializar o objeto
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);//nos usamos create para retornar 201, é mais apropriado para essa situação
        //o criate pode um objeto do tipo URI
	}

    @DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {//path sever para reconhecer o caminho
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
    
    @PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {//como aqui vc prescisa reconhecer o Id e mexer com os atributos internos do usuario vc usa essas duas anotations
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean success = service.login(user.getEmail(), user.getPassword());
        if (success) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

    }
}



