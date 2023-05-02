package com.example.pfeApi.vehicule;

import com.example.pfeApi.ecole.Ecole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehiculeRepository extends JpaRepository<Vehicule , Long> {
   Vehicule[] findVehiculeByEcole(Ecole id);

}
