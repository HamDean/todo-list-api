package com.hamdeen.todolistapi.repositories;

import com.hamdeen.todolistapi.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
