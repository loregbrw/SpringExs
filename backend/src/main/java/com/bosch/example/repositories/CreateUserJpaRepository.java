package com.bosch.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bosch.example.model.UserData;

import java.util.List;
import java.util.Optional;


@Repository
public interface CreateUserJpaRepository extends JpaRepository<UserData, Long> {
    List<UserData> findByUsername(String username);
    List<UserData> findByEmail(String email);

    Optional<UserData> findById(Long id);
}
