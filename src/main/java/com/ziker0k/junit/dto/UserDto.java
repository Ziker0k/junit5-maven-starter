package com.ziker0k.junit.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class UserDto {
    Long id;
    String username;
    String password;
}
