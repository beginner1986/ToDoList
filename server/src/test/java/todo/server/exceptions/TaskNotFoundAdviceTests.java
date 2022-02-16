package todo.server.exceptions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import todo.server.ServerApplication;
import todo.server.repository.TaskRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@AutoConfigureMockMvc
public class TaskNotFoundAdviceTests {

    @Autowired
    MockMvc mvc;
    @Autowired
    TaskRepository repository;

    @Test
    public void nonExistingTaskGetQueryThrowsTaskNotFoundExceptionTest() throws Exception {
        mvc.perform(get("/task/-1")
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isNotFound())
                .andExpect(result -> Assert.assertTrue(result.getResolvedException() instanceof TaskNotFoundException))
                .andExpect(content().string(containsString("Could not find task -1")));
    }

}
