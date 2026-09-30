package com.rida.task_api.service;

import com.rida.task_api.dto.TaskRequest;
import com.rida.task_api.dto.TaskResponse;
import com.rida.task_api.entity.Task;
import com.rida.task_api.mapper.TaskMapper;
import com.rida.task_api.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskMapper taskMapper ;
    @Mock
    private TaskRepository taskRepository ;
    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask() {
        TaskRequest request = new TaskRequest();
        request.setTitle("Learn Spring");
        request.setDescription("Study Spring Boot");

        Task task = new Task();
        task.setTitle("Learn Spring");
        task.setDescription("Study Spring Boot");
        when(taskMapper.toEntity(request))
                .thenReturn(task);
        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Learn Spring");
        savedTask.setDescription("Study Spring Boot");
        when(taskRepository.save(task))
                .thenReturn(savedTask);
        TaskResponse taskResponse =new TaskResponse();
        taskResponse.setId(savedTask.getId());
        taskResponse.setDescription(savedTask.getDescription());
        taskResponse.setTitle(savedTask.getTitle());
        taskResponse.setCompleted(false);
        when(taskMapper.toResponse(savedTask))
                .thenReturn(taskResponse);
        TaskResponse result = taskService.createTask(request);

        verify(taskMapper).toEntity(request);
        verify(taskRepository).save(task);
        verify(taskMapper).toResponse(savedTask);

        assertEquals(1L, result.getId());
        assertEquals("Learn Spring", result.getTitle());
        assertEquals("Study Spring Boot", result.getDescription());
        assertFalse(result.getCompleted());
    }

    @Test
    void updateTaskTitle() {
        Long id =1L;
        String Title="new title";
        Task task = new Task();
        task.setId(id);
        task.setTitle("OLD TITLE");
        when(taskRepository.findById(id))
                .thenReturn(Optional.of(task));
        TaskResponse  taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTitle(Title);
when( taskMapper.toResponse(task)).thenReturn(taskResponse);
        TaskResponse result = taskService.updateTaskTitle(id, Title);

        assertEquals(Title, task.getTitle());

        verify(taskRepository).findById(id);
        verify(taskMapper).toResponse(task);

        assertEquals(id, result.getId());
        assertEquals(Title, result.getTitle());
    }
    @Test
    void updateTaskTitle_taskNotFound() {
        Long id = 1L;

        when(taskRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                java.util.NoSuchElementException.class,
                () -> taskService.updateTaskTitle(id, "New title")
        );
    }

    @Test
    void getAllTasks() {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        when(taskRepository.findAll()).thenReturn(tasks);
        List<TaskResponse> tasksr = new ArrayList<>();
        TaskResponse taskResponse =new TaskResponse();
        taskResponse.setId(task1.getId());
        taskResponse.setTitle(task1.getTitle());

        TaskResponse taskResponse2 =new TaskResponse();
        taskResponse2.setId(task2.getId());
        taskResponse2.setTitle(task2.getTitle());

        tasksr.add(taskResponse);
        tasksr.add(taskResponse2);
        when(taskMapper.toResponse(task1)).thenReturn(taskResponse);
        when(taskMapper.toResponse(task2))
                .thenReturn(taskResponse2);
        List<TaskResponse> result = taskService.getAllTasks();
        assertEquals(tasksr, result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Task 1", result.get(0).getTitle());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Task 2", result.get(1).getTitle());

    }
}