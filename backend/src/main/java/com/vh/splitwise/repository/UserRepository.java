package com.vh.splitwise.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vh.splitwise.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);

  <S extends User> S save(S user);
}
