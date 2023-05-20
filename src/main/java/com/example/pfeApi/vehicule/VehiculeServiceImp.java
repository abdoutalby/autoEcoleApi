package com.example.pfeApi.vehicule;

import com.example.pfeApi.ecole.Ecole;
import com.example.pfeApi.ecole.EcoleRepository;
import com.example.pfeApi.utils.API;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImp implements VehiculeService{
    private final VehiculeRepository vehiculeRepository;
    private final EcoleRepository ecoleRepository;
    @Override
    public ResponseEntity<?> save(VehiculeDto vehicule) {
        Optional<Ecole> ecole = ecoleRepository.findById(vehicule.getEcoleId());
        if (ecole.isPresent()) {
            Vehicule v = Vehicule.builder()
                    .marque(vehicule.getMarque())
                    .matricule(vehicule.getMatricule())
                    .type(vehicule.getType())
                    .ecole(ecole.get())
                    .build();
            this.vehiculeRepository.save(v);
            return API.getResponseEntity("vehicule added succsefully", HttpStatus.CREATED);
        } else {
        return API.getResponseEntity("no ecole found for this call ", HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<?> getByOwner(Long id) {
        Optional<Ecole> ecole = ecoleRepository.findById(id);
        if (ecole.isPresent()){
            return ResponseEntity.ok(this.vehiculeRepository.findVehiculeByEcole(ecole.get()));
        }return API.getResponseEntity("no ecole match this call ", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Vehicule> vehicule = this.vehiculeRepository.findById(id);
        if (vehicule.isPresent()){
            this.vehiculeRepository.deleteById(id);
            return API.getResponseEntity("vehicule deleted succsefully ", HttpStatus.OK);
        }
        return API.getResponseEntity("no vehicule match this call", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(Long id, Vehicule vehicule) {
        Optional<Vehicule> v = this.vehiculeRepository.findById(id);
        if (v.isPresent()){
            Vehicule up = v.get();
            up.setEcole(vehicule.getEcole());
            up.setType(vehicule.getType());
            up.setMatricule(vehicule.getMatricule());
            return ResponseEntity.ok(this.vehiculeRepository.save(up));
        }
        return API.getResponseEntity("no vehicule match this call",HttpStatus.OK);
    }
}
