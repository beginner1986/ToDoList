package todo.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todo.server.domain.Task;
import todo.server.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public @ResponseBody List<Task> getAllTasks() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Task getTaskById(@PathVariable Long id) {
        Optional<Task> task = repository.findById(id);
        if(task.isEmpty()) return null;

        return task.get();
    }
}
