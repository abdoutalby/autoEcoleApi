package com.example.pfeApi.payment;

import com.example.pfeApi.ecole.Ecole;
import com.example.pfeApi.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private double amount ;
    @ManyToOne
    private User client;
    @ManyToOne
    private Ecole ecole;
}
