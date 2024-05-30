package com.testeStefanini.StefaniniSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeStefanini.StefaniniSpring.Entities.OrderItem;
import com.testeStefanini.StefaniniSpring.Entities.PK.OrderItemPK;
//não usa componentes pois está erdando do JpaRepository, que já tem essa tag
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> { // repositorios sempre seram interfaçe

}