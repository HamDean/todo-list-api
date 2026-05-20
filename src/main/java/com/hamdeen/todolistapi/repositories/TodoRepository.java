package com.hamdeen.todolistapi.repositories;

import com.hamdeen.todolistapi.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Transactional
    @Modifying
    @Query("delete from Todo t")
    void deleteFirstBy();
}
