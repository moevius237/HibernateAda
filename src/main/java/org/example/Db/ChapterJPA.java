package org.example.Db;

import org.example.entity.Chapter;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

//CRUD
//Muchos a muchos con personas y direcciones
//oara cada persona cuantas direcciones tienen

/*
Direcciones
findAllbyStreetNumber -->no recibe un id y devulece una direccion de la lista de direcciones
findALLStreetANDIdByNumber --> devuelve todas las calles y el numero(listas de string las direcciones y el numero)
 */
public class ChapterJPA extends GenericDAOJPA<Chapter> implements ChapterDAO{
    public ChapterJPA() {
        super(Chapter.class);
    }

    @Override
    public void create(Chapter chapter) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(chapter);
            session.getTransaction().commit();
        }catch (HibernateException e){
            throw new HibernateException(e);
        }
    }

    @Override
    public void deletebyId(Long id) {
        try {

        }catch (HibernateException e){
            throw new HibernateException(e);
        }
    }

    @Override
    public void update(Chapter chapter) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            transaction = session.beginTransaction();
            session.merge(chapter);
            transaction.commit();
        }catch (HibernateException e){
            throw new HibernateException(e);
        }
    }
}
