package com.hamdeen.todolistapi.controllers;

import com.hamdeen.todolistapi.dtos.AddTodoRequest;
import com.hamdeen.todolistapi.dtos.TodoDto;
import com.hamdeen.todolistapi.entities.Todo;
import com.hamdeen.todolistapi.mappers.TodoMapper;
import com.hamdeen.todolistapi.repositories.TodoRepository;
import com.hamdeen.todolistapi.services.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;

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
}
