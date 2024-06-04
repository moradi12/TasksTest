package exam1.example.demo2.CLR;

import exam1.example.demo2.Exceptions.PersonException;
import exam1.example.demo2.Service.PersonService;
import exam1.example.demo2.Service.TaskDbService;
import exam1.example.demo2.beans.Person;
import exam1.example.demo2.beans.TaskDb;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Order(1)
@RequiredArgsConstructor
public class Test implements CommandLineRunner {

    private final PersonService personService;
    private final TaskDbService taskDbService;

    @Override
    public void run(String... args) throws Exception {
        try {
            Person person = Person.builder()
                    .name("David")
                    .phone("1234567890")
                    .build();
            personService.savePerson(person);

            TaskDb taskDb = TaskDb.builder()
                    .taskName("Task 1")
                    .description("Cleaning")
//                    .addTask("Clean")
                    .NameIncharge("Mofasa")
                    .isComplete(false)
                    .dateOfExecution(LocalDateTime.now())
                    .person(person)
                    .build();
            taskDbService.saveTask(taskDb);

        } catch (PersonException e) {
            System.err.println("PersonException occurred:" + e.getMessage());
        }
    }
}
