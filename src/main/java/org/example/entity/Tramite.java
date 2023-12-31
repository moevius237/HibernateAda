package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tramite")
public class Tramite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "tipo_tramite")
    private String tipo;

    private LocalDateTime fecha;


    //mappedBy --> dice quien es el propietario de la relacion
    @OneToOne(mappedBy ="tramite",
    cascade = CascadeType.REMOVE)//Nombre del campo de la clase
    private Presupuesto presupuesto;

    @OneToMany(mappedBy = "tramite")
    private List<DiarioClientes> diarioClientes;

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    @Override
    public String toString() {
        return "Tramite{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", fecha=" + fecha +
                ", presupuesto=" + presupuesto +
                ", diarioClientes=" + diarioClientes +
                '}';
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Tramite(){

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
