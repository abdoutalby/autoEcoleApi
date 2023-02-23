package com.example.pfeApi.repositories;

import com.example.pfeApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
