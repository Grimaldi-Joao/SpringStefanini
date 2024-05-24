package com.testeStefanini.StefaniniSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeStefanini.StefaniniSpring.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> { // repositorios sempre seram interfaçe

}