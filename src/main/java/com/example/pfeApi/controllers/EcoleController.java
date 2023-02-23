package com.example.pfeApi.controllers;

import com.example.pfeApi.model.Ecole;
import com.example.pfeApi.services.EcoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/ecole")
public class EcoleController {

    @Autowired
    EcoleService ecoleService;

    @PostMapping()
    public ResponseEntity<?> addEcole(Ecole ecole){
        return this.ecoleService.addEcole(ecole);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEcoles(){
        return this.ecoleService.getAllEcoles();
    }
}
