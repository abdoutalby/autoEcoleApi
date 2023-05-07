package com.example.pfeApi.auth;

import com.example.pfeApi.config.JwtService;
import com.example.pfeApi.token.Token;
import com.example.pfeApi.token.TokenRepository;
import com.example.pfeApi.token.TokenType;
import com.example.pfeApi.user.Role;
import com.example.pfeApi.user.User;
import com.example.pfeApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public ResponseEntity<?> register(RegisterRequest request) {
   if(!repository.existsByEmail(request.getEmail())){
     var user = User.builder()
             .firstname(request.getFirstname())
             .lastname(request.getLastname())
             .email(request.getEmail())
             .password(passwordEncoder.encode(request.getPassword()))
             .enabled(request.getEnabled() ? true : false)
             .role(Role.USER)
             .build();
     var savedUser = repository.save(user);
     var jwtToken = jwtService.generateToken(user);
     saveUserToken(savedUser, jwtToken);
     return ResponseEntity.ok().body( AuthenticationResponse.builder()
             .token(jwtToken)
             .build());
   }else {
     return ResponseEntity.badRequest().body("{\"message\":\" email already exists\"}");
   }

  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    log.info("login request {}", request);
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    var user = repository.findByEmail(request.getEmail())
            .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
  }


  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
