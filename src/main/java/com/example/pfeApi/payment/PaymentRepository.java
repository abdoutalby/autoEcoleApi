package com.example.pfeApi.payment;

import com.example.pfeApi.ecole.Ecole;
import com.example.pfeApi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment , Long> {
    List<Payment> findByClient(User user);
    List<Payment> findByEcole(Ecole ecole);
}
