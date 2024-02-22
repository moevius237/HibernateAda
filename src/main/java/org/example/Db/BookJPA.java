package org.example.Db;

import jakarta.persistence.criteria.*;
import org.example.entity.Book;
import org.example.entity.Chapter;
import org.example.exceptions.ErrorCode;
import org.example.exceptions.SeveroException;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookJPA extends GenericDAOJPA<Book> implements BookDb {
    public BookJPA() {

    }

    @Override
    public void create(Book book) throws SeveroException {
    try(Session session = HibernateUtil.getSessionFactory().openSession()){
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null){
                transaction.rollback();
            }
            throw new SeveroException(ErrorCode.INSERT_ERROR, "Error al insertar un tramite");

        }
    }
    }

    @Override
    public void deletebyId(Long id) throws SeveroException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            try {
                session.beginTransaction();
                session.remove(session.find(Book.class ,id));
                session.getTransaction().commit();
            } catch (HibernateException e) {
                throw new SeveroException(ErrorCode.DELETE_ERROR, "Error al insertar un tramite");
            }
        }
    }

    @Override
    public List<Book> getBookList() throws SeveroException {
        List<Book> books = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Book> bookQuery = session.createQuery("from Book",Book.class);
            return bookQuery.getResultList();
        }catch (HibernateException e){
            throw new SeveroException(ErrorCode.UPDATE_ERROR, "Error al insertar un tramite");
        }
    }

    @Override
    public void delete(Book book) throws SeveroException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(book);
            session.getTransaction().commit();
        }catch (HibernateException e) {
            throw new SeveroException(ErrorCode.DELETE_ERROR, "Error al insertar un tramite");
        }
    }

    @Override
    public void createListChapters(Book book, Set<Chapter> chapters) throws SeveroException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(book);
          //  session.set(book);
            session.persist(book);
            session.getTransaction().commit();
        }
        //Query quiero saber cual es el libro que tiene mas capitulo

        /*
        SELECT l.titulo , Count(c.id) as nu
        From library l
        Join chapter c ON l.id = c.book_id
        Group by c book_id
        Order by nu DESC
         */
    }
    void getBookWithMostChapters(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = builder.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            Join<Book,Chapter> join = root.join("chapters");
            Expression<Long> numCap = builder.count(join.get("id"));
            criteriaQuery.select(root.get("titulo"))
                    .groupBy(root.get("id"))
                    .orderBy(builder.desc(numCap));
            System.out.println(session.createQuery(criteriaQuery).setMaxResults(1).getSingleResult());
        }
    }
}
