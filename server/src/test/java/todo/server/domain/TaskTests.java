package todo.server.domain;

import org.junit.Assert;
import org.junit.Test;

public class TaskTests {

    @Test
    public void lombokDataAnnotationProductsWorkProperlyTest() {
        Task task1 = new Task(1L, "Task description", true);
        Task task2 = new Task();
        task2.setId(1L);
        task2.setDescription("Task description");
        task2.setIsDone(true);

        Assert.assertEquals(task1, task2);
    }

    @Test
    public void customConstructorCreatesTaskInstanceTest() {
        Task task = new Task("Task description", true);

        Assert.assertEquals(task.getDescription(), "Task description");
        Assert.assertEquals(task.getIsDone(), true);
    }
}
