package org.example.Db;

import org.example.exceptions.SeveroException;

public interface GenericDao<T> {
    void create(T t) throws SeveroException;

    void deletebyId(Long id) throws SeveroException;

    void delete(T t) throws SeveroException;

    void update(T t);
    T FindbyYd(Long id);
}
