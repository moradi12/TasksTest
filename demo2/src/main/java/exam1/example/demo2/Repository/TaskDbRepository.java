package exam1.example.demo2.Repository;

import exam1.example.demo2.beans.Person;
import exam1.example.demo2.beans.TaskDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDbRepository extends JpaRepository<TaskDb, Integer> {
    List<TaskDb> findAll();
    List<TaskDb> findByIsComplete(boolean isComplete);
    List<TaskDb> findByTaskName(String Name);
    List<TaskDb> findByPerson(Person person);

}
