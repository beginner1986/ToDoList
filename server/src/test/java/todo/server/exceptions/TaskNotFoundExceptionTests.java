package todo.server.exceptions;

import org.junit.Assert;
import org.junit.Test;

public class TaskNotFoundExceptionTests {

    @Test
    public void TaskNotFoundExceptionMessageTest() {
        String message = "";
        try {
            throw new TaskNotFoundException(1L);
        } catch(TaskNotFoundException exception) {
            message = exception.getMessage();
        }

        Assert.assertEquals(message, "Could not find task 1");
    }
}
