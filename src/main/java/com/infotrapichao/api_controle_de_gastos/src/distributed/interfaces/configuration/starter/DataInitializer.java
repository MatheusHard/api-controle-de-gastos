package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.configuration.starter;
import com.infotrapichao.api_controle_de_gastos.src.application.contracts.security.IUserApplication;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(IUserApplication userApplication,
                           @Value("${app.bootstrap-admin.password:}") String initialPassword) {
        return args -> {
            if (userApplication.findByUsername("admin") == null) {
                if (initialPassword == null || initialPassword.length() < 16) {
                    throw new IllegalStateException("Defina app.bootstrap-admin.password com pelo menos 16 caracteres antes de criar o administrador.");
                }

                User admin = new User();
                admin.setEmail("admin@example.com");
                admin.setUsername("admin");
                admin.setUpdatedAt(LocalDateTime.now());
                admin.setCreatedAt(LocalDateTime.now());
                admin.setPassword(initialPassword);
                var roles = List.of("MANAGERS", "USERS");
                admin.setRoles(roles);

                userApplication.createUser(admin);

                System.out.println("🟢 Usuário admin criado com sucesso!");
            }
        };
    }
}
