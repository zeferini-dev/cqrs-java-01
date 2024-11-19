package primeiro.api.cqrsjava01.services;

import org.springframework.http.ResponseEntity;
import primeiro.api.cqrsjava01.models.Person;

import java.util.List;
import java.util.Optional;

public interface IPeopleService {
    ResponseEntity<List<Person>> getPeople();
    List<Person> getPeopleByName(String name);
    Optional<Person> getPersonById(String id);
    Person createPerson(Person person);
    Person updatePerson(String id, Person person);
    void deletePerson(String id);
    void generatePeople(Integer quantity);
}
