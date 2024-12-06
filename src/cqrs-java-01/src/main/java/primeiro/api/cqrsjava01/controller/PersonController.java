package primeiro.api.cqrsjava01.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import primeiro.api.cqrsjava01.dto.PersonRequestDTO;
import primeiro.api.cqrsjava01.dto.PersonResponseDTO;
import primeiro.api.cqrsjava01.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Persons", description = "APIs relacionadas a pessoas")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @Operation(summary = "Lista todas as Pessoas", description = "Retorna uma  Lista com todas as Pessoas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Pessoas obtida com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<PersonResponseDTO>> getAllPersons() {
        List<PersonResponseDTO> persons = personService.findAll();
        return ResponseEntity.ok(persons);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Busca uma pessoa pelo ID", description = "Retorna a PESSOA correspondente ao ID fornecido", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PersonResponseDTO> getPersonById(@PathVariable String id) {
        return personService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cria uma pessoa", description = "Adiciona uma nova PESSOA à base de dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a criação do Pessoa"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PersonResponseDTO> createPerson(@RequestBody PersonRequestDTO personRequestDTO) {
        PersonResponseDTO createdPerson = personService.save(personRequestDTO);
        return ResponseEntity.status(201).body(createdPerson);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma pessoa", description = "Atualiza uma PESSOA à base de dados", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para atualização"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PersonResponseDTO> updatePerson(
            @PathVariable String id, @RequestBody PersonRequestDTO personRequestDTO) {
        try {
            PersonResponseDTO updatedPerson = personService.update(id, personRequestDTO);
            return ResponseEntity.ok(updatedPerson);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma pessoa", description = "Exclui uma PESSOA definitivamente da base de dados", method = "DELETE", parameters = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletePerson(@PathVariable String id) {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
