package todo.server.dev_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import todo.server.domain.Task;
import todo.server.repository.TaskRepository;

@Profile("!prod")
@Configuration
public class H2DatabaseInit {

    private TaskRepository repository;

    @Autowired
    public H2DatabaseInit(TaskRepository repository) {
        this.repository = repository;
    }

    @Bean
    public void insertTestDataIntoDatabase() {
        repository.save(new Task("Buy something to eat", false));
        repository.save(new Task("Walk the dog", true));
        repository.save(new Task("Book the hotel", false));
        repository.save(new Task("Book the plane", false));
        repository.save(new Task("Print the itinerary", false));
    }
}
