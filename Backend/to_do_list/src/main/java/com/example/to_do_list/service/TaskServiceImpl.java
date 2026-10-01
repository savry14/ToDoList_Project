package com.example.to_do_list.service;

import com.example.to_do_list.dto.request.TaskRequest;
import com.example.to_do_list.dto.response.TaskResponse;
import com.example.to_do_list.entity.TaskEntity;
import com.example.to_do_list.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    private TaskResponse taskResponse(TaskEntity task) {
        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setCompleted(task.isCompleted());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());

        return response;
    }

    @Override
    public TaskResponse createTask(TaskRequest request){
        TaskEntity task = new TaskEntity();

        task.setTitle(request.getTitle());

        TaskEntity savedTask = taskRepository.save(task);

        return taskResponse(savedTask);
    }

    @Override
    public List<TaskResponse> getTasks(){
       List <TaskEntity> tasks = taskRepository.findAll();
       return tasks.stream()
               .map(this::taskResponse)
               .toList();

    }

    @Override
    public TaskResponse getTask(Long id){
        TaskEntity task = taskRepository.findById(id).orElse(null);
        if(task == null){
            return null;
        }
        return taskResponse(task);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest request){
        TaskEntity existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask == null){
            return null;
        }
        existingTask.setTitle(request.getTitle());

        TaskEntity updateTask = taskRepository.save(existingTask);
        return taskResponse(updateTask);
    }

    @Override
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
