package com.testeStefanini.StefaniniSpring.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.testeStefanini.StefaniniSpring.Entities.User;
import com.testeStefanini.StefaniniSpring.Repository.UserRepository;
import com.testeStefanini.StefaniniSpring.Resource.Exception.Enum.ExceptionEnum;
import com.testeStefanini.StefaniniSpring.Service.exceptions.CreateNewUserException;
import com.testeStefanini.StefaniniSpring.Service.exceptions.DatabaseException;
import com.testeStefanini.StefaniniSpring.Service.exceptions.PasswordException;
import com.testeStefanini.StefaniniSpring.Service.exceptions.ResourceNotFoundException;
import com.testeStefanini.StefaniniSpring.Service.exceptions.ValidationException;

import jakarta.persistence.EntityNotFoundException;

@Service// não se usa um @component pois como isso é um serviço é para deixar semanticamente correto
public class UserService {

    @Autowired
    private UserRepository repository;

	@Autowired
	private CryptoService cryptoService;

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,63}$";

    public List<User> findAll(){
        return repository.findAll();
    }

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id,ExceptionEnum.Resource_not_found));
	}

    public User insert(User obj) {//inseriri um novo objeto do tipo user
		checkEmail(obj);
		checkPas(obj);
		obj.setPassword(CryptoService.encryptPassword(obj.getPassword()));
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id, ExceptionEnum.Resource_not_found);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage(), ExceptionEnum.Database_error);//aqui é uma excessão da minha camada de serviço
		}
	}
        /*
         * catch(RunTimeExepition e){
         * e.printStackTrace();
         * }
         * use esse codigo para imprimir o erro no terminal e descobri como trata-lo
         */

    public User update(Long id, User obj) {//não ter campo vazio sendo att
		try {
			User entity = repository.getReferenceById(id);
			validUser(obj);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id,ExceptionEnum.Validation_error);
		}	
	}

    private void updateData(User entity, User obj) { //atualização de usuario
		if (obj.getName() != null) {
			entity.setName(obj.getName());
		}
		if (obj.getEmail() != null) {
			checkEmail(obj);
			entity.setEmail(obj.getEmail());
		}
		if (obj.getPhone() != null) {
			entity.setPhone(obj.getPhone());
		}
	}

	private void validUser(User user){
		if (user.getEmail() == null && user.getPhone() == null && user.getName() == null) {
			throw new ValidationException("You need to fill in the blanks ",ExceptionEnum.Validation_error);
		}
        if (user.getEmail().isEmpty()) {
            throw new ValidationException("Email is required",ExceptionEnum.Validation_error);
        }
        if (user.getPhone().isEmpty()) {
            throw new ValidationException("Phone number is required", ExceptionEnum.Validation_error);
        }
	}

	private void checkEmail(User user){
		/*for (User exstUsers : allUsers) {
			if (exstUsers.getEmail().equals(user.getEmail())) {
				throw new CreateNewUserException("Email is being used");  função menos eficiente
			}
		}*/
		if(repository.existsByEmail(user.getEmail()) != false){
			throw new CreateNewUserException("Email is being used", ExceptionEnum.New_user_error);
		}
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(user.getEmail());
		if (matcher.matches()!= true) {
			throw new ValidationException("Email invalido", ExceptionEnum.Validation_error);
		}
	}

	private void checkPas(User user){
		if (user.getPassword().length() <= 8) {
			throw new PasswordException("Password is invalid",ExceptionEnum.password_invalid);
		}
	}

	public boolean login(String email, String senha){
		User user = repository.findByEmail(email);

		if (user == null) {
			return false;
		}
		return cryptoService.matches(senha,user.getPassword());
	}

}
