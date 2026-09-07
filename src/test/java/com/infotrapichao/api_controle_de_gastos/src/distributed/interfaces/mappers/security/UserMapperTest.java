package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers.security;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.security.get.UserDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserMapperTest {

    @Test
    void toUser_shouldMapFieldsFromDto() {
        LocalDateTime createdAt = LocalDateTime.of(2026, 8, 23, 10, 15);
        LocalDateTime updatedAt = LocalDateTime.of(2026, 8, 23, 11, 30);
        UserDTO dto = new UserDTO(
                1,
                createdAt,
                updatedAt,
                "matheus",
                "matheus@example.com",
                "senha",
                List.of("MANAGERS", "USERS"),
                null,
                null
        );

        User user = UserMapper.toUser(dto);

        assertEquals(dto.getId(), user.getId());
        assertEquals(dto.getCreatedAt(), user.getCreatedAt());
        assertEquals(dto.getUpdatedAt(), user.getUpdatedAt());
        assertEquals(dto.getUsername(), user.getUsername());
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getPassword(), user.getPassword());
        assertEquals(dto.getRoles(), user.getRoles());
    }

    @Test
    void toUserDTOList_shouldRemovePasswords() {
        User user = new User();
        user.setId(10);
        user.setUsername("admin");
        user.setEmail("admin@example.com");
        user.setPassword("secret");
        user.setRoles(List.of("MANAGERS"));

        List<UserDTO> result = UserMapper.toUserDTOList(List.of(user));

        assertEquals(1, result.size());
        assertEquals("admin", result.getFirst().getUsername());
        assertNull(result.getFirst().getPassword());
    }
}
