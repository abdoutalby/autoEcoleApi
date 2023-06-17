package com.example.pfeApi.auth;

import com.example.pfeApi.config.JwtService;
import com.example.pfeApi.ecole.Ecole;
import com.example.pfeApi.ecole.EcoleDto;
import com.example.pfeApi.ecole.EcoleRepository;
import com.example.pfeApi.ecole.EcoleServiceImp;
import com.example.pfeApi.files.FileService;
import com.example.pfeApi.token.Token;
import com.example.pfeApi.token.TokenRepository;
import com.example.pfeApi.token.TokenType;
import com.example.pfeApi.user.Role;
import com.example.pfeApi.user.User;
import com.example.pfeApi.user.UserRepository;
import com.example.pfeApi.utils.API;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final EcoleServiceImp ecoleServiceImp;
  private final FileService fileService;
  private final EcoleRepository ecoleRepository;

  public ResponseEntity<?> register(RegisterRequest request , MultipartFile image) {
    var ecoleId ="";
   if(!repository.existsByEmail(request.getEmail())){
      ecoleId = request.getEcoleId();
      var user = User.builder()
             .firstname(request.getFirstname())
             .lastname(request.getLastname())
             .email(request.getEmail())
             .password(passwordEncoder.encode(request.getPassword()))
             .enabled(request.getEnabled())
             .phone(request.getPhone())
             .adress(request.getAdress())
             .imageUrl(fileService.save(image,UUID.randomUUID()+ request.getEmail()))
             .build();
     switch (request.getRole()) {
       case "admin" -> {
         user.setRole(Role.ADMIN);
       }
       case "user" -> {
         user.setRole(Role.USER);
         ;
       }
       case "ecole" -> {
         user.setRole(Role.ECOLE);
       }
       case "instructor" -> {
         user.setRole(Role.INSTRUCTOR);
       }
     }
     var savedUser = repository.save(user);
     if (savedUser.getRole().equals(Role.ECOLE)){
       var ecole = ecoleRepository.save(Ecole.builder()
               .name(savedUser.getEmail())
               .adress(savedUser.getAdress())
               .owner(savedUser)
               .password(request.getPassword())
               .email(savedUser.getEmail())
               .build());
     }
    else if ((savedUser.getRole().equals(Role.USER)
             ||
             savedUser.getRole().equals(Role.INSTRUCTOR)) &&request.getEcoleId() != null ) {
         log.info("ecole id : {}", ecoleId);
         log.info("ecole : {}",savedUser.getRole().equals(Role.USER)  );
         if (savedUser.getRole().equals(Role.USER)) {
           this.ecoleServiceImp.addClient(Long.valueOf(ecoleId), savedUser.getId());
         } else if (savedUser.getRole().equals(Role.INSTRUCTOR))
           this.ecoleServiceImp.addMentor(Long.valueOf(ecoleId), savedUser.getId());
       }
     var jwtToken = jwtService.generateToken(savedUser , user.getId());
     saveUserToken(savedUser, jwtToken);
     return ResponseEntity.ok().body( AuthenticationResponse.builder()
             .token(jwtToken)
                     .user(savedUser)
             .build());
   }else {
     return API.getResponseEntity("email already exists", HttpStatus.BAD_REQUEST);
   }

  }

  public ResponseEntity<?> authenticate(AuthenticationRequest request) {
      var user = repository.findByEmail(request.getEmail())
              .orElseThrow();
      if (user.isEnabled() ){
          if (user.isAccountNonExpired()){
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );

    var jwtToken = jwtService.generateToken(user, user.getId());
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return ResponseEntity.ok( AuthenticationResponse.builder()
            .token(jwtToken)
            .user(user)
            .ecoleId(user.getRole().equals(Role.ADMIN)?
                            null
                            :
                            user.getRole().equals(Role.ECOLE)
                                    ?
                    ecoleRepository.findEcoleByOwner(user).getId()
                                    :
                    user.getRole().equals(Role.INSTRUCTOR)
                            ?user.getEcoleMentor().getId()
                            :
                    user.getEcole().getId()
                    )
            .build());
  }else {
          return ResponseEntity.badRequest().body("Account is  expired");
      }}else{
          return ResponseEntity.badRequest().body("Account is  not active yet ");
      }
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
