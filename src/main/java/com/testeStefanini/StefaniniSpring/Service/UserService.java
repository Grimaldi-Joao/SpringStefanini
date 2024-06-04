package com.testeStefanini.StefaniniSpring.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.testeStefanini.StefaniniSpring.Entities.User;
import com.testeStefanini.StefaniniSpring.Repository.UserRepository;
import com.testeStefanini.StefaniniSpring.Service.exceptions.CreateNewUserException;
import com.testeStefanini.StefaniniSpring.Service.exceptions.DatabaseException;
import com.testeStefanini.StefaniniSpring.Service.exceptions.ResourceNotFoundException;
import com.testeStefanini.StefaniniSpring.Service.exceptions.ValidationException;

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
		checkEmail(obj);
		obj.setPassword(CryptoService.encryptPassword(obj.getPassword()));
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
			validUser(obj);
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

	private void validUser(User user){
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            throw new ValidationException("Phone number is required");
        }
	}

	private void checkEmail(User user){
		List<User> allUsers = findAll();
		for (User exstUsers : allUsers) {
			if (exstUsers.getEmail().equals(user.getEmail())) {
				throw new CreateNewUserException("Email is being used");
			}
		}
	}
}
