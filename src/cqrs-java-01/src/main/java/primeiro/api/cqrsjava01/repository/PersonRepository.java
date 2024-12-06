package primeiro.api.cqrsjava01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import primeiro.api.cqrsjava01.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
}
