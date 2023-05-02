package com.example.pfeApi.ecole;

import com.example.pfeApi.payment.Payment;
import com.example.pfeApi.user.User;
import com.example.pfeApi.vehicule.Vehicule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Ecole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name ;
    private String email ;
    private String password ;
    private String adress;
    @OneToOne
    @JsonIgnore
    private User owner;
    @OneToMany(mappedBy = "ecole")
    private List<User> clients = new ArrayList();

    @OneToMany(mappedBy = "ecole")
    @JsonIgnore
    private List<Vehicule> vehicules = new ArrayList<>();

    @OneToMany
    @JsonIgnore
    private List<Payment> payments =  new ArrayList<>();
}
