package com.example.pfeApi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/enable/{id}")
    public ResponseEntity<?> enable(@PathVariable("id") Integer id){
        return userService.enable(id);
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity<?> changePassword( @RequestBody ChanngePasswordReqest reqest){
        return userService.changePassword(reqest.getId() , reqest.getPassword());
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return userService.getAll();
    }


}
