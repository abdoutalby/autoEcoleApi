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

    @GetMapping("/getByOwnerId/{id}")
    public ResponseEntity<?> getByOwnerId(@PathVariable("id") Integer id){
        return  ecoleService.getByOwnerId(id);
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

    @PostMapping("/addMentor/{id}/{idMentor}")
    public ResponseEntity<?> addMentor(@PathVariable("id") Long id , @PathVariable("idMentor")Integer idClient){
        return this.ecoleService.addMentor(id,idClient);
    }
    @PostMapping("/removeMentor/{id}/{idMentor}")
    public ResponseEntity<?> remoteMentor(@PathVariable("id") Long id , @PathVariable("idMentor")Integer idClient){
        return this.ecoleService.removeMentor(id,idClient);
    }
    @GetMapping("/getAllMentors/{id}")
    public ResponseEntity<?> getMentors(@PathVariable("id") Long id ){
        return ecoleService.getAllMentors(id);
    }


}
