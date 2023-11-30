package org.example.Db;

import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.dto.AddressDtor;
import org.example.entity.Address;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AddresDaoJPA extends GenericDAOJPA<Address> implements AddresDao{
    public AddresDaoJPA(Class<Address> entittyClass) {
        super(entittyClass);
    }

    public AddresDaoJPA() {
        super();
    }

    @Override
    public List<Address> findAllByStreetNumber(String number) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder criterio = session.getCriteriaBuilder();
            CriteriaQuery<Address> criteriaQuery = criterio.createQuery(Address.class);
            Root<Address> addressRoot = criteriaQuery.from(Address.class);
            criteriaQuery.select(addressRoot)
                    .where(criterio.equal(addressRoot.get("number"),number));
            return session.createQuery(criteriaQuery).getResultList();

        }
    }
    @Override
    public List<String> findALlStreetNameByNumber(String number) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<String> criteria = builder.createQuery(String.class);
            Root<Address> stringRoot = criteria.from(Address.class);
            criteria.select(stringRoot.get("street"))
                    .where(builder.equal(stringRoot.get("number"),number));

            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public List<Object[]> findAllStreetAndIdByNumber(String number) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
            Root<Address> addressRoot = criteria.from(Address.class);
            criteria.multiselect(addressRoot.get("street"),
                    addressRoot.get("id"))
                    .where(builder.equal(addressRoot.get("number"),number));
            return session.createQuery(criteria).getResultList();
        }
        //usar List<Object[]> debido a que es muy amplio y puede dar error por muchos lados distintos como si por ejemplo fuera null
    }
    /*
    public List<Tuple> findAllStreetAndIdByNumberTuple(String number) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple[]> criteria = builder.createQuery(Tuple[].class);
            Root<Address> addressRoot = criteria.from(Address.class);
            criteria.multiselect(addressRoot.get("street"),
                            addressRoot.get("id"))
                    .where(builder.equal(addressRoot.get("number"),number));
            List<Tuple> tuples = (List<Tuple>) session.createQuery(criteria).getResultList();
            Tuple tu = tuples.get(0);
            int num = (int) tu.get(0);
            return (List<Tuple>) session.createQuery(criteria).getResultList();
        }

     */

    public List<AddressDtor[]> findAllStreetAndIdByNumberAddresDtor(String number) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<AddressDtor[]> criteria = builder.createQuery(AddressDtor[].class);
            Root<Address> addressRoot = criteria.from(Address.class);
            criteria.multiselect(addressRoot.get("street"),
                            addressRoot.get("id"))
                    .where(builder.equal(addressRoot.get("number"),number));
            return session.createQuery(criteria).getResultList();
        }
    }
}
