package org.example.Db;

import org.example.exceptions.SeveroException;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/*

 */
public class GenericDAOJPA<T> implements GenericDao<T> {
    
    private Class<T> entittyClass;
    
    public GenericDAOJPA(Class<T> entittyClass){
        this.entittyClass = entittyClass;
    }
    @Override
    public void create(T t) throws SeveroException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
        }catch (HibernateException e){
            throw new HibernateException(e);
        }
    }

    @Override
    public void deletebyId(Long id) throws SeveroException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.remove(FindbyYd(id));
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(T t) throws SeveroException {
    
    }

    @Override
    public void update(T t) {

    }

    @Override
    public T FindbyYd(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.find(entittyClass,id);
            return session.find(entittyClass,id);

        }
    }
}
