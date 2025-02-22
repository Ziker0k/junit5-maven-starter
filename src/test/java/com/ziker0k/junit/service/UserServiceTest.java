package com.ziker0k.junit.service;

import com.ziker0k.junit.dto.UserDto;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.*;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
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
//        assertEquals(2, users.size());
        assertThat(users).hasSize(2);
    }

    @Test
    void loginSuccessIfUserExists() {
        userService.add(MAXIM);
        Optional<UserDto> maybeUser = userService.login(MAXIM.getUsername(), MAXIM.getPassword());

//        assertTrue(maybeUser.isPresent());
        assertThat(maybeUser).isPresent();

//        maybeUser.ifPresent(userDto -> assertEquals(MAXIM, userDto));
        maybeUser.ifPresent(userDto -> assertThat(userDto).isEqualTo(MAXIM));
    }

    @Test
    void usersConvertedToMapById() {
        userService.add(MAXIM, EVGENIY);

        Map<Long, UserDto> users = userService.getAllConvertedById();

        MatcherAssert.assertThat(users, IsMapContaining.hasKey(EVGENIY.getId()));

        assertAll(
                () -> assertThat(users).containsKeys(MAXIM.getId(), EVGENIY.getId()),
                () -> assertThat(users).containsValues(MAXIM, EVGENIY)
        );

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
