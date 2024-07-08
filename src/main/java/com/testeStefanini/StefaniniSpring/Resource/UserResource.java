package com.testeStefanini.StefaniniSpring.Resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.testeStefanini.StefaniniSpring.DTO.UserDTO;
import com.testeStefanini.StefaniniSpring.DTO.UserDTOPost;
import com.testeStefanini.StefaniniSpring.DTO.UserDtoPut;
import com.testeStefanini.StefaniniSpring.DTO.Record.LoginRequestDTO;
import com.testeStefanini.StefaniniSpring.DTO.Response.UserResponseDTO;
import com.testeStefanini.StefaniniSpring.Entities.User;
import com.testeStefanini.StefaniniSpring.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserResource {// recursos da classe User
    
    @Autowired
    private UserService service;

    @GetMapping //Para o spring entender que é um metodo get do HTTP tem que usar essa anotation
    public ResponseEntity<List<UserDTO>> fiandAll(){//responseEntity é um tipo de retorno do spring que retorna respostas de requisições web
        //aqui ele está retornando todos os usuarios
        List<User> list = service.findAll();
        List<UserDTO> listDtoUsers = list.stream().map(UserDTO::new).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(listDtoUsers);
        //ok() é para retornar a resposta com sucesso no http
        //body() retornar o corpo da resposta nesse caso retorna o corpo de u
    }

    @GetMapping(value = {"/{id}"})//retornando os usuarios pelo id
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){//para reconhecer que o id do getmappin é o muesmo da entrada nos usamos o Pathvariable

        User objUser = service.findById(id);
        UserDTO objUserDtoGet = new UserDTO(objUser);
        return ResponseEntity.ok().body(objUserDtoGet);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@Valid @RequestBody UserDTOPost objUserDTOPost) {//RequestBody serve para desserializar o objeto
        User newUser = new User(null, objUserDTOPost.getName(),objUserDTOPost.getEmail(),objUserDTOPost.getPhone(),objUserDTOPost.getPassword());
		newUser = service.insert(newUser);
        UserResponseDTO responseNewUser = new UserResponseDTO(newUser);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(uri).body(responseNewUser);//nos usamos create para retornar 201, é mais apropriado para essa situação
        //o criate pode um objeto do tipo URI
	}

    @DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {//path sever para reconhecer o caminho
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
    
    @PutMapping(value = "/{id}")
	public ResponseEntity<UserResponseDTO> update( @PathVariable Long id,@Valid @RequestBody UserDtoPut objUser) {//como aqui vc prescisa reconhecer o Id e mexer com os atributos internos do usuario vc usa essas duas anotations
		User newUserPut = new User(id, objUser.getName(), objUser.getEmail(), objUser.getPhone(), null);
        newUserPut= service.update(id, newUserPut);
        UserResponseDTO responseNewUserUpdate = new UserResponseDTO(newUserPut); 
		return ResponseEntity.ok().body(responseNewUserUpdate);
	}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
    boolean success = service.login(loginRequestDTO.email(), loginRequestDTO.password());
    if (success) {
        return ResponseEntity.ok("Login successful");
    }
    return ResponseEntity.status(401).body("Invalid credentials");
}
}



