package com.example.pfeApi.user;

import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> enable(Integer id);
    ResponseEntity<?> changePassword( Integer id,  String newPassword);
    ResponseEntity<?> desable(Integer id);
    ResponseEntity<?> getAll();
}
