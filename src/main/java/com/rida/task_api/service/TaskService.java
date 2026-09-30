package com.rida.task_api.service;

import com.rida.task_api.dto.TaskRequest;
import com.rida.task_api.dto.TaskResponse;
import com.rida.task_api.entity.Task;
import com.rida.task_api.mapper.TaskMapper;
import com.rida.task_api.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskmapper;
    public TaskService(TaskMapper taskmapper,TaskRepository taskRepository){
        this.taskmapper=taskmapper;
        this.taskRepository=taskRepository;
    }
    public TaskResponse createTask(TaskRequest taskrequest){
         Task task=taskmapper.toEntity(taskrequest);
         Task SavedTask =taskRepository.save(task);
         return taskmapper.toResponse(SavedTask);


    }
    @Transactional
    public TaskResponse updateTaskTitle(Long id,String title){
        Task task=taskRepository.findById(id).orElseThrow();
        task.setTitle(title);
        return taskmapper.toResponse(task);
    }

    @Transactional
    public List<TaskResponse> getAllTasks(){
        List<TaskResponse> tsr = new ArrayList<>();
       List<Task> task=taskRepository.findAll();
       for(Task i : task){
           tsr.add(taskmapper.toResponse(i));
       }
       return tsr ;
    }
}
