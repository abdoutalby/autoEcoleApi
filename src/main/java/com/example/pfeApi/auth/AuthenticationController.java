package com.example.pfeApi.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<?> register(
      @ModelAttribute RegisterRequest request,
      @RequestParam("image") MultipartFile image
  ) {
    return  service.register(request , image);
  }
  @PostMapping("/login")
  public ResponseEntity<?> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
