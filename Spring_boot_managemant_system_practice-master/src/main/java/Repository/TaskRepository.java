package Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import Model.Task;

public interface TaskRepository extends  JpaRepository<Task, Long> {
}
