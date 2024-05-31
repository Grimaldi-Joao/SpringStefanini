package com.testeStefanini.StefaniniSpring.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.testeStefanini.StefaniniSpring.Entities.User;
import com.testeStefanini.StefaniniSpring.Repository.UserRepository;
import com.testeStefanini.StefaniniSpring.Service.exceptions.DatabaseException;
import com.testeStefanini.StefaniniSpring.Service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service// não se usa um @component pois como isso é um serviço é para deixar semanticamente correto
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

    public User insert(User obj) {//inseriri um novo objeto do tipo user
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());//aqui é uma excessão da minha camada de serviço
		}
	}
        /*
         * catch(RunTimeExepition e){
         * e.printStackTrace();
         * }
         * use esse codigo para imprimir o erro no terminal e descobri como trata-lo
         */

    public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}

    private void updateData(User entity, User obj) { //atualização de usuario
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}

}
