package com.example.pfeApi.user;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface UserService {
    ResponseEntity<?> enable(Integer id);
    ResponseEntity<?> changePassword( Integer id,  String newPassword);
    ResponseEntity<?> desable(Integer id);
    ResponseEntity<?> getAll();

    ResponseEntity<?> delete(Integer id) throws IOException;

    ResponseEntity<?> findByEmail(String email);

    ResponseEntity<?> getAllActiveEcole();

    ResponseEntity<?> getAllInactiveEcole();

    ResponseEntity<?> getAllEcoles();
}
