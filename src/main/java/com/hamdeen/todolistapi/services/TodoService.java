package com.hamdeen.todolistapi.services;

import com.hamdeen.todolistapi.dtos.AddTodoRequest;
import com.hamdeen.todolistapi.dtos.TodoDto;
import com.hamdeen.todolistapi.entities.Todo;
import com.hamdeen.todolistapi.mappers.TodoMapper;
import com.hamdeen.todolistapi.repositories.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

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

        todoRepository.save(todo);

        return todoMapper.toTodoDto(todo);
    }
}
