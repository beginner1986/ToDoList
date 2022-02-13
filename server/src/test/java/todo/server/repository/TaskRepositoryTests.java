package todo.server.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import todo.server.domain.Task;
import todo.server.repository.TaskRepository;

import java.util.List;

@DataJpaTest
public class TaskRepositoryTests {

    @Autowired
    TaskRepository repository;

    @Test
    public void getAllTasksTest() {
        List<Task> tasks = repository.findAll();

        Task task = tasks.get(0);
        Assert.assertEquals(task.getDescription(), "Buy something to eat");
        Assert.assertEquals(task.getIsDone(), false);
        Assert.assertEquals(tasks.size(), 5);
    }
}
