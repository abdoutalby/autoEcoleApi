package com.example.pfeApi.vehicule;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> save(
            @RequestParam("image") MultipartFile image,
            @ModelAttribute VehiculeDto vehiculeDto){
        return this.vehiculeService.save(vehiculeDto , image);
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
