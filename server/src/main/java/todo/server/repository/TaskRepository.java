package todo.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.server.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
