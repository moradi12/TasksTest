package exam1.example.demo2.Service;

import exam1.example.demo2.Exceptions.TaskException;
import exam1.example.demo2.Repository.PersonRepository;
import exam1.example.demo2.Repository.TaskDbRepository;
import exam1.example.demo2.beans.Person;
import exam1.example.demo2.beans.TaskDb;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskDbService {
    private final TaskDbRepository taskDbRepository;
    private final PersonRepository personRepository;

    public List<TaskDb> getAllTasks() {
        return taskDbRepository.findAll();
    }

    public List<TaskDb> getTasksByCompletionStatus(boolean isComplete) {
        return taskDbRepository.findByIsComplete(isComplete);
    }

    public List<TaskDb> getTasksByName(String taskName) {
        return taskDbRepository.findByTaskName(taskName);
    }

    public TaskDb getTaskById(int id) {
        return taskDbRepository.findById(id)
                .orElseThrow(() -> new TaskException("Task not found with id: " + id));
    }

    public TaskDb saveTask(TaskDb taskDb) {
        try {
            if (taskDb.getPerson() != null) {
                taskDb.setPerson(personRepository.save(taskDb.getPerson()));
            }
            taskDb.setDateOfExecution(LocalDateTime.now());
            return taskDbRepository.save(taskDb);
        } catch (DataIntegrityViolationException ex) {
            throw new TaskException("Failed to save task due to data integrity violation: " + ex.getMessage());
        } catch (Exception ex) {
            throw new TaskException("Failed to save task: " + ex.getMessage());
        }
    }

    public void deleteTaskById(int id) {
        if (!taskDbRepository.existsById(id)) {
            throw new TaskException("Task not found with id:" + id);
        }
        taskDbRepository.deleteById(id);
    }
    public TaskDb updateTaskCompletionStatus(int id, boolean taskDb) {
        TaskDb existingTask = getTaskById(id);
        existingTask.setComplete(taskDb);
        return taskDbRepository.save(existingTask);
    }

    public List<TaskDb> getTasksByPerson(Person person) {
        return taskDbRepository.findByPerson(person);
    }

  public TaskDb updateTask(int id, TaskDb updatedTaskData) {
        TaskDb existingTask = getTaskById(id);
        existingTask.setTaskName(updatedTaskData.getTaskName());
        existingTask.setDescription(updatedTaskData.getDescription());
        existingTask.setDateOfExecution(updatedTaskData.getDateOfExecution());
        existingTask.setPerson(updatedTaskData.getPerson());
        taskDbRepository.save(existingTask);
        return existingTask;
    }



}
