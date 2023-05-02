package com.example.pfeApi.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final  PaymentServiceImp paymentServiceImp;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return this.paymentServiceImp.getAll();
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PaymentDto paymentDto){
        return this.paymentServiceImp.save(paymentDto);
    }
    @GetMapping("/findByUser/{id}")
    public ResponseEntity<?> getByUser(@PathVariable("id") Integer id){
        return this.paymentServiceImp.getAllByUser(id);
    }
    @GetMapping("/findByEcole/{id}")
    public ResponseEntity<?> getByEcole(@PathVariable("id") Long id){
        return this.paymentServiceImp.getAllByEcole(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id , @RequestBody PaymentDto paymentDto){
        return this.paymentServiceImp.update(id,paymentDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id ){
        return this.paymentServiceImp.delete(id);
    }
}
