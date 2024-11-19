package primeiro.api.cqrsjava01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import primeiro.api.cqrsjava01.models.Person;
import primeiro.api.cqrsjava01.services.IPeopleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/people")
public class PeopleController {

    private final IPeopleService peopleService;


    @Value("${api.version}")
    private String apiVersion;

    public PeopleController(IPeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping ("/version")
    public String getApiVersion() {
        return apiVersion;
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getPeople() {
        return peopleService.getPeople();
    }

    @GetMapping("/name/{name}")
    public List<Person> getPeopleByName(@PathVariable String name) {
        return peopleService.getPeopleByName(name);
    }

    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable String id) {
        return peopleService.getPersonById(id);
    }

    @PostMapping("/create-person")
    public Person createPerson(@RequestBody Person person) {
        return peopleService.createPerson(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable String id, @RequestBody Person person) {
        return peopleService.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable String id) {
        peopleService.deletePerson(id);
    }

    @GetMapping("/create-people/{quantity}")
    public String createPeople(@PathVariable Integer quantity) {
        peopleService.generatePeople(quantity);
        return "Created %d People".formatted(quantity);
    }
}
