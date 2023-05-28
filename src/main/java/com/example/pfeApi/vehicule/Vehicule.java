package com.example.pfeApi.vehicule;

import com.example.pfeApi.ecole.Ecole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String type;
    private String matricule;
    private String marque;
    private String imageUrl ;
    @ManyToOne
    @JsonIgnore
    private Ecole ecole;
}
