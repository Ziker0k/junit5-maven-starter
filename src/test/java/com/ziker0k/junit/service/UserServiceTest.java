package com.ziker0k.junit.service;

import com.ziker0k.junit.dto.UserDto;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {
    private UserService userService;

    @BeforeAll
    void setUp() {
        System.out.println("Before all tests: " + this);
    }

    @BeforeEach
    void prepare() {
        System.out.println("Before each test: " + this);
        userService = new UserService();
    }

    @Test
    void usersEmptyIfNoUserAdded() {
        System.out.println("Test 1: " + this);
        var users = userService.getAll();
        assertTrue(users.isEmpty());
    }

    @Test
    void userSizeIfUserAdded() {
        System.out.println("Test 2: " + this);
        userService.add(new UserDto());
        userService.add(new UserDto());

        var users = userService.getAll();
        assertEquals(2, users.size());
    }

    @AfterEach
    void cleanUp() {
        System.out.println("After each test: " + this);
    }

    @AfterAll
    void cleanUpAll() {
        System.out.println("After all tests: " + this);
    }
}
