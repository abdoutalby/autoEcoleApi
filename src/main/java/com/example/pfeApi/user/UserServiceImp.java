package com.example.pfeApi.user;

import com.example.pfeApi.utils.API;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            return API.getResponseEntity("Invalid username",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> changePassword(Integer id, String newPassword) {
        var user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return ResponseEntity.ok(userRepository.save(user));
        }else {
            return API.getResponseEntity("user not found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
