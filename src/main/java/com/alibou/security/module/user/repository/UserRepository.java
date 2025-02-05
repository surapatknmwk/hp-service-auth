package com.alibou.security.module.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alibou.security.module.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);
}
