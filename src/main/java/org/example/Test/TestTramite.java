package org.example.Test;

import org.example.Db.TramiteDB;
import org.example.Db.TramiteDBI;
import org.example.entity.Tramite;
import org.example.exceptions.SeveroException;

import java.time.LocalDateTime;

public class TestTramite {

    public static void main(String[] args) {
        LocalDateTime ahora = LocalDateTime.now();
        Tramite tramite = new Tramite();
        tramite.setTipo("X");
        tramite.setFecha(ahora);

        Tramite t2 = new Tramite();
        t2.setTipo("Y");
        t2.setFecha(ahora);

        TramiteDBI tramiteDBI = new TramiteDB();
        try {
            tramiteDBI.create(tramite);
            tramiteDBI.delete(tramite);
            tramiteDBI.create(t2);
            System.out.println(tramiteDBI.findById(2L));

        } catch (SeveroException e) {
            throw new RuntimeException(e);
        }
    }
}
