package org.example.Db;

import org.example.entity.Tramite;
import org.example.exceptions.SeveroException;

public interface TramiteDBI {

    void create(Tramite tramite) throws SeveroException;

    void delete(Tramite tramite) throws SeveroException;
    void update(Tramite tramite) throws SeveroException;

    Tramite findById(Long id) throws SeveroException;
    void deleteById(Long id) throws SeveroException;

}
