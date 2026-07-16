package com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.security;

import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll(Specification<User> userSpecification);
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
