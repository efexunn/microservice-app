package com.efexunn.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Integer, User> {
}
