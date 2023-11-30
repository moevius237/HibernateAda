package org.example.Test;

import org.example.Db.AddresDao;
import org.example.Db.AddresDaoJPA;
import org.example.entity.Address;
import org.example.exceptions.SeveroException;

public class TestAddress {
    public static void main(String[] args) {
        AddresDao dao = new AddresDaoJPA();
        try {
            //dao.create(new Address("tomas y valiente", "31A"));
            System.out.println(dao.findAllStreetAndIdByNumberAddresDtor("3A"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
