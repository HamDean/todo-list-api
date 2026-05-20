package com.hamdeen.todolistapi.mappers;

import com.hamdeen.todolistapi.dtos.TodoDto;
import com.hamdeen.todolistapi.entities.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    @Mapping(source = "id", target = "id")
    TodoDto toTodoDto(Todo todo);
}
