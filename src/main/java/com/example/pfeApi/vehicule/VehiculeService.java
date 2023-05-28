package com.example.pfeApi.vehicule;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface VehiculeService {
    ResponseEntity<?> save(VehiculeDto vehicule , MultipartFile image);
    ResponseEntity<?> getByOwner(Long id);

    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> update(Long id , Vehicule vehicule);
}
