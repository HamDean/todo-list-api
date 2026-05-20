package com.hamdeen.todolistapi.services;

import com.hamdeen.todolistapi.dtos.RegisterUserRequest;
import com.hamdeen.todolistapi.dtos.UserDto;
import com.hamdeen.todolistapi.entities.User;
import com.hamdeen.todolistapi.mappers.UserMapper;
import com.hamdeen.todolistapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto registerUser(RegisterUserRequest request) {
        var user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(new Date());

        userRepository.save(user);
        return userMapper.toUserDto(user);
    }
}
