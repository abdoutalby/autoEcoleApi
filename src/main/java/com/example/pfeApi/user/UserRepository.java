package com.example.pfeApi.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    List<User> findAllByRole(Role role);

  List<User> findAllByRoleAndEnabled(Role role, boolean enabled);
}
