package karaf.person.dao.impl;

import karaf.person.dao.PersonDAO;
import karaf.person.model.Person;
import karaf.person.persistence.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class PersonDAOImpl implements PersonDAO {

    private final EntityManager entityManager;

    public PersonDAOImpl(PersistenceUtil persistenceUtil) {
        this.entityManager = persistenceUtil.getPersistenceUtil();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @Override
    public List getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        Root<Person> personRoot = cq.from(Person.class);
        cq.select(personRoot);

        Query query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public void add(Person person) {
        entityManager.persist(person);
    }

    @Override
    public Person getById(int id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public void update(Person person, int id) {
        person.setId(id);
        entityManager.merge(person);
    }

    @Override
    public void delete(int id) {
        Person person = getById(id);
        entityManager.remove(person);
    }

    @Override
    public boolean checkId(int id) {
        try {
            Person person = entityManager.find(Person.class, id);
            return person != null;
        } catch (NoResultException e) {
            return false;
        }
    }
}
