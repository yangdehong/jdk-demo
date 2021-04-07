package com.ydh.redsheep;

import com.ydh.redsheep.mapstruct.Person;
import com.ydh.redsheep.mapstruct.PersonConverter;
import com.ydh.redsheep.mapstruct.PersonDTO;
import com.ydh.redsheep.mapstruct.User;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class MapStructTests {

    @Test
    void contextLoads() {
        Person person = new Person(1L,"zhige","zhige.me@gmail.com",new Date(),new User(18));
        PersonDTO personDTO = PersonConverter.INSTANCE.domain2dto(person);
        System.out.println(personDTO);

        List<Person> people = new ArrayList<>();
        people.add(person);
        List<PersonDTO> personDTOs = PersonConverter.INSTANCE.domain2dto(people);
        System.out.println(personDTOs);
    }

}
