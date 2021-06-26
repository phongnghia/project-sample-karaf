package karaf.person.service.impl;

import karaf.person.dao.PersonDAO;
import karaf.person.model.Person;
import karaf.person.service.PersonService;

import java.util.Collection;

public class PersonServiceImpl implements PersonService {


    private final PersonDAO personDAO;

    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public Collection<Person> allPerson() {
        return personDAO.getAll();
    }

    @Override
    public void addPerson(Person person) {
        /*entityManager.persist(person);*/
        personDAO.add(person);
    }

    @Override
    public Person getPersonById(int id) {
        return personDAO.getById(id);
    }

    @Override
    public void updatePerson(Person person, int id) {
        personDAO.update(person, id);
    }

    @Override
    public void deletePerson(int id) {
        personDAO.delete(id);
    }

    @Override
    public boolean checkId(int id) {
        return personDAO.checkId(id);
    }
}
