package com.hamdeen.todolistapi.repositories;

import com.hamdeen.todolistapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
