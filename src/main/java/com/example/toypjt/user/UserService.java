package com.example.toypjt.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signUp(UserSignUpRequestDto requestDto) {
        if(userRepository.findByUsername((requestDto.getUsername())).isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }

        String rawPassword = requestDto.getPassword();

        User user = User.builder()
                .username(requestDto.getUsername())
                .password(rawPassword)
                .build();

        userRepository.save(user);
    }
}
