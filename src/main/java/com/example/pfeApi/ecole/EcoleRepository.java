package com.example.pfeApi.ecole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EcoleRepository extends JpaRepository<Ecole , Long> {
    Optional<Ecole> findEcoleByEmail(String username);
}
