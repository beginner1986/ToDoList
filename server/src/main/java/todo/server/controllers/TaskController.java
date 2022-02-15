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
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        Optional<Task> task = repository.findById(id);
        if(task.isEmpty()) return null;

        return task.get();
    }

    @PostMapping
    public Task addTask(@RequestBody Task newTask) {
        return repository.save(newTask);
    }

    @PatchMapping("/{id}")
    public Task updateTask(@RequestBody Task updatedTask, @PathVariable Long id) {
        Optional<Task> optionalTask = repository.findById(id);
        if(optionalTask.isEmpty()) return null;

        Task task = optionalTask.get();
        task.setDescription(updatedTask.getDescription());
        task.setIsDone(updatedTask.getIsDone());

        return repository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
