package exam1.example.demo2.Controller;

import exam1.example.demo2.Service.PersonService;
import exam1.example.demo2.Service.TaskDbService;
import exam1.example.demo2.beans.Person;
import exam1.example.demo2.beans.TaskDb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/task")
public class ControllerV2 {
    private final TaskDbService taskDbService;
    private final PersonService personService;

    public ControllerV2(TaskDbService taskDbService, PersonService personService) {
        this.taskDbService = taskDbService;
        this.personService = personService;
    }

    @GetMapping("/tasks")
    public List<TaskDb> getAllTasks() {
        return taskDbService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public TaskDb getTaskById(@PathVariable("id") int id) {
        return taskDbService.getTaskById(id);
    }

    @PostMapping("/tasks")
    public TaskDb saveTask(@RequestBody TaskDb taskDb) {
        return taskDbService.saveTask(taskDb);
    }

    @PutMapping("/{id}")
    public TaskDb updateTask(@PathVariable("id") int id, @RequestBody TaskDb taskDb) {
        return taskDbService.updateTask(id, taskDb);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTaskById(@PathVariable("id") int id) {
        taskDbService.deleteTaskById(id);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable int id) {
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }

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


}


//    @PutMapping("/{id}/complete")
//    public TaskDb updateTaskCompletionStatus(@PathVariable("id") int id, @RequestParam("isComplete") boolean isComplete) {
//        return taskDbService.updateTaskCompletionStatus(id, isComplete);
//    }

//    @GetMapping("/person/{id}")
//    public List<TaskDb> getTasksByPerson(@PathVariable("id") int id) {
//        Person person = personService.getPersonById(id);
//        return taskDbService.getTasksByPerson(person);
//    }