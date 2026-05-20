package com.hamdeen.todolistapi.services;

import com.hamdeen.todolistapi.dtos.AddTodoRequest;
import com.hamdeen.todolistapi.dtos.TodoDto;
import com.hamdeen.todolistapi.entities.Todo;
import com.hamdeen.todolistapi.entities.User;
import com.hamdeen.todolistapi.exceptions.TodoNotFoundException;
import com.hamdeen.todolistapi.mappers.TodoMapper;
import com.hamdeen.todolistapi.repositories.TodoRepository;
import com.hamdeen.todolistapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final UserRepository userRepository;

    public TodoDto addTodo(AddTodoRequest request) {
        var todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setUpdatedAt(new Date());
        todo.setCreatedAt(new Date());

        var user = getCurrentUser();
        todo.setUser(user);

        todoRepository.save(todo);

        return todoMapper.toTodoDto(todo);
    }

    private @Nullable User getCurrentUser() {
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        var userId = Long.valueOf(authentication.getPrincipal().toString());
        return userRepository.findById(userId).orElse(null);
    }

    public List<TodoDto> getAllTodos() {
        return todoRepository
                .findAllByUserId(getCurrentUser().getId())
                .stream()
                .map(todoMapper::toTodoDto)
                .toList();
    }

    public TodoDto updateTodo(Long id, AddTodoRequest request) {
        var todo = todoRepository.findById(id).orElse(null);
        if (todo == null || !todo.getUser().getId().equals(getCurrentUser().getId())) {
            throw new TodoNotFoundException();
        }

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setUpdatedAt(new Date());

        todoRepository.save(todo);

        return todoMapper.toTodoDto(todo);
    }

    public void deleteTodo(Long id) {
        var todo = todoRepository.findById(id).orElse(null);
        if (todo == null || !todo.getUser().getId().equals(getCurrentUser().getId())) {
            throw new TodoNotFoundException();
        }
        todoRepository.delete(todo);
    }
}
