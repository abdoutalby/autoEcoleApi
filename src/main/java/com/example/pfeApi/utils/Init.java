package com.example.pfeApi.utils;

import com.example.pfeApi.user.Role;
import com.example.pfeApi.user.User;
import com.example.pfeApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Init implements CommandLineRunner {
    private final UserRepository userRepository ;
    private final PasswordEncoder encoder;
    @Override
    public void run(String... args) throws Exception {
        if(!this.userRepository.existsByEmail("admin"))
        {this.userRepository.save(User.builder()
                        .email("admin")
                        .password("admin")
                        .adress("admin")
                        .firstname("admin")
                        .lastname("admin")
                        .phone("21238537")
                        .password(encoder.encode("admin"))
                        .enabled(true)
                        .role(Role.ADMIN)
                .build());
    }
    }
}
