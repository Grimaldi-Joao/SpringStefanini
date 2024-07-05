package com.testeStefanini.StefaniniSpring.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeStefanini.StefaniniSpring.Entities.Category;
import com.testeStefanini.StefaniniSpring.Repository.CategoryRepository;

@Service// não se usa um @component pois como isso é um serviço é para deixar semanticamente correto
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long Id){
        Optional<Category> objCategory = repository.findById(Id);
        return objCategory.get();
    }

}
