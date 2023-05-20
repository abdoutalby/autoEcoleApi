package com.example.pfeApi.ecole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EcoleDto {
    private String name ;
    private String adress;
    private Integer ownerId;
}
