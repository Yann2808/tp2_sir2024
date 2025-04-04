package dao;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public abstract class AbstractJpaDao<K, T extends Serializable> implements IGenericDao<K, T> {

    private Class<T> clazz;

    protected EntityManager entityManager;

    public AbstractJpaDao() {
        this.entityManager = EntityManagerHelper.getEntityManager();
    }

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(K id) {
        return entityManager.find(clazz, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("select e from " + clazz.getName() + " as e",clazz).getResultList();
    }

    public T save(T entity) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        try {
            entityManager.persist(entity);
            t.commit();
            return entity;
        } catch (Exception e) {
            t.rollback();
            throw e;
        }
    }

    public T update(final T entity) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        try {
            T res = entityManager.merge(entity);
            t.commit();
            return res;
        } catch (Exception e) {
            t.rollback();
            throw e;
        }

    }

    public void delete(T entity) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        try {
            entityManager.remove(entity);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw e;
        }

    }

    public void deleteById(K entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }
}