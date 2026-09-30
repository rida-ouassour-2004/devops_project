package com.rida.task_api.controller;

import com.rida.task_api.dto.TaskRequest;
import com.rida.task_api.dto.TaskResponse;
import com.rida.task_api.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }
    @PostMapping
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest){
       return  taskService.createTask(taskRequest);
    }
@PutMapping("/{id}/title")
    public TaskResponse updateTaskTitle(
    @PathVariable Long id ,
    @RequestParam String title){
        return taskService.updateTaskTitle(id,title);
    }

    @GetMapping("/all")
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }
    @GetMapping("/instance")
    public String getInstance() {
        try {
            return java.net.InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }


}
