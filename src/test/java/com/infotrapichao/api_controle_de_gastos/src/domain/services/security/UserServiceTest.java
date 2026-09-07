package com.infotrapichao.api_controle_de_gastos.src.domain.services.security;

import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.security.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        userService = new UserService(userRepository);
        Field field = UserService.class.getDeclaredField("cripty");
        field.setAccessible(true);
        field.set(userService, passwordEncoder);
    }

    @Test
    void createUser_shouldEncodePasswordBeforeSaving() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setRoles(List.of("MANAGERS"));

        when(passwordEncoder.encode("123456")).thenReturn("encoded-password");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User saved = userService.createUser(user);

        assertEquals("encoded-password", saved.getPassword());
        verify(passwordEncoder).encode("123456");
        verify(userRepository).save(user);
    }

    @Test
    void updateUser_shouldEncodeNewPasswordAndStampUpdateTime() {
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("new-password");

        when(userRepository.existsById(1)).thenReturn(true);
        when(passwordEncoder.encode("new-password")).thenReturn("encoded-new-password");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User updated = userService.updateUser(user);

        assertEquals("encoded-new-password", updated.getPassword());
        assertNotNull(updated.getUpdatedAt());
        verify(passwordEncoder).encode("new-password");
        verify(userRepository).save(user);
    }

    @Test
    void findAll_shouldDelegateToRepository() {
        when(userRepository.findAll()).thenReturn(List.of(new User()));

        List<User> users = userService.findAll();

        assertEquals(1, users.size());
        verify(userRepository).findAll();
    }
}
