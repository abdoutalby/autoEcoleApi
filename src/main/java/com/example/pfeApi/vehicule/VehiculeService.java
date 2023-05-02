package com.example.pfeApi.vehicule;

import org.springframework.http.ResponseEntity;

public interface VehiculeService {
    ResponseEntity<?> save(VehiculeDto vehicule);
    ResponseEntity<?> getByOwner(Long id);

}
