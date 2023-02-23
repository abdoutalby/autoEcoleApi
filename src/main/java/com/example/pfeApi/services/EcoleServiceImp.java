package com.example.pfeApi.services;

import com.example.pfeApi.model.Ecole;
import com.example.pfeApi.repositories.EcoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EcoleServiceImp implements EcoleService{
    @Autowired
    EcoleRepository ecoleRepository;
    @Override
    public ResponseEntity<?> getAllEcoles() {
        return ResponseEntity.ok(ecoleRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getEcoleById(Long id) {
        return ResponseEntity.ok(ecoleRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> addEcole(Ecole ecole) {
        if(ecole.getNom().isEmpty() || ecole.getDescription().isEmpty()){
            return ResponseEntity.status(400).body("Les champs nom et description sont obligatoires");
        }
        return ResponseEntity.ok(ecoleRepository.save(ecole));
    }
}
