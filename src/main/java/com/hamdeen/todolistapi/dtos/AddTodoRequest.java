package com.hamdeen.todolistapi.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddTodoRequest {
    @NotNull
    private String title;

    @NotNull
    private String description;
}
