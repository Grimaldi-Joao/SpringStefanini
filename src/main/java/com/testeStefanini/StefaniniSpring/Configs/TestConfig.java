package com.testeStefanini.StefaniniSpring.Configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.testeStefanini.StefaniniSpring.Entities.User;
import com.testeStefanini.StefaniniSpring.Repository.UserRepository;

@Configuration // isso demonstra que é uma classe de configuração
@Profile("test") // isso aponta que essa classe é uma configuração especifica para teste
public class TestConfig implements CommandLineRunner {// essa classe serve para popular o a nossa aplicação

    @Autowired
    private UserRepository UserRepository;// declarando dependencia

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
    
        UserRepository.saveAll(Arrays.asList(u1, u2));
    }

}