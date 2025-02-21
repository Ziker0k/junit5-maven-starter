package com.ziker0k.junit.service;

import com.ziker0k.junit.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final List<UserDto> users = new ArrayList<>();

    List<UserDto> getAll() {
        return users;
    }

    public boolean add(UserDto userDto) {
        return users.add(userDto);
    }
}
