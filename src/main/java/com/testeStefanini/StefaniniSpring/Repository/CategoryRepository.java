package com.testeStefanini.StefaniniSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeStefanini.StefaniniSpring.Entities.Category;
//não usa componentes pois está erdando do JpaRepository, que já tem essa tag
public interface CategoryRepository extends JpaRepository<Category, Long> { // repositorios sempre seram interfaçe

}
