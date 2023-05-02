package com.example.pfeApi.payment;

import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<?> save(PaymentDto paymentDto);
    ResponseEntity<?> getAll();
    ResponseEntity<?> getAllByUser(Integer id );
    ResponseEntity<?> getAllByEcole(Long id);
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> update(Long id, PaymentDto paymentDto);

}
