package com.ziker0k.junit.service;

import com.ziker0k.junit.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final List<UserDto> users = new ArrayList<>();

    List<UserDto> getAll() {
        return users;
    }

    public boolean add(UserDto userDto) {
        return users.add(userDto);
    }

    public Optional<UserDto> login(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }
}
