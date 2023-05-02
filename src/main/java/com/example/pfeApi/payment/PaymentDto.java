package com.example.pfeApi.payment;

import lombok.Data;

@Data
public class PaymentDto {
    private double amount ;
    private int idUser;
    private Long idEcole;
}
