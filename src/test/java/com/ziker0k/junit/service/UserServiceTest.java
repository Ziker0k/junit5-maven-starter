package com.ziker0k.junit.service;

import com.ziker0k.junit.dto.UserDto;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {
    private static final UserDto MAXIM = UserDto.of(1L, "Maxim", "password");
    private static final UserDto EVGENIY = UserDto.of(2L, "Evgeniy", "123");
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
        userService.add(MAXIM);
        userService.add(EVGENIY);

        var users = userService.getAll();
        assertEquals(2, users.size());
    }

    @Test
    void loginSuccessIfUserExists() {
        userService.add(MAXIM);
        Optional<UserDto> maybeUser = userService.login(MAXIM.getUsername(), MAXIM.getPassword());

        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(userDto -> assertEquals(MAXIM, userDto));
    }

    @Test
    void loginFailIfPasswordIncorrect() {
        userService.add(MAXIM);
        var maybeUser = userService.login(EVGENIY.getUsername(), "1234");

        assertTrue(maybeUser.isEmpty());
    }

    @Test
    void loginFailIfUserNotFound() {
        userService.add(EVGENIY);

        var maybeUser = userService.login("dumm", "12345");

        assertTrue(maybeUser.isEmpty());
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
