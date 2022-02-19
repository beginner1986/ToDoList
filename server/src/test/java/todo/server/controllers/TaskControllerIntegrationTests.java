package todo.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import todo.server.ServerApplication;
import todo.server.domain.Task;
import todo.server.repository.TaskRepository;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@AutoConfigureMockMvc
public class TaskControllerIntegrationTests {

    @Autowired
    MockMvc mvc;
    @Autowired
    TaskRepository repository;

    @Test
    public void getAllTasksTest() throws Exception {
        mvc.perform(get("/task")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(5)));
    }

    @Test
    public void getTaskByIdTest() throws Exception {
        mvc.perform(get("/task/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description", is("Buy something to eat")))
                .andExpect(jsonPath("$.isDone", is(false)));
    }

    @Test
    public void getTaskByIdThrowsTaskNotFoundExceptionTest() throws Exception {
        final long id = 999;

        mvc.perform(get("/task/" + id)
                    .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Could not find task " + id)));
    }

    @Test
    public void addTaskTest() throws Exception {
        Task task = new Task("Prepare test case", true);

        mvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(task)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(7));

        repository.deleteById(7L);
    }

    @Test
    public void updateTaskTest() throws Exception {
        Task newTask = new Task("Modified task description", false);

        mvc.perform(patch("/task/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(newTask)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description", is("Modified task description")));
    }

    @Test
    public void updateTaskThrowsTaskNotFoundExceptionTest() throws Exception {
        final long id = 998;
        Task task = new Task(id, "Prepare test case", true);

        mvc.perform(patch("/task/" + id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(task)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Could not find task " + id)));
    }

    @Test
    public void deleteTaskTest() throws Exception {
        mvc.perform(delete("/task/1"))
                .andExpect(status().isNoContent());

        mvc.perform(get("/task"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(4)));
    }

    @Test
    public void deleteTaskThrowsTaskNotFoundExceptionTest() throws Exception {
        final long id = 997;

        mvc.perform(delete("/task/" + id))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Could not find task " + id)));
    }

    private String jsonToString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
