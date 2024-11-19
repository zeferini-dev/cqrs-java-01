package primeiro.api.cqrsjava01.services;

import com.github.javafaker.Faker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import primeiro.api.cqrsjava01.models.Person;

import java.util.*;

@Service
public class PeopleService implements IPeopleService {

    private final List<Person> people;
    private final Faker faker = new Faker();


    public PeopleService() {
        people = new ArrayList<>();
    }

    @Override
    public ResponseEntity<List<Person>> getPeople() {
        return people.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(people);
    }

    @Override
    public List<Person> getPeopleByName(String name) {
        if (name.isEmpty()) {
            return Collections.emptyList();
        }

        if (people.isEmpty()) {
            return Collections.emptyList();
        }

        return people.stream()
                .filter(person -> person.getFullName().contains(name))
                .toList();
    }

    @Override
    public Optional<Person> getPersonById(String id) {
        if (id.isEmpty()) {
            return Optional.empty();
        }

        return people.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public Person createPerson(Person person) {
        person.setId(UUID.randomUUID().toString());
        people.add(person);
        return person;
    }

    @Override
    public Person updatePerson(String id, Person person) {
        if (people.isEmpty()) {
            return null;
        }

        Person personInDB = getPersonById(id).orElse(null);

        if (personInDB == null) {
            return null;
        }
        else
        {
            personInDB.setFullName(person.getFullName());
            personInDB.setBirthDate(person.getBirthDate());
            personInDB.setAge(person.getAge());
            return personInDB;
        }
    }

    @Override
    public void deletePerson(String id) {
        if (id.isEmpty()) {
            return;
        }

        Person personInDB = getPersonById(id).orElse(null);

        if (personInDB == null) {
            return;
        }
        else
        {
            people.remove(personInDB);
        }
    }

    @Override
    public void generatePeople(Integer quantity) {
        for (int i = 0; i < quantity; i++) {
            Person person = Person.builder()
                    .id(UUID.randomUUID().toString())
                    .fullName(faker.name().fullName())
                    .birthDate(faker.date().birthday())
                    .age(faker.number().numberBetween(18, 100))
                    .build();
            people.add(person);
        }
    }
}
