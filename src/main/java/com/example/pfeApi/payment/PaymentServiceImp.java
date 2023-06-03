package com.example.pfeApi.payment;

import com.example.pfeApi.ecole.Ecole;
import com.example.pfeApi.ecole.EcoleRepository;
import com.example.pfeApi.user.User;
import com.example.pfeApi.user.UserRepository;
import com.example.pfeApi.utils.API;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImp implements PaymentService{
    private final PaymentRepository paymentRepository;
    private final EcoleRepository ecoleRepository;
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<?> save(PaymentDto paymentDto) {
        Optional<User> user = userRepository.findById(paymentDto.getIdUser());
        if (user.isPresent()){
            Optional<Ecole> ecole = ecoleRepository.findById(paymentDto.getIdEcole());
            if (ecole.isPresent()){
                    Payment p=  Payment.builder()
                            .amount(paymentDto.getAmount())
                            .client(user.get())
                            .ecole(ecole.get())
                            .build();
                    paymentRepository.save(p);
                    return API.getResponseEntity("payment added successfully ",HttpStatus.CREATED);
            }else {
                return API.getResponseEntity("no ecole match this call",HttpStatus.BAD_REQUEST);
            }
        }else {
            return API.getResponseEntity("no user match this call", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(paymentRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getAllByUser(Integer id) {
    Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return ResponseEntity.ok(paymentRepository.findByClient(user.get()));
        }else{
            return API.getResponseEntity("no user match this call",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAllByEcole(Long id ) {
        Optional<Ecole> ecole = ecoleRepository.findById(id);
        if (ecole.isPresent()){
            return ResponseEntity.ok(paymentRepository.findByEcole(ecole.get()));
        }else {
            return API.getResponseEntity("no ecole match this call",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()){
            paymentRepository.delete(payment.get());
            return API.getResponseEntity("payment removed ",HttpStatus.OK);
        }
        return API.getResponseEntity("no payment matching this call", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> update(Long id , PaymentDto paymentDto) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()){
            Optional<User> user = userRepository.findById(paymentDto.getIdUser());
            if (user.isPresent()){
                Optional<Ecole> ecole = ecoleRepository.findById(paymentDto.getIdEcole());
                if (ecole.isPresent()){
                    Payment p=  Payment.builder()
                            .id(id)
                            .amount(paymentDto.getAmount())
                            .client(user.get())
                            .ecole(ecole.get())
                            .build();
                    paymentRepository.save(p);
                    return API.getResponseEntity("payment updated successfully ",HttpStatus.CREATED);
                }else {
                    return API.getResponseEntity("no ecole match this call",HttpStatus.BAD_REQUEST);
                }
            }else {
                return API.getResponseEntity("no user match this call", HttpStatus.BAD_REQUEST);
            }
        }else {
            return API.getResponseEntity("no payment matching this call ", HttpStatus.BAD_REQUEST);
        }
    }
}
