package com.example.to_do_list.controller;

import com.example.to_do_list.dto.request.TaskRequest;
import com.example.to_do_list.dto.response.TaskResponse;
import com.example.to_do_list.entity.TaskEntity;
import com.example.to_do_list.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
@Data
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public TaskResponse createTask(@RequestBody TaskRequest request){
        return taskService.createTask(request);
    }

    @GetMapping("/getAll")
    public List<TaskResponse> getTasks(){
        return taskService.getTasks();
    }

    @GetMapping("/getOne/{id}")
    public TaskResponse getTask(@PathVariable Long id){
        return taskService.getTask(id);
    }

    @PutMapping("/update/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody TaskRequest request){
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }



}