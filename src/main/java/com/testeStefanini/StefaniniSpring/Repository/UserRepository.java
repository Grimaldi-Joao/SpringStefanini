package com.testeStefanini.StefaniniSpring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { // repositorios sempre seram interfaçe

}