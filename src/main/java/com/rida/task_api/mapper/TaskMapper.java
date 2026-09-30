package com.rida.task_api.mapper;

import com.rida.task_api.dto.TaskRequest;
import com.rida.task_api.dto.TaskResponse;
import com.rida.task_api.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task toEntity(TaskRequest taskRequest ){
        Task task =new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        return task ;
    }

    public TaskResponse toResponse(Task task){
        TaskResponse taskresponse=new TaskResponse();
        taskresponse.setId(task.getId());
        taskresponse.setTitle(task.getTitle());
        taskresponse.setDescription(task.getDescription());
        taskresponse.setCompleted(task.isCompleted());
        return taskresponse;

    }

}
