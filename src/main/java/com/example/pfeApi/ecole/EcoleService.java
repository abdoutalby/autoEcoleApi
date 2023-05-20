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

    ResponseEntity<?> getByOwnerId(Integer id);

    ResponseEntity<?> addMentor(Long id, Integer idClient);

    ResponseEntity<?> removeMentor(Long id, Integer idClient);

    ResponseEntity<?> getAllMentors(Long id);
}
