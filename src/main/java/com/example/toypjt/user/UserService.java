package com.example.toypjt.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public String singIn(UserSignInRequestDto requestDto) {
        Optional<User> optionalUser = userRepository.findByUsername(requestDto.getUsername());

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        User user = optionalUser.get();

        if(!user.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return "로그인 성공";
    }
}
