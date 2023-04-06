package com.example.pfeApi.ecole;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EcoleServiceImp implements EcoleService{
    private final EcoleRepository ecoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> save(Ecole ecole) {

        ecole.setPassword(passwordEncoder.encode(ecole.getPassword()));
        return ResponseEntity.ok(ecoleRepository.save(ecole));
    }

    @Override
    public ResponseEntity<?> getAllClients(Long id) {
        Optional<Ecole> ecole = ecoleRepository.findById(id);
        if (ecole.isPresent()) {
            return ResponseEntity.ok(ecole.get().getClients());
        }else {
        return  ResponseEntity.ok("no clients found");
        }

    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ecoleRepository.findAll());
    }
}
