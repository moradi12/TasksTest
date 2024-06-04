package exam1.example.demo2.Service;

import exam1.example.demo2.Exceptions.PersonException;
import exam1.example.demo2.Repository.PersonRepository;
import exam1.example.demo2.beans.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public List<Person> getPersonsByName(String name) {
        return personRepository.findByName(name);
    }

    public List<Person> getPersonsByPhone(String phone) {
        return personRepository.findByPhone(phone);
    }

    public Person getPersonById(int id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonException("Person not found with id" + id));
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePersonById(int id) {
        if (!personRepository.existsById(id)) {
            throw new PersonException("Person not found with id" + id);
        }
        personRepository.deleteById(id);
    }
}
