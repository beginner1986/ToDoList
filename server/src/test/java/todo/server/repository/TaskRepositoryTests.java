package todo.server.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import todo.server.ServerApplication;
import todo.server.dev_config.H2DatabaseInit;
import todo.server.domain.Task;

import java.util.List;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@Transactional
public class TaskRepositoryTests {

    @Autowired
    TaskRepository repository;
    @Autowired
    H2DatabaseInit dbConfig;

    @BeforeEach
    public void setUpTests() {
        dbConfig.insertTestDataIntoDatabase();
    }

    @Test
    public void getAllTasksTest() {
        List<Task> tasks = repository.findAll();
        Task task = tasks.get(0);

        Assert.assertEquals(task.getDescription(), "Buy something to eat");
        Assert.assertEquals(task.getIsDone(), false);
        Assert.assertEquals(tasks.size(), 5);
    }

    @Test
    public void getTaskByIdTest() {
        Task task = repository.getById(1L);

        Assert.assertEquals(task.getDescription(), "Buy something to eat");
        Assert.assertEquals(task.getIsDone(), false);
    }

    @Test
    public void updateTaskTest() {
        Task task = repository.getById(1L);
        task.setIsDone(true);
        repository.save(task);

        Task modifiedTask = repository.getById(1L);

        Assert.assertEquals(task.getDescription(), modifiedTask.getDescription());
        Assert.assertEquals(modifiedTask.getIsDone(), true);
    }

    @Test
    public void deleteTaskTest() {
        int entitiesCount = repository.findAll().size();
        repository.deleteById(1L);

        Assert.assertEquals(repository.findAll().size(), entitiesCount - 1);
    }

    @Test
    public void createTaskTest() {
        int entitiesCount = repository.findAll().size();
        Task task = new Task("Do something important", false);
        repository.save(task);

        Assert.assertEquals(repository.findAll().size(), entitiesCount + 1);

        Task addedTask = repository.getById((long) (entitiesCount + 1));
        Assert.assertEquals(task.getDescription(), addedTask.getDescription());
        Assert.assertEquals(task.getIsDone(), addedTask.getIsDone());
    }
}
