package com.example.pfeApi.vehicule;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vehicule")
@RequiredArgsConstructor
public class VehiculeController {
    private final VehiculeServiceImp vehiculeService;

    @GetMapping("/getByEcoleId/{id}")
    public ResponseEntity<?> getByEcole(@PathVariable("id") Long id){
        return this.vehiculeService.getByOwner(id);
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody VehiculeDto vehiculeDto){
        return this.vehiculeService.save(vehiculeDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return this.vehiculeService.delete(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id , @RequestBody Vehicule vehicule){
        return this.vehiculeService.update(id,vehicule);
    }
}
