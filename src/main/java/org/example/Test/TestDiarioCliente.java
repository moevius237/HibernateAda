package org.example.Test;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.Db.TramiteDB;
import org.example.Db.TramiteDBI;
import org.example.entity.Address;
import org.example.entity.DiarioClientes;
import org.example.entity.Person;
import org.example.entity.Tramite;
import org.example.exceptions.SeveroException;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TestDiarioCliente {
/*
HQL --> Hibernate query language
criteria
 */
    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        Logger l = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        l.setLevel(Level.OFF);
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DiarioClientes> criteria = builder.createQuery(DiarioClientes.class);

            Root<DiarioClientes> root = criteria.from(DiarioClientes.class);
            System.out.println( session.createQuery(criteria).getResultList());
/*
            //Consultar las direcciones de una persona dada
            CriteriaBuilder builder1 = session.getCriteriaBuilder();
            CriteriaQuery<Address> criteriaQ = builder1.createQuery(Address.class);
            //Tabla la que voy a hacer la consulta
            Root<Address> root1 = criteriaQ.from(Address.class);

            Join<Address, Person> join = root1.join("people");
            criteriaQ.where(
                    builder1.equal(join.get("id"),5L)
            );

 */
            //Consultar las direcciones de una persona dada que y la calle contenga la palabra plaza
            CriteriaBuilder builder1 = session.getCriteriaBuilder();
            CriteriaQuery<Address> criteriaQ = builder1.createQuery(Address.class);
            //Tabla la que voy a hacer la consulta
            Root<Address> root1 = criteriaQ.from(Address.class);

            Join<Address, Person> join = root1.join("people");
            criteriaQ.where(
                    builder1.and(
                            builder1.equal(join.get("id"),5L),
                            builder1.equal(root1.get("street"),"%plaza%"))

            );

            System.out.println(session.createQuery(criteriaQ).getResultList());


            //Si quiero borrar el tramite me borre los presupuestos(Cascade)
            TramiteDBI tramiteDBI = new TramiteDB();
            tramiteDBI.deleteById(1L);


        } catch (SeveroException e) {
            throw new RuntimeException(e);
        }
    }
}
