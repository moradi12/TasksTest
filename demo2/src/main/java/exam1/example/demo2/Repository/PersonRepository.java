package exam1.example.demo2.Repository;

import exam1.example.demo2.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findAll();
    List<Person> findByName(String name);
    List<Person> findByPhone(String phone);



}
