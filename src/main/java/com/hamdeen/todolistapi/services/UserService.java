package com.hamdeen.todolistapi.services;

import com.hamdeen.todolistapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        var user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}
