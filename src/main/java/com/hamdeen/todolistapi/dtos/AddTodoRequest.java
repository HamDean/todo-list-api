package com.hamdeen.todolistapi.dtos;

import lombok.Data;

@Data
public class AddTodoRequest {
    private String title;
    private String description;
}
