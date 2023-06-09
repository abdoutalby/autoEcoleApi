package com.example.pfeApi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
 public class UserController {

    private final UserService userService;

    @PutMapping("/enable/{id}")
    public ResponseEntity<?> enable(@PathVariable("id") Integer id){
        return userService.enable(id);
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disable(@PathVariable("id") Integer id){
        return userService.desable(id);
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity<?> changePassword( @RequestBody ChanngePasswordReqest reqest){
        return userService.changePassword(reqest.getId() , reqest.getPassword());
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return userService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws IOException {
        return userService.delete(id);
    }

    @GetMapping("/findByEmail/{email}")
    public  ResponseEntity<?> findByEmail(@PathVariable("email") String email){
        return  this.userService.findByEmail(email);
    }

    @GetMapping("/getAllActiveEcoles/")
    public ResponseEntity<?> getAllActiveEcole(){
        return this.userService.getAllActiveEcole();
    }
        @GetMapping("/getAllInactiveEcoles/")
        public ResponseEntity<?> getAllInactiveEcole(){
        return this.userService.getAllInactiveEcole();
        }
        @GetMapping("/getAllEnabledUser/")
        public ResponseEntity<?> getAllEcoles(){
        return this.userService.getAllEcoles();
        }

}
