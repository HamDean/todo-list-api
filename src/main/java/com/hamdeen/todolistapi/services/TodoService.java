package com.hamdeen.todolistapi.services;

import com.hamdeen.todolistapi.dtos.AddTodoRequest;
import com.hamdeen.todolistapi.dtos.TodoDto;
import com.hamdeen.todolistapi.entities.Todo;
import com.hamdeen.todolistapi.exceptions.TodoNotFoundException;
import com.hamdeen.todolistapi.mappers.TodoMapper;
import com.hamdeen.todolistapi.repositories.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoDto addTodo(AddTodoRequest request) {
        var todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setUpdatedAt(new Date());
        todo.setCreatedAt(new Date());

        todoRepository.save(todo);

        return todoMapper.toTodoDto(todo);
    }

    public List<TodoDto> getAllTodos() {
        return todoRepository
                .findAll()
                .stream()
                .map(todoMapper::toTodoDto)
                .toList();
    }

    public TodoDto updateTodo(Long id, AddTodoRequest request) {
        var todo = todoRepository.findById(id).orElse(null);
        if (todo == null) {
            throw new TodoNotFoundException();
        }

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setUpdatedAt(new Date());

        todoRepository.save(todo);

        return todoMapper.toTodoDto(todo);
    }
}
