package io.github.zbrant.restwithspring.services;

import io.github.zbrant.restwithspring.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id){
        logger.info("Finding one person");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("serri");
        person.setLastName("7");
        person.setAddress("Rio de Janiro - Brasil");
        person.setGender("Male");
        return person;
    }

    public Person create(Person person){
        logger.info("Creating one person!");
        return person;
    }

    public Person update(Person person){
        logger.info("Updating one person!");
        return person;
    }

    public void delete(String id){
        logger.info("deleting one person!");
    }

    private Person mockPerson(int i) {
        logger.info("Finding all person");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name" + i);
        person.setLastName("Last name" + i) ;
        person.setAddress("Some address in brasil" + i);
        person.setGender("Male");
        return person;
    }
}
