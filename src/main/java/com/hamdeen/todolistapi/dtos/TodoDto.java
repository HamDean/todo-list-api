package com.hamdeen.todolistapi.dtos;

import lombok.Data;

@Data
public class TodoDto {
    private Long id;
    private String title;
    private String description;
}
