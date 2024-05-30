package com.testeStefanini.StefaniniSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeStefanini.StefaniniSpring.Entities.Product;
//não usa componentes pois está erdando do JpaRepository, que já tem essa tag
public interface ProductRepository extends JpaRepository<Product, Long> { // repositorios sempre seram interfaçe

}
