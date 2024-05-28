package com.testeStefanini.StefaniniSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeStefanini.StefaniniSpring.Entities.Order;
//não usa componentes pois está erdando do JpaRepository, que já tem essa tag
public interface OrderRepository extends JpaRepository<Order, Long> { // repositorios sempre seram interfaçe

}