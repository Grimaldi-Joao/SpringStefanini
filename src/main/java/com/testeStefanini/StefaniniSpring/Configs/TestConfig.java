package com.testeStefanini.StefaniniSpring.Configs;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.testeStefanini.StefaniniSpring.Entities.Category;
import com.testeStefanini.StefaniniSpring.Entities.Order;
import com.testeStefanini.StefaniniSpring.Entities.User;
import com.testeStefanini.StefaniniSpring.Entities.Enum.OrderStatus;
import com.testeStefanini.StefaniniSpring.Repository.CategoryRepository;
import com.testeStefanini.StefaniniSpring.Repository.OrderRepository;
import com.testeStefanini.StefaniniSpring.Repository.UserRepository;

@Configuration // isso demonstra que é uma classe de configuração
@Profile("test") // isso aponta que essa classe é uma configuração especifica para teste
public class TestConfig implements CommandLineRunner {// essa classe serve para popular o a nossa aplicação

    @Autowired
    private UserRepository UserRepository;// declarando dependencia

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID ,u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.DELIVERED ,u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.WATING_PAYMENT ,u1);

        Category cat1 = new Category(null, "Electronics"); 
        Category cat2 = new Category(null, "Books"); 
        Category cat3 = new Category(null, "Computers"); 


        UserRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
    }

}