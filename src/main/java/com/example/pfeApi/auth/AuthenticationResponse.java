package com.example.pfeApi.auth;

import com.example.pfeApi.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
  private String token;
  private User user;
  private Long ecoleId;
}
