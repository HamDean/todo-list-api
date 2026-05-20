package com.hamdeen.todolistapi.controllers;

import com.hamdeen.todolistapi.dtos.AddTodoRequest;
import com.hamdeen.todolistapi.dtos.TodoDto;
import com.hamdeen.todolistapi.entities.Todo;
import com.hamdeen.todolistapi.exceptions.TodoNotFoundException;
import com.hamdeen.todolistapi.mappers.TodoMapper;
import com.hamdeen.todolistapi.repositories.TodoRepository;
import com.hamdeen.todolistapi.services.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(
            @RequestBody AddTodoRequest request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var todoDto = todoService.addTodo(request);
        var uri = uriComponentsBuilder
                .path("/api/todo/{id}")
                .buildAndExpand(todoDto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(todoDto);
    }

    @GetMapping
    public List<TodoDto> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PutMapping("/{id}")
    public TodoDto updateTodo(@PathVariable Long id, @RequestBody AddTodoRequest request) {
        return todoService.updateTodo(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<String> handleTodoNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Todo not found");
    }
}
