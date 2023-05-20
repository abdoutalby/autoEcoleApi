package com.example.pfeApi.ecole;

import com.example.pfeApi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface EcoleRepository extends JpaRepository<Ecole , Long> {
    Optional<Ecole> findEcoleByEmail(String username);

    Ecole findEcoleByOwner(User user);
}
