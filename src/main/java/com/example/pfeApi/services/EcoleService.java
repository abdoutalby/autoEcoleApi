package com.example.pfeApi.services;

import com.example.pfeApi.model.Ecole;
import org.springframework.http.ResponseEntity;

public interface EcoleService {
    ResponseEntity<?> getAllEcoles();
    ResponseEntity<?> getEcoleById(Long id);
    ResponseEntity<?> addEcole(Ecole ecole);


}
