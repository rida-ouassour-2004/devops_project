package com.rida.task_api.mapper;

import com.rida.task_api.dto.TaskRequest;
import com.rida.task_api.dto.TaskResponse;
import com.rida.task_api.entity.Task;
import com.rida.task_api.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {
    private TaskMapper taskMapper=new TaskMapper();
    @Test
    void toEntity() {
        TaskRequest taskRequest=new TaskRequest();
         String title = "title in taskrequest";
         String description="description in taskrequest";
        taskRequest.setTitle(title);
        taskRequest.setDescription(description);
        Task task=taskMapper.toEntity(taskRequest);

        assertEquals(taskRequest.getTitle(),task.getTitle());
        assertEquals(taskRequest.getDescription(),task.getDescription());
    }


    @Test
    void toResponse() {
        Task task =new Task();
        String title = "title in task";
        String description="description in task";
        Long id = 1L;
        boolean completed= false;
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setCompleted(completed);
        TaskResponse taskResponse = taskMapper.toResponse(task);
        assertEquals(task.getId(), taskResponse.getId());
        assertEquals(task.getTitle(), taskResponse.getTitle());
        assertEquals(task.getDescription(), taskResponse.getDescription());
        assertEquals(task.isCompleted(), taskResponse.getCompleted());    }
}