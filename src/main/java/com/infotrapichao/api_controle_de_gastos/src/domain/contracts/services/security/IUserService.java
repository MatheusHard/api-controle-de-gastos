package com.infotrapichao.api_controle_de_gastos.src.domain.contracts.services.security;

import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;

import java.util.List;

public interface IUserService {

    User findById(Integer id);
    User createUser(User user);
    User updateUser(User user);
    List<User> findAll();
    User findByUsername(String username);
}
