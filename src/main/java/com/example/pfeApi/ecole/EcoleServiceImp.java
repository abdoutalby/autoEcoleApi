package com.example.pfeApi.ecole;

import com.example.pfeApi.user.Role;
import com.example.pfeApi.user.User;
import com.example.pfeApi.user.UserRepository;
import com.example.pfeApi.utils.API;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EcoleServiceImp implements EcoleService{
    private final EcoleRepository ecoleRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> save(EcoleDto ecole) {
        Optional<User> owner = this.userRepository.findById(ecole.getOwnerId());
        if (owner.isPresent()){
                Ecole e = Ecole.builder()
                        .adress(ecole.getAdress())
                        .name(ecole.getName())
                        .email(owner.get().getEmail())
                        .password(owner.get().getPassword())
                        .owner(owner.get())
                        .build();
                ecoleRepository.save(e);
               return API.getResponseEntity("ecole added succsefully ", HttpStatus.CREATED);
        }else {
        return     API.getResponseEntity("owner not found", HttpStatus.BAD_REQUEST);
        }
      }

    @Override
    public ResponseEntity<?> getAllClients(Long id) {
        Optional<Ecole> ecole = ecoleRepository.findById(id);
        if (ecole.isPresent()) {
            return ResponseEntity.ok(ecole.get().getClients());
        }else {
        return API.getResponseEntity("no ecole matching this call",HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> addClient(Long id, Integer clientId) {
       Optional<User> client =this.userRepository.findById(clientId);
       if (client.isPresent()&&client.get().getRole().equals(Role.USER)){
           Optional<Ecole> e =  this.ecoleRepository.findById(id);
           if (e.isPresent()){
               User u = client.get();
               Ecole ecole = e.get();
               u.setEcole(ecole);
               this.userRepository.save(u);
               ecole.getClients().add(u);
                this.ecoleRepository.save(ecole);
                   return API.getResponseEntity(
                           "client "+client.get().getEmail()+" added to ecole "+ecole.getName(),
                           HttpStatus.OK);
           }else {
               return API.getResponseEntity(
                       "no ecole matching this request",
                       HttpStatus.OK);
           }
       }else {
           return API.getResponseEntity(
                   "no client matching this request",
                   HttpStatus.OK);
       }

    }

    @Override
    public ResponseEntity<?> removeClient(Long id, Integer clientId) {
        Optional<User> client =this.userRepository.findById(clientId);
        if (client.isPresent()){
            Optional<Ecole> e =  this.ecoleRepository.findById(id);
            if (e.isPresent()){
                User u = client.get();
                Ecole ecole = e.get();
                u.setEcole(null);
                this.userRepository.save(u);
                ecole.getClients().remove(u);
                this.ecoleRepository.save(ecole);
                return API.getResponseEntity(
                        "client "+client.get().getEmail()+" removed from  "+ecole.getName(),
                        HttpStatus.OK);
            }else {
                return API.getResponseEntity(
                        "no ecole matching this request",
                        HttpStatus.OK);
            }
        }else {
            return API.getResponseEntity(
                    "no client matching this request",
                    HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Ecole> ecole = this.ecoleRepository.findById(id);
        if (ecole.isPresent()){
            return ResponseEntity.ok(ecole.get());
        }else {
            return API.getResponseEntity("no ecole matching this id",HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Ecole> ecole = this.ecoleRepository.findById(id);
        if (ecole.isPresent()){
            this.ecoleRepository.delete(ecole.get());
            return API.getResponseEntity("ecole delete successfully",HttpStatus.OK);

        }else {
           return API.getResponseEntity("no ecole matching this id",HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> getByOwnerId(Integer id) {
        Optional<User> owner = this.userRepository.findById(id);
        if (owner.isPresent()) {
            if (owner.get().getRole().equals(Role.ECOLE)) {
                return ResponseEntity.ok(this.ecoleRepository.findEcoleByOwner(owner.get()));
            } else {
               return API.getResponseEntity("there is no owner matching this call", HttpStatus.BAD_REQUEST);
            }
        } else {
            return API.getResponseEntity("no owner match this call", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> addMentor(Long id, Integer idClient) {
        Optional<User> client =this.userRepository.findById(idClient);
        if (client.isPresent()){
            if (client.get().getRole().equals(Role.INSTRUCTOR)){
                Optional<Ecole> e =  this.ecoleRepository.findById(id);
                if (e.isPresent()){
                    User u = client.get();
                    Ecole ecole = e.get();
                    u.setEcoleMentor(ecole);
                    this.userRepository.save(u);
                    ecole.getMentors().add(u);
                    this.ecoleRepository.save(ecole);
                    return API.getResponseEntity(
                            "mentor "+client.get().getEmail()+" added to ecole "+ecole.getName(),
                            HttpStatus.OK);
                }else {
                    return API.getResponseEntity(
                            "no ecole matching this request",
                            HttpStatus.OK);
                }
            }
            else {
                return API.getResponseEntity(
                        "this client is not a mentor",
                        HttpStatus.OK);
            }

        }

        else {
            return API.getResponseEntity(
                    "no mentor matching this request",
                    HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> removeMentor(Long id, Integer idClient) {
        Optional<User> client =this.userRepository.findById(idClient);
        if (client.isPresent()){
            Optional<Ecole> e =  this.ecoleRepository.findById(id);
            if (e.isPresent()){
                User u = client.get();
                Ecole ecole = e.get();
                u.setEcoleMentor(null);
                this.userRepository.save(u);
                ecole.getMentors().remove(u);
                this.ecoleRepository.save(ecole);
                return API.getResponseEntity(
                        "mentor "+client.get().getEmail()+" removed from  "+ecole.getName(),
                        HttpStatus.OK);
            }else {
                return API.getResponseEntity(
                        "no ecole matching this request",
                        HttpStatus.OK);
            }
        }else {
            return API.getResponseEntity(
                    "no mentor matching this request",
                    HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> getAllMentors(Long id) {
        Optional<Ecole> ecole = ecoleRepository.findById(id);
        if (ecole.isPresent()) {
            return ResponseEntity.ok(ecole.get().getMentors());
        }else {
            return API.getResponseEntity("no ecole matching this call",HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ecoleRepository.findAll());
    }
}
