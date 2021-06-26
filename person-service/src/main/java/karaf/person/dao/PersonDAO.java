package karaf.person.dao;

import karaf.person.model.Person;

import java.util.Collection;

public interface PersonDAO {
    Collection<Person> getAll();

    void add(Person person);

    Person getById(int id);

    void update(Person person, int id);

    void delete(int id);

    boolean checkId(int id);
}
