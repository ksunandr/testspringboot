package com.ksan.example.testspringboot.servingwebcontent.repositories;

import com.ksan.example.testspringboot.servingwebcontent.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
