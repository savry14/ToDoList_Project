package com.example.to_do_list.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
