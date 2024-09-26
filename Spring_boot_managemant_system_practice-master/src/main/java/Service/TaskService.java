package Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import Model.Task;
import Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
@Service

public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task>  getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }


    public void deleteTaskById(Long id) {

        taskRepository.deleteById(id);
    }


    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
                .map( task ->{
                  task.setTitle(updatedTask.getTitle());
                  task.setDescription(updatedTask.getDescription());
                    task.setStatus(updatedTask.getStatus());
                    return taskRepository.save(task);
                })
        .orElseThrow(() -> new RuntimeException("Task not found"));

    }

}
