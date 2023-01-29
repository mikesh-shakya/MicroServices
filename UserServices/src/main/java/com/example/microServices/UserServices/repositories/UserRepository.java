package com.example.microServices.UserServices.repositories;

import com.example.microServices.UserServices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
