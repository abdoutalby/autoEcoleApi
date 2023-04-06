package com.example.pfeApi.ecole;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/ecole")
public class EcoleController {
    private final EcoleServiceImp ecoleService;


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ecoleService.getAll();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Ecole ecole){
        return ecoleService.save(ecole);
    }

    @GetMapping("/getAllClients/{id}")
    public ResponseEntity<?> getAllClients(@PathVariable("id") Long id ){
        return ecoleService.getAllClients(id);
    }
}
