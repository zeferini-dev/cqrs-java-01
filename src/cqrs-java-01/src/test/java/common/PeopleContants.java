package common;

import primeiro.api.cqrsjava01.domain.Person;

import java.util.Date;

public class PeopleContants {
    public static final Person PERSON = Person.builder()
            .id("1")
            .fullName("John Doe")
            .birthDate(new Date())
            .age(30)
            .build();
}
