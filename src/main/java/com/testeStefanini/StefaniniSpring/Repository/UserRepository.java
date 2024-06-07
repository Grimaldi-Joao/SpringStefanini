package com.testeStefanini.StefaniniSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeStefanini.StefaniniSpring.Entities.User;
//não usa componentes pois está erdando do JpaRepository, que já tem essa tag
public interface UserRepository extends JpaRepository<User, Long> { // repositorios sempre seram interfaçe

    public User findByEmail(String email);
}