package exam1.example.demo2.Controller;

import exam1.example.demo2.Exceptions.TaskException;
import exam1.example.demo2.Service.PersonService;
import exam1.example.demo2.Service.TaskDbService;
import exam1.example.demo2.beans.Person;
import exam1.example.demo2.beans.TaskDb;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin
//@RestController
//@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class MainController {

    private final PersonService personService;
    private final TaskDbService taskDbService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok().body(persons);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        Person person = personService.getPersonById(id);
        return ResponseEntity.ok().body(person);
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        Person savedPerson = personService.savePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable int id) {
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDb>> getAllTasks() {
        List<TaskDb> tasks = taskDbService.getAllTasks();
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDb> getTaskById(@PathVariable int id) {
        TaskDb task = taskDbService.getTaskById(id);
        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskDb> saveTask(@RequestBody TaskDb taskDb) {
        try {
            TaskDb savedTask = taskDbService.saveTask(taskDb);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
        } catch (TaskException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable int id) {
        taskDbService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDb> updateTaskCompletionStatus(@PathVariable int id, @RequestBody TaskDb taskDb) {
        try {
            TaskDb updatedTask = taskDbService.updateTaskCompletionStatus(id, taskDb.isComplete());
            return ResponseEntity.ok().body(updatedTask);
        } catch (TaskException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
