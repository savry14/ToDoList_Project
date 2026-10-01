package com.example.to_do_list.service;

import com.example.to_do_list.dto.request.TaskRequest;
import com.example.to_do_list.dto.response.TaskResponse;
import com.example.to_do_list.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TaskService {
    TaskResponse createTask(TaskRequest request);
    List<TaskResponse> getTasks();
    TaskResponse getTask(Long id);
    TaskResponse updateTask(Long id, TaskRequest request);
    void deleteTask(Long id);
}
