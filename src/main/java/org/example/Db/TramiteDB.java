package org.example.Db;

import org.example.entity.Tramite;
import org.example.exceptions.ErrorCode;
import org.example.exceptions.SeveroException;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
Creamos 2 tablas Book(id no incrementado , titulo , autor) y Chapter(id autoincrementado , paginas),
tendran una realación, sera una relacion bidereccional

-Crearemos nuestras clases en paquete db
--create, deletebyid , getBookList, delete(Elimina libro y capitulos) --> Book
--createList,  -->Chapter
-Query: quiero saber cuál es el libro que tiene mas capitulos
 */

public class TramiteDB implements TramiteDBI{

    @Override
    public void create(Tramite tramite) throws SeveroException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = null;
            try {
                transaction= session.beginTransaction();
                session.persist(tramite);
                transaction.commit();
            }catch (RuntimeException e){
                if (transaction != null){
                transaction.rollback();
                }
             //   throw new RuntimeException();
                throw new SeveroException(ErrorCode.INSERT_ERROR, "Error al insertar un tramite");
            }
            }
        }

    @Override
    public void delete(Tramite tramite)throws SeveroException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try{
                transaction=session.beginTransaction();
                session.remove(tramite);
                transaction.commit();
            }catch (RuntimeException e){
                if (transaction != null){
                transaction.rollback();
                }
                throw new SeveroException(ErrorCode.DELETE_ERROR,"ERROR al borrar el tramite");
            }

        }
    }

    @Override
    public void update(Tramite tramite) throws SeveroException {
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = null;
            try {
                transaction= session.beginTransaction();
                session.merge(tramite);
                transaction.commit();
            }catch (HibernateException e){
                if (transaction != null){
                    transaction.rollback();
                }
                throw new SeveroException(ErrorCode.UPDATE_ERROR,"ERROR al actualizar el tramite");
            }
        }
    }

    @Override
    public Tramite findById(Long id) throws SeveroException {
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            return session.find(Tramite.class,id);
        }
    }

    @Override
    public void deleteById(Long id) throws SeveroException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            try {


                session.beginTransaction();
                session.remove(session.find(Tramite.class, id));
                session.getTransaction().commit();
            }catch (HibernateException e){
                throw new SeveroException(ErrorCode.DELETE_ERROR, "ERROR al borrar por id");
            }
        }
    }
}
