package org.example.entity;

import jakarta.persistence.*;

@Entity
    @Table(name = "presupuesto")
    public class Presupuesto{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String lugar;

        @OneToOne
        @JoinColumn(name = "tramite_id")
        private Tramite tramite;

        public Presupuesto(){}

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

}
