package primeiro.api.cqrsjava01.service;

import primeiro.api.cqrsjava01.dto.PersonRequestDTO;
import primeiro.api.cqrsjava01.dto.PersonResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonResponseDTO> findAll();
    PersonResponseDTO findById(String id);
    PersonResponseDTO save(PersonRequestDTO personRequestDTO);
    PersonResponseDTO update(String id, PersonRequestDTO personRequestDTO);
    void deleteById(String id);
}
