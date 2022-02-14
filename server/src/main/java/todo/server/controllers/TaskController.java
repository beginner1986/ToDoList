package todo.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import todo.server.domain.Task;
import todo.server.repository.TaskRepository;

import java.util.List;

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
}
