package com.testeStefanini.StefaniniSpring.Resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testeStefanini.StefaniniSpring.Entities.User;
import com.testeStefanini.StefaniniSpring.Service.UserService;

@RestController
@RequestMapping(value = "/login")
public class authResource {
    

    @Autowired
    private UserService service;

    @PostMapping
        public ResponseEntity<User> insert(String email, String password) {
		User obj = new User(null, null, email, null, password);
            obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
