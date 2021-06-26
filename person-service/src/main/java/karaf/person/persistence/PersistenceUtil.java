package karaf.person.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class PersistenceUtil {

    @PersistenceContext(unitName = "person-data")
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getPersistenceUtil() {
        return entityManager;
    }
}