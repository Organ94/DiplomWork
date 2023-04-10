package com.example.diplomwork.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.junit.jupiter.api.Assertions.*;
import static com.example.diplomwork.service.TestData.*;

class AuthenticationRepositoryTest {

    private AuthenticationRepository authenticationRepository;

    private final Map<String, String> testTokensAndUsernames = new ConcurrentHashMap<>();

    @BeforeEach
    void setUp() {
        authenticationRepository = new AuthenticationRepository();
        authenticationRepository.putTokenAndUsername(TOKEN_1, USERNAME_1);
        testTokensAndUsernames.clear();
        testTokensAndUsernames.put(TOKEN_1, USERNAME_1);
    }

    @Test
    void putTokenAndUsername() {
        String beforePut = authenticationRepository.getUsernameByToken(TOKEN_2);
        assertNull(beforePut);
        authenticationRepository.putTokenAndUsername(TOKEN_2, USERNAME_2);
        String afterPut = authenticationRepository.getUsernameByToken(TOKEN_2);
        assertEquals(USERNAME_2, afterPut);
    }

    @Test
    void removeTokenAndUsernameByToken() {
        String beforeRemove = authenticationRepository.getUsernameByToken(TOKEN_1);
        assertNotNull(beforeRemove);
        authenticationRepository.removeTokenAndUsernameByToken(TOKEN_1);
        String afterRemove = authenticationRepository.getUsernameByToken(TOKEN_1);
        assertNull(afterRemove);
    }

    @Test
    void getUsernameByToken() {
        assertEquals(testTokensAndUsernames.get(TOKEN_1), authenticationRepository.getUsernameByToken(TOKEN_1));
    }
}
