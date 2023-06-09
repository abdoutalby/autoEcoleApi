package com.example.pfeApi.user;

import com.example.pfeApi.ecole.Ecole;
import com.example.pfeApi.payment.Payment;
import com.example.pfeApi.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String firstname;
  private String lastname;
  @Column(unique = true)
  private String email;
  private String password;
  private String phone ;
  private String adress ;
  private String imageUrl;
  private Boolean enabled=false;
  @OneToOne(mappedBy = "owner")
  @JsonIgnore
  private Ecole own;

  @ManyToOne
  @JsonIgnore
  private Ecole ecole;

  @ManyToOne
  @JsonIgnore
  private Ecole ecoleMentor;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Token> tokens;

  @OneToMany(mappedBy = "client")
  @JsonIgnore
  private List<Payment> payments;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }
}
