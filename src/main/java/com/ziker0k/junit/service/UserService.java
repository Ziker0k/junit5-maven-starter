package com.ziker0k.junit.service;

import com.ziker0k.junit.dto.UserDto;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class UserService {

    private final List<UserDto> users = new ArrayList<>();

    List<UserDto> getAll() {
        return users;
    }

//    public boolean add(UserDto userDto) {
//        return users.add(userDto);
//    }

    public void add(UserDto... userDtos) {
        this.users.addAll(Arrays.asList(userDtos));
    }

    public Optional<UserDto> login(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password are required");
        }
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }

    public Map<Long, UserDto> getAllConvertedById() {
        return users.stream()
                .collect(toMap(UserDto::getId, Function.identity()));
    }
}
