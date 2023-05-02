package com.example.pfeApi.vehicule;

import lombok.Data;

@Data
public class VehiculeDto {
    private String type;
    private String matricule;
    private String marque;
    private Long ecoleId;
}
