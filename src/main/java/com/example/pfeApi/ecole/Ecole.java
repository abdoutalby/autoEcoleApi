package com.example.pfeApi.ecole;

import com.example.pfeApi.user.User;
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
    private String Email ;
    private String Password ;
    private String adress;
    @OneToMany(mappedBy = "ecole")
    private List<User> clients = new ArrayList();

}
