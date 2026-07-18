package com.infotrapichao.api_controle_de_gastos.src.domain.services.security;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.security.UserDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import com.infotrapichao.api_controle_de_gastos.src.domain.contracts.services.security.IUserService;
import com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.security.UserRepository;
import com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.specification.GastoSpecification;
import com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.specification.security.UserSpecification;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements IUserService {

    private final UserRepository _userRepository;

    public UserService(UserRepository userRepository){
        this._userRepository = userRepository;
    }
    @Autowired
    private PasswordEncoder cripty;

    @Override
    public User findById(Integer id) {
        return _userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public User createUser(User user){

        if(user.getId() != null && _userRepository.existsById(user.getId())){
            throw new IllegalArgumentException("Usuário já cadastrado!!!");
        }else{
            String pass = user.getPassword();
            //cript
            user.setPassword(cripty.encode(pass));
            return _userRepository.save(user);
        }
    }

    @Override
    public User updateUser(User user) {

        if(!_userRepository.existsById(user.getId())){
            throw new IllegalArgumentException("Usuário não cadastrado!!!");
        }else{
             if(user.getPassword() != null) user.setPassword(cripty.encode(user.getPassword()));
             user.setUpdatedAt(LocalDateTime.now());
             return _userRepository.save(user);
        }
    }

    @Override
    public List<User> findAll() {
        return _userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return _userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllByFilter(UserDTO filter) {

        return  _userRepository.findAll(UserSpecification.withFiltersDTO(filter));
    }

}
