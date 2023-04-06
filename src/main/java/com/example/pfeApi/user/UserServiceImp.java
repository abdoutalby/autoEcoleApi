package com.example.pfeApi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<?> enable(Integer id) {
        var user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setEnabled(true);
        return ResponseEntity.ok(userRepository.save(user));
        }else {
            return ResponseEntity.badRequest().body("Invalid username");
        }
    }

    @Override
    public ResponseEntity<?> changePassword(Integer id, String newPassword) {
        var user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return ResponseEntity.ok(userRepository.save(user));
        }else {
            return ResponseEntity.badRequest().body("user not found");
        }
    }
}
