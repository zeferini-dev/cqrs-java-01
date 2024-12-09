package primeiro.api.cqrsjava01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import primeiro.api.cqrsjava01.domain.Person;
import primeiro.api.cqrsjava01.dto.PersonRequestDTO;
import primeiro.api.cqrsjava01.dto.PersonResponseDTO;
import primeiro.api.cqrsjava01.repository.PersonRepository;
import primeiro.api.cqrsjava01.service.PersonService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonResponseDTO> findAll() {
        return personRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonResponseDTO findById(String id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));

        return mapToResponseDTO(person);
    }

    @Override
    public PersonResponseDTO save(PersonRequestDTO personRequestDTO) {
        Person person = mapToEntity(personRequestDTO);
        Person savedPerson = personRepository.save(person);
        return mapToResponseDTO(savedPerson);
    }

    @Override
    public PersonResponseDTO update(String id, PersonRequestDTO personRequestDTO) {
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson.isPresent()) {
            Person personToUpdate = existingPerson.get();
            personToUpdate.setFullName(personRequestDTO.getFullName());
            personToUpdate.setBirthDate(personRequestDTO.getBirthDate());
            personToUpdate.setAge(personRequestDTO.getAge());
            Person updatedPerson = personRepository.save(personToUpdate);
            return mapToResponseDTO(updatedPerson);
        } else {
            throw new RuntimeException("Person not found with id: " + id);
        }
    }

    @Override
    public void deleteById(String id) {
        personRepository.deleteById(id);
    }

    private Person mapToEntity(PersonRequestDTO dto) {
        return Person.builder()
                .fullName(dto.getFullName())
                .birthDate(dto.getBirthDate())
                .age(dto.getAge())
                .build();
    }

    private PersonResponseDTO mapToResponseDTO(Person person) {
        return PersonResponseDTO.builder()
                .id(person.getId())
                .fullName(person.getFullName())
                .birthDate(person.getBirthDate())
                .age(person.getAge())
                .build();
    }
}
