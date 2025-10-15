package com.example.toypjt.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInRequestDto {
    private String username;
    private String password;
}
