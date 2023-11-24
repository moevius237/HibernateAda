package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "diario_cliente")
public class DiarioClientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "entrada_diario")
    private String entradaDiario;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "tramite_id")
    private Tramite tramite;

    public DiarioClientes() {
    }

    @Override
    public String toString() {
        return "DiarioClientes{" +
                "id=" + id +
                ", entradaDiario='" + entradaDiario + '\'' +
                ", fecha=" + fecha +
                ", tramite=" + tramite +
                '}';
    }

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEntradaDiario() {
        return entradaDiario;
    }

    public void setEntradaDiario(String entradaDiario) {
        this.entradaDiario = entradaDiario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }
}
