package org.example.Test;

import org.example.entity.Presupuesto;
import org.example.entity.Tramite;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class TestPresupuestoTramite {
    public static void main(String[] args) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Tramite tramite = new Tramite();
            tramite.setTipo("Credito");
            tramite.setFecha(LocalDateTime.now());
            Tramite t = session.find(Tramite.class,1L);
            t.setFecha(LocalDateTime.now());
            session.persist(t);

            Query<Tramite> query = session
                    .createQuery("from Tramite Where tipo= :tipoTram",Tramite.class).setParameter("tipoTram","Credito");

            List<Tramite> tramites = query.getResultList();
            tramites.forEach(System.out::println);

            //getResultList --> cuando la query devuelve 0 o mas de un resultado
            //getStringResult --> solo para casos donde la query devulve exactamente
                                //resultado sino da excepcion
            //getResultStream --> no lo uso

            Presupuesto presupuesto = new Presupuesto();
            presupuesto.setLugar("Elche");
            presupuesto.setTramite(tramite);

            session.persist(tramite);
            session.getTransaction().commit();
            session.persist(presupuesto);
            session.getTransaction().commit();

        }catch (HibernateException e){
            if (session != null && session.getTransaction() != null){
            session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null){
            session.close();
            }
        }
    }
}
