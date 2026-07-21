package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.controllers.security;


import com.infotrapichao.api_controle_de_gastos.src.application.contracts.security.IUserApplication;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.created.security.UserCreatedRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.get.GastoRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.get.security.UserRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.updated.security.UserUpdatedRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.security.UserDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers.GastoMapper;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers.security.UserMapper;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final IUserApplication _userApplication;

    public UserController(IUserApplication userApplication){
        this._userApplication = userApplication;
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserCreatedRequestDTO userDTO){

        User usuario = UserMapper.toUser(userDTO);
        var userCreated = _userApplication.createUser(usuario);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }

    @PutMapping()
    public ResponseEntity<User> put(@Valid @RequestBody UserUpdatedRequestDTO userDTO){

        User usuario = UserMapper.toUser(userDTO);
        var userUpdated = _userApplication.updateUser(usuario);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userUpdated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userUpdated);    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll(){
        var lista = UserMapper.toUserDTOList(_userApplication.findAll());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Integer id){
        var user = _userApplication.findById(id);
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/filtrar")
    public ResponseEntity<List<UserDTO>> filtrar(@ModelAttribute UserRequestDTO filter) {

        UserDTO dto = UserMapper.toUserDTO(filter);
        var users = _userApplication.findAllByFilter(dto);
        var lista = UserMapper.toUserDTOList(users);
        return ResponseEntity.ok(lista);
    }
}
