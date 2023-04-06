package com.example.pfeApi.ecole;


import org.springframework.http.ResponseEntity;

public interface EcoleService {
    ResponseEntity<?> save(Ecole ecole);
    ResponseEntity<?> getAll();
    ResponseEntity<?> getAllClients(Long id);
}
