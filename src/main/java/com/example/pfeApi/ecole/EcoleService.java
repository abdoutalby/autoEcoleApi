package com.example.pfeApi.ecole;


import org.springframework.http.ResponseEntity;

public interface EcoleService {
    ResponseEntity<?> save(EcoleDto ecole);
    ResponseEntity<?> getAll();
    ResponseEntity<?> getAllClients(Long id);
    ResponseEntity<?> addClient(Long id , Integer clientId);
    ResponseEntity<?> removeClient(Long id , Integer clientId);
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> delete(Long id);
}
