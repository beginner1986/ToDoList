package todo.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.server.domain.Task;
import todo.server.exceptions.TaskNotFoundException;
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
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(
                repository.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(
                repository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException(id)),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task newTask) {
        return new ResponseEntity<>(
                repository.save(newTask),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task updatedTask, @PathVariable Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setDescription(updatedTask.getDescription());
        task.setIsDone(updatedTask.getIsDone());

        return new ResponseEntity<>(
                repository.save(task),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
