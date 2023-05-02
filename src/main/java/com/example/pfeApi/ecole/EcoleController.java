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

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id")Long  id)
    {
        return ecoleService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        return  this.ecoleService.delete(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody EcoleDto ecole){
        return ecoleService.save(ecole);
    }

    @PostMapping("/addClient/{id}/{idClient}")
    public ResponseEntity<?> addClient(@PathVariable("id") Long id , @PathVariable("idClient")Integer idClient){
        return this.ecoleService.addClient(id,idClient);
    }
    @PostMapping("/removeClient/{id}/{idClient}")
    public ResponseEntity<?> removeClient(@PathVariable("id") Long id , @PathVariable("idClient")Integer idClient){
        return this.ecoleService.removeClient(id,idClient);
    }
    @GetMapping("/getAllClients/{id}")
    public ResponseEntity<?> getAllClients(@PathVariable("id") Long id ){
        return ecoleService.getAllClients(id);
    }
}
