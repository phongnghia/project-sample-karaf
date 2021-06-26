package karaf.person.service;


import karaf.person.model.Person;

import java.util.Collection;

public interface PersonService {
    Collection<Person> allPerson();

    void addPerson(Person person);

    Person getPersonById(int id);

    void updatePerson(Person person, int id);

    void deletePerson(int id);

    boolean checkId(int id);
}